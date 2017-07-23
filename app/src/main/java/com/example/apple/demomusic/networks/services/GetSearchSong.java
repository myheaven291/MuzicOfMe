package com.example.apple.demomusic.networks.services;

import com.example.apple.demomusic.networks.json_models.searchsongs.SearchMain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by apple on 7/22/17.
 */

public interface GetSearchSong {
    @GET("http://api.mp3.zing.vn/api/mobile/search/song")
    Call<SearchMain> getSearchSong(@Query("requestdata") String request);
}
