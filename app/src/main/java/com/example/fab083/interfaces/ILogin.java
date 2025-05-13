package com.example.fab083.interfaces;

import com.example.fab083.api.ApiResponseGeneral;
import com.example.fab083.api.ApiResponseLogin;
import com.example.fab083.dtos.DtoBloquearRegistroNullInput;
import com.example.fab083.dtos.DtoLoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILogin {
    @POST("api/General/ValidarLogin/")  // Reemplaza "login" con el endpoint de tu API
    Call<ApiResponseLogin> ValidarLogin(@Body DtoLoginRequest loginRequest);


}
