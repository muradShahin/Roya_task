package com.murad.roya_news.views.data_source;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.murad.roya_news.api.RoyaApi;
import com.murad.roya_news.helpers.Constants;
import com.murad.roya_news.helpers.NetworkState;
import com.murad.roya_news.models.RoyaResponse;
import com.murad.roya_news.models.SectionInfo;

import java.util.List;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository extends PageKeyedDataSource<Integer, SectionInfo> {

    private Context context;

    private static MutableLiveData<NetworkState> _networkState = new MutableLiveData<NetworkState>();

    private static MutableLiveData<List<SectionInfo>> _cashedData =new MutableLiveData<>();

    public MainRepository(Context context){

        this.context=context;
    }


    /**
     * we can't directly inject classes in android
     * so this is a walk around to solve this issue
     */
    @InstallIn(SingletonComponent.class)
    @EntryPoint
    interface ProvideRoyaApi{
        RoyaApi getApi();
    }

    private RoyaApi getRoyaApi(){
        ProvideRoyaApi entryPointAccessors =  EntryPointAccessors.fromApplication(
                context,
                ProvideRoyaApi.class
        );

        return entryPointAccessors.getApi();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, SectionInfo> callback) {
        _networkState.postValue(NetworkState.LOADING);

        getRoyaApi().getNews(Constants.PAGE).enqueue(new Callback<RoyaResponse>() {
            @Override
            public void onResponse(Call<RoyaResponse> call, Response<RoyaResponse> response) {
                _networkState.postValue(NetworkState.LOADED);

                _cashedData.postValue(response.body().getSectionInfo());
                callback.onResult(response.body().getSectionInfo(),null,Constants.PAGE+1);
            }

            @Override
            public void onFailure(Call<RoyaResponse> call, Throwable t) {
                _networkState.postValue(NetworkState.ERROR);

            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, SectionInfo> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, SectionInfo> callback) {
        _networkState.postValue(NetworkState.LOADING);

        getRoyaApi().getNews(params.key).enqueue(new Callback<RoyaResponse>() {
            @Override
            public void onResponse(Call<RoyaResponse> call, Response<RoyaResponse> response) {

                _networkState.postValue(NetworkState.LOADED);
                _cashedData.postValue(response.body().getSectionInfo());

                callback.onResult(response.body().getSectionInfo(),params.key+1);


            }

            @Override
            public void onFailure(Call<RoyaResponse> call, Throwable t) {
                _networkState.postValue(NetworkState.ERROR);

            }
        });

    }




    public static LiveData<NetworkState> getNetworkState(){
        return _networkState;
    }

    public static LiveData<List<SectionInfo>> getCashedData(){return _cashedData;}
}
