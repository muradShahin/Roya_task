package com.murad.roya_news.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.murad.roya_news.databinding.NewsCashedRowsBinding;
import com.murad.roya_news.databinding.NewsViewBinding;
import com.murad.roya_news.local_db.entites.News;

import java.util.List;

public class CashedDataAdapter extends RecyclerView.Adapter<CashedDataAdapter.NewsViewHolder> {

    private List<News> listOfNews;

    public CashedDataAdapter(List<News> list) {

        this.listOfNews = list;
    }

    public void submitNewList(List<News> list){
        this.listOfNews = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsCashedRowsBinding newsViewBinding = NewsCashedRowsBinding.inflate(layoutInflater,parent,false);

        return new NewsViewHolder(newsViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.newsViewBinding.setNews(listOfNews.get(position));
    }

    @Override
    public int getItemCount() {
        return listOfNews.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        NewsCashedRowsBinding newsViewBinding;

        public NewsViewHolder(@NonNull NewsCashedRowsBinding newsViewBinding) {
            super(newsViewBinding.getRoot());
            this.newsViewBinding = newsViewBinding;
        }
    }
}
