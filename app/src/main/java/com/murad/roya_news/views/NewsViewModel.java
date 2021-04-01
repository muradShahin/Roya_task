package com.murad.roya_news.views;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.murad.roya_news.helpers.NetworkState;
import com.murad.roya_news.local_db.DaoNews;
import com.murad.roya_news.local_db.entites.News;
import com.murad.roya_news.models.Section;
import com.murad.roya_news.models.SectionInfo;
import com.murad.roya_news.views.data_source.RepositoryFactoryConfig;

import java.util.List;


public class NewsViewModel extends ViewModel {

    private RepositoryFactoryConfig repository;
    private DaoNews daoNews;

    private static final String TAG = "NewsViewModel";

    private MutableLiveData<List<News>> _newsFromCash = new MutableLiveData<>();



    public NewsViewModel(RepositoryFactoryConfig repository, DaoNews daoNews){
        this.repository=repository;
        this.daoNews=daoNews;
    }


    /**
     *
     * @return the news data as a pagedList from RepositoryFactoryConfig class
     */

    LiveData<PagedList<SectionInfo>> getNews(){

      return   repository.fetchRoyaNews();
    }


    /**
     *
     * @return the Network state from the RepositoryFactoryConfig
     * network state can be : LOADING,RUNNING,SUCCESS
     */
    LiveData<NetworkState> getNetWorkState(){

        return repository.network();
    }


    /**
     *
     * @return the news data but this time as a List not a pagedList ,
     * so we can use these data to store it in Room Database
     */
    LiveData<List<SectionInfo>> getCashedData(){

        return repository.cashedData();

    }


    /**
     *
     * this method will take a list of news and loop through it and store each news in Room Db
     */
    public void storeNewsInRoomDb(List<SectionInfo> list){

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (SectionInfo item:list) {


                    daoNews.insert(new News(item.getImageLink(),item.getNewsTitle(),item.getSectionName()));

                }

            }
        }).start();

    }

    public void getNewsFromRoom(){

        new Thread(new Runnable() {
            @Override
            public void run() {

               _newsFromCash.postValue(daoNews.getCashedNews());

            }
        }).start();

    }

    public LiveData<List<News>> getNewsFromCash(){
        return _newsFromCash;
    }


}
