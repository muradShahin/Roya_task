package com.murad.roya_news.local_db.entites;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.murad.roya_news.R;

@Entity(tableName = "Roya_News")
public class News {


    @ColumnInfo(name = "image_link")
    private String image_link;

    @ColumnInfo(name = "news_title")
    private String news_title;

    @ColumnInfo(name = "section_name")
    private String section_name;

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;


    public News(String image_link, String news_title, String section_name) {
        this.image_link = image_link;
        this.news_title = news_title;
        this.section_name = section_name;
    }

    public String getImage_link() {
        return image_link;
    }

    public String getNews_title() {
        return news_title;
    }

    public String getSection_name() {
        return section_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @BindingAdapter("android:loadImage2")
    public static void loadImage2(ImageView imageView, String imageUrl){

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_loading);
        Glide.with(imageView)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView);

    }

}
