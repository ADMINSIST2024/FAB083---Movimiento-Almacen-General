package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseInputTipoDocumento;
import com.example.fab083.api.ApiResponseObtenerTipoMovTransferencia;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ITipoMovimiento {
    @GET("api/TipoMovimiento/ObtenerTipoMovTransferencia/")
    Call<ApiResponseObtenerTipoMovTransferencia> ObtenerTipoMovTransferencia();
}
