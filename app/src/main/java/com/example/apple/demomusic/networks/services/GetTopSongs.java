package com.example.apple.demomusic.networks.services;

import com.example.apple.demomusic.networks.json_models.topsongs.TopSongsJSONModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by apple on 7/21/17.
 */

public interface GetTopSongs {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<TopSongsJSONModel> getTopSongs(@Path("id") String id);
}
