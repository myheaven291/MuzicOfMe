package com.example.apple.demomusic.networks;

import com.example.apple.demomusic.networks.json_models.AllMusicTypesJSONModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by apple on 7/15/17.
 */

public interface GetMusicTypes {
    @GET("api")
    Call<AllMusicTypesJSONModel> getMusicTypes();
}
