package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseAlmacen;
import com.example.fab083.api.ApiResponseAlmacenXCodigo;
import com.example.fab083.api.ApiResponseObtenerCorelativoAlmacen;
import com.example.fab083.api.ApiResponseObtenerRegistroFMOVALG2;
import com.example.fab083.api.ApiResponseUtilizaRegistroCorrelativoAlmacen;
import com.example.fab083.api.ApiResponseValidarAlamcenXCcosto;
import com.example.fab083.dtos.DtoAlmacenXCodigoOutput;
import com.example.fab083.dtos.DtoObtenerCorelativoAlmacenOutput;
import com.example.fab083.dtos.DtoObtenerRegistroFMOVALG2Outputs;
import com.example.fab083.dtos.DtoUtilizaRegistroCorrelativoAlmacenOutput;
import com.example.fab083.dtos.DtoValidarAlamcenXCcostoOutput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IAlmacen {
    @GET("api/Almacen/ObtenerAlmacen/")
    Call<ApiResponseAlmacen> ObtenerAlmacen();

    @POST("api/Almacen/ObtenerAlmacenXCodigo/")
    Call<ApiResponseAlmacenXCodigo> ObtenerAlmacenXCodigo(@Body DtoAlmacenXCodigoOutput obj_AlmacenOutput);

    @POST("api/Almacen/ObtenerRegistroFMVALG2/")
    Call<ApiResponseObtenerRegistroFMOVALG2> ObtenerRegistroFMVALG2(@Body DtoObtenerRegistroFMOVALG2Outputs obj_AlmacenOutput);

    @POST("api/Almacen/ObtenerCorrelativoAlmacen/")
    Call<ApiResponseObtenerCorelativoAlmacen> ObtenerCorrelativoAlmacen(@Body DtoObtenerCorelativoAlmacenOutput obj);


    @POST("api/Almacen/UtilizaRegistroCorrelativoAlmacen/")
    Call<ApiResponseUtilizaRegistroCorrelativoAlmacen> UtilizaRegistroCorrelativoAlmacen(@Body DtoUtilizaRegistroCorrelativoAlmacenOutput obj);

    @POST("api/Almacen/ValidarAlamcenXCcosto/")
    Call<ApiResponseValidarAlamcenXCcosto> ValidarAlamcenXCcosto(@Body DtoValidarAlamcenXCcostoOutput obj);

}
