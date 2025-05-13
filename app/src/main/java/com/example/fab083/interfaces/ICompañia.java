package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseCompañia;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICompañia {
    @GET("api/Compania/ObtenerCompania/")
    Call<ApiResponseCompañia> ObtenerCompañia();
}
