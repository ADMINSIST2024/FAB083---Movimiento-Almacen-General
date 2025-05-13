package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerDiasPermitidosInput;
import com.example.fab083.dtos.DtoObtenerFechaSistemaAyerInput;

public class ApiResponseObtenerDiasPermitidos {
    public String success;
    public String message;
    public String errorMessage;
    public DtoObtenerDiasPermitidosInput result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DtoObtenerDiasPermitidosInput getResult() {
        return result;
    }

    public void setResult(DtoObtenerDiasPermitidosInput result) {
        this.result = result;
    }
}
