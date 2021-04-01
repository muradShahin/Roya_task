package com.murad.roya_news.api;

import com.murad.roya_news.models.RoyaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RoyaApi {

    @GET("section/get/1/info/{page}")
    Call<RoyaResponse> getNews(@Path("page") int page);


}
