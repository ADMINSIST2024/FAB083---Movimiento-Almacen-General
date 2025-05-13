package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseCompañia;
import com.example.fab083.api.ApiResponseInputTipoDocumento;
import com.example.fab083.api.ApiResponseTipoDocumento;
import com.example.fab083.dtos.DtoCboTipoDocOutput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ITipoDocumento {

  @POST("api/TipoDocumento/ObtenerTipoMovSalida/")
  Call<ApiResponseTipoDocumento> ObtenerTipoMovSalida(@Body DtoCboTipoDocOutput dtoTipoDocumento);


  @GET("api/TipoDocumento/ObtenerTipoDocInput/")
  Call<ApiResponseInputTipoDocumento> ObtenerTipoDocInput();


}
