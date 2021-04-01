package com.murad.roya_news.local_db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.murad.roya_news.local_db.entites.News;

@Database(entities = {News.class},version = 1,exportSchema = false)
public abstract class RoomDb extends RoomDatabase {


    public abstract DaoNews daoNews();
}
