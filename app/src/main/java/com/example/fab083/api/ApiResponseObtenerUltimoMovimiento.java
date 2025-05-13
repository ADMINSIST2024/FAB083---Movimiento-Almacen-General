package com.example.fab083.api;

import com.example.fab083.dtos.DtoUltimoMovimientoInput;
import com.example.fab083.dtos.DtoUltimoMovimientoOutput;

public class ApiResponseObtenerUltimoMovimiento {
    private boolean success;
    private String message;
    private DtoUltimoMovimientoOutput result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DtoUltimoMovimientoOutput getResult() {
        return result;
    }

    public void setResult(DtoUltimoMovimientoOutput result) {
        this.result = result;
    }
}
