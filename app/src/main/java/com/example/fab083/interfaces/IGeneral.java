package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseBloquearRegistroCorrelativo;
import com.example.fab083.api.ApiResponseBloquearRegistroCorrelativoNull;
import com.example.fab083.api.ApiResponseCriterioTipoExistencia;
import com.example.fab083.api.ApiResponseDesbloquearFABCORRE;
import com.example.fab083.api.ApiResponseDesbloquearTABCORRE;
import com.example.fab083.api.ApiResponseGeneral;
import com.example.fab083.api.ApiResponseObtenerDatosStockEmpaque;
import com.example.fab083.api.ApiResponseObtenerDiasPermitidos;
import com.example.fab083.api.ApiResponseObtenerDiasPermitidosAyer;
import com.example.fab083.api.ApiResponseObtenerFMOVALG2;
import com.example.fab083.api.ApiResponseObtenerFechaSistema;
import com.example.fab083.api.ApiResponseObtenerFechaSistemaAyer;
import com.example.fab083.api.ApiResponseObtenerHoraSistema;
import com.example.fab083.api.ApiResponseObtenerUltimoMovimiento;
import com.example.fab083.api.ApiResponseUtilizaRegistro;
import com.example.fab083.api.ApiResponseValidaUsoCorrelativo;
import com.example.fab083.api.ApiResponseValidarEstadoCarga;
import com.example.fab083.api.ApiResponseValidarEstadoConcecutivo;
import com.example.fab083.api.ApiResponseValidarNroOrden;
import com.example.fab083.dtos.DtoBloquearRegistroCorrelativoNullOutput;
import com.example.fab083.dtos.DtoBloquearRegistroCorrelativoOutput;
import com.example.fab083.dtos.DtoBloquearRegistroNullInput;
import com.example.fab083.dtos.DtoCriterioTipoExistenciaOutput;
import com.example.fab083.dtos.DtoDesbloquearFABCORREOutput;
import com.example.fab083.dtos.DtoDesbloquearRegistroOutput;
import com.example.fab083.dtos.DtoDesbloquearTABCORREOutput;
import com.example.fab083.dtos.DtoObtenerDatosStockEmpaqueOutput;
import com.example.fab083.dtos.DtoObtenerFMOVALG2Output;
import com.example.fab083.dtos.DtoUltimoMovimientoInput;
import com.example.fab083.dtos.DtoValidarCargaOutput;
import com.example.fab083.dtos.DtoValidarConsecutivoOutput;
import com.example.fab083.dtos.DtoValidarNroOrdenInput;
import com.example.fab083.dtos.DtoValidarNroOrdenOutput;
import com.example.fab083.dtos.DtoValidarTipoListadoOutput;
import com.example.fab083.dtos.DtoutilizaRegistroOutput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IGeneral {
    @POST("api/General/BloquearRegistroNull/")
    Call<ApiResponseGeneral> BloquearRegistroNull(@Body DtoBloquearRegistroNullInput obj_BloquearRegistriNullOutput);
    @POST("api/General/UtilizaRegistro/")
    Call<ApiResponseUtilizaRegistro> UtilizaRegistro(@Body DtoutilizaRegistroOutput obj);
    @POST("api/General/BloquearRegistro/")
    Call<ApiResponseGeneral> BloquearRegistro(@Body DtoBloquearRegistroNullInput obj_BloquearRegistriNullOutput);
    @POST("api/General/ValidarTipoListado/")
    Call<ApiResponseGeneral> ValidarTipoListado(@Body DtoValidarTipoListadoOutput obj);
    @POST("api/General/DesbloquearRegistro/")
    Call<ApiResponseGeneral> DesbloquearRegistro(@Body DtoDesbloquearRegistroOutput obj);
    @GET("api/General/ObtenerHoraSistema/")
    Call<ApiResponseObtenerHoraSistema> ObtenerHoraSistema();
    @GET("api/General/ObtenerFechaSistemaAyer/")
    Call<ApiResponseObtenerFechaSistemaAyer> ObtenerFechaSistemaAyer();
    @GET("api/General/ObtenerFechaSistema/")
    Call<ApiResponseObtenerFechaSistema> ObtenerFechaSistema();
    @GET("api/General/ObtenerDiasPermitidos/")
    Call<ApiResponseObtenerDiasPermitidos> ObtenerDiasPermitidos();
    @GET("api/General/ObtenerDiasPermitidosAyer/")
    Call<ApiResponseObtenerDiasPermitidosAyer> ObtenerDiasPermitidosAyer();
    @POST("api/General/ObtenerDatosStockEmpaque/")
    Call<ApiResponseObtenerDatosStockEmpaque> ObtenerDatosStockEmpaque(@Body DtoObtenerDatosStockEmpaqueOutput obj);
    @POST("api/General/BloquearRegistroCorrelativoNull/")
    Call<ApiResponseBloquearRegistroCorrelativoNull> BloquearRegistroCorrelativoNull(@Body DtoBloquearRegistroCorrelativoNullOutput obj_BloquearRegistroCorrelativoNullOutput);
    @POST("api/General/BloquearRegistroCorrelativo/")
    Call<ApiResponseBloquearRegistroCorrelativo> BloquearRegistroCorrelativo(@Body DtoBloquearRegistroCorrelativoOutput obj);
    @POST("api/General/CriterioTipoExistencia/")
    Call<ApiResponseCriterioTipoExistencia> CriterioTipoExistencia(@Body DtoCriterioTipoExistenciaOutput obj);
    @GET("api/General/ValidaUsoCorrelativo/")
    Call<ApiResponseValidaUsoCorrelativo> ValidaUsoCorrelativo();
    @POST("api/General/ObtenerFMOVALG2/")
    Call<ApiResponseObtenerFMOVALG2> ObtenerFMOVALG2(@Body DtoObtenerFMOVALG2Output obj);
    @POST("api/General/DesbloquearTABCORRE/")
    Call<ApiResponseDesbloquearTABCORRE> DesbloquearTABCORRE(@Body DtoDesbloquearTABCORREOutput obj);
    @POST("api/General/DesbloquearFABCORRE/")
    Call<ApiResponseDesbloquearFABCORRE> DesbloquearFABCORRE(@Body DtoDesbloquearFABCORREOutput obj);
    @POST("api/General/validarNroOrden/")
    Call<ApiResponseValidarNroOrden> validarNroOrden(@Body DtoValidarNroOrdenOutput obj);
    @POST("api/General/validarEstadoCarga/")
    Call<ApiResponseValidarEstadoCarga> validarEstadoCarga(@Body DtoValidarCargaOutput obj);
    @POST("api/General/validarEstadoConsecutivo/")
    Call<ApiResponseValidarEstadoConcecutivo> validarEstadoConsecutivo(@Body DtoValidarConsecutivoOutput obj);
    @POST("api/General/obtenerUltimoMovimiento/")
    Call<ApiResponseObtenerUltimoMovimiento> obtenerUltimoMovimiento(@Body DtoUltimoMovimientoInput obj);
}
