package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseDesbloquearTABCORRE;
import com.example.fab083.api.ApiResponseGrabarAlta;
import com.example.fab083.api.ApiResponseGrabarODRPCON1;
import com.example.fab083.api.ApiResponseProcesarGuardado;
import com.example.fab083.dtos.DtoAltaOutput;
import com.example.fab083.dtos.DtoDesbloquearTABCORREOutput;
import com.example.fab083.dtos.DtoGrabarODRPCON1Output;
import com.example.fab083.dtos.DtoObtenerDatosEtiqueta;
import com.example.fab083.dtos.DtoObtenerDatosStockEmpaqueInput;
import com.example.fab083.dtos.DtoProcesarGuardado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAlta {


    @POST("api/v1/Alta/GrabarAlta/")
    Call<ApiResponseGrabarAlta> GrabarAlta(@Body DtoAltaOutput obj);

    @POST("api/v1/Alta/GrabarODRPCON1/")
    Call<ApiResponseGrabarODRPCON1> GrabarODRPCON1(@Body DtoGrabarODRPCON1Output obj);

    @POST("api/v1/Alta/ProcesarGuardado/")
    Call<ApiResponseProcesarGuardado> ProcesarGuardado(@Body DtoProcesarGuardado obj);
}
