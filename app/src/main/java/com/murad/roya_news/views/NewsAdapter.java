package com.murad.roya_news.views;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.murad.roya_news.R;
import com.murad.roya_news.databinding.NetworkViewBinding;
import com.murad.roya_news.databinding.NewsViewBinding;
import com.murad.roya_news.helpers.NetworkState;
import com.murad.roya_news.helpers.ProgressBar;
import com.murad.roya_news.models.Section;
import com.murad.roya_news.models.SectionInfo;

import java.util.Objects;

public class NewsAdapter extends PagedListAdapter<SectionInfo, RecyclerView.ViewHolder> {

    private final int NEWS_VIEW=1;
    private final int NETWORK_VIEW=2;

    private NetworkState networkState;

    public static final DiffUtil.ItemCallback<SectionInfo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SectionInfo>() {

                @Override
                public boolean areItemsTheSame(@NonNull SectionInfo oldItem, @NonNull SectionInfo newItem) {
                    return oldItem == newItem;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull SectionInfo oldItem, @NonNull SectionInfo newItem) {
                    return oldItem.equals(newItem);
                }
            };

    protected NewsAdapter() {
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(viewType == NEWS_VIEW){

            NewsViewBinding newsViewBinding = NewsViewBinding.inflate(layoutInflater,parent,false);
            return new NewsViewHolder(newsViewBinding);
        }else{
            NetworkViewBinding networkViewBinding = NetworkViewBinding.inflate(layoutInflater,parent,false);
            return new LoadingViewHolder(networkViewBinding);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == NEWS_VIEW){

            ((NewsViewHolder)holder).newsViewBinding.setSectionInfo(getItem(position));
            ((NewsViewHolder)holder).newsViewBinding.executePendingBindings();
        }else{
            if(networkState == NetworkState.LOADING)
               ((LoadingViewHolder)holder).networkViewBinding.setProgress(new ProgressBar(0));
            else
                ((LoadingViewHolder)holder).networkViewBinding.setProgress(new ProgressBar(8));

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position < super.getItemCount())
             return NEWS_VIEW;
        return NETWORK_VIEW;
    }

    /**
     *
     * @return   indicate if there is a next row to be fetchec
     *   so if the network state is in Loading state that mean , new data are being fetched
     *   so we have extra row
     */
    private boolean hasExtraRow(){

        return super.getItemCount() !=0 && (networkState == NetworkState.LOADING);

    }

    /**
     *
     *  if we have extra rows to be fetched we should increase the itemCount so the LOADING VIEW can appear
     */
    @Override
    public int getItemCount() {
        if(hasExtraRow())
             return super.getItemCount()+1;
        return super.getItemCount();
    }

    public void setNetworkState(NetworkState networkState){
        this.networkState = networkState;
        notifyItemChanged(super.getItemCount());
    }


    class NewsViewHolder extends RecyclerView.ViewHolder{


        NewsViewBinding  newsViewBinding;

        public NewsViewHolder(@NonNull NewsViewBinding newsViewBinding) {
            super(newsViewBinding.getRoot());
            this.newsViewBinding = newsViewBinding;

        }


    }

    class LoadingViewHolder extends RecyclerView.ViewHolder{

        NetworkViewBinding networkViewBinding;

        public LoadingViewHolder(NetworkViewBinding networkViewBinding){
            super(networkViewBinding.getRoot());

            this.networkViewBinding = networkViewBinding;


        }


    }
}
