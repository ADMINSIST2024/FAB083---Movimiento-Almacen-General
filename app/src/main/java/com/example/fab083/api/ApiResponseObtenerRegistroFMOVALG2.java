package com.example.fab083.api;

import com.example.fab083.dtos.DtoAlmacen;
import com.example.fab083.dtos.DtoObtenerRegistroFMOVALG2Input;
import com.example.fab083.dtos.DtoObtenerRegistroFMOVALG2Outputs;

import java.util.List;

public class ApiResponseObtenerRegistroFMOVALG2 {
    private boolean success;
    private String errorMessage;
    private List<DtoObtenerRegistroFMOVALG2Input> result;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<DtoObtenerRegistroFMOVALG2Input> getResult() {
        return result;
    }

    public void setResult(List<DtoObtenerRegistroFMOVALG2Input> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
