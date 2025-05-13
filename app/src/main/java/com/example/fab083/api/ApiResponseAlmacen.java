package com.example.fab083.api;

import com.example.fab083.dtos.DtoAlmacen;
import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;

import java.util.List;

public class ApiResponseAlmacen {
    private boolean success;
    private String errorMessage;
    private List<DtoAlmacen> result;
    private String message;


    // Getters y setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public List<DtoAlmacen> getResult() {
        return result;
    }

    public void setResult(List<DtoAlmacen> result) {
        this.result = result;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
