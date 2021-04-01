package com.murad.roya_news.views;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.murad.roya_news.R;
import com.murad.roya_news.helpers.NetworkState;
import com.murad.roya_news.local_db.DaoNews;
import com.murad.roya_news.local_db.entites.News;
import com.murad.roya_news.models.SectionInfo;
import com.murad.roya_news.views.data_source.RepositoryFactoryConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class News_fragment extends Fragment {


    private NewsViewModel newsViewModel;
    private RecyclerView news_recycler;
    private NewsAdapter adapter;
    private static final String TAG = "News_fragment";
    private ProgressBar progressBar;
    private boolean justEntered=true;

    @Inject
    public DaoNews daoNews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar=view.findViewById(R.id.progressBar2);
        adapter=new NewsAdapter();
        news_recycler=view.findViewById(R.id.rec);
        news_recycler.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false));




        newsViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

                return (T) new NewsViewModel(new RepositoryFactoryConfig(requireContext()),daoNews);
            }
        }).get(NewsViewModel.class);


        if(checkInterNetConnection()) {
            news_recycler.setAdapter(adapter);

            newsViewModel.getNews().observe(getViewLifecycleOwner(), new Observer<PagedList<SectionInfo>>() {
                @Override
                public void onChanged(PagedList<SectionInfo> sectionInfos) {

                    adapter.submitList(sectionInfos);
                }
            });
        }else{

            /**
             * get data from Room Database in case of no connection
             */
            Toast.makeText(requireActivity(), "No Connection !",Toast.LENGTH_SHORT).show();

            CashedDataAdapter adapter =new CashedDataAdapter(new ArrayList<>());
            newsViewModel.getNewsFromRoom();
            news_recycler.setAdapter(adapter);

            newsViewModel.getNewsFromCash().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
                @Override
                public void onChanged(List<News> news) {

                    adapter.submitNewList(news);

                }
            });


        }

        if(checkInterNetConnection()) {
            newsViewModel.getNetWorkState().observe(getViewLifecycleOwner(), new Observer<NetworkState>() {
                @Override
                public void onChanged(NetworkState networkState) {
                    Log.d(TAG, "onChanged: " + networkState);
                    adapter.setNetworkState(networkState);

                    if (networkState == NetworkState.LOADED) {
                        progressBar.setVisibility(View.GONE);
                        justEntered = false;
                    } else if (justEntered) {
                        progressBar.setVisibility(View.VISIBLE);

                    }

                }
            });

        }


        if(checkInterNetConnection()) {
            newsViewModel.getCashedData().observe(getViewLifecycleOwner(), new Observer<List<SectionInfo>>() {
                @Override
                public void onChanged(List<SectionInfo> sectionInfos) {
                    Log.d(TAG, "onChanged: " + sectionInfos.size());
                    newsViewModel.storeNewsInRoomDb(sectionInfos);
                }
            });

        }




    }

    private boolean checkInterNetConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() ==
                NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){

            return true;
        }
        return false;
    }
}
