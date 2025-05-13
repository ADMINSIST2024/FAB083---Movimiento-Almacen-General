package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseAlmacen;
import com.example.fab083.api.ApiResponseCentroCosto;
import com.example.fab083.api.ApiResponseCentroCostoXAlmacen;
import com.example.fab083.dtos.DtoAlmacenXCodigoOutput;
import com.example.fab083.dtos.DtoObtenerCentroCostosoOutput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ICentroCosto {
    @GET("api/CentroCosto/ObtenerCentroCostos/")
    Call<ApiResponseCentroCosto> ObtenerCentroCostos();


    @POST("api/CentroCosto/ObtenerCentroCostoXCodigo/")
    Call<ApiResponseCentroCostoXAlmacen> ObtenerCentroCostosXAlmacen(@Body DtoObtenerCentroCostosoOutput obj_CentroCostoOutput);
}
