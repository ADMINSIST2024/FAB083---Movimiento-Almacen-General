package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerInputTipoDocumentoInput;
import com.example.fab083.dtos.DtoTipoDocumento;

import java.util.List;

public class ApiResponseInputTipoDocumento {

    private boolean success;

    private List<DtoObtenerInputTipoDocumentoInput> result;
    public boolean isSuccess() {
        return success;
    }

    public List<DtoObtenerInputTipoDocumentoInput> ListaInputTipoDocumento() {return result;}

}
