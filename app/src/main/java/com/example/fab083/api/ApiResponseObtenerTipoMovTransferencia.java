package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerInputTipoDocumentoInput;
import com.example.fab083.dtos.DtoObtenerTipoMovTransferenciaInput;

import java.util.List;

public class ApiResponseObtenerTipoMovTransferencia {

    private boolean success;

    private List<DtoObtenerTipoMovTransferenciaInput> result;
    public boolean isSuccess() {
        return success;
    }

    public List<DtoObtenerTipoMovTransferenciaInput> ListaObtenerTipoMovTransferenciaInput() {return result;}
}
