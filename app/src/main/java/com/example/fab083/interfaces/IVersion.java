package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseVersion;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IVersion {
    @GET("api/Version")
    Call<ApiResponseVersion> getVersion();
}
