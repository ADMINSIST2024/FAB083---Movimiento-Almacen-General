package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseLogin;
import com.example.fab083.api.ApiResponseMovimiento;
import com.example.fab083.dtos.DtoLoginRequest;
import com.example.fab083.dtos.DtoMovimientoAlmacenInput;
import com.example.fab083.dtos.DtoMovimientoAlmacenOutput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IMovimiento {
    @POST("api/Movimiento/ConsultaMovimiento/")
    Call<ApiResponseMovimiento> ConsultaMovimiento(@Body DtoMovimientoAlmacenOutput obj);
}
