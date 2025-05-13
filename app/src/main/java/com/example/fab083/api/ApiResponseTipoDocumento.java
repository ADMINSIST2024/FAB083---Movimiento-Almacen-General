package com.example.fab083.api;

import com.example.fab083.dtos.DtoAlmacen;
import com.example.fab083.dtos.DtoCboTipoDocOutput;
import com.example.fab083.dtos.DtoTipoDocumento;

import java.util.List;

public class ApiResponseTipoDocumento {

    private boolean success;
    private List<DtoTipoDocumento> result;
    public boolean isSuccess() {
        return success;
    }

    public List<DtoTipoDocumento> ListaTipoDocumento() {return result;}



}
