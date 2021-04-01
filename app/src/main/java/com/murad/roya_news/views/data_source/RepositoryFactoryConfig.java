package com.murad.roya_news.views.data_source;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.murad.roya_news.helpers.Constants;
import com.murad.roya_news.helpers.NetworkState;
import com.murad.roya_news.models.Section;
import com.murad.roya_news.models.SectionInfo;

import java.util.List;

/**
 *
 * this class job is to retrieve an instance of MainRepository class
 * and to get the pagedList and the networkState and the cashedData from that instance
 */

public class RepositoryFactoryConfig {

    private MainRepositoryFactory factory;
    private LiveData<PagedList<SectionInfo>> pagedList;
    private Context context;

    public RepositoryFactoryConfig(Context context){

        this.context = context;
    }


    public LiveData<PagedList<SectionInfo>> fetchRoyaNews(){

        factory = new MainRepositoryFactory(context);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(Constants.PER_PAGE)
                .build();

        pagedList = new  LivePagedListBuilder(factory,config).build();


        return pagedList;

    }

    /**
     *
     * @return the newtwork state form MainRepository class
     */
    public LiveData<NetworkState> network (){
        return Transformations.switchMap(factory.mainRepo,network ->  MainRepository.getNetworkState());
    }


    /**
     *
     * @return cashed data from MainRepository
     */
    public LiveData<List<SectionInfo>> cashedData(){
        return Transformations.switchMap(factory.mainRepo, list->MainRepository.getCashedData());
    }








}
