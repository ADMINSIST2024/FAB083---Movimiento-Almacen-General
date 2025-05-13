package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerDiasPermitidosAyerInput;
import com.example.fab083.dtos.DtoObtenerDiasPermitidosInput;

public class ApiResponseObtenerDiasPermitidosAyer {
    public String success;
    public String message;
    public String errorMessage;
    public DtoObtenerDiasPermitidosAyerInput result;

    public DtoObtenerDiasPermitidosAyerInput getResult() {
        return result;
    }

    public void setResult(DtoObtenerDiasPermitidosAyerInput result) {
        this.result = result;
    }

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


}
