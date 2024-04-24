package com.example.myandroidtest.Utils;


import com.example.myandroidtest.model.ImageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface UserApi {

    @Headers({"Content-Type:application/json"})
    @GET("media-coverages?limit=100")
    Call<List<ImageModel>> getImageList();

}