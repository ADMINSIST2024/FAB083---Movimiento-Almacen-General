package com.example.fab083.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fab083.api.ApiResponseAlmacen;
import com.example.fab083.api.ApiResponseAlmacenXCodigo;
import com.example.fab083.api.ApiResponseBloquearRegistroCorrelativo;
import com.example.fab083.api.ApiResponseBloquearRegistroCorrelativoNull;
import com.example.fab083.api.ApiResponseCentroCosto;
import com.example.fab083.api.ApiResponseCentroCostoXAlmacen;
import com.example.fab083.api.ApiResponseCompañia;
import com.example.fab083.api.ApiResponseCriterioTipoExistencia;
import com.example.fab083.api.ApiResponseDesbloquearFABCORRE;
import com.example.fab083.api.ApiResponseDesbloquearTABCORRE;
import com.example.fab083.api.ApiResponseEtiqueta;
import com.example.fab083.api.ApiResponseGeneral;
import com.example.fab083.api.ApiResponseGrabarAlta;
import com.example.fab083.api.ApiResponseGrabarODRPCON1;
import com.example.fab083.api.ApiResponseInputTipoDocumento;
import com.example.fab083.api.ApiResponseObtenerCorelativoAlmacen;
import com.example.fab083.api.ApiResponseObtenerDatosStockEmpaque;
import com.example.fab083.api.ApiResponseObtenerDiasPermitidos;
import com.example.fab083.api.ApiResponseObtenerDiasPermitidosAyer;
import com.example.fab083.api.ApiResponseObtenerFMOVALG2;
import com.example.fab083.api.ApiResponseObtenerFechaSistema;
import com.example.fab083.api.ApiResponseObtenerFechaSistemaAyer;
import com.example.fab083.api.ApiResponseObtenerHoraSistema;
import com.example.fab083.api.ApiResponseObtenerRegistroFMOVALG2;
import com.example.fab083.api.ApiResponseObtenerTipoMovTransferencia;
import com.example.fab083.api.ApiResponseObtenerUltimoMovimiento;
import com.example.fab083.api.ApiResponseProcesarGuardado;
import com.example.fab083.api.ApiResponseTipoDocumento;
import com.example.fab083.api.ApiResponseUtilizaRegistro;
import com.example.fab083.api.ApiResponseUtilizaRegistroCorrelativoAlmacen;
import com.example.fab083.api.ApiResponseValidaUsoCorrelativo;
import com.example.fab083.api.ApiResponseValidarAlamcenXCcosto;
import com.example.fab083.api.ApiResponseValidarEstadoCarga;
import com.example.fab083.api.ApiResponseValidarEstadoConcecutivo;
import com.example.fab083.api.ApiResponseValidarNroOrden;
import com.example.fab083.dtos.DtoAltaOutput;
import com.example.fab083.dtos.DtoBloquearRegistroCorrelativoNullOutput;
import com.example.fab083.dtos.DtoBloquearRegistroCorrelativoOutput;
import com.example.fab083.dtos.DtoBloquearRegistroNullInput;
import com.example.fab083.dtos.DtoAlmacen;
import com.example.fab083.dtos.DtoAlmacenXCodigoInput;
import com.example.fab083.dtos.DtoAlmacenXCodigoOutput;
import com.example.fab083.dtos.DtoCboTipoDocOutput;
import com.example.fab083.dtos.DtoCompañia;
import com.example.fab083.dtos.DtoCriterioTipoExistenciaOutput;
import com.example.fab083.dtos.DtoDesbloquearFABCORREOutput;
import com.example.fab083.dtos.DtoDesbloquearRegistroOutput;
import com.example.fab083.dtos.DtoDesbloquearTABCORREInput;
import com.example.fab083.dtos.DtoDesbloquearTABCORREOutput;
import com.example.fab083.dtos.DtoGrabarODRPCON1Output;
import com.example.fab083.dtos.DtoObtenerCentroCostosInput;
import com.example.fab083.dtos.DtoObtenerCentroCostosXAlmacenInput;
import com.example.fab083.dtos.DtoObtenerCentroCostosoOutput;
import com.example.fab083.dtos.DtoObtenerCorelativoAlmacenInput;
import com.example.fab083.dtos.DtoObtenerCorelativoAlmacenOutput;
import com.example.fab083.dtos.DtoObtenerDatosEtiqueta;
import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;
import com.example.fab083.dtos.DtoObtenerDatosEtiquetaOutput;
import com.example.fab083.dtos.DtoObtenerDatosStockEmpaqueInput;
import com.example.fab083.dtos.DtoObtenerDatosStockEmpaqueOutput;
import com.example.fab083.dtos.DtoObtenerDiasPermitidosAyerInput;
import com.example.fab083.dtos.DtoObtenerDiasPermitidosInput;
import com.example.fab083.dtos.DtoObtenerFMOVALG2Input;
import com.example.fab083.dtos.DtoObtenerFMOVALG2Output;
import com.example.fab083.dtos.DtoObtenerFechaSistemaAyerInput;
import com.example.fab083.dtos.DtoObtenerFechaSistemaInput;
import com.example.fab083.dtos.DtoObtenerHoraSistemaInput;
import com.example.fab083.dtos.DtoObtenerInputTipoDocumentoInput;
import com.example.fab083.dtos.DtoObtenerRegistroFMOVALG2Input;
import com.example.fab083.dtos.DtoObtenerRegistroFMOVALG2Outputs;
import com.example.fab083.dtos.DtoObtenerTipoMovTransferenciaInput;
import com.example.fab083.dtos.DtoProcesarGuardado;
import com.example.fab083.dtos.DtoProcesarlectura;
import com.example.fab083.dtos.DtoTipoDocumento;
import com.example.fab083.dtos.DtoUltimoMovimientoInput;
import com.example.fab083.dtos.DtoUltimoMovimientoOutput;
import com.example.fab083.dtos.DtoUtilizaRegistroCorrelativoAlmacenInput;
import com.example.fab083.dtos.DtoUtilizaRegistroCorrelativoAlmacenOutput;
import com.example.fab083.dtos.DtoValidaUsoCorrelativoInput;
import com.example.fab083.dtos.DtoValidarAlamcenXCcostoInput;
import com.example.fab083.dtos.DtoValidarAlamcenXCcostoOutput;
import com.example.fab083.dtos.DtoValidarCargaOutput;
import com.example.fab083.dtos.DtoValidarConsecutivoOutput;
import com.example.fab083.dtos.DtoValidarNroOrdenInput;
import com.example.fab083.dtos.DtoValidarNroOrdenOutput;
import com.example.fab083.dtos.DtoValidarTipoListadoOutput;
import com.example.fab083.dtos.DtoutilizaRegistroInput;
import com.example.fab083.dtos.DtoutilizaRegistroOutput;
import com.example.fab083.interfaces.IAlmacen;
import com.example.fab083.interfaces.IAlta;
import com.example.fab083.interfaces.ICentroCosto;
import com.example.fab083.interfaces.ICompañia;
import com.example.fab083.interfaces.IEtiqueta;
import com.example.fab083.interfaces.IGeneral;
import com.example.fab083.interfaces.ITipoDocumento;
import com.example.fab083.interfaces.ITipoMovimiento;
import com.example.fab083.retrofit.RetrofitClient;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AltaViewModel extends ViewModel {
    private final MutableLiveData<List<DtoCompañia>> itemsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoAlmacen>> ListaAlmacenLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoAlmacen>> ListaAlmacenDestinoLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoObtenerCentroCostosInput>> ListaCentroCostoLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoObtenerCentroCostosXAlmacenInput>> ListaCentroCostoXAlmacenLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoObtenerDatosEtiquetaInput>> ListaDatosEtiquetaLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoObtenerDatosEtiquetaInput>> ListaProcesoLecturaLiveData = new MutableLiveData<>();

    //  private SingleLiveEvent<List<DtoObtenerDatosEtiquetaInput>> ListaDatosEtiquetaLiveData = new SingleLiveEvent<>();
    private final MutableLiveData<List<DtoObtenerRegistroFMOVALG2Input>> ListaDatosFMOVALG2LiveData = new MutableLiveData<>();
    private final MutableLiveData<DtoObtenerHoraSistemaInput> resultHoraSistemaLiveData = new MutableLiveData<>();
    private final MutableLiveData<DtoObtenerFechaSistemaAyerInput> resultFechaSistemaAyerLiveData = new MutableLiveData<>();
    private final MutableLiveData<DtoObtenerFechaSistemaInput> resultFechaSistemaLiveData = new MutableLiveData<>();
    private final MutableLiveData<DtoObtenerDiasPermitidosInput> resultDiasPermitidosLiveData = new MutableLiveData<>();

    private final MutableLiveData<DtoObtenerDiasPermitidosAyerInput> resultDiasPermitidosAyerLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoObtenerDatosStockEmpaqueInput>> resultObtenerDatosStockEmpaqueLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoValidaUsoCorrelativoInput>> resultValidaUsoCorrelativoLiveData = new MutableLiveData<>();

    private final MutableLiveData<List<DtoObtenerCorelativoAlmacenInput>> resultObtenerCorelativoAlmacenLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> resultProcesarGuardado = new MutableLiveData<>();


    private final MutableLiveData<List<DtoUtilizaRegistroCorrelativoAlmacenInput>> resultUtilizaRegistroCorrelativoAlmacenLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoValidarAlamcenXCcostoInput>> ListaValidarAlamcenXCcostoLiveData = new MutableLiveData<>();

    private final MutableLiveData<DtoutilizaRegistroInput> ListaDatosUtilizaRegistroLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoAlmacenXCodigoInput>> ListaAlmacenXCodigoLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoTipoDocumento>> ListaTipoDocumentoLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoObtenerTipoMovTransferenciaInput>> ListaTipoMovLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<DtoObtenerInputTipoDocumentoInput>> ListaInputTipoDocumentoLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Integer> resultBloquearRegistro = new MutableLiveData<>();
    private MutableLiveData<Integer> resultBloquearRegistroCorrelativoNull = new MutableLiveData<>();
    private MutableLiveData<Integer> resultBloquearRegistroCorrelativo = new MutableLiveData<>();

    private MutableLiveData<Integer> resultGrabarAlta = new MutableLiveData<>();
    private MutableLiveData<Integer> resultCriterioTipoExistencia = new MutableLiveData<>();
    private MutableLiveData<Integer> resultDesbloquearTABCORRE = new MutableLiveData<>();
    private MutableLiveData<Integer> resultGrabarODRPCON1 = new MutableLiveData<>();
    private MutableLiveData<Integer> resultDesbloquearFABCORRE = new MutableLiveData<>();

    private MutableLiveData<Integer> resultCantidadRegistro = new MutableLiveData<>();

    private MutableLiveData<Boolean> isTipoListadoValido = new MutableLiveData<>();
    private MutableLiveData<Integer> resultDesbloquearRegistro = new MutableLiveData<>();
    private final MutableLiveData<List<DtoObtenerFMOVALG2Input>> resultObtenerFMOVALG2LiveData = new MutableLiveData<>();

    private MutableLiveData<ApiResponseEtiqueta> respuestaEtiquetaLiveData = new MutableLiveData<>();

    private MutableLiveData<ApiResponseValidarNroOrden> resultvalidarNroOrden = new MutableLiveData<>();
    private MutableLiveData<ApiResponseValidarEstadoCarga> resultvalidarCarga = new MutableLiveData<>();
    private MutableLiveData<ApiResponseValidarEstadoConcecutivo> resultvalidarEstadoConsecutivo = new MutableLiveData<>();
    private MutableLiveData<ApiResponseObtenerUltimoMovimiento> resultObtenerUltimoMovimiento = new MutableLiveData<>();

    private final ICompañia Compañia;
    private final IAlmacen Almacen;
    private final ICentroCosto CentroCosto;
    private final ITipoDocumento TipoDocumento;
    private final ITipoMovimiento TipoMovimiento;
    private final IEtiqueta Etiqueta;
    private final IGeneral General;
    private final IAlta Alta;

    public AltaViewModel() {
        //Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:5234/");
        //Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.194:8082/");
        //Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.149:8096/");
        // PRODUCCION
        Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.149:8097/");


        Compañia = retrofit.create(ICompañia.class);
        Almacen = retrofit.create(IAlmacen.class);
        TipoDocumento = retrofit.create(ITipoDocumento.class);
        TipoMovimiento = retrofit.create(ITipoMovimiento.class);
        CentroCosto = retrofit.create(ICentroCosto.class);
        Etiqueta = retrofit.create(IEtiqueta.class);
        General = retrofit.create(IGeneral.class);
        Alta= retrofit.create(IAlta.class);
        ObtenerCompania();
        ObtenerAlmacenOrigen();
        ObtenerAlmacenDestino();

    }

    public LiveData<DtoutilizaRegistroInput> getListaDatosUtilizaRegistroLiveData() {
        return ListaDatosUtilizaRegistroLiveData;
    }

    public LiveData<List<DtoObtenerRegistroFMOVALG2Input>> getListaDatosFMOVALG2LiveData() {
        return ListaDatosFMOVALG2LiveData;
    }

    public LiveData<List<DtoObtenerCentroCostosInput>> getListaCentroCostos() {
        return ListaCentroCostoLiveData;
    }

    public LiveData<List<DtoObtenerCentroCostosXAlmacenInput>> getListaCentroCostosXAlmacen() {
        return ListaCentroCostoXAlmacenLiveData;
    }

    public LiveData<List<DtoObtenerDatosEtiquetaInput>> getListaDatosEtiqueta() {
        return ListaDatosEtiquetaLiveData;
    }

    public LiveData<List<DtoCompañia>> getItems() {
        return itemsLiveData;
    }

    public LiveData<DtoObtenerDiasPermitidosInput> getObtenerDiasPermitidos() {
        return resultDiasPermitidosLiveData;
    }

    public LiveData<DtoObtenerDiasPermitidosAyerInput> getObtenerDiasPermitidosAyer() {
        return resultDiasPermitidosAyerLiveData;
    }

    public LiveData<DtoObtenerHoraSistemaInput> getObtenerHoraSistema() {
        return resultHoraSistemaLiveData;
    }
    public LiveData<String> getresultProcesarGuardado() {
        return resultProcesarGuardado;
    }
    public LiveData<ApiResponseValidarNroOrden> getresultvalidarNroOrden()
    {
        return resultvalidarNroOrden;
    }
    public LiveData<ApiResponseValidarEstadoCarga> getresultvalidarEstadoCarga()
    {
        return resultvalidarCarga;
    }

    public LiveData<DtoObtenerFechaSistemaAyerInput> getObtenerFechaSistemaAyer() {
        return resultFechaSistemaAyerLiveData;
    }

    public LiveData<DtoObtenerFechaSistemaInput> getObtenerFechaSistema() {
        return resultFechaSistemaLiveData;
    }

    public LiveData<List<DtoUtilizaRegistroCorrelativoAlmacenInput>> getUtilizaRegistroCorrelativoAlmacen() {
        return resultUtilizaRegistroCorrelativoAlmacenLiveData;
    }
    public LiveData<List<DtoObtenerDatosStockEmpaqueInput>> getObtenerDatosStockEmpaque() {
        return resultObtenerDatosStockEmpaqueLiveData;
    }

    public LiveData<List<DtoAlmacen>> getAlmacen() {
        return ListaAlmacenLiveData;
    }
    public LiveData<List<DtoAlmacen>> getAlmacenDestino(){
        return ListaAlmacenDestinoLiveData;
    }
    public LiveData<List<DtoValidarAlamcenXCcostoInput>> getValidarAlamcenXCcostoInput() {
        return ListaValidarAlamcenXCcostoLiveData;
    }

    public LiveData<List<DtoObtenerInputTipoDocumentoInput>> getListaInputTipoDocumentoLiveData() {
        return ListaInputTipoDocumentoLiveData;
    }

    public LiveData<List<DtoTipoDocumento>> getTipoDocumento() {
        return ListaTipoDocumentoLiveData;
    }

    public LiveData<List<DtoObtenerTipoMovTransferenciaInput>> getTipoDocumentoTipoMovTransferencia() {
        return ListaTipoMovLiveData;
    }

    public LiveData<List<DtoAlmacenXCodigoInput>> getAlmacenXCodigo() {
        return ListaAlmacenXCodigoLiveData;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Integer> getBloquearRegistro() {
        return resultBloquearRegistro;
    }

    public LiveData<Integer> getresultBloquearRegistroCorrelativoNull() {return resultBloquearRegistroCorrelativoNull;}
    public LiveData<Integer> getresultBloquearRegistroCorrelativo() {return resultBloquearRegistroCorrelativo;}
    public LiveData<Integer> getresultCriterioTipoExistencia() {return resultCriterioTipoExistencia;}
    public LiveData<Integer> getresultDesbloquearFABCORRE() {return resultDesbloquearFABCORRE;}

    public LiveData<Integer> getCantidadRegistro() {
        return resultCantidadRegistro;
    }

    public LiveData<Boolean> getIsTipoListadoValido() {
        return isTipoListadoValido;
    }

    public LiveData<Integer> getDesbloquearRegistro() {
        return resultDesbloquearRegistro;
    }
    public LiveData<Integer> getGrabarAlta() {
        return resultGrabarAlta;
    }
    public LiveData<Integer> getGrabarODRPCON1() {
        return resultGrabarODRPCON1;
    }


    public LiveData<Integer> getresultDesbloquearTABCORRE() {
        return resultDesbloquearTABCORRE;
    }
    public LiveData<List<DtoObtenerCorelativoAlmacenInput>> getObtenerCorelativoAlmacen() { return resultObtenerCorelativoAlmacenLiveData; }
    public LiveData<List<DtoValidaUsoCorrelativoInput>> getValidaUsoCorrelativo() { return resultValidaUsoCorrelativoLiveData; }
    public LiveData<List<DtoObtenerFMOVALG2Input>> getObtenerFMOVALG2LiveData() { return resultObtenerFMOVALG2LiveData; }

    public LiveData<ApiResponseEtiqueta> getRespuestaEtiquetaLiveData() {
        return respuestaEtiquetaLiveData;
    }
    public LiveData<ApiResponseValidarEstadoConcecutivo> getRespuestaValidarEstadoConcecutivoLiveData() {
        return resultvalidarEstadoConsecutivo;
    }
    public LiveData<ApiResponseObtenerUltimoMovimiento> getResponseObtenerUltimoMovimientoLiveData(){
        return resultObtenerUltimoMovimiento;
    }

    public void limpiarRespuestaEtiqueta() {
        respuestaEtiquetaLiveData.setValue(null); // Esto solo funciona si es MutableLiveData
    }


    // CARGA DE COMBOS

    private void ObtenerCompania() {
        isLoading.setValue(true);
        errorMessage.setValue(null);

        Call<ApiResponseCompañia> call = Compañia.ObtenerCompañia();
        call.enqueue(new Callback<ApiResponseCompañia>() {

            @Override
            public void onResponse(Call<ApiResponseCompañia> call, Response<ApiResponseCompañia> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<DtoCompañia> companyList = response.body().getResult();
                    itemsLiveData.setValue(companyList);
                } else {
                    errorMessage.setValue("Error en la respuesta: " + response.message());
                    Log.e("API Response", "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponseCompañia> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error al realizar la solicitud: " + t.getMessage());
                Log.e("API Error", "Error al realizar la solicitud: " + t.getMessage(), t);
            }
        });
    }

    private void ObtenerAlmacenOrigen() {
        isLoading.setValue(true);
        errorMessage.setValue(null);

        try {
            Call<ApiResponseAlmacen> call = Almacen.ObtenerAlmacen();
            call.enqueue(new Callback<ApiResponseAlmacen>() {

                @Override
                public void onResponse(Call<ApiResponseAlmacen> call, Response<ApiResponseAlmacen> response) {
                    isLoading.setValue(false);
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        List<DtoAlmacen> listaAlmacen = response.body().getResult();
                        ListaAlmacenLiveData.setValue(listaAlmacen);
                    } else {
                        errorMessage.setValue("Error en la respuesta ObtenerAlmacenOrigen: " + response.message());
                        Log.e("API Response", "Error en la respuesta ObtenerAlmacenOrigen: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseAlmacen> call, Throwable t) {
                    isLoading.setValue(false);
                    errorMessage.setValue("Error al realizar la solicitud: " + t.getMessage());
                    Log.e("API Error", "Error al realizar la solicitud: " + t.getMessage(), t);
                }
            });
        } catch (Exception e) {
            isLoading.setValue(false);
            errorMessage.setValue("Error inesperado: " + e.getMessage());
            Log.e("API Error", "Error inesperado: " + e.getMessage(), e);
        }
    }

    private void ObtenerAlmacenDestino() {
        isLoading.setValue(true);
        errorMessage.setValue(null);

        try {
            Call<ApiResponseAlmacen> call = Almacen.ObtenerAlmacen();
            call.enqueue(new Callback<ApiResponseAlmacen>() {

                @Override
                public void onResponse(Call<ApiResponseAlmacen> call, Response<ApiResponseAlmacen> response) {
                    isLoading.setValue(false);
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        List<DtoAlmacen> listaAlmacen = response.body().getResult();
                        ListaAlmacenDestinoLiveData.setValue(listaAlmacen);
                    } else {
                        errorMessage.setValue("Error en la respuesta ObtenerAlmacenDestino: " + response.message());
                        Log.e("API Response", "Error en la respuesta ObtenerAlmacenDestino: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseAlmacen> call, Throwable t) {
                    isLoading.setValue(false);
                    errorMessage.setValue("Error al realizar la solicitud: " + t.getMessage());
                    Log.e("API Error", "Error al realizar la solicitud: " + t.getMessage(), t);
                }
            });
        } catch (Exception e) {
            isLoading.setValue(false);
            errorMessage.setValue("Error inesperado: " + e.getMessage());
            Log.e("API Error", "Error inesperado: " + e.getMessage(), e);
        }
    }

    public void ObtenerTipoMovimientoSalida(int CodigoAlmacen) {

        isLoading.setValue(true);
        errorMessage.setValue(null);
        ListaTipoDocumentoLiveData.setValue(null);
        DtoCboTipoDocOutput obj_TipoDocOutput = new DtoCboTipoDocOutput();
        obj_TipoDocOutput.setCodigoAlmacen(CodigoAlmacen);
        Call<ApiResponseTipoDocumento> call = TipoDocumento.ObtenerTipoMovSalida(obj_TipoDocOutput);
        call.enqueue(new Callback<ApiResponseTipoDocumento>() {

            @Override
            public void onResponse(Call<ApiResponseTipoDocumento> call, Response<ApiResponseTipoDocumento> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<DtoTipoDocumento> listaTipoDocumento = response.body().ListaTipoDocumento();
                    ListaTipoDocumentoLiveData.setValue(listaTipoDocumento);

                } else {
                    String error = "Error en la respuesta ObtenerTipoMovimientoSalida: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseTipoDocumento> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerAlmacenXCodigo(int CodigoAlmacen) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        ListaAlmacenXCodigoLiveData.setValue(null);
        DtoAlmacenXCodigoOutput obj_AlmacenOutput = new DtoAlmacenXCodigoOutput();
        obj_AlmacenOutput.setCodigoAlmacen(CodigoAlmacen);

        Call<ApiResponseAlmacenXCodigo> call = Almacen.ObtenerAlmacenXCodigo(obj_AlmacenOutput);
        try {
            call.enqueue(new Callback<ApiResponseAlmacenXCodigo>() {
                @Override
                public void onResponse(Call<ApiResponseAlmacenXCodigo> call, Response<ApiResponseAlmacenXCodigo> response) {
                    isLoading.setValue(false);
                    Log.d("ObtenerAlmacenXCodigo", "Response received: " + response.toString());
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        List<DtoAlmacenXCodigoInput> obj_dtoAlmacenXCodigoInputList = response.body().getResult();
                        ListaAlmacenXCodigoLiveData.setValue(obj_dtoAlmacenXCodigoInputList);
                        for (DtoAlmacenXCodigoInput almacen : obj_dtoAlmacenXCodigoInputList) {
                            Log.d("ObtenerAlmacenXCodigo", "Almacen: " + almacen.toString());
                        }
                    } else {
                        String error = "Error en la respuesta ObtenerAlmacenXCodigo: " + response.message();
                        errorMessage.setValue(error);
                        Log.e("ObtenerAlmacenXCodigo", error);
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseAlmacenXCodigo> call, Throwable t) {
                    isLoading.setValue(false);
                    String error = "Error al realizar la solicitud: " + t.getMessage();
                    errorMessage.setValue(error);
                    Log.e("API Error", error, t);
                }
            });
        } catch (Exception e) {
            isLoading.setValue(false);
            String error = "Exception during enqueue: " + e.getMessage();
            errorMessage.setValue(error);
            Log.e("ObtenerAlmacenXCodigo", error, e);
        }
    }

    public void ObtenerInputTipoDocumento() {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        ListaInputTipoDocumentoLiveData.setValue(null);
        Call<ApiResponseInputTipoDocumento> call = TipoDocumento.ObtenerTipoDocInput();
        try {
            call.enqueue(new Callback<ApiResponseInputTipoDocumento>() {
                @Override
                public void onResponse(Call<ApiResponseInputTipoDocumento> call, Response<ApiResponseInputTipoDocumento> response) {
                    isLoading.setValue(false);

                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        List<DtoObtenerInputTipoDocumentoInput> obj_dtoObtenerInputTipoDocumentoInput = response.body().ListaInputTipoDocumento();
                        ListaInputTipoDocumentoLiveData.setValue(obj_dtoObtenerInputTipoDocumentoInput);
                    } else {
                        String error = "Error en la respuesta ObtenerInputTipoDocumento: " + response.message();
                        errorMessage.setValue(error);
                        Log.e("API Response Error", error);
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseInputTipoDocumento> call, Throwable t) {
                    isLoading.setValue(false);
                    String error = "Error al realizar la solicitud: " + t.getMessage();
                    errorMessage.setValue(error);
                    Log.e("API Error", error, t);
                }
            });
        } catch (Exception e) {
            isLoading.setValue(false);
            String error = "Exception during enqueue: " + e.getMessage();
            errorMessage.setValue(error);
            Log.e("ObtenerInputTipoDocumento", error, e);
        }
    }

    public void ObtenerTipoMovimientoTransferencia() {

        isLoading.setValue(true);
        errorMessage.setValue(null);

        Call<ApiResponseObtenerTipoMovTransferencia> call = TipoMovimiento.ObtenerTipoMovTransferencia();
        call.enqueue(new Callback<ApiResponseObtenerTipoMovTransferencia>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerTipoMovTransferencia> call, Response<ApiResponseObtenerTipoMovTransferencia> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<DtoObtenerTipoMovTransferenciaInput> listaTipoMov = response.body().ListaObtenerTipoMovTransferenciaInput();
                    ListaTipoMovLiveData.setValue(listaTipoMov);

                } else {
                    String error = "Error en la respuesta ObtenerTipoMovimientoTransferencia: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerTipoMovTransferencia> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerCentroCostos() {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        ListaCentroCostoLiveData.setValue(null);
        try {
            Call<ApiResponseCentroCosto> call = CentroCosto.ObtenerCentroCostos();
            call.enqueue(new Callback<ApiResponseCentroCosto>() {

                @Override
                public void onResponse(Call<ApiResponseCentroCosto> call, Response<ApiResponseCentroCosto> response) {
                    isLoading.setValue(false);
                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        List<DtoObtenerCentroCostosInput> listaCentroCosto = response.body().getResult();
                        ListaCentroCostoLiveData.setValue(listaCentroCosto);
                    } else {
                        errorMessage.setValue("Error en la respuesta ObtenerCentroCostos: " + response.message());
                        Log.e("API Response", "Error en la respuesta ObtenerCentroCostos: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseCentroCosto> call, Throwable t) {
                    isLoading.setValue(false);
                    errorMessage.setValue("Error al realizar la solicitud: " + t.getMessage());
                    Log.e("API Error", "Error al realizar la solicitud: " + t.getMessage(), t);
                }
            });
        } catch (Exception e) {
            isLoading.setValue(false);
            errorMessage.setValue("Error inesperado: " + e.getMessage());
            Log.e("API Error", "Error inesperado: " + e.getMessage(), e);
        }
    }

    public void ObtenerCentroCostosXAlmacen(int CodigoAlmacen) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        ListaCentroCostoXAlmacenLiveData.setValue(null);
        DtoObtenerCentroCostosoOutput obj_CentroCostos = new DtoObtenerCentroCostosoOutput();
        obj_CentroCostos.setCodAlmacen(CodigoAlmacen);
        Call<ApiResponseCentroCostoXAlmacen> call = CentroCosto.ObtenerCentroCostosXAlmacen(obj_CentroCostos);
        call.enqueue(new Callback<ApiResponseCentroCostoXAlmacen>() {

            @Override
            public void onResponse(Call<ApiResponseCentroCostoXAlmacen> call, Response<ApiResponseCentroCostoXAlmacen> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    List<DtoObtenerCentroCostosXAlmacenInput> listaCC = response.body().getResult();
                    ListaCentroCostoXAlmacenLiveData.setValue(listaCC);
                } else {
                    String error = "Error en la respuesta ObtenerCentroCostosXAlmacen: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseCentroCostoXAlmacen> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    // LEER CODIGOS

    public void ObtenerDatosEtiqueta(String CodigoEtiqueta) {
        ListaDatosEtiquetaLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);
        DtoObtenerDatosEtiquetaOutput obj_ObtenerDatosEtiquetaOutput = new DtoObtenerDatosEtiquetaOutput();
        obj_ObtenerDatosEtiquetaOutput.setCodEtiqueta(CodigoEtiqueta);

        Call<ApiResponseEtiqueta> call = Etiqueta.ObtenerDatosEtiqueta(obj_ObtenerDatosEtiquetaOutput);
        call.enqueue(new Callback<ApiResponseEtiqueta>() {

            @Override
            public void onResponse(Call<ApiResponseEtiqueta> call, Response<ApiResponseEtiqueta> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {

                    List<DtoObtenerDatosEtiquetaInput> listaCC = response.body().getResult();
                    ListaDatosEtiquetaLiveData.setValue(listaCC);
                } else {
                    String error = "Error en la respuesta ObtenerDatosEtiqueta: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseEtiqueta> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ProcesoLecturaEtiqueta(DtoProcesarlectura request) {
        //respuestaEtiquetaLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);
        Call<ApiResponseEtiqueta> call = Etiqueta.ProcesoLecturaEtiqueta(request);
        call.enqueue(new Callback<ApiResponseEtiqueta>() {
            @Override
            public void onResponse(Call<ApiResponseEtiqueta> call, Response<ApiResponseEtiqueta> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    respuestaEtiquetaLiveData.setValue(response.body()); // Enviar la respuesta completa al LiveData
                }else {
                    String error = "Error en la respuesta ObtenerDatosEtiqueta: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseEtiqueta> call, Throwable t) {
                isLoading.setValue(false);
                if(t instanceof SocketTimeoutException){
                    Log.e("Timeout", "Tiempo de espera agotado");
                }else {
                    String error = "Error al realizar la solicitud: " + t.getMessage();
                    errorMessage.setValue(error);
                    Log.e("API Error", error, t);
                }
            }
        });
    }

    public void ValidarRegistroFMOVALG2(String CodigoEtiqueta) {
        ListaDatosFMOVALG2LiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        DtoObtenerRegistroFMOVALG2Outputs obj = new DtoObtenerRegistroFMOVALG2Outputs();
        obj.setCodEtiqueta(CodigoEtiqueta);

        Call<ApiResponseObtenerRegistroFMOVALG2> call = Almacen.ObtenerRegistroFMVALG2(obj);
        call.enqueue(new Callback<ApiResponseObtenerRegistroFMOVALG2>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerRegistroFMOVALG2> call, Response<ApiResponseObtenerRegistroFMOVALG2> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseObtenerRegistroFMOVALG2 apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        List<DtoObtenerRegistroFMOVALG2Input> lista = apiResponse.getResult();
                        ListaDatosFMOVALG2LiveData.setValue(lista);

                        Log.d("API Response", "Datos obtenidos satisfactoriamente: " + lista.size() + " elementos.");
                    } else {
                        String error = "Error en la respuesta ValidarRegistroFMOVALG2: " + apiResponse.getErrorMessage();
                        errorMessage.setValue(error);
                        Log.e("API Response", error);
                    }
                } else {
                    String error = "Error en la respuesta ValidarRegistroFMOVALG2: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerRegistroFMOVALG2> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void utilizaRegistro(String CodigoEtiqueta) {
        ListaDatosUtilizaRegistroLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        DtoutilizaRegistroOutput obj_Output = new DtoutilizaRegistroOutput();
        obj_Output.setCodigoEtiqueta(CodigoEtiqueta);

        Call<ApiResponseUtilizaRegistro> call = General.UtilizaRegistro(obj_Output);
        call.enqueue(new Callback<ApiResponseUtilizaRegistro>() {

            @Override
            public void onResponse(Call<ApiResponseUtilizaRegistro> call, Response<ApiResponseUtilizaRegistro> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    ApiResponseUtilizaRegistro apiResponse = response.body();

                    DtoutilizaRegistroInput datos = apiResponse.getResult();
                    ListaDatosUtilizaRegistroLiveData.setValue(datos);

                } else {
                    String error = "Error en la respuesta utilizaRegistro: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseUtilizaRegistro> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud utilizaRegistro: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void BloquearRegistro(String codigoEtiqueta, String pcName) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultBloquearRegistro.setValue(null);
        // Crear el objeto DTO de entrada para la API
        DtoBloquearRegistroNullInput obj_BloquearRegistroNullDtoInput = new DtoBloquearRegistroNullInput();
        obj_BloquearRegistroNullDtoInput.setCodigoEtiqueta(codigoEtiqueta);
        obj_BloquearRegistroNullDtoInput.setPcName(pcName);

        // Llamar a la API
        Call<ApiResponseGeneral> call = General.BloquearRegistro(obj_BloquearRegistroNullDtoInput);
        call.enqueue(new Callback<ApiResponseGeneral>() {
            @Override
            public void onResponse(Call<ApiResponseGeneral> call, Response<ApiResponseGeneral> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseGeneral apiResponse = response.body();

                    int datos = Integer.parseInt(apiResponse.result);
                    resultBloquearRegistro.setValue(datos);
                    Log.d("API Response", "Registro bloqueado satisfactoriamente.");
                } else {
                    String error = "Error en la respuesta BloquearRegistro: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGeneral> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ValidarTipoListado(int nroOrden) {
        // Indica que la solicitud ha comenzado
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultCantidadRegistro.setValue(null);

        // Crear el objeto DTO de entrada para la API
        DtoValidarTipoListadoOutput obj_ValidarTipoListado = new DtoValidarTipoListadoOutput();
        obj_ValidarTipoListado.setNroOrden(nroOrden);

        // Llamar a la API
        Call<ApiResponseGeneral> call = General.ValidarTipoListado(obj_ValidarTipoListado);
        call.enqueue(new Callback<ApiResponseGeneral>() {
            @Override
            public void onResponse(Call<ApiResponseGeneral> call, Response<ApiResponseGeneral> response) {
                // Finaliza la carga
                isLoading.setValue(false);

                // Verifica si la respuesta es exitosa y tiene cuerpo
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseGeneral apiResponse = response.body();

                    try {
                        int datos = Integer.parseInt(apiResponse.result);
                        resultCantidadRegistro.setValue(datos);

                        // Si el resultado es mayor a 0, la validación es exitosa (true)
                        if (datos > 0) {
                            isTipoListadoValido.setValue(true);
                        } else {
                            isTipoListadoValido.setValue(false);
                        }
                        Log.d("API Response", "Validación de tipo de listado satisfactoria.");
                    } catch (NumberFormatException e) {
                        String error = "Error en el formato del resultado de la API: " + apiResponse.result;
                        errorMessage.setValue(error);
                        Log.e("API Response", error);
                        isTipoListadoValido.setValue(false); // Si hay error, asumimos que no es válido
                    }
                } else {
                    String error = "Error en la respuesta ValidarTipoListado: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                    isTipoListadoValido.setValue(false); // Error en la respuesta también marca como inválido
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGeneral> call, Throwable t) {
                // Finaliza la carga en caso de error
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
                isTipoListadoValido.setValue(false); // Falla en la solicitud también marca como inválido
            }
        });
    }


    public void ObtenerHoraSistema() {
        resultHoraSistemaLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        Call<ApiResponseObtenerHoraSistema> call = General.ObtenerHoraSistema();
        call.enqueue(new Callback<ApiResponseObtenerHoraSistema>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerHoraSistema> call, Response<ApiResponseObtenerHoraSistema> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseObtenerHoraSistema lista = response.body();
                    resultHoraSistemaLiveData.setValue(lista.getResult());
                } else {
                    String error = "Error en la respuesta ObtenerHoraSistema: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerHoraSistema> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerFechaSistemaAyer() {
        resultFechaSistemaAyerLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        Call<ApiResponseObtenerFechaSistemaAyer> call = General.ObtenerFechaSistemaAyer();
        call.enqueue(new Callback<ApiResponseObtenerFechaSistemaAyer>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerFechaSistemaAyer> call, Response<ApiResponseObtenerFechaSistemaAyer> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseObtenerFechaSistemaAyer FechaSistemaAyer = response.body();
                    resultFechaSistemaAyerLiveData.setValue(FechaSistemaAyer.getResult());
                } else {
                    String error = "Error en la respuesta ObtenerHoraSistema: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerFechaSistemaAyer> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerFechaSistema() {
        resultFechaSistemaLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        Call<ApiResponseObtenerFechaSistema> call = General.ObtenerFechaSistema();
        call.enqueue(new Callback<ApiResponseObtenerFechaSistema>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerFechaSistema> call, Response<ApiResponseObtenerFechaSistema> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseObtenerFechaSistema FechaSistema = response.body();
                    resultFechaSistemaLiveData.setValue(FechaSistema.getResult());
                } else {
                    String error = "Error en la respuesta ObtenerHoraSistema: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerFechaSistema> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerDiasPermitidos() {
        resultDiasPermitidosLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        Call<ApiResponseObtenerDiasPermitidos> call = General.ObtenerDiasPermitidos();
        call.enqueue(new Callback<ApiResponseObtenerDiasPermitidos>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerDiasPermitidos> call, Response<ApiResponseObtenerDiasPermitidos> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseObtenerDiasPermitidos obj_Api = response.body();
                    resultDiasPermitidosLiveData.setValue(obj_Api.getResult());
                } else {
                    String error = "Error en la respuesta ObtenerDiasPermitidos: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerDiasPermitidos> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerDiasPermitidosAyer() {
        resultDiasPermitidosAyerLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        Call<ApiResponseObtenerDiasPermitidosAyer> call = General.ObtenerDiasPermitidosAyer();
        call.enqueue(new Callback<ApiResponseObtenerDiasPermitidosAyer>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerDiasPermitidosAyer> call, Response<ApiResponseObtenerDiasPermitidosAyer> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseObtenerDiasPermitidosAyer obj_Api = response.body();
                    resultDiasPermitidosAyerLiveData.setValue(obj_Api.getResult());
                } else {
                    String error = "Error en la respuesta ObtenerDiasPermitidos: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerDiasPermitidosAyer> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerDatosStockEmpaque(String CodigoEtiquetas) {
        resultObtenerDatosStockEmpaqueLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        DtoObtenerDatosStockEmpaqueOutput obj = new DtoObtenerDatosStockEmpaqueOutput();
        obj.setCodigoEtiquetas(CodigoEtiquetas);
        Call<ApiResponseObtenerDatosStockEmpaque> call = General.ObtenerDatosStockEmpaque(obj);
        call.enqueue(new Callback<ApiResponseObtenerDatosStockEmpaque>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerDatosStockEmpaque> call, Response<ApiResponseObtenerDatosStockEmpaque> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    List<DtoObtenerDatosStockEmpaqueInput> listaCC = response.body().getResult();
                    resultObtenerDatosStockEmpaqueLiveData.setValue(listaCC);
                } else {
                    String error = "Error en la respuesta ObtenerDatosStockEmpaque: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerDatosStockEmpaque> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud ObtenerDatosStockEmpaque: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void BloquearRegistroCorrelativoNull(String pcName, int codAlm, int codcia, String TipMovSeleccionado) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultBloquearRegistroCorrelativoNull.setValue(null);
        // Crear el objeto DTO de entrada para la API
        DtoBloquearRegistroCorrelativoNullOutput obj_BloquearRegistroCorrelativoNullDtoOutput = new DtoBloquearRegistroCorrelativoNullOutput();
        obj_BloquearRegistroCorrelativoNullDtoOutput.setPcName(pcName);
        obj_BloquearRegistroCorrelativoNullDtoOutput.setCodCia(codcia);
        obj_BloquearRegistroCorrelativoNullDtoOutput.setCodAlg(codAlm);
        obj_BloquearRegistroCorrelativoNullDtoOutput.setTmvcor(TipMovSeleccionado);

        // Llamar a la API
        Call<ApiResponseBloquearRegistroCorrelativoNull> call = General.BloquearRegistroCorrelativoNull(obj_BloquearRegistroCorrelativoNullDtoOutput);
        call.enqueue(new Callback<ApiResponseBloquearRegistroCorrelativoNull>() {
            @Override
            public void onResponse(Call<ApiResponseBloquearRegistroCorrelativoNull> call, Response<ApiResponseBloquearRegistroCorrelativoNull> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseBloquearRegistroCorrelativoNull apiResponse = response.body();
                    int datos = Integer.parseInt(apiResponse.getResult());
                    resultBloquearRegistroCorrelativoNull.setValue(datos);
                    Log.d("API Response", "Registro bloqueado satisfactoriamente.");
                } else {
                    String error = "Error en la respuesta BloquearRegistro: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseBloquearRegistroCorrelativoNull> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerCorelativoAlmacen( int codAlm, int codcia, String TipMovSeleccionado) {
        resultObtenerCorelativoAlmacenLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        DtoObtenerCorelativoAlmacenOutput obj = new DtoObtenerCorelativoAlmacenOutput();
        obj.setCodigoCia(codcia);
        obj.setCodigoAlmacen(codAlm);
        obj.setTipoMovimiento(TipMovSeleccionado);

        Call<ApiResponseObtenerCorelativoAlmacen> call = Almacen.ObtenerCorrelativoAlmacen(obj);
        call.enqueue(new Callback<ApiResponseObtenerCorelativoAlmacen>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerCorelativoAlmacen> call, Response<ApiResponseObtenerCorelativoAlmacen> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    List<DtoObtenerCorelativoAlmacenInput> listaCC = response.body().getResult();
                    resultObtenerCorelativoAlmacenLiveData.setValue(listaCC);

                } else {
                    String error = "Error en la respuesta ObtenerDatosStockEmpaque: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerCorelativoAlmacen> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud ObtenerDatosStockEmpaque: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void UtilizaRegistroCorrelativoAlmacen(int codAlm, int codcia, String TipMovSeleccionado) {
        resultUtilizaRegistroCorrelativoAlmacenLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        // Creación del objeto con los parámetros necesarios
        DtoUtilizaRegistroCorrelativoAlmacenOutput obj = new DtoUtilizaRegistroCorrelativoAlmacenOutput();
        obj.setCodigoAlmacen(codAlm);
        obj.setCodigoCia(codcia);
        obj.setTipoMovimiento(TipMovSeleccionado);

        // Llamada a la API usando Retrofit
        Call<ApiResponseUtilizaRegistroCorrelativoAlmacen> call = Almacen.UtilizaRegistroCorrelativoAlmacen(obj);
        call.enqueue(new Callback<ApiResponseUtilizaRegistroCorrelativoAlmacen>() {

            @Override
            public void onResponse(Call<ApiResponseUtilizaRegistroCorrelativoAlmacen> call, Response<ApiResponseUtilizaRegistroCorrelativoAlmacen> response) {
                isLoading.setValue(false); // Detener el indicador de carga

                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    // Si la respuesta es exitosa, obtenemos los datos esperados
                    List<DtoUtilizaRegistroCorrelativoAlmacenInput> obj_UtilzaCorrelativoAlm = response.body().getResult();
                    resultUtilizaRegistroCorrelativoAlmacenLiveData.setValue(obj_UtilzaCorrelativoAlm);
                } else {
                    // Si hay un error en la respuesta de la API
                    String error = "Error en la respuesta utilizaRegistro: " + response.message();
                    Log.e("API Response", "Código de estado: " + response.code() + " | Cuerpo de error: " + response.errorBody());
                    errorMessage.setValue(error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseUtilizaRegistroCorrelativoAlmacen> call, Throwable t) {
                isLoading.setValue(false); // Detener el indicador de carga
                String error = "Error al realizar la solicitud utilizaRegistro: " + t.getMessage();

                if (t instanceof IOException) {
                    // Error relacionado con la red, como problemas de conexión
                    error = "Problema de red: " + t.getMessage();
                } else {
                    // Otro tipo de error (parsing de JSON, etc.)
                    error = "Error inesperado: " + t.getMessage();
                }

                errorMessage.setValue(error); // Actualiza el mensaje de error
                Log.e("API Error", error, t); // Imprime el error en el log
            }
        });
    }

    public void BloquearRegistroCorrelativo(String pcName, int codAlm, int codcia, String TipMovSeleccionado) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultBloquearRegistroCorrelativo.setValue(null);
        // Crear el objeto DTO de entrada para la API
        DtoBloquearRegistroCorrelativoOutput obj_BloquearRegistroCorrelativoDtoOutput = new DtoBloquearRegistroCorrelativoOutput();
        obj_BloquearRegistroCorrelativoDtoOutput.setPcName(pcName);
        obj_BloquearRegistroCorrelativoDtoOutput.setCodCia(codcia);
        obj_BloquearRegistroCorrelativoDtoOutput.setCodAlg(codAlm);
        obj_BloquearRegistroCorrelativoDtoOutput.setTmvcor(TipMovSeleccionado);

        // Llamar a la API
        Call<ApiResponseBloquearRegistroCorrelativo> call = General.BloquearRegistroCorrelativo(obj_BloquearRegistroCorrelativoDtoOutput);
        call.enqueue(new Callback<ApiResponseBloquearRegistroCorrelativo>() {
            @Override
            public void onResponse(Call<ApiResponseBloquearRegistroCorrelativo> call, Response<ApiResponseBloquearRegistroCorrelativo> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseBloquearRegistroCorrelativo apiResponse = response.body();

                    int datos = Integer.parseInt(apiResponse.getResult());
                    resultBloquearRegistroCorrelativo.setValue(datos);
                    Log.d("API Response", "Registro bloqueado satisfactoriamente.");
                } else {
                    String error = "Error en la respuesta BloquearRegistro: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseBloquearRegistroCorrelativo> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ValidarAlamcenXCcosto(int CodigoAlmacen) {
        ListaValidarAlamcenXCcostoLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);
        DtoValidarAlamcenXCcostoOutput obj_AlmacenOutput = new DtoValidarAlamcenXCcostoOutput();
        obj_AlmacenOutput.setCodAlg(CodigoAlmacen);

        Call<ApiResponseValidarAlamcenXCcosto> call = Almacen.ValidarAlamcenXCcosto(obj_AlmacenOutput);
        try {
            call.enqueue(new Callback<ApiResponseValidarAlamcenXCcosto>() {
                @Override
                public void onResponse(Call<ApiResponseValidarAlamcenXCcosto> call, Response<ApiResponseValidarAlamcenXCcosto> response) {
                    isLoading.setValue(false);

                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                        List<DtoValidarAlamcenXCcostoInput> obj = response.body().getResult();
                        ListaValidarAlamcenXCcostoLiveData.setValue(obj);

                    } else {
                        String error = "Error en la respuesta ObtenerAlmacenXCodigo: " + response.message();
                        errorMessage.setValue(error);
                        Log.e("ObtenerAlmacenXCodigo", error);
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseValidarAlamcenXCcosto> call, Throwable t) {
                    isLoading.setValue(false);
                    String error = "Error al realizar la solicitud: " + t.getMessage();
                    errorMessage.setValue(error);
                    Log.e("API Error", error, t);
                }
            });
        } catch (Exception e) {
            isLoading.setValue(false);
            String error = "Exception during enqueue: " + e.getMessage();
            errorMessage.setValue(error);
            Log.e("ObtenerAlmacenXCodigo", error, e);
        }

    }

    public void CriterioTipoExistencia(String codtex) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultCriterioTipoExistencia.setValue(null);

        DtoCriterioTipoExistenciaOutput obj = new DtoCriterioTipoExistenciaOutput();
        obj.setCodTex((int) Double.parseDouble(codtex));


        // Llamar a la API
        Call<ApiResponseCriterioTipoExistencia> call = General.CriterioTipoExistencia(obj);
        call.enqueue(new Callback<ApiResponseCriterioTipoExistencia>() {
            @Override
            public void onResponse(Call<ApiResponseCriterioTipoExistencia> call, Response<ApiResponseCriterioTipoExistencia> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseCriterioTipoExistencia apiResponse = response.body();

                    int datos = Integer.parseInt(apiResponse.getResult());
                    resultCriterioTipoExistencia.setValue(datos);

                } else {
                    String error = "Error en la respuesta CriterioTipoExistencia: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseCriterioTipoExistencia> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ValidaUsoCorrelativo() {
        resultValidaUsoCorrelativoLiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);

        Call<ApiResponseValidaUsoCorrelativo> call = General.ValidaUsoCorrelativo();
        call.enqueue(new Callback<ApiResponseValidaUsoCorrelativo>() {

            @Override
            public void onResponse(Call<ApiResponseValidaUsoCorrelativo> call, Response<ApiResponseValidaUsoCorrelativo> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {

                    List<DtoValidaUsoCorrelativoInput> obj = response.body().getResult();
                    resultValidaUsoCorrelativoLiveData.setValue(obj);


                } else {
                    String error = "Error en la respuesta ValidaUsoCorrelativo: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseValidaUsoCorrelativo> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud ValidaUsoCorrelativo: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void ObtenerFMOVALG2(String codigo) {
        resultObtenerFMOVALG2LiveData.setValue(null);
        isLoading.setValue(true);
        errorMessage.setValue(null);
        DtoObtenerFMOVALG2Output obj = new DtoObtenerFMOVALG2Output();
        obj.setCodigoEtiquetas(codigo);
        Call<ApiResponseObtenerFMOVALG2> call = General.ObtenerFMOVALG2(obj);
        call.enqueue(new Callback<ApiResponseObtenerFMOVALG2>() {

            @Override
            public void onResponse(Call<ApiResponseObtenerFMOVALG2> call, Response<ApiResponseObtenerFMOVALG2> response) {

                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {

                    List<DtoObtenerFMOVALG2Input> obj = response.body().getResult();
                    resultObtenerFMOVALG2LiveData.setValue(obj);


                } else {
                    String error = "Error en la respuesta ObtenerFMOVALG2: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerFMOVALG2> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud ObtenerFMOVALG2: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }


    // DESBLOQUEO
    public void DesbloquearRegistro(String codigoEtiqueta) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultDesbloquearRegistro.setValue(null);

        DtoDesbloquearRegistroOutput obj_DesbloquearRegistro = new DtoDesbloquearRegistroOutput();
        obj_DesbloquearRegistro.setCodigoEtiqueta(codigoEtiqueta);

        Call<ApiResponseGeneral> call = General.DesbloquearRegistro(obj_DesbloquearRegistro);
        call.enqueue(new Callback<ApiResponseGeneral>() {
            @Override
            public void onResponse(Call<ApiResponseGeneral> call, Response<ApiResponseGeneral> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseGeneral apiResponse = response.body();

                    int datos = Integer.parseInt(apiResponse.result);
                    resultDesbloquearRegistro.setValue(datos);
                    Log.d("API Response", "Registro Desbloqueado satisfactoriamente.");
                } else {
                    String error = "Error en la respuesta DesbloquearRegistro: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGeneral> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud DesbloquearRegistro: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }
    public void DesbloquearTABCORRE(int codcia) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultDesbloquearTABCORRE.setValue(null);


        DtoDesbloquearTABCORREOutput obj_DtoDesbloquearTABCORREOutput = new DtoDesbloquearTABCORREOutput();
        obj_DtoDesbloquearTABCORREOutput.setCodCia(codcia);


        // Llamar a la API
        Call<ApiResponseDesbloquearTABCORRE> call = General.DesbloquearTABCORRE(obj_DtoDesbloquearTABCORREOutput);
        call.enqueue(new Callback<ApiResponseDesbloquearTABCORRE>() {
            @Override
            public void onResponse(Call<ApiResponseDesbloquearTABCORRE> call, Response<ApiResponseDesbloquearTABCORRE> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseDesbloquearTABCORRE apiResponse = response.body();

                    int datos = Integer.parseInt(apiResponse.result);
                    resultDesbloquearTABCORRE.setValue(datos);

                } else {
                    String error = "Error en la respuesta DesbloquearTABCORRE: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseDesbloquearTABCORRE> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }
    public void DesbloquearFABCORRE(int codcia,int codalg,String tmvcor)
    {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultDesbloquearFABCORRE.setValue(null);
        DtoDesbloquearFABCORREOutput obj_DesbloquearFABCORREDtoOutput = new DtoDesbloquearFABCORREOutput();

        obj_DesbloquearFABCORREDtoOutput.setCodcia(codcia);
        obj_DesbloquearFABCORREDtoOutput.setCodalg(codalg);
        obj_DesbloquearFABCORREDtoOutput.setTmvcor(tmvcor);


        Call<ApiResponseDesbloquearFABCORRE> call = General.DesbloquearFABCORRE (obj_DesbloquearFABCORREDtoOutput);
        call.enqueue(new Callback<ApiResponseDesbloquearFABCORRE>() {
            @Override
            public void onResponse(Call<ApiResponseDesbloquearFABCORRE> call, Response<ApiResponseDesbloquearFABCORRE> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseDesbloquearFABCORRE apiResponse = response.body();

                    int datos = Integer.parseInt(apiResponse.getResult());
                    resultDesbloquearFABCORRE.setValue(datos);
                    Log.d("API Response", "Registro grabado satisfactoriamente.");
                } else {
                    String error = "Error en la respuesta GrabarAlta: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseDesbloquearFABCORRE> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }


   // PROCESO DE GUARDADO
    public void ProcesarGuardado(DtoProcesarGuardado request) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultProcesarGuardado.setValue(null);
        // Crear el objeto DTO de entrada para la API

        // Llamar a la API
        Call<ApiResponseProcesarGuardado> call = Alta.ProcesarGuardado (request);
        call.enqueue(new Callback<ApiResponseProcesarGuardado>() {
            @Override
            public void onResponse(Call<ApiResponseProcesarGuardado> call, Response<ApiResponseProcesarGuardado> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {

                    ApiResponseProcesarGuardado apiResponse = response.body();

                    int datos =apiResponse.getResult();
                   String mensaje = apiResponse.getMessage();
                    resultProcesarGuardado.setValue(mensaje);


                } else {
                    String error = "Error en la respuesta ObtenerDatosStockEmpaque: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseProcesarGuardado> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }
    public void GrabarAlta(String codigo,int codcia,
                           int codalg,String tmvmag,String nmvmag,String cscmag,int secma2,String codtmv,String cremag,String cencos,int ademag,String ucrmag,
                           String  caemag,String refere,String ctdor1,String anoor1,String nroor1,String cscor2,String fecmag,String pcName,String ncrma2

    ) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultGrabarAlta.setValue(null);
        // Crear el objeto DTO de entrada para la API
        DtoAltaOutput obj_AltaDtoOutput = new DtoAltaOutput();
        obj_AltaDtoOutput.setCodigo(codigo);
        obj_AltaDtoOutput.setCodcia(codcia);
        obj_AltaDtoOutput.setCodalg(codalg);
        obj_AltaDtoOutput.setTmvmag(tmvmag);
        obj_AltaDtoOutput.setNmvmag(Long.parseLong(nmvmag));
        obj_AltaDtoOutput.setCscmag(Integer.parseInt(cscmag));
        obj_AltaDtoOutput.setSecma2(secma2);
        obj_AltaDtoOutput.setCodtmv(codtmv);
        obj_AltaDtoOutput.setCremag(Double.parseDouble(cremag));
        obj_AltaDtoOutput.setCencos(Integer.parseInt(cencos));
        obj_AltaDtoOutput.setAdemag(ademag);
        obj_AltaDtoOutput.setUcrmag(ucrmag);
        obj_AltaDtoOutput.setCaemag((int) Double.parseDouble(caemag));
        obj_AltaDtoOutput.setRefere(Integer.parseInt(refere));
        obj_AltaDtoOutput.setCtdor1(ctdor1);
        obj_AltaDtoOutput.setAnoor1(anoor1 != null && !anoor1.isEmpty() ? Integer.parseInt(anoor1) : 0);
        obj_AltaDtoOutput.setNroor1(nroor1 != null && !nroor1.isEmpty() ? Integer.parseInt(nroor1) : 0);
        obj_AltaDtoOutput.setCscor2(cscor2 != null && !cscor2.isEmpty() ? Integer.parseInt(cscor2) : 0);
        obj_AltaDtoOutput.setFecmag(fecmag);
        obj_AltaDtoOutput.setPcname(pcName);
        obj_AltaDtoOutput.setNcrma2(ncrma2 != null && !ncrma2.isEmpty() ? Integer.parseInt(ncrma2) : 0);

        Call<ApiResponseGrabarAlta> call = Alta.GrabarAlta (obj_AltaDtoOutput);
        call.enqueue(new Callback<ApiResponseGrabarAlta>() {
            @Override
            public void onResponse(Call<ApiResponseGrabarAlta> call, Response<ApiResponseGrabarAlta> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseGrabarAlta apiResponse = response.body();

                    int datos = Integer.parseInt(apiResponse.getResult());
                    resultGrabarAlta.setValue(datos);
                    Log.d("API Response", "Registro grabado satisfactoriamente.");
                } else {
                    String error = "Error en la respuesta GrabarAlta: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGrabarAlta> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }
    public void GrabarODRPCON1(int codcia,String anoor1,String nroor1,int codalg,String fempc1,String pcName,String codtmv,String cencos,String ucrmag)
    {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultGrabarODRPCON1.setValue(null);
        DtoGrabarODRPCON1Output obj_AltaDtoOutput = new DtoGrabarODRPCON1Output();

        obj_AltaDtoOutput.setCodcia(codcia);
        obj_AltaDtoOutput.setCodalg(codalg);
        obj_AltaDtoOutput.setCodtmv(codtmv);
        obj_AltaDtoOutput.setCencos(Integer.parseInt(cencos));
        obj_AltaDtoOutput.setUcrmag(ucrmag);
        obj_AltaDtoOutput.setAnoor1(anoor1 != null && !anoor1.isEmpty() ? Integer.parseInt(anoor1) : 0);
        obj_AltaDtoOutput.setNroor1(nroor1 != null && !nroor1.isEmpty() ? Integer.parseInt(nroor1) : 0);
        obj_AltaDtoOutput.setPcname(pcName);
        obj_AltaDtoOutput.setFecmag(fempc1);

        Call<ApiResponseGrabarODRPCON1> call = Alta.GrabarODRPCON1 (obj_AltaDtoOutput);
        call.enqueue(new Callback<ApiResponseGrabarODRPCON1>() {
            @Override
            public void onResponse(Call<ApiResponseGrabarODRPCON1> call, Response<ApiResponseGrabarODRPCON1> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseGrabarODRPCON1 apiResponse = response.body();

                    int datos = Integer.parseInt(apiResponse.getResult());
                    resultGrabarODRPCON1.setValue(datos);
                    Log.d("API Response", "Registro grabado satisfactoriamente.");
                } else {
                    String error = "Error en la respuesta GrabarAlta: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGrabarODRPCON1> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }



    public void validarNroOrden(int orden, int codAlmacen) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultvalidarNroOrden.setValue(null);
        DtoValidarNroOrdenOutput obj=new DtoValidarNroOrdenOutput();
        obj.nroOrden=orden;
        obj.codAlmacen=codAlmacen;

        Call<ApiResponseValidarNroOrden> call = General.validarNroOrden (obj);
        call.enqueue(new Callback<ApiResponseValidarNroOrden>() {
            @Override
            public void onResponse(Call<ApiResponseValidarNroOrden> call, Response<ApiResponseValidarNroOrden> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseValidarNroOrden apiResponse = response.body();
                    resultvalidarNroOrden.setValue(apiResponse);
                } else {
                    String error = "Error en la respuesta ObtenerDatosStockEmpaque: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseValidarNroOrden> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void validarCarga(int orden, int carga) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultvalidarCarga.setValue(null);
        DtoValidarCargaOutput obj=new DtoValidarCargaOutput();
        obj.nroOrden=orden;
        obj.nroCarga=carga;

        Call<ApiResponseValidarEstadoCarga> call = General.validarEstadoCarga (obj);
        call.enqueue(new Callback<ApiResponseValidarEstadoCarga>() {
            @Override
            public void onResponse(Call<ApiResponseValidarEstadoCarga> call, Response<ApiResponseValidarEstadoCarga> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {

                    ApiResponseValidarEstadoCarga apiResponse = response.body();

                    resultvalidarCarga.setValue(apiResponse);


                } else {
                    String error = "Error en la respuesta ObtenerDatosStockEmpaque: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseValidarEstadoCarga> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }


    public void validarEstadoConsecutivo(int orden,String tipomovimiento,int consecutivo) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultvalidarEstadoConsecutivo.setValue(null);

        DtoValidarConsecutivoOutput obj=new DtoValidarConsecutivoOutput();
        obj.nroOrden=orden;
        obj.tipoMovimiento=tipomovimiento;
        obj.consecutivo=consecutivo;

        Call<ApiResponseValidarEstadoConcecutivo> call = General.validarEstadoConsecutivo(obj);
        call.enqueue(new Callback<ApiResponseValidarEstadoConcecutivo>() {
            @Override
            public void onResponse(Call<ApiResponseValidarEstadoConcecutivo> call, Response<ApiResponseValidarEstadoConcecutivo> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {

                    ApiResponseValidarEstadoConcecutivo apiResponse = response.body();

                    resultvalidarEstadoConsecutivo.setValue(apiResponse);


                } else {
                    String error = "Error en la respuesta ObtenerDatosStockEmpaque: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseValidarEstadoConcecutivo> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }

    public void obtenerUltimoMovimiento(DtoUltimoMovimientoInput dtoUltimoMovimientoInput){
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultObtenerUltimoMovimiento.setValue(null);

        Call<ApiResponseObtenerUltimoMovimiento> call = General.obtenerUltimoMovimiento(dtoUltimoMovimientoInput);
        call.enqueue(new Callback<ApiResponseObtenerUltimoMovimiento>() {
            @Override
            public void onResponse(Call<ApiResponseObtenerUltimoMovimiento> call, Response<ApiResponseObtenerUltimoMovimiento> response) {
                isLoading.setValue(false);
                if(response.isSuccessful() && response.body() != null){
                    ApiResponseObtenerUltimoMovimiento apiResponse = response.body();
                    resultObtenerUltimoMovimiento.setValue(apiResponse);
                }else{
                    String error = "Error en la respuesta ObtenetUltimoMovimiento: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseObtenerUltimoMovimiento> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }
}
