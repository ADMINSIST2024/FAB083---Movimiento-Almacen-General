package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerFMOVALG2Input;
import com.example.fab083.dtos.DtoValidaUsoCorrelativoInput;

import java.util.List;

public class ApiResponseObtenerFMOVALG2 {
    public String success;
    public String message;
    public String errorMessage;
    public List<DtoObtenerFMOVALG2Input> result;

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

    public List<DtoObtenerFMOVALG2Input> getResult() {
        return result;
    }

    public void setResult(List<DtoObtenerFMOVALG2Input> result) {
        this.result = result;
    }
}
