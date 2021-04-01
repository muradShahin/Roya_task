package com.murad.roya_news.di;

import android.content.Context;

import androidx.room.Room;

import com.murad.roya_news.api.RoyaApi;
import com.murad.roya_news.helpers.Constants;
import com.murad.roya_news.local_db.DaoNews;
import com.murad.roya_news.local_db.RoomDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    /**
     *
     * @return RoyaApi , so we can inject it anywhere in the project
     */

    @Singleton
    @Provides
    public static RoyaApi provideRoyaApi(){

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.API_URI)
                .build()
                .create(RoyaApi.class);
    }


    /**
     *
     * @return RoomDb , so we can use it in the process of providing a DaoNews
     */

    @Singleton
    @Provides
    public static RoomDb provideRoomDb(@ApplicationContext Context context){
        return Room.databaseBuilder(context,
                RoomDb.class,
                "Roya_database").build();
    }


    /**
     *
     * @return DaoNews dependencies
     */

    @Singleton
    @Provides
    public static DaoNews provideDao(RoomDb roomDb){
        return  roomDb.daoNews();
    }

}
