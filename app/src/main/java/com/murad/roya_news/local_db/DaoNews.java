package com.murad.roya_news.local_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.murad.roya_news.local_db.entites.News;
import com.murad.roya_news.models.SectionInfo;

import java.util.List;

@Dao
public interface DaoNews {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);

    @Query("SELECT * FROM Roya_News ")
     List<News>getCashedNews();
}
