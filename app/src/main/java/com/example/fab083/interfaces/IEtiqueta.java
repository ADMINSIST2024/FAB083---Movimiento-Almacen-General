package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseCentroCostoXAlmacen;
import com.example.fab083.api.ApiResponseEtiqueta;
import com.example.fab083.dtos.DtoObtenerCentroCostosoOutput;
import com.example.fab083.dtos.DtoObtenerDatosEtiquetaOutput;
import com.example.fab083.dtos.DtoProcesarlectura;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IEtiqueta {
    @POST("api/General/ObtenerDatosEtiqueta/")
    Call<ApiResponseEtiqueta> ObtenerDatosEtiqueta(@Body DtoObtenerDatosEtiquetaOutput obj_EtiquetaOutput);

    @POST("api/v1/Alta/ProcesoLecturaEtiqueta/")
    Call<ApiResponseEtiqueta> ProcesoLecturaEtiqueta(@Body DtoProcesarlectura obj);

}
