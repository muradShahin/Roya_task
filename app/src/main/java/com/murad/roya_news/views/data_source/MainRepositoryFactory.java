package com.murad.roya_news.views.data_source;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.murad.roya_news.api.RoyaApi;
import com.murad.roya_news.models.SectionInfo;

public class MainRepositoryFactory extends DataSource.Factory<Integer, SectionInfo> {

    MutableLiveData<MainRepository> mainRepo =new MutableLiveData<MainRepository>();

    private Context context;

    public MainRepositoryFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    public DataSource<Integer, SectionInfo> create() {

        MainRepository mainRepository=new MainRepository(context);
        mainRepo.postValue(mainRepository);

        return mainRepository;
    }
}
