package com.example.fab083.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.fab083.R;
import com.example.fab083.api.ApiResponseEtiqueta;
import com.example.fab083.api.ApiResponseObtenerUltimoMovimiento;
import com.example.fab083.api.ApiResponseValidarEstadoCarga;
import com.example.fab083.api.ApiResponseValidarEstadoConcecutivo;
import com.example.fab083.api.ApiResponseValidarNroOrden;
import com.example.fab083.databinding.FragmentHomeBinding;
import com.example.fab083.dtos.DtoAlmacen;
import com.example.fab083.dtos.DtoAlmacenXCodigoInput;
import com.example.fab083.dtos.DtoCompañia;
import com.example.fab083.dtos.DtoObtenerCentroCostosInput;
import com.example.fab083.dtos.DtoObtenerCentroCostosXAlmacenInput;
import com.example.fab083.dtos.DtoObtenerCorelativoAlmacenInput;
import com.example.fab083.dtos.DtoObtenerDatosEtiqueta;
import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;
import com.example.fab083.dtos.DtoObtenerDatosStockEmpaqueInput;
import com.example.fab083.dtos.DtoObtenerFMOVALG2Input;
import com.example.fab083.dtos.DtoObtenerHoraSistemaInput;
import com.example.fab083.dtos.DtoObtenerInputTipoDocumentoInput;
import com.example.fab083.dtos.DtoObtenerRegistroFMOVALG2Input;
import com.example.fab083.dtos.DtoObtenerTipoMovTransferenciaInput;
import com.example.fab083.dtos.DtoProcesarGuardado;
import com.example.fab083.dtos.DtoProcesarlectura;
import com.example.fab083.dtos.DtoTipoDocumento;
import com.example.fab083.dtos.DtoUltimoMovimientoInput;
import com.example.fab083.dtos.DtoUltimoMovimientoOutput;
import com.example.fab083.dtos.DtoUtilizaRegistroCorrelativoAlmacenInput;
import com.example.fab083.dtos.DtoValidaUsoCorrelativoInput;
import com.example.fab083.dtos.DtoValidarAlamcenXCcostoInput;
import com.example.fab083.dtos.DtoValidarCargaInput;
import com.example.fab083.dtos.DtoValidarNroOrdenInput;
import com.example.fab083.model.CodigoAdapter;
import com.example.fab083.model.EtiquetasAdapter;
import com.example.fab083.ui.progres.ProgressDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.internal.Util;
import pl.droidsonroids.gif.GifDrawable;

public class AltaFragment extends Fragment implements CodigoAdapter.OnTotalesChangeListener {

    private DtoUltimoMovimientoOutput dtoUltimoMovimientoOutput = new DtoUltimoMovimientoOutput();
    private final Queue<String> colaCodigos = new LinkedList<>();
    ImageView imgLoading ;
    LinearLayout layoutLoading;
    private boolean estaProcesando = false;
    private ProgressDialogFragment progressDialog;
    private String List_CodExis;
    private FragmentHomeBinding binding;
    private AutoCompleteTextView ddlCompania, ddlAlmacenOrigen, ddlCentroCosto, ddlAlmacenDestino;
    private AutoCompleteTextView ddlTipoDocumento;
    private ArrayAdapter<DtoCompañia> adapterItems;
    private ArrayAdapter<DtoAlmacen> adapterAlmacenOrigen;
    private ArrayAdapter<DtoAlmacen> adapterAlmacenDestino;
    private ArrayAdapter<DtoObtenerCentroCostosInput> adapterCentroCosto;
    private ArrayAdapter<DtoObtenerCentroCostosXAlmacenInput> adapterCentroCostoXalmacen;
    private ArrayAdapter<DtoTipoDocumento> adapterTipoDocumento;
    private ArrayAdapter<DtoObtenerTipoMovTransferenciaInput> adapterTipoTipoMovTransferenciaInput;
    private ArrayAdapter<DtoAlmacenXCodigoInput> adapterObtenerAlmacenXCodigoInput;
    private List<DtoObtenerDatosStockEmpaqueInput> ListaDatosObtenerDatosStockEmpaque;
    private ProgressBar progressBar;
    private TextInputLayout txtTipoDocLayout, layoutconsecutivo, layoutnrocarga, compania_layout, tipo_layout,
            almacenOrigen_layout, almacenDestino_layout, centroCosto_layout;
    //private MaterialTextView textTituloInputAlmacenDestino;
    private TextView textViewCodigoAlmacen, textViewNombreAlmacen, textViewRequiereNroOrden, txtproceso, txtnrocargamaximo,
            textViewRequiereCscOrden, textViewtipoDocumento, textVierequiereNroCarga, textVierecentroCosto, dialog_title;
    private TextInputEditText txtTipoDoc, TextInputEditTextCodigo, TextInputEditTextNota, TextInputEditTextSeleccionados,
            txtanio, txtnroorden, txtconsecutivo, txtnrocarga, txtarticulo, txtTotalConos, txtTotalKg;
    private RadioButton radioButtonSalida, radioButtonTransferencia, radioButtonTotal,
            radioButtonParcial, radioButtonProducto, radioButtonEnvace;
    private RadioGroup radioGroupDescargue, radioGroupTipoMov;
    private RecyclerView recyclerView;
    private CodigoAdapter codigoAdapter;
    private TextInputEditText dateInput;
    //private Boolean datosRecibidos;
    private boolean isTextUpdating = false;
    boolean isKeyboardVisible = false;
    private MaterialButton btnGuardar, btnborrartodo;
    private View view;
    ImageView txtcancelar;
    DecimalFormat df;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Configurar el separador decimal como punto
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        df = new DecimalFormat("#.###",symbols);

        //View
        AltaViewModel altaViewModel = new ViewModelProvider(this).get(AltaViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Funciones de inicializacion
        variable(altaViewModel, inflater, container, root);
        SeleccionarCboAlmacenOrigen(altaViewModel);
        EliminarItem(altaViewModel);
        BorrarTodo(altaViewModel);

        VerificarDatosGrabados();
        ConfigurarIdioma();
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return root;
    }

    private void ConfigurarIdioma(){
        // Configurar el idioma en español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        getResources().getConfiguration().setLocale(locale);
        getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
    }

    public void VerificarDatosGrabados(){
        //Datos grabados
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
        String listaJson2 = sharedPreferences.getString("listaRecyclerView", "");

        //Extrae informacion de la data guardada
        if(listaJson2 != null && !listaJson2.isEmpty()) {

            try {
                JSONArray jsonArray = new JSONArray(listaJson2);
                EtiquetasAdapter etiquetasAdapter = new EtiquetasAdapter();
                etiquetasAdapter.convertJsonArray(jsonArray).forEach(
                        element -> codigoAdapter.addValoresGuardados(element)
                );
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            int cantidadItems = codigoAdapter.cantidadItems();
            TextInputEditTextSeleccionados.setText(String.valueOf(cantidadItems));

            txtTotalConos.setText(String.valueOf(codigoAdapter.cantidadConos()));
            txtTotalKg.setText(df.format(codigoAdapter.cantidadKg()));
            if(cantidadItems > 0) {
                compania_layout.setEnabled(false);
                tipo_layout.setEnabled(false);
                almacenOrigen_layout.setEnabled(false);
                centroCosto_layout.setEnabled(false);

                ddlCompania.setEnabled(false);
                ddlAlmacenOrigen.setEnabled(false);
                ddlTipoDocumento.setEnabled(false);
                ddlCentroCosto.setEnabled(false);

            }else{
                BorrarDatosGuardados();
                compania_layout.setEnabled(true);
                tipo_layout.setEnabled(true);
                almacenOrigen_layout.setEnabled(true);
                centroCosto_layout.setEnabled(true);

                ddlCompania.setEnabled(true);
                ddlAlmacenOrigen.setEnabled(true);
                ddlTipoDocumento.setEnabled(true);
                ddlCentroCosto.setEnabled(true);
            }
        }
    }

    public void EliminarItem(AltaViewModel altaViewModel) {
        // Configurar el ItemTouchHelper para el deslizamiento lateral
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; // No necesitas mover elementos
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Obtener la posición del elemento deslizado
                int position = viewHolder.getAdapterPosition();
            /*String codigoItem = codigoAdapter.codigoList.get(position);

            // Separar la cadena por ":" para obtener el número de etiqueta (asumido como el primer valor)
            String[] codigoPartes = codigoItem.split(":");*/
                String numeroEtiqueta = codigoAdapter.codigoList.get(position).getCodigo();

                // Inflar el layout personalizado para el diálogo
                LayoutInflater inflater = LayoutInflater.from(requireContext());
                View dialogView = inflater.inflate(R.layout.dialog_confirm_delete, null);
                TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);

                // Asignar el texto dinámicamente
                dialogMessage.setText("¿Estás seguro de que deseas eliminar la etiqueta " + numeroEtiqueta + "?");

                // Crear el diálogo con el template
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setView(dialogView)
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Eliminar el elemento de la lista y notificar al adaptador
                                codigoAdapter.codigoList.remove(position);
                                TextInputEditTextSeleccionados.setText(String.valueOf(codigoAdapter.cantidadItems()));

                                txtTotalConos.setText(String.valueOf(codigoAdapter.cantidadConos()));
                                txtTotalKg.setText(df.format(codigoAdapter.cantidadKg()));
                                int cantidadItem = codigoAdapter.cantidadItems();
                                if(cantidadItem == 0) {
                                    dateInput.setEnabled(true);
                                    EliminarCabecera();
                                    ddlCompania.setEnabled(true);
                                    ddlAlmacenOrigen.setEnabled(true);
                                    ddlTipoDocumento.setEnabled(true);
                                    ddlCentroCosto.setEnabled(true);

                                    compania_layout.setEnabled(true);
                                    tipo_layout.setEnabled(true);
                                    almacenOrigen_layout.setEnabled(true);
                                    centroCosto_layout.setEnabled(true);

                                }
                                altaViewModel.DesbloquearRegistro(numeroEtiqueta);
                                codigoAdapter.notifyItemRemoved(position);

                                EliminarListaGuardada();
                                AgregarLista();

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Si se cancela, restablecer el elemento en la posición original
                                codigoAdapter.notifyItemChanged(position);
                            }
                        })
                        .create()
                        .show();
            }
        };
        // Añadir el ItemTouchHelper al RecyclerView
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void EliminarListaGuardada() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("listaRecyclerView"); // Esto elimina la entrada
        editor.apply(); // Confirmar los cambios
    }

    public void EliminarCabecera() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("codigoTipoMov"); // Esto elimina la entrada
        editor.apply(); // Confirmar los cambios
    }

    public void AgregarLista() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String listaJson = gson.toJson(codigoAdapter.codigoList);  // Convertir la lista a JSON
        editor.putString("listaRecyclerView", listaJson);
        editor.apply();
    }

    public void variable(AltaViewModel altaViewModel, LayoutInflater inflater, ViewGroup container, View root) {
        //Instancia con los controles
        txtnrocargamaximo = root.findViewById(R.id.txtnrocargamaximo);
        txtproceso = root.findViewById(R.id.txtproceso);
        progressBar = root.findViewById(R.id.progress_bar);
        progressDialog = new ProgressDialogFragment();
        ddlCompania = root.findViewById(R.id.auto_complete_txt);
        ddlAlmacenOrigen = root.findViewById(R.id.ddlAlmacenOrigen);
        ddlAlmacenDestino = root.findViewById(R.id.dllAlmacenDestino);
        ddlCentroCosto = root.findViewById(R.id.ddlCentroCosto);
        ddlTipoDocumento = root.findViewById(R.id.ddlTipo);
        textViewCodigoAlmacen = root.findViewById(R.id.textViewCodigoAlmacen);
        textViewNombreAlmacen = root.findViewById(R.id.textViewNombreAlmacen);
        textViewRequiereNroOrden = root.findViewById(R.id.textViewRequiereNroOrden);
        textViewRequiereCscOrden = root.findViewById(R.id.textViewRequiereCscOrden);
        textViewtipoDocumento = root.findViewById(R.id.textViewtipoDocumento);
        textVierequiereNroCarga = root.findViewById(R.id.textVierequiereNroCarga);
        textVierecentroCosto = root.findViewById(R.id.textVierecentroCosto);
        TextInputEditTextCodigo = root.findViewById(R.id.TextInputEditTextCodigo);
        TextInputEditTextNota = root.findViewById(R.id.TextInputEditTextNota);
        TextInputEditTextSeleccionados = root.findViewById(R.id.TextInputEditTextSeleccionados);
        txtTotalConos = root.findViewById(R.id.txtTotalConos);
        txtTotalKg = root.findViewById(R.id.txtTotalKg);
        txtanio = root.findViewById(R.id.txtanio);
        txtnroorden = root.findViewById(R.id.txtnroorden);
        txtconsecutivo = root.findViewById(R.id.txtconsecutivo);
        txtnrocarga = root.findViewById(R.id.txtnrocarga);
        txtarticulo = root.findViewById(R.id.txtarticulo);
        layoutconsecutivo = root.findViewById(R.id.layoutconsecutivo);
        layoutnrocarga = root.findViewById(R.id.layoutnrocarga);
        //dialog_title = root.findViewById(R.id.dialog_title);
        btnGuardar = root.findViewById(R.id.btnguardar);
        imgLoading = root.findViewById(R.id.imgLoading);
        layoutLoading = root.findViewById(R.id.layoutLoading);
        btnborrartodo = root.findViewById(R.id.btnborrartodo);
        compania_layout = root.findViewById(R.id.compania_layout);
        tipo_layout = root.findViewById(R.id.tipo_layout);
        almacenOrigen_layout = root.findViewById(R.id.almacenOrigen_layout);
        almacenDestino_layout = root.findViewById(R.id.almacenDestino_layout);
        centroCosto_layout = root.findViewById(R.id.centroCosto_layout);
        radioGroupTipoMov = root.findViewById(R.id.radioGroup);
        dateInput = root.findViewById(R.id.TextInputEditTextFecha);
        txtTipoDocLayout = root.findViewById(R.id.TextInputLayoutTipo);
        txtTipoDoc = txtTipoDocLayout.findViewById(R.id.textInputEditTextTipo);
        RadioGroup radioGroup = root.findViewById(R.id.radioGroup);
        radioGroupDescargue = root.findViewById(R.id.radioGroupDescargue);
        radioButtonSalida = root.findViewById(R.id.radioButtonSalida);
        radioButtonTransferencia = root.findViewById(R.id.radioButtonTransferencia);
        radioButtonTotal = root.findViewById(R.id.rbTotal);
        radioButtonParcial = root.findViewById(R.id.rbParcial);
        radioButtonProducto = root.findViewById(R.id.rbProducto);
        radioButtonEnvace = root.findViewById(R.id.rbEnvace);
        TextInputLayout TextInputLayoutCodigo = root.findViewById(R.id.TextInputLayoutCodigo);
        txtcancelar = root.findViewById(R.id.txtcancelar);
        almacenDestino_layout = root.findViewById(R.id.almacenDestino_layout);
        //RadioButton
        //textTituloInputAlmacenDestino = root.findViewById(R.id.textTituloInputAlmacenDestino);
        //TextInputLayout txtAlmacenOrigen = root.findViewById(R.id.txtAlmacenOrigen);
        view = inflater.inflate(R.layout.alert, container, false);

        // Obtén la fecha actual
        Calendar fec = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));

        // Verifica si la hora actual está entre las 00:00 AM y las 07:00 AM (Turno noche)
        int hourOfDay = fec.get(Calendar.HOUR_OF_DAY);
        if (hourOfDay >= 0 && hourOfDay < 7) {
            // Resta un día a la fecha actual
            fec.add(Calendar.DAY_OF_MONTH, -1);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(fec.getTime());

        // Establece la fecha actual en el TextInputEditText
        dateInput.setText(currentDate);

        ddlCompania.setVisibility(View.GONE);
        almacenDestino_layout.setVisibility(View.GONE);
        //textTituloInputAlmacenDestino.setVisibility(View.GONE);
        radioButtonSalida.setChecked(true);

        CargarCboCompania(altaViewModel);
        ConfigurarRadioGroupTipoMov(radioGroup, altaViewModel);
        ConfigurarRadioGroupDescargue(radioGroupDescargue, altaViewModel);
        Guardar(altaViewModel);
        Cancelar(altaViewModel);
        ConfigurarNroOrden(altaViewModel);
        ConfigurarCarga(altaViewModel);
        ConfigurarConsecutivo(altaViewModel);
        // Configurar el RecyclerView y el adaptador
        recyclerView = root.findViewById(R.id.contactListId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        codigoAdapter = new CodigoAdapter(TextInputEditTextCodigo, this);
        recyclerView.setAdapter(codigoAdapter);

        inflater.inflate(R.layout.custom_dialog, container, false);

        dateInput.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();

            String dateText = dateInput.getText().toString();
            if(!dateText.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
                    Date date = sdf.parse(dateText);
                    if(date != null) {
                        calendar.setTime(date);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Crea el DatePickerDialog con la fecha actual o la fecha del campo de texto
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    v.getContext(),
                    (view, year, month, dayOfMonth) -> {
                        // Establece la fecha seleccionada en el EditText
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
                        dateInput.setText(sdf.format(calendar.getTime()));
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            // Muestra el DatePickerDialog
            datePickerDialog.show();
        });

        // Configura el TextWatcher para manejar el cambio de texto
        dateInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No hacer nada antes de que el texto cambie
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Puedes realizar acciones mientras se está escribiendo si lo deseas
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Puedes realizar acciones después de que el texto haya cambiado
                // Define la zona horaria de Perú
                TimeZone peruTimeZone = TimeZone.getTimeZone("America/Lima");
                Calendar calendar = Calendar.getInstance(peruTimeZone);
                Date fechaActual = calendar.getTime();
                String fechaInputString = dateInput.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
                dateFormat.setTimeZone(TimeZone.getTimeZone("America/Lima"));
                Date fechaInput = null;
                try {
                    fechaInput = dateFormat.parse(fechaInputString);
                } catch (Exception e) {
                    e.printStackTrace();  // Manejar la excepción si la conversión falla
                }

                if(fechaInput != null) {
                    if(fechaInput.after(fechaActual)) {
                        // La fecha del DateInput es mayor que la fecha actual
                        mostrarDialog("La fecha del movimiento no puede ser mayor a la fecha actual");

                        dateInput.setText("");
                    }else{}
                }else{
                    mostrarDialog("Fecha ingresada no válida.");
                }
            }
        });

        // Configura el TextWatcher para manejar el cambio de texto
        TextInputEditTextCodigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No hacer nada antes de que el texto cambie
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Puedes realizar acciones mientras se está escribiendo si lo deseas
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Solo procesa el texto si no está siendo actualizado programáticamente
                String codigo = TextInputEditTextCodigo.getText().toString();
                if(!isKeyboardVisible) {
                    if(!isTextUpdating) {
                        if(!codigo.isEmpty()) {
                            agregarCodigoACola(altaViewModel, codigo);
                            //lecturaEtiqueta(altaViewModel, codigo);
                            limpiarTexto();
                        }
                    }
                }else{

                }
            }
        });

        TextInputEditTextCodigo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Detecta si se ha presionado la acción "Done" (o similar)
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    String codigo = TextInputEditTextCodigo.getText().toString();

                    // Asegúrate de que el campo no esté vacío antes de ejecutar el proceso
                    if(!codigo.isEmpty()) {
                        //lecturaEtiqueta(altaViewModel, codigo);
                        agregarCodigoACola(altaViewModel, codigo);
                        limpiarTexto();  // Limpiar el texto después de la lectura
                    }else{
                        mostrarDialog("El código no puede estar vacío");
                    }
                    return true;  // Indica que el evento ha sido manejado
                }
                return false;
            }
        });

        // Esto deshabilita la apertura automática del teclado cuando se obtiene el foco
        TextInputEditTextCodigo.setShowSoftInputOnFocus(false);


        // Configura el OnClickListener para el ícono del teclado
        TextInputLayoutCodigo.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isKeyboardVisible) {
                    // Mostrar el teclado
                    TextInputEditTextCodigo.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(TextInputEditTextCodigo, InputMethodManager.SHOW_IMPLICIT);

                    // Cambiar el ícono a la nueva imagen
                    TextInputLayoutCodigo.setEndIconDrawable(getResources().getDrawable(R.drawable.tecladopc));

                    isKeyboardVisible = true; // Actualizar el estado
                }else{
                    // Ocultar el teclado
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(TextInputEditTextCodigo.getWindowToken(), 0);

                    // Cambiar el ícono de nuevo al teclado
                    TextInputLayoutCodigo.setEndIconDrawable(getResources().getDrawable(R.drawable.qrfinal));
                    TextInputEditTextCodigo.setText("");
                    isKeyboardVisible = false; // Actualizar el estado

                }
            }


        });


        radioButtonTotal.setChecked(true);
    }

    public void ConfigurarConsecutivo(AltaViewModel altaViewModel) {
        txtconsecutivo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {

                    int consecutivo = txtconsecutivo.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtconsecutivo.getText().toString());
                    int orden = txtnroorden.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnroorden.getText().toString());
                    String tipomovimiento = txtTipoDoc.getText().toString();

                    validarEstadoConsecutivo(altaViewModel, orden, tipomovimiento, consecutivo);

                    return true;
                }
                return false;
            }
        });

        txtconsecutivo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {

                    int consecutivo = txtconsecutivo.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtconsecutivo.getText().toString());
                    int orden = txtnroorden.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnroorden.getText().toString());
                    String tipomovimiento = txtTipoDoc.getText().toString();

                    validarEstadoConsecutivo(altaViewModel, orden, tipomovimiento, consecutivo);
                }
            }
        });
    }

    private void obtenerUltimoMovimiento(AltaViewModel altaViewModel, DtoUltimoMovimientoInput dtoUltimoMovimientoInput){
        altaViewModel.getResponseObtenerUltimoMovimientoLiveData().removeObservers(getViewLifecycleOwner());
        altaViewModel.obtenerUltimoMovimiento(dtoUltimoMovimientoInput);
        altaViewModel.getResponseObtenerUltimoMovimientoLiveData().observe(getViewLifecycleOwner(), result -> ResultadoUltimoMovimiento(altaViewModel, result));
    }
    private void ResultadoUltimoMovimiento(AltaViewModel altaViewModel, ApiResponseObtenerUltimoMovimiento result){
        if(result != null){
            boolean resultSuccess = result.isSuccess();
            String mensaje = result.getMessage();
            dtoUltimoMovimientoOutput = result.getResult();
            if(resultSuccess){
                if(dtoUltimoMovimientoOutput == null){
                    mostrarDialog(mensaje);
                }
            }else{
                mostrarDialog(mensaje);
            }
        }
    }

    public void validarEstadoConsecutivo(AltaViewModel altaViewModel, int orden, String tipomovimiento, int consecutivo) {
        altaViewModel.getRespuestaValidarEstadoConcecutivoLiveData().removeObservers(getViewLifecycleOwner());
        altaViewModel.validarEstadoConsecutivo(orden, tipomovimiento, consecutivo);
        altaViewModel.getRespuestaValidarEstadoConcecutivoLiveData().observe(getViewLifecycleOwner(), result -> ResultadoEstadoConsecutivo(altaViewModel, result));

    }

    public void ResultadoEstadoConsecutivo(AltaViewModel altaViewModel, ApiResponseValidarEstadoConcecutivo result) {

        if(result != null) {
            boolean resultSuccess = result.isSuccess();
            String mensaje = result.getMessage();
            String resultado = result.getResult();
            String xrnoalg = textViewRequiereNroOrden.getText().toString();
            String xrcoalg = textViewRequiereCscOrden.getText().toString();


            if(xrnoalg.equals("S") && xrcoalg.equals("N")) {


            }else if(xrnoalg.equals("S") && xrcoalg.equals("S")) {


                if(txtnroorden.getText().toString().length() == 0) {
                    return;
                }

                if(txtconsecutivo.getText().toString().trim().length() == 0) {
                    return;
                }

                if(resultado.equals("N")) {

                    mostrarDialog(mensaje);
                    txtconsecutivo.setText("");

                }else{
                    txtconsecutivo.setEnabled(false);
                    DtoUltimoMovimientoInput dtoUltimoMovimientoInput = new DtoUltimoMovimientoInput();
                    dtoUltimoMovimientoInput.setNroOrden(Integer.parseInt(txtnroorden.getText().toString()));
                    dtoUltimoMovimientoInput.setNroCsc(Integer.parseInt(txtconsecutivo.getText().toString()));

                    obtenerUltimoMovimiento(altaViewModel,dtoUltimoMovimientoInput);
                    //txtarticulo.requestFocus();
                }

            }else{
                txtconsecutivo.setEnabled(false);
                // txtarticulo.requestFocus();
                DtoUltimoMovimientoInput dtoUltimoMovimientoInput = new DtoUltimoMovimientoInput();
                dtoUltimoMovimientoInput.setNroOrden(Integer.parseInt(txtnroorden.getText().toString()));
                dtoUltimoMovimientoInput.setNroCsc(Integer.parseInt(txtconsecutivo.getText().toString()));

                obtenerUltimoMovimiento(altaViewModel,dtoUltimoMovimientoInput);
            }
        }

    }

    public void ConfigurarCarga(AltaViewModel altaViewModel) {
        txtnrocarga.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    int carga = txtnrocarga.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnrocarga.getText().toString());
                    int cargamaxima = txtnrocargamaximo.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnrocargamaximo.getText().toString());
                    int orden = txtnroorden.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnroorden.getText().toString());

                    if(carga > cargamaxima) {
                        mostrarDialog("El Nº de carga ingresado " + carga + " es mayor al Nº carga permitida " + cargamaxima);
                        txtnrocarga.setText("");
                    }else{
                        validarEstadoCarga(altaViewModel, orden, carga);
                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nroCarga", txtnrocarga.getText().toString());
                        //editor.putString("nroOrden", txtnroorden.getText().toString());

                        editor.apply();

                        TextInputEditTextCodigo.requestFocus();
                    }
                    return true;
                }
                return false;
            }
        });

        txtnrocarga.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(!txtnrocarga.getText().toString().isEmpty()){
                        int carga = txtnrocarga.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnrocarga.getText().toString());
                        int cargamaxima = txtnrocargamaximo.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnrocargamaximo.getText().toString());
                        int orden = txtnroorden.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnroorden.getText().toString());
                        if(carga > cargamaxima) {
                            mostrarDialog("El Nº de carga ingresado " + carga + " es mayor al Nº carga permitida " + cargamaxima);
                            txtnrocarga.setText("");
                        }else{
                            validarEstadoCarga(altaViewModel, orden, carga);

                            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("nroCarga", txtnrocarga.getText().toString());
                            //editor.putString("nroOrden", txtnroorden.getText().toString());

                            editor.apply();
                            TextInputEditTextCodigo.requestFocus();
                        }
                    }
                }
            }
        });
    }


    public void validarEstadoCarga(AltaViewModel altaViewModel, int orden, int carga) {
        altaViewModel.getresultvalidarEstadoCarga().removeObservers(getViewLifecycleOwner());
        altaViewModel.validarCarga(orden, carga);
        altaViewModel.getresultvalidarEstadoCarga().observe(getViewLifecycleOwner(), result -> ResultadoEstadoCArga(altaViewModel, result));
    }

    public void ResultadoEstadoCArga(AltaViewModel altaViewModel, ApiResponseValidarEstadoCarga result) {

        if(result != null) {
            boolean resultSuccess = result.isSuccess();
            String mensaje = result.getMessage();
            List<DtoValidarCargaInput> listaCC = result.getResult();

            if(!resultSuccess) {
                mostrarDialog(mensaje);
                txtnrocarga.setText("");
            }else{
                txtnrocarga.setEnabled(false);
            }
        }
    }

    public void ConfigurarNroOrden(AltaViewModel altaViewModel) {
        txtnroorden.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    String codAlm = ddlAlmacenOrigen.getText().toString().split(" - ")[0];

                    if(codAlm == null || codAlm.trim().isEmpty()){
                        mostrarDialog("Debe seleccionar el Almacen de Origen");
                        return false;
                    }else {
                        txtnrocarga.setText("");
                        int orden = txtnroorden.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnroorden.getText().toString());
                        int codAlmacen = Integer.parseInt(ddlAlmacenOrigen.getText().toString().split(" - ")[0]);

                        validarNroOrden(altaViewModel, orden, codAlmacen);

                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nroOrden", txtnroorden.getText().toString());
                        //editor.putString("nroCarga", txtnrocarga.getText().toString());
                        editor.apply();

                        txtnrocarga.requestFocus();
                        return true;
                    }
                }
                return false;
            }
        });

        txtnroorden.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    String codAlm = ddlAlmacenOrigen.getText().toString().split(" - ")[0];

                    if(codAlm == null || codAlm.trim().isEmpty()){
                        mostrarDialog("Debe seleccionar el Almacen de Origen");
                    }else {
                        txtnrocarga.setText("");
                        int orden = txtnroorden.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnroorden.getText().toString());
                        int codAlmacen = Integer.parseInt(ddlAlmacenOrigen.getText().toString().split(" - ")[0]);
                        validarNroOrden(altaViewModel, orden, codAlmacen);
                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nroOrden", txtnroorden.getText().toString());
                        //editor.putString("nroCarga", txtnrocarga.getText().toString());

                        editor.apply();
                        txtnrocarga.requestFocus();
                    }
                }
            }
        });
    }

    public void validarNroOrden(AltaViewModel altaViewModel, int orden, int codAlmacen) {
        altaViewModel.getresultvalidarNroOrden().removeObservers(getViewLifecycleOwner());
        altaViewModel.validarNroOrden(orden, codAlmacen);
        altaViewModel.getresultvalidarNroOrden().observe(getViewLifecycleOwner(), result -> ResultadovalidarNroOrden(altaViewModel, result));

    }

    public void ResultadovalidarNroOrden(AltaViewModel altaViewModel, ApiResponseValidarNroOrden result) {
        txtproceso.setText("");
        txtnrocargamaximo.setText("");
        if(result != null) {
            boolean resultSuccess = result.isSuccess();
            String mensaje = result.getMessage();
            List<DtoValidarNroOrdenInput> listaCC = result.getResult();

            if(resultSuccess) {
                int estadoOrden = Integer.parseInt(listaCC.get(0).getEstadoOrden());
                int codAlmacen = Integer.parseInt(ddlAlmacenOrigen.getText().toString().split(" - ")[0]);

                //if(estadoOrden < 2) {
                    txtanio.setText(listaCC.get(0).getAnio());
                    txtnrocargamaximo.setText(listaCC.get(0).getNroCarga());
                    txtproceso.setText(listaCC.get(0).getCodigoProceso());
                    txtarticulo.setText(listaCC.get(0).getDescripcionArticulo());
                    List_CodExis = listaCC.get(0).getList_CodExis();
                    txtnroorden.setEnabled(false);

                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nroOrden", txtnroorden.getText().toString());
                    editor.putString("nroCarga", txtnrocarga.getText().toString());

                    editor.apply();

                    txtnrocarga.requestFocus();
                /*}else{
                    mostrarDialog("No se puede realizar el movimiento ,ya que el Nº Orden esta cerrado.");
                    txtnroorden.setText("");
                }*/
            }else{
                mostrarDialog(mensaje);
                txtnroorden.setText("");
            }
        }
    }

    private void limpiarTexto() {
        isTextUpdating = true; // Activa la bandera para evitar que el TextWatcher procese el cambio
        TextInputEditTextCodigo.setText(""); // Limpia el campo
        isTextUpdating = false; // Desactiva la bandera después de limpiar
    }

    public String obtenerNombreDelDispositivo(Context context) {

        //return "test";
        String nameEquipo = "";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            nameEquipo =  Settings.Global.getString(context.getContentResolver(), Settings.Global.DEVICE_NAME);
        }else{
            nameEquipo =  Settings.Secure.getString(context.getContentResolver(), "bluetooth_name");
        }

        if(nameEquipo.length() > 15){
            SharedPreferences preferences = requireContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
            nameEquipo =preferences.getString("usuario", "");
        }

        return  nameEquipo;
    }

    public boolean ValidarFechaProceso(Context context, String FechaProceso) {
        String fechaInputString = dateInput.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        boolean respuesta = false;

        try {
            Date fechaInput = dateFormat.parse(fechaInputString);  // La fecha ingresada
            Date fechaProceso = dateFormat.parse(FechaProceso);    // La fecha de proceso

            if(fechaInput != null && fechaProceso != null) {
                // Fecha de proceso es anterior o igual a la fecha de entrada
                // if (fechaProceso.compareTo(fechaInput) <= 0) // fechaProceso es menor o igual a fechaInput
                //{
                // if(dateFormat.format(fechaInput).compareTo(dateFormat.format(fechaProceso)) <= 0)

                if(fechaProceso.compareTo(fechaInput) <= 0) {

                    // Deshabilitar el elemento
                    dateInput.setEnabled(false);
                    respuesta = true;


                }else{
                    // Mostrar mensaje y enfocar el elemento
                    new AlertDialog.Builder(context).setTitle("Movimiento Almacen").setMessage("No es posible utilizar el item seleccionado porque en la fecha del movimiento, el item no figura con stock en el almacén").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.ok, null).show();
                    dateInput.requestFocus();
                    respuesta = false;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // En caso de error en el parsing o valores nulos, devolver false como valor predeterminado
        return respuesta;
    }

    public void validarRegistroFMOVALG2(AltaViewModel altaViewModel, String codigoEtiqueta) {

        try {
            altaViewModel.ValidarRegistroFMOVALG2(codigoEtiqueta);
            altaViewModel.getListaDatosFMOVALG2LiveData().observe(getViewLifecycleOwner(), new Observer<List<DtoObtenerRegistroFMOVALG2Input>>() {
                @Override
                public void onChanged(List<DtoObtenerRegistroFMOVALG2Input> lista) {
                    try {
                        if(lista != null && !lista.isEmpty()) {
                            Log.d("API ValidarRegistroFMOVALG2", "Si Hay datos");


                        }else{
                            Log.d("API ValidarRegistroFMOVALG2", "No se recibieron datos.");

                        }
                    } catch (Exception e) {
                        // Manejo de cualquier excepción inesperada dentro del Observer
                        Log.e("API ValidarRegistroFMOVALG2 ", "Error al procesar la lista de datos.", e);

                    }
                }
            });
        } catch (Exception e) {
            // Manejo de cualquier excepción inesperada al validar el registro
            Log.e("API Response ", "Error en la validación del registro FMOVALG2.", e);
        }
    }

    public void lecturaEtiqueta(AltaViewModel altaViewModel, String codigo) {
        // progressBar.setVisibility(View.VISIBLE);
        progressDialog.show(getParentFragmentManager(), "progress");

        String codigoEtiqueta = obtenerCodigoFinal(codigo); //
        String pcName = obtenerNombreDelDispositivo(getContext());
        String fechaIngresada = dateInput.getText().toString();
        String codcompania = ddlCompania.getText().toString().split(" - ")[0];
        String almacen = ddlAlmacenOrigen.getText().toString().split(" - ")[0];
        //Integer nroOrden = Integer.parseInt(txtnroorden.getText().toString());

        DtoProcesarlectura request = new DtoProcesarlectura();
        request.etiqueta = codigoEtiqueta;
        request.pcName = pcName;
        request.fechaIngresada = fechaIngresada;
        request.codcompania = codcompania;
        request.almacen = almacen;
        //request.nroOrden = nroOrden;
        request.ListCodExis = this.List_CodExis;

        boolean rspValidarCampos;
        rspValidarCampos = validarCamposLeerEtiquetas();

        if(!rspValidarCampos) {
            //ProcesoLecturaEtiqueta(altaViewModel,request);
            int posicionBusqueda = codigoAdapter.BuscarCodigo(codigoEtiqueta);
            if(posicionBusqueda >= 0) {
                mostrarDialog("Etiqueta ya esta agregada a la lista");

                recyclerView.scrollToPosition(posicionBusqueda);
                codigoAdapter.highlightItem(posicionBusqueda);

                progressDialog.dismiss();
                estaProcesando = false;
                procesarSiguienteCodigo(altaViewModel);
            }else{
                ProcesoLecturaEtiqueta(altaViewModel, request);
                progressDialog.dismiss();
            }
        }else{
            if(progressDialog != null) {
                progressDialog.dismiss();
            }
            estaProcesando = false;
            procesarSiguienteCodigo(altaViewModel);
        }
        /*altaViewModel.getBloquearRegistro().removeObservers(getViewLifecycleOwner());
        altaViewModel.BloquearRegistro(codigoFinal, pcName);
        altaViewModel.getBloquearRegistro().observe(getViewLifecycleOwner(), result -> manejarResultadoBloqueo(result, altaViewModel, codigo, pcName));
        */

        // Procesar el siguiente código en la cola
        //procesarSiguienteCodigo(altaViewModel);
    }

    public void ProcesoLecturaEtiqueta(AltaViewModel altaViewModel, DtoProcesarlectura request) {
        // Borra el valor anterior para evitar recibir respuestas antiguas
        altaViewModel.limpiarRespuestaEtiqueta();
        altaViewModel.getRespuestaEtiquetaLiveData().removeObservers(getViewLifecycleOwner());
        altaViewModel.ProcesoLecturaEtiqueta(request);
        altaViewModel.getRespuestaEtiquetaLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                manejarResultadoProcesoLecturaEtiqueta(altaViewModel, result);
                // Limpia el LiveData después de procesarlo
                altaViewModel.limpiarRespuestaEtiqueta();
            }
        } /*manejarResultadoProcesoLecturaEtiqueta(altaViewModel, result)*/
        );
    }

    public void manejarResultadoProcesoLecturaEtiqueta(AltaViewModel altaViewModel, ApiResponseEtiqueta result) {
        try {
            if(result != null & estaProcesando) {
                estaProcesando = false;
                boolean resultSuccess = result.isSuccess();
                String mensaje = result.getMessage();
                List<DtoObtenerDatosEtiquetaInput> listaCC = result.getResult();

                if(resultSuccess) {
                    Queue<String> abc =  colaCodigos;
                    String fechaFormateada = formatearFecha(listaCC.get(0).getFecmag());

                    if(!ValidarFechaProceso(getContext(), fechaFormateada)) {
                        altaViewModel.DesbloquearRegistro(listaCC.get(0).getCodigo());
                        // Ocultar el ProgressDialog
                        // validacion de loading
                        if(progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        return;
                    }else{
                        // verifica si el codigo ya esta agregado a la listaa
                        if(!codigoAdapter.contains(listaCC.get(0).getCodigo())) {
                            final boolean[] confirmacion = {true};

                            try {
                                int codAlmacen = Integer.parseInt(ddlAlmacenOrigen.getText().toString().split(" - ")[0]);

                                if(dtoUltimoMovimientoOutput != null && codAlmacen == 81){
                                    if(Integer.parseInt(listaCC.get(0).getCodexi()) != dtoUltimoMovimientoOutput.getCodExistencia()){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                                        builder.setTitle("Confirmacion");
                                        builder.setMessage("La etiqueta escaneada pertenece a una existencia distinta a la ultima registrada para el Nro de Orden y consecutivo ingresado \n " +
                                                "¿Deseas registrarlo?");
                                        builder.setPositiveButton("Grabar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                CargarEtiquetas(altaViewModel, listaCC);
                                            }
                                        });
                                        builder.setNegativeButton("Cancelar",(dialog, which) -> dialog.dismiss());
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }else{
                                        CargarEtiquetas(altaViewModel, listaCC);
                                    }
                                }else{
                                    CargarEtiquetas(altaViewModel, listaCC);
                                }
                            }catch (Exception ex){}
                        }else{
                            if(progressDialog != null) {
                                progressDialog.dismiss();
                            }
                            mostrarDialog("La etiqueta se encuentra agregada");
                        }
                    }
                }else{  // Ocultar el ProgressDialog
                    if(progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    mostrarDialog(mensaje);

                }

            }else{
                estaProcesando = false;
                // Manejo del caso donde el objeto es null o la respuesta no es exitosa
            }
            procesarSiguienteCodigo(altaViewModel);
        }catch (Exception ex){
            mostrarDialog("No se pudo leer una etiqueta");
        }

    }

    private void CargarEtiquetas(AltaViewModel altaViewModel, List<DtoObtenerDatosEtiquetaInput> listaCC){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
        //obtienes los codigos de existenciad de la lista
        List<String> etiquetas = codigoAdapter.getAllCodigoEtiquetas();
        // obtiene la secuencia siguiente
        int secuencia = codigoAdapter.getNextSecuencia();

        // SI ES LA PRIMERA ETIQUETA
        if(etiquetas.isEmpty()) {
            //es el primer registro
            codigoAdapter.addValores(listaCC.get(0), secuencia);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String codigoTipoMov = ddlTipoDocumento.getText().toString().split(" - ")[0];
            String codigoAlmacenSeleccionado = ddlAlmacenOrigen.getText().toString().split(" - ")[0];
            String codigoCCSeleccionado = ddlCentroCosto.getText().toString().split(" - ")[0];

            editor.putString("codigoTipoMov", codigoTipoMov);
            editor.putString("codigoAlmacenSeleccionado", codigoAlmacenSeleccionado);
            editor.putString("codigoCCSeleccionado", codigoCCSeleccionado);
            //editor.apply();

            //BuscarOrdenGrabada(altaViewModel);
            Gson gson = new Gson();
            String listaJson = gson.toJson(codigoAdapter.codigoList);  // Convertir la lista a JSON

            editor.putString("listaRecyclerView", listaJson);
            editor.apply();

            // Ocultar el ProgressDialog
            if(progressDialog != null) {
                progressDialog.dismiss();
            }

        }else{
            if(esCodigoExistenciaValido(etiquetas, String.valueOf(listaCC.get(0).getCodtex()))) {

                mObtenerFiltroLoteTonalidad(altaViewModel, etiquetas, listaCC.get(0));
                Log.d("API agregarRegistroSiEsValido", "Registro agregado correctamente");
                //int secuencia2 = codigoAdapter.getNextSecuencia();
                codigoAdapter.addValores(listaCC.get(0), secuencia);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                String codigoTipoMov = ddlTipoDocumento.getText().toString().split(" - ")[0];
                String codigoAlmacenSeleccionado = ddlAlmacenOrigen.getText().toString().split(" - ")[0];
                String codigoCCSeleccionado = ddlCentroCosto.getText().toString().split(" - ")[0];
                editor.putString("codigoTipoMov", codigoTipoMov);
                editor.putString("codigoAlmacenSeleccionado", codigoAlmacenSeleccionado);
                editor.putString("codigoCCSeleccionado", codigoCCSeleccionado);

                //editor.apply();

                Gson gson = new Gson();

                String listaJson = gson.toJson(codigoAdapter.codigoList);  // Convertir la lista a JSON

                editor.putString("listaRecyclerView", listaJson);
                editor.apply();
                // Ocultar el ProgressDialog
                if(progressDialog != null) {
                    progressDialog.dismiss();
                }
            }else{
                // Ocultar el ProgressDialog
                if(progressDialog != null) {
                    progressDialog.dismiss();
                }
                mostrarDialog("El registro tiene diferente código de existencia");
                return;
            }

        }

        TextInputEditTextSeleccionados.setText(String.valueOf(codigoAdapter.cantidadItems()));
        txtTotalConos.setText(String.valueOf(codigoAdapter.cantidadConos()));
        txtTotalKg.setText(df.format(codigoAdapter.cantidadKg()));
    }

    private String obtenerCodigoFinal(String codigo) {
            if(codigo.length() == 17) {
            return codigo.substring(0, 2).trim() + codigo.substring(5, 17).trim() + "0000";
        }else if(codigo.length() == 14) {
            return codigo + "0000";
        }else{
            return codigo;
        }
    }


    private String formatearFecha(String fecha) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date date = originalFormat.parse(fecha);
            return date != null ? newFormat.format(date) : "";
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean esCodigoExistenciaValido(List<String> etiquetas, String codigoExistencia) {
        for (String etiqueta : etiquetas) {
            if(etiqueta.equals(codigoExistencia)) {
                return true;
            }
        }
        return false;
    }


    public void mObtenerFiltroLoteTonalidad(AltaViewModel altaViewModel, List<String> etiquetas,
                                            DtoObtenerDatosEtiquetaInput ultimoItem) {

        String sCODTEX = String.valueOf(ultimoItem.getCodtex());
        String sCODCIA = String.valueOf(ultimoItem.getCodcia());
        String sCODPRV = String.valueOf(ultimoItem.getCodprv());
        String ltomag = String.valueOf(ultimoItem.getLtomag());


        String sRpta = "";
        String sResult = "";

        String sCode = "1";
        String sDesc = "";

        if("0".equals(sCode)) {
            // Manejar caso cuando sCode es "0"
        }else if("1".equals(sCode)) {

            String sRNOALG = textViewRequiereNroOrden.getText().toString();
            String sRNCALG = textViewRequiereCscOrden.getText().toString();

            if("S".equals(sRNOALG) && "S".equals(sRNCALG)) {
                sRpta = "S";
            }else{
                sRpta = "N";
            }
        }else{
            sRpta = "N";
        }
        String movimientoX = "S";


        if("S".equals(sRpta) &&
                "1".equals(sCODTEX) &&
                "10".equals(sCODCIA) &&
                "P0111".equals(sCODPRV) &&
                "S".equals(movimientoX)) {
            if(ltomag == null || "".equals(ltomag)) {
                sResult = "Este artículo no contiene el campo Lote Tonalidad: LTOMAG.";
                mostrarDialog("Este artículo no contiene el campo Lote Tonalidad: LTOMAG.");

            }else{
                ValidarTipoListado(altaViewModel, 0, etiquetas, ultimoItem);

            }
        }
    }

    private void ValidarTipoListado(AltaViewModel altaViewModel, int nroOrden, List<String> etiquetas,
                                    DtoObtenerDatosEtiquetaInput ultimoItem) {
        altaViewModel.getIsTipoListadoValido().observe(getViewLifecycleOwner(), isValid -> {
            if(isValid != null && isValid) {
                // Si es válido, ejecuta la lógica correspondiente
                Log.d("API Validación", "El listado es válido");
                // Aquí puedes ejecutar lo que necesites hacer cuando sea válido
            }else{
                if(esMismoLoteyTonalidad(etiquetas, ultimoItem)) {
                    int secuencia = codigoAdapter.getNextSecuencia();
                    codigoAdapter.addValores(ultimoItem, secuencia);
                }else{

                    mostrarDialog("Los articulos deben tener el mismo LOTE TONALIDAD.");
                }

            }
        });

        // Llamas al método que realiza la validación
        altaViewModel.ValidarTipoListado(nroOrden);
    }

    private boolean esMismoLoteyTonalidad(List<String> etiquetas, DtoObtenerDatosEtiquetaInput ultimoItem) {

        String codigoExistencia = String.valueOf(ultimoItem.getCodtex());
        String codigoArticulo = String.valueOf(ultimoItem.getCodexi());
        String colorHilado = String.valueOf(ultimoItem.getCodchi());
        String codProveedor = String.valueOf(ultimoItem.getCodprv());
        String umReal = String.valueOf(ultimoItem.getUrhmag());

        for (String etiqueta : etiquetas) {
            // Aquí comparamos todos los parámetros con la etiqueta
            if(etiqueta.equals(codigoExistencia) &&
                    etiqueta.equals(codigoArticulo) &&
                    etiqueta.equals(colorHilado) &&
                    etiqueta.equals(codProveedor) &&
                    etiqueta.equals(umReal)) {
                return true;
            }
        }
        return false;
    }

    private void showCustomDialog() {

        dialog_title.setText("Alerta");
        // Inflar el diseño del diálogo
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);

        // Crear el diálogo
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext()).setView(dialogView);

        // Mostrar el diálogo
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void SeleccionarCboCompania() {
        ddlCompania.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DtoCompañia compañia = (DtoCompañia) parent.getItemAtPosition(position);
                //Toast.makeText(requireContext(), "Código: " + compañia.getCodCia(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SeleccionarCboAlmacenOrigen(AltaViewModel altaViewModel) {
        ddlAlmacenOrigen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DtoAlmacen almacen = (DtoAlmacen) parent.getItemAtPosition(position);
                int CodigoAlmacen = almacen.getCodalg();
                int selectedId = radioGroupTipoMov.getCheckedRadioButtonId();
                String TipMovSeleccionado = (selectedId == R.id.radioButtonSalida) ? "S" : "T";
                String codcompania = ddlCompania.getText().toString().split(" - ")[0];

                ConfiguracionSeleccionarCboAlmacenOrigen(altaViewModel, CodigoAlmacen);
                //ObtenerCorrelativoAlmacenNota(altaViewModel, TipMovSeleccionado, CodigoAlmacen, codcompania); A
            }
        });
    }

    private void ConfigurarRadioGroupTipoMov(RadioGroup radioGroup, AltaViewModel altaViewModel) {

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radioButtonSalida) {

                almacenDestino_layout.setVisibility(View.GONE);
                //textTituloInputAlmacenDestino.setVisibility(View.GONE);

                radioButtonTotal.setChecked(true);
                radioButtonParcial.setChecked(false);
                radioButtonTotal.setEnabled(true);
                radioButtonParcial.setEnabled(true);

            }else if(checkedId == R.id.radioButtonTransferencia) {
                almacenDestino_layout.setVisibility(View.VISIBLE);
                //textTituloInputAlmacenDestino.setVisibility(View.VISIBLE);

                radioButtonTotal.setChecked(true);
                radioButtonParcial.setChecked(false);
                radioButtonParcial.setEnabled(false);
                radioButtonTotal.setEnabled(false);

                radioButtonProducto.setEnabled(false);
                radioButtonEnvace.setEnabled(false);
                radioButtonProducto.setChecked(true);
            }
        });
    }

    private void ConfigurarRadioGroupDescargue(RadioGroup radioGroup, AltaViewModel altaViewModel) {

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.rbParcial) {

                radioButtonProducto.setChecked(true);
                radioButtonProducto.setEnabled(true);
                radioButtonEnvace.setEnabled(true);

            }else if(checkedId == R.id.rbTotal) {

                radioButtonTotal.setChecked(true);
                radioButtonProducto.setChecked(true);

                radioButtonProducto.setEnabled(false);
                radioButtonEnvace.setEnabled(false);
            }
        });
    }

    private void CargarCboCompania(AltaViewModel altaViewModel) {

        progressDialog.show(getParentFragmentManager(), "progress");

        altaViewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<DtoCompañia>>() {
            @Override
            public void onChanged(List<DtoCompañia> items) {
                //CompaniaList = items;
                adapterItems = new ArrayAdapter<DtoCompañia>(requireContext(), R.layout.simple_dropdown_compact, items) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if(convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_dropdown_compact, parent, false);
                        }
                        TextView textView = convertView.findViewById(android.R.id.text1);
                        textView.setText(getItem(position).getDesCia());
                        return convertView;
                    }
                };
                ddlCompania.setAdapter(adapterItems);

                // Establecer el valor por defecto según el código
                for (int i = 0; i < items.size(); i++) {
                    if(items.get(i).getCodCia() == 10) {
                        ddlCompania.setText(adapterItems.getItem(i).getDesCia(), false); // Seleccionar el elemento
                        break;
                    }
                }
                CargarCboAlmacenOrigen(altaViewModel);
                CargarCboAlmacenDestino(altaViewModel);

                // Ocultar el ProgressDialog
                if(progressDialog != null) {
                    progressDialog.dismiss();
                }
                ddlCompania.setVisibility(View.VISIBLE);
            }
        });

    }

    private void CargarCboAlmacenOrigen(AltaViewModel altaViewModel) {
        altaViewModel.getAlmacen().observe(getViewLifecycleOwner(), new Observer<List<DtoAlmacen>>() {
            @Override
            public void onChanged(List<DtoAlmacen> items) {
                //almacenList = items;
                adapterAlmacenOrigen = new ArrayAdapter<DtoAlmacen>(requireContext(), R.layout.simple_dropdown_compact, items) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if(convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_dropdown_compact, parent, false);
                        }
                        TextView textView = convertView.findViewById(android.R.id.text1);
                        textView.setText(getItem(position).getDesalg());
                        return convertView;
                    }
                };

                //Cargar datos grabados
                ddlAlmacenOrigen.setAdapter(adapterAlmacenOrigen);
                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String codigoAlmacenSeleccionadoGuardado = sharedPreferences.getString("codigoAlmacenSeleccionado", "");

                if(codigoAlmacenSeleccionadoGuardado != null && !codigoAlmacenSeleccionadoGuardado.isEmpty()) {
                    int codigoalmacenseleccioando = 0;
                    for (int i = 0; i < items.size(); i++) {
                        if(items.get(i).getCodalg() == Integer.parseInt(codigoAlmacenSeleccionadoGuardado)) {
                            ddlAlmacenOrigen.setText(adapterAlmacenOrigen.getItem(i).getDesalg(), false); // Seleccionar el elemento
                            codigoalmacenseleccioando = items.get(i).getCodalg();
                            break;
                        }
                    }
                    int selectedId = radioGroupTipoMov.getCheckedRadioButtonId();
                    String TipMovSeleccionado = (selectedId == R.id.radioButtonSalida) ? "S" : "T";
                    String codcompania = ddlCompania.getText().toString().split(" - ")[0];

                    ConfiguracionSeleccionarCboAlmacenOrigen(altaViewModel, codigoalmacenseleccioando);

                    //ObtenerCorrelativoAlmacenNota(altaViewModel, TipMovSeleccionado, codigoalmacenseleccioando, codcompania);
                }


              /* ESTE CODIGO ES PARA OBTENER UN ALMACEN POR DEFECTO AL CARGAR POR PRIMERA VEZ EL COMBO
               int codigoalmacenseleccioando = 0;
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).getCodalg()==83) {
                        ddlAlmacenOrigen.setText(adapterAlmacenOrigen.getItem(i).getDesalg(), false); // Seleccionar el elemento
                        codigoalmacenseleccioando = items.get(i).getCodalg();
                        break;
                    }
                }
                int selectedId = radioGroupTipoMov.getCheckedRadioButtonId();
                String TipMovSeleccionado = (selectedId == R.id.radioButtonSalida) ? "S" : "T";
                String codcompania = ddlCompania.getText().toString().split(" - ")[0];

               ConfiguracionSeleccionarCboAlmacenOrigen( altaViewModel,codigoalmacenseleccioando );

               ObtenerCorrelativoAlmacenNota(altaViewModel, TipMovSeleccionado, codigoalmacenseleccioando, codcompania);
                */

                // Ocultar ProgressBar y mostrar AutoCompleteTextView después de cargar los datos
                progressBar.setVisibility(View.GONE);
                ddlAlmacenOrigen.setVisibility(View.VISIBLE);
            }
        });
    }

    private void CargarCboAlmacenDestino(AltaViewModel altaViewModel) {
        altaViewModel.getAlmacenDestino().observe(getViewLifecycleOwner(), new Observer<List<DtoAlmacen>>() {
            @Override
            public void onChanged(List<DtoAlmacen> items) {
                //almacenDestinoList = items;
                adapterAlmacenDestino = new ArrayAdapter<DtoAlmacen>(requireContext(), R.layout.simple_dropdown_compact, items) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if(convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_dropdown_compact, parent, false);
                        }
                        TextView textView = convertView.findViewById(android.R.id.text1);
                        textView.setText(getItem(position).getDesalg());
                        return convertView;
                    }
                };
                ddlAlmacenDestino.setAdapter(adapterAlmacenDestino);

                //Codigo para seleccionar el almacen grabado en preferencias
                /*SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String codigoAlmacenSeleccionadoGuardado = sharedPreferences.getString("codigoAlmacenSeleccionado", "");

                if (codigoAlmacenSeleccionadoGuardado != null && !codigoAlmacenSeleccionadoGuardado.isEmpty())
                {
                    int codigoalmacenseleccioando = 0;
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCodalg()==Integer.parseInt(codigoAlmacenSeleccionadoGuardado) ) {
                            ddlAlmacenOrigen.setText(adapterAlmacenDestino.getItem(i).getDesalg(), false); // Seleccionar el elemento
                            codigoalmacenseleccioando = items.get(i).getCodalg();
                            break;
                        }
                    }
                    int selectedId = radioGroupTipoMov.getCheckedRadioButtonId();
                    String TipMovSeleccionado = (selectedId == R.id.radioButtonSalida) ? "S" : "T";
                    String codcompania = ddlCompania.getText().toString().split(" - ")[0];

                    ConfiguracionSeleccionarCboAlmacenOrigen( altaViewModel,codigoalmacenseleccioando );

                    ObtenerCorrelativoAlmacenNota(altaViewModel, TipMovSeleccionado, codigoalmacenseleccioando, codcompania);
                }*/

                progressBar.setVisibility(View.GONE);
                //ddlAlmacenOrigen.setVisibility(View.VISIBLE);
            }
        });
    }

    private void CargarCboTipoMovimientoSalida(AltaViewModel altaViewModel, int CodigoAlmacen) {

        altaViewModel.getTipoDocumento().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerTipoMovimientoSalida(CodigoAlmacen);
        altaViewModel.getTipoDocumento().observe(getViewLifecycleOwner(), new Observer<List<DtoTipoDocumento>>() {
            @Override
            public void onChanged(List<DtoTipoDocumento> items) {
                if(items != null) {
                    //TipDocList = items;
                    adapterTipoDocumento = new ArrayAdapter<DtoTipoDocumento>(requireContext(), R.layout.simple_dropdown_compact, items) {
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            if(convertView == null) {
                                convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_dropdown_compact, parent, false);
                            }
                            TextView textView = convertView.findViewById(android.R.id.text1);
                            textView.setText(getItem(position).getDesTipDoc());
                            return convertView;
                        }
                    };
                    ddlTipoDocumento.setAdapter(adapterTipoDocumento);
                    ddlTipoDocumento.setText(adapterTipoDocumento.getItem(0).getDesTipDoc(), false);  // Selecciona el primer elemento
                    // Ocultar ProgressBar y mostrar AutoCompleteTextView después de cargar los datos
                    progressBar.setVisibility(View.GONE);
                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    ddlTipoDocumento.setVisibility(View.VISIBLE);
                    String codigoTipoMovGuardado = sharedPreferences.getString("codigoTipoMov", "");

                    // Establecer el valor por defecto según el código
                    for (int i = 0; i < items.size(); i++) {
                        if(items.get(i).getCodTipDoc().equals(codigoTipoMovGuardado)) {
                            ddlTipoDocumento.setText(adapterTipoDocumento.getItem(i).getDesTipDoc(), false); // Seleccionar el elemento
                            break;
                        }
                    }

                }
            }
        });
    }


    private void CargarCboTipoMovimientoTransferencia(AltaViewModel altaViewModel) {

        altaViewModel.ObtenerTipoMovimientoTransferencia();
        altaViewModel.getTipoDocumentoTipoMovTransferencia().observe(getViewLifecycleOwner(), new Observer<List<DtoObtenerTipoMovTransferenciaInput>>() {
            @Override
            public void onChanged(List<DtoObtenerTipoMovTransferenciaInput> items) {
                // TipDocList = items;
                adapterTipoTipoMovTransferenciaInput = new ArrayAdapter<DtoObtenerTipoMovTransferenciaInput>(requireContext(), R.layout.simple_dropdown_compact, items) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if(convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_dropdown_compact, parent, false);
                        }
                        TextView textView = convertView.findViewById(android.R.id.text1);
                        textView.setText(getItem(position).getDesTipMov());
                        return convertView;
                    }
                };
                ddlTipoDocumento.setAdapter(adapterTipoTipoMovTransferenciaInput);
                // Ocultar ProgressBar y mostrar AutoCompleteTextView después de cargar los datos
                progressBar.setVisibility(View.GONE);
                ddlTipoDocumento.setVisibility(View.VISIBLE);
            }
        });

    }

    private void CargarCboCentroCosto(AltaViewModel altaViewModel) {
        altaViewModel.getListaCentroCostos().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerCentroCostos();
        altaViewModel.getListaCentroCostos().observe(getViewLifecycleOwner(), new Observer<List<DtoObtenerCentroCostosInput>>() {
            @Override
            public void onChanged(List<DtoObtenerCentroCostosInput> items) {
                if(items != null && !items.isEmpty()) {
                    //centrocostoList = items;
                    adapterCentroCosto = new ArrayAdapter<DtoObtenerCentroCostosInput>(requireContext(), R.layout.simple_dropdown_compact, items) {
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            if(convertView == null) {
                                convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_dropdown_compact, parent, false);
                            }
                            TextView textView = convertView.findViewById(android.R.id.text1);
                            textView.setText(getItem(position).getDesCentroCosto());
                            return convertView;
                        }
                    };
                    ddlCentroCosto.setAdapter(adapterCentroCosto);

                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String codigocodigoCCSeleccionadoGuardado = sharedPreferences.getString("codigoCCSeleccionado", "");

                    if(codigocodigoCCSeleccionadoGuardado != null && !codigocodigoCCSeleccionadoGuardado.isEmpty()) {
                        for (int i = 0; i < items.size(); i++) {
                            if(items.get(i).getCodCentroCosto() == Integer.parseInt(codigocodigoCCSeleccionadoGuardado)) {
                                ddlCentroCosto.setText(adapterCentroCosto.getItem(i).getDesCentroCosto(), false); // Seleccionar el elemento

                                break;
                            }
                        }
                    }

                    // Ocultar ProgressBar y mostrar AutoCompleteTextView después de cargar los datos
                    progressBar.setVisibility(View.GONE);
                    ddlCentroCosto.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void CargarCboCentroCostoXAlmacen(AltaViewModel altaViewModel, int CodigoAlmacen) {
        altaViewModel.getListaCentroCostosXAlmacen().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerCentroCostosXAlmacen(CodigoAlmacen);
        altaViewModel.getListaCentroCostosXAlmacen().observe(getViewLifecycleOwner(), new Observer<List<DtoObtenerCentroCostosXAlmacenInput>>() {
            @Override
            public void onChanged(List<DtoObtenerCentroCostosXAlmacenInput> items) {
                //  centrocostoList = items;
                if(items != null && !items.isEmpty()) {
                    adapterCentroCostoXalmacen = new ArrayAdapter<DtoObtenerCentroCostosXAlmacenInput>(requireContext(), R.layout.simple_dropdown_compact, items) {
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            if(convertView == null) {
                                convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_dropdown_compact, parent, false);
                            }
                            TextView textView = convertView.findViewById(android.R.id.text1);
                            textView.setText(getItem(position).getDesCentroCosto());
                            return convertView;
                        }
                    };
                    ddlCentroCosto.setAdapter(adapterCentroCostoXalmacen);
                    if(items.size() == 1) {
                        ddlCentroCosto.setText(adapterCentroCostoXalmacen.getItem(0).getDesCentroCosto(), false);  // Selecciona el único elemento
                    }
                }
                // Ocultar ProgressBar y mostrar AutoCompleteTextView después de cargar los datos
                progressBar.setVisibility(View.GONE);
                ddlCentroCosto.setVisibility(View.VISIBLE);
            }
        });
    }

    private void ConfiguracionObtenerTipoMovimiento(AltaViewModel altaViewModel, int CodigoAlmacen, String xrnoalg, String xrcoalg) {
        CargarCboTipoMovimientoSalida(altaViewModel, CodigoAlmacen);
        BuscarOrdenGrabada(altaViewModel);
        if(xrnoalg.equals("S")) {
            if(radioButtonSalida.isChecked()) {
                //txtnrocarga.setText("");
                txtnrocarga.setEnabled(true);

            }else{
                //txtnrocarga.setText("");
                txtnrocarga.setEnabled(false);
                txtnrocarga.setBackgroundColor(Color.LTGRAY);
            }
        }


        if(xrnoalg.equals("S") && xrcoalg.equals("N")) {
            if(radioButtonSalida.isChecked()) {
                ObtenerInputTipoDocumento(altaViewModel);

                txtanio.setText("");
                //txtnroorden.setText("");
                txtconsecutivo.setText("");
                ddlTipoDocumento.setEnabled(false);

                txtnroorden.setEnabled(true);


                txtconsecutivo.setEnabled(false);
                txtconsecutivo.setBackgroundColor(Color.LTGRAY);

            }else{
                CargarCboTipoMovimientoTransferencia(altaViewModel);
                //txtnroorden.setText("");
                txtconsecutivo.setText("");

                txtconsecutivo.setEnabled(false);

                txtnroorden.setEnabled(false);


            }
            txtarticulo.setText("");
            txtarticulo.setEnabled(false);
            txtarticulo.setBackgroundColor(Color.LTGRAY);


        }else if(xrnoalg.equals("S") && xrcoalg.equals("S")) {
            if(radioButtonSalida.isChecked()) {
                ObtenerInputTipoDocumento(altaViewModel);
                txtanio.setText("");
                //txtnroorden.setText("");
                txtconsecutivo.setText("");
                ddlTipoDocumento.setEnabled(false);


                txtconsecutivo.setEnabled(true);
                txtnroorden.setEnabled(true);
                txtarticulo.setEnabled(false);
                txtarticulo.setBackgroundColor(Color.LTGRAY);
                txtnrocarga.setEnabled(false);
                txtnrocarga.setBackgroundColor(Color.LTGRAY);
            }else{
                txtconsecutivo.setText("");
                //txtnroorden.setText("");
                txtarticulo.setText("");
                CargarCboTipoMovimientoTransferencia(altaViewModel);

                txtconsecutivo.setEnabled(false);
                //   txtconsecutivo.setBackgroundResource(R.drawable.background_disabled);
                txtnroorden.setEnabled(false);
                //  txtnroorden.setBackgroundResource(R.drawable.background_disabled);
            }

        }else if(xrnoalg.equals("N") && xrcoalg.equals("N")) {
            txtTipoDoc.setText("");
            txtanio.setText("");
            //txtnroorden.setText("");
            txtconsecutivo.setText("");
            //   ddlTipoDocumento.setEnabled(true);
            txtarticulo.setText("");

            txtanio.setEnabled(false);
            txtanio.setBackgroundColor(Color.LTGRAY);


            txtconsecutivo.setEnabled(false);
            txtconsecutivo.setBackgroundColor(Color.LTGRAY);

            txtnroorden.setEnabled(false);
            txtnroorden.setBackgroundColor(Color.LTGRAY);

            txtarticulo.setEnabled(false);
            txtarticulo.setBackgroundColor(Color.LTGRAY);

            txtnrocarga.setEnabled(false);
            txtnrocarga.setBackgroundColor(Color.LTGRAY);

        }


    }

    private void ConfiguracionObtenerCentroCostos(AltaViewModel altaViewModel, int codcosX) {
        if(codcosX == 0) {
            CargarCboCentroCosto(altaViewModel);

        }else{
            CargarCboCentroCostoXAlmacen(altaViewModel, codcosX);
        }


    }

    private void ConfiguracionSeleccionarCboAlmacenOrigen(AltaViewModel altaViewModel, int CodigoAlmacen) {
        altaViewModel.getAlmacenXCodigo().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerAlmacenXCodigo(CodigoAlmacen);
        altaViewModel.getAlmacenXCodigo().observe(getViewLifecycleOwner(), new Observer<List<DtoAlmacenXCodigoInput>>() {
            @Override
            public void onChanged(List<DtoAlmacenXCodigoInput> items) {

                if(items != null && !items.isEmpty()) {
                    DtoAlmacenXCodigoInput almacen = items.get(0); // Tomamos el primer elemento para mostrarlo

                    textViewCodigoAlmacen.setText(String.valueOf(almacen.getCodigoAlmacen()));
                    textViewNombreAlmacen.setText(almacen.getDescripcionAlmacen());
                    textViewRequiereNroOrden.setText(almacen.getRequiereNroOrden());
                    textViewRequiereCscOrden.setText(almacen.getRequiereCscOrden());
                    textViewtipoDocumento.setText(almacen.getTipoDocumento());
                    textVierequiereNroCarga.setText(almacen.getRequiereNroCarga());
                    textVierecentroCosto.setText(almacen.getCentroCosto());

                    int CodAlmacen = Integer.parseInt(textViewCodigoAlmacen.getText().toString());
                    String xrnoalg = textViewRequiereNroOrden.getText().toString();
                    String xrcoalg = textViewRequiereCscOrden.getText().toString();
                    int codcosX = Integer.parseInt(textVierecentroCosto.getText().toString());
                    ConfiguracionObtenerTipoMovimiento(altaViewModel, CodAlmacen, xrnoalg, xrcoalg);
                    ConfiguracionObtenerCentroCostos(altaViewModel, codcosX);

                    if(Objects.equals(almacen.getRequiereNroCarga(), "S")){
                        layoutconsecutivo.setVisibility(View.GONE);
                        layoutnrocarga.setVisibility(View.VISIBLE);
                    }else if(Objects.equals(almacen.getRequiereCscOrden(), "S")) {
                        layoutconsecutivo.setVisibility(View.VISIBLE);
                        layoutnrocarga.setVisibility(View.GONE);
                    }
                }

                // Desvincular el observador para evitar más llamadas innecesarias a onChanged
                //  altaViewModel.getAlmacenXCodigo().removeObserver(this);

                ddlTipoDocumento.setVisibility(View.VISIBLE);
            }

        });
    }

    private void BuscarOrdenGrabada(AltaViewModel altaViewModel) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String nroOrdenGuardado = sharedPreferences.getString("nroOrden", "");
        String nroCargaGuardado = sharedPreferences.getString("nroCarga", "");

        txtnroorden.setText(nroOrdenGuardado);
        int orden = txtnroorden.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnroorden.getText().toString());
        int codAlmacen = Integer.parseInt(ddlAlmacenOrigen.getText().toString().split(" - ")[0]);
        if(orden > 0) {
            validarNroOrden(altaViewModel, orden, codAlmacen);
        }


        txtnrocarga.setText(nroCargaGuardado);
        int nrocarga = txtnrocarga.getText().toString().isEmpty() ? 0 : Integer.parseInt(txtnrocarga.getText().toString());
        if(nrocarga > 0) {
            validarEstadoCarga(altaViewModel, orden, Integer.parseInt(txtnrocarga.getText().toString()));
        }
    }

    private void ObtenerInputTipoDocumento(AltaViewModel altaViewModel) {
        altaViewModel.getListaInputTipoDocumentoLiveData().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerInputTipoDocumento();
        altaViewModel.getListaInputTipoDocumentoLiveData().observe(getViewLifecycleOwner(), new Observer<List<DtoObtenerInputTipoDocumentoInput>>() {
            @Override
            public void onChanged(List<DtoObtenerInputTipoDocumentoInput> items) {
                //ObtenerInputTipoDocumentoInputList = items;
                if(items != null && !items.isEmpty()) {
                    DtoObtenerInputTipoDocumentoInput tip = items.get(0); // Tomamos el primer elemento para mostrarlo
                    txtTipoDoc.setText(String.valueOf((tip.getCodigoTipDoc())));
                }else{
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void BorrarDatosGuardados() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();  // Borra todo el contenido
        editor.apply();
    }

    private void Guardar(AltaViewModel altaViewModel) {

        final String[] fecsis = {""};
        final String[] fecmen = {""};
        final String[] fecact = {""};
        final String[] fecmagX = {""};
        final boolean[] validacion = {false};

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Confirmacion")
                            .setMessage("¿Estás seguro de que deseas grabar los datos?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(!validarCampos()) {
                                        try {
                                            List<DtoObtenerDatosEtiqueta> TotalEtiquetas = codigoAdapter.ObtenerTotalEtiquetas();
                                            List<DtoObtenerDatosStockEmpaqueInput> etiquetasStock = codigoAdapter.ObtenerTodasEtiquetasStock();

                                            String msjValidacionEtiquetas = validacionEtiquetas(TotalEtiquetas);
                                            if(msjValidacionEtiquetas.length() == 0){
                                                String ObtenerTodasEtiquetas = codigoAdapter.ObtenerTodasEtiquetas();

                                                int selectedId = radioGroupTipoMov.getCheckedRadioButtonId();
                                                String TipMovSeleccionado = (selectedId == R.id.radioButtonSalida) ? "S" : "T";
                                                String pcName = obtenerNombreDelDispositivo(getContext());
                                                int codAlm = Integer.parseInt(textViewCodigoAlmacen.getText().toString());
                                                String codcia = ddlCompania.getText().toString().split(" - ")[0];
                                                int codcompania = Integer.parseInt(codcia);
                                                int codalg = codAlm;
                                                String tmvmag = TipMovSeleccionado;
                                                String nmvmag = TextInputEditTextNota.getText().toString(); // Nota
                                                String codtmv = ddlTipoDocumento.getText().toString().split(" - ")[0];
                                                String cencos = ddlCentroCosto.getText().toString().split(" - ")[0];
                                                int ademag = (ddlAlmacenDestino.getText().toString().split(" - ")[0].trim().length() == 0) ? 0 : Integer.parseInt(ddlAlmacenDestino.getText().toString().split(" - ")[0].trim());
                                                String refere = TextInputEditTextNota.getText().toString(); // Nota
                                                //Ordenes
                                                String ctdor1 = txtTipoDoc.getText().toString(); //tipo
                                                String anoor1 = txtanio.getText().toString(); // año
                                                String nroor1 = txtnroorden.getText().toString(); // nro
                                                String cscor2 = txtconsecutivo.getText().toString(); // consecutivo
                                                String ncrma2 = txtnrocarga.getText().toString(); // carga

                                                String fecmag = "";
                                                String inputDate = dateInput.getText().toString().trim();
                                                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                                Date date = inputFormat.parse(inputDate);
                                                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                                fecmag = outputFormat.format(date);

                                                // Declaración de variables
                                                int almacen = 0;
                                                int almacenX = 0;
                                                int cCostoX = 0;

                                                // Diferenciar
                                                if(tmvmag.equals("T")) {
                                                    almacen = ademag;
                                                }else if(tmvmag.equals("S")) {
                                                    almacen = codalg;
                                                }

                                                String fechaMovimiento = fecmag;
                                                String usuario = getActivity().getIntent().getStringExtra("nombreUsuario");

                                                DtoProcesarGuardado request = new DtoProcesarGuardado();
                                                request.setTotalEtiquetas(TotalEtiquetas);
                                                request.setCodcompania(codcompania);
                                                request.setCodalg(codalg);
                                                request.setTmvmag(tmvmag);
                                                request.setNmvmag(nmvmag);
                                                request.setCodtmv(codtmv);
                                                request.setCencos(cencos);
                                                request.setAdemag(ademag);
                                                request.setRefere(refere);
                                                request.setCtdor1(ctdor1);
                                                request.setAnoor1(anoor1);
                                                request.setNroor1(nroor1);
                                                request.setCscor2(cscor2);
                                                request.setFecmag(fecmag);
                                                request.setNcrma2(ncrma2);
                                                request.setAlmacen(almacen);
                                                request.setFechaMovimiento(fechaMovimiento);
                                                request.setPcName(pcName);
                                                request.setEtiquetasStock(etiquetasStock);
                                                request.setObtenerTodasEtiquetas(ObtenerTodasEtiquetas);
                                                request.setUsuario(usuario);

                                                ProcesarGuardado(altaViewModel, request);
                                            }else {
                                                if(progressDialog != null) {
                                                    progressDialog.dismiss();
                                                }
                                                mostrarDialog(msjValidacionEtiquetas);
                                            }
                                        } catch (ParseException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }else{
                                        if(progressDialog != null) {
                                            progressDialog.dismiss();
                                        }
                                    }
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                } catch (Exception e) {
                    if(progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    Log.e("API Error", "Error al Guardar : " + e.getMessage(), e);
                }
            }
        });
    }

    private String validacionEtiquetas(List<DtoObtenerDatosEtiqueta> etiquetas){
        String res = "";
        for(DtoObtenerDatosEtiqueta item : etiquetas){
            if(item.salida_cantidad > item.stock_cantidad){
                res = "Hay etiquetas que exceden la cantidad de salida con la cantidad de Stock, por favor corregirlas.";
            }else if(item.salida_cantidad == 0){
                res = "Hay etiquetas que no tiene cantidad, por favor corregirlas.";
            }
        }
        return  res;
    }

    private void BorrarTodo(AltaViewModel altaViewModel) {
        btnborrartodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cant = codigoAdapter.cantidadItems();
                if(cant > 0) {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Confirmación")
                            .setMessage("¿Estás seguro de que deseas borrar todos los datos?")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        List<DtoObtenerDatosEtiqueta> TotalEtiquetas = codigoAdapter.ObtenerTotalEtiquetas();
                                        for (DtoObtenerDatosEtiqueta etiqueta : TotalEtiquetas) {

                                            String numeroEtiqueta = etiqueta.CodigoEtiqueta;
                                            altaViewModel.DesbloquearRegistro(numeroEtiqueta);
                                        }

                                        codigoAdapter.clearAdapter();
                                        BorrarDatosGuardados();
                                        ddlCompania.setEnabled(true);
                                        ddlAlmacenOrigen.setEnabled(true);
                                        ddlTipoDocumento.setEnabled(true);
                                        ddlCentroCosto.setEnabled(true);

                                        compania_layout.setEnabled(true);
                                        tipo_layout.setEnabled(true);
                                        almacenOrigen_layout.setEnabled(true);
                                        centroCosto_layout.setEnabled(true);

                                        int cantidadItems = codigoAdapter.cantidadItems();
                                        TextInputEditTextSeleccionados.setText(String.valueOf(cantidadItems));

                                        txtTotalConos.setText(String.valueOf(codigoAdapter.cantidadConos()));
                                        txtTotalKg.setText(df.format(codigoAdapter.cantidadKg()));
                                    } catch (Exception e) {
                                        if(progressDialog != null) {
                                            progressDialog.dismiss();
                                        }
                                        Log.e("API Error", "Error al Guardar : " + e.getMessage(), e);
                                    }
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }else{
                    mostrarDialog("No existen etiquetas agregadas.");
                }
            }
        });
    }


    public boolean validarCamposLeerEtiquetas() {
        boolean hayErrores = false;
        String codcia = ddlCompania.getText().toString().split(" - ")[0];
        String TipoDocumento = ddlTipoDocumento.getText().toString().split(" - ")[0];
        String codAlm = ddlAlmacenOrigen.getText().toString().split(" - ")[0];
        String cencos = ddlCentroCosto.getText().toString().split(" - ")[0];
        String xrnoalg = textViewRequiereNroOrden.getText().toString();
        String xrcoalg = textViewRequiereCscOrden.getText().toString();
        String xrcnalg = textVierequiereNroCarga.getText().toString();

        int selectedId = radioGroupTipoMov.getCheckedRadioButtonId();
        String TipMovSeleccionado = (selectedId == R.id.radioButtonSalida) ? "S" : "T";

        //   int cantetiquetas=codigoAdapter.cantidadItems();

        if(codcia == null || codcia.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el tipo de Compañia");
            hayErrores = true;
        }else if(codAlm == null || codAlm.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el Almacen de Origen");
            hayErrores = true;
        }else if(TipoDocumento == null || TipoDocumento.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el Tipo de Movimiento");
            hayErrores = true;
        }else if(cencos == null || cencos.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el Centro de Costo");
            hayErrores = true;
        }

        if(TipMovSeleccionado.equals("S")) {
            if(xrnoalg.equals("S") && xrcoalg.equals("N")) {
                if(txtTipoDoc.getText().toString().isEmpty()) {
                    mostrarDialog("Ingrese tipo de doc.");
                    hayErrores = true;
                }else if(txtnroorden.getText().toString().isEmpty()) {
                    mostrarDialog("Ingrese numero orden.");
                    hayErrores = true;
                }else if(txtanio.getText().toString().isEmpty()) {
                    mostrarDialog("Nº orden debe tener año.");
                    hayErrores = true;
                }else if(xrcnalg.equals("S") && txtnrocarga.getText().toString().isEmpty()) {
                    mostrarDialog("Ingrese Nº de carga");
                    hayErrores = true;
                }
            }else if(xrnoalg.equals("S") && xrcoalg.equals("S")) {
                if(txtTipoDoc.getText().toString().isEmpty()) {
                    mostrarDialog("Ingrese tipo de doc.");
                    hayErrores = true;
                }else if(txtnroorden.getText().toString().isEmpty()) {
                    mostrarDialog("Ingrese numero orden.");
                    hayErrores = true;
                }else if(txtanio.getText().toString().isEmpty()) {
                    mostrarDialog("Nº orden debe tener año.");
                    hayErrores = true;
                }else if(txtconsecutivo.getText().toString().isEmpty()) {
                    mostrarDialog("Ingrese consecutivo.");
                    txtconsecutivo.setEnabled(true);
                    hayErrores = true;
                }else if(xrcnalg.equals("S")) {
                    if(txtnrocarga.getText().toString().isEmpty()) {
                        mostrarDialog("Ingrese Nº de carga");
                        hayErrores = true;
                    }
                }
            }
        }
        return hayErrores;
    }

    public boolean validarCampos() {
        boolean hayErrores = false;

        String codcia = ddlCompania.getText().toString().split(" - ")[0];
        String TipoDocumento = ddlTipoDocumento.getText().toString().split(" - ")[0];
        String codAlm = ddlAlmacenOrigen.getText().toString().split(" - ")[0];
        String cencos = ddlCentroCosto.getText().toString().split(" - ")[0];

        int cantetiquetas = codigoAdapter.cantidadItems();

        if(TipoDocumento == null || TipoDocumento.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el Tipo de Movimiento");
            hayErrores = true;
        }else if(codcia == null || codcia.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el tipo de Compañia");
            hayErrores = true;
        }else if(codAlm == null || codAlm.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el Almacen de Origen");
            hayErrores = true;
        }else if(cencos == null || cencos.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el Centro de Costo");
            hayErrores = true;
        }else if(cantetiquetas == 0) {
            mostrarDialog("No se han escaneado etiquetas.");
            hayErrores = true;
        }
        return hayErrores;
    }

    private void mostrarDialog(String mensaje) {
        // Inflar el diseño personalizado del diálogo
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert, null);

        // Referencias a los elementos del diálogo
        TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
        TextView dialogMessage = dialogView.findViewById(R.id.dialog_message);
        Button closeButton = dialogView.findViewById(R.id.btn_close);

        // Personalizar el contenido del diálogo (si es necesario)
        dialogTitle.setText("Alerta");
        dialogMessage.setText(mensaje);

        // Crear el diálogo
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(dialogView);

        // Crear y mostrar el diálogo
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        // Acción para cerrar el diálogo al presionar el botón
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public void ValidarStockEmpaque(AltaViewModel altaViewModel) {
        int contador[] = {0};
        String etiquetas = codigoAdapter.ObtenerTodasEtiquetas();

        altaViewModel.getObtenerDatosStockEmpaque().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerDatosStockEmpaque(etiquetas);
        altaViewModel.getObtenerDatosStockEmpaque().observe(getViewLifecycleOwner(), datos -> {
            if(datos != null) {
                List<DtoObtenerDatosStockEmpaqueInput> etiquetasStock = codigoAdapter.ObtenerTodasEtiquetasStock();
                ListaDatosObtenerDatosStockEmpaque = datos;
                for (DtoObtenerDatosStockEmpaqueInput etiquetaStock : etiquetasStock) {
                    String codigoEtiquetaStock = etiquetaStock.getCodigoEtiqueta();
                    Double stockRealStock = etiquetaStock.getStockreal();
                    for (DtoObtenerDatosStockEmpaqueInput datosStock : ListaDatosObtenerDatosStockEmpaque) {
                        String codigoEtiquetaDatos = datosStock.getCodigoEtiqueta();
                        Double stockRealDatos = datosStock.getStockreal();

                        if(codigoEtiquetaStock.equals(codigoEtiquetaDatos)) {
                            if(stockRealDatos < stockRealStock) {
                                contador[0]++;
                            }
                        }
                    }
                }

                if(contador[0] > 0) {
                    System.out.println("Hay " + contador[0] + " etiqueta que no se puede registrar por falta de stock.");
                    mostrarDialog("Hay " + contador[0] + " etiqueta que no se puede registrar por falta de stock.");
                }else{

                    int selectedId = radioGroupTipoMov.getCheckedRadioButtonId();
                    String TipMovSeleccionado = (selectedId == R.id.radioButtonSalida) ? "S" : "T";
                    String pcName = obtenerNombreDelDispositivo(getContext());
                    int codAlm = Integer.parseInt(textViewCodigoAlmacen.getText().toString());
                    String codcompania = ddlCompania.getText().toString().split(" - ")[0];

                    BloquearRegistroCorrelativoNull(altaViewModel, TipMovSeleccionado, pcName, codAlm, codcompania);
                    ObtenerCorrelativoAlmacen(altaViewModel, TipMovSeleccionado, codAlm, codcompania, pcName);


                }
            }
        });
    }

    public void DesbloquearFABCORRE(AltaViewModel altaViewModel, int codcia, int codalg, String tmvcor) {
        altaViewModel.getresultDesbloquearFABCORRE().removeObservers(getViewLifecycleOwner());
        altaViewModel.DesbloquearFABCORRE(codcia, codalg, tmvcor);
        altaViewModel.getresultDesbloquearFABCORRE().observe(getViewLifecycleOwner(), result -> ResultadoDesbloquearFABCORRE(result));
    }

    private void ResultadoDesbloquearFABCORRE(Integer result) {
        if(result != null) {
            if(result > 0) {

                System.out.println("Registro Actualizado");
            }else{
                System.out.println("Registro no Actualizado");
            }
        }
    }


    public void CriterioTipoExistencia(AltaViewModel altaViewModel, String codtex, String codigoEtiqueta, int codcia,
                                       int codalg, String tmvmag, String nmvmag, String cscmag, int secma2, String codtmv, String cremag, String cencos, int ademag, String ucrmag,
                                       String caemag, String refere, String ctdor1, String anoor1, String nroor1, String cscor2, String fecmag, String pcName, String ncrma2) {
        altaViewModel.getresultCriterioTipoExistencia().removeObservers(getViewLifecycleOwner());
        altaViewModel.CriterioTipoExistencia(codtex);
        altaViewModel.getresultCriterioTipoExistencia().observe(getViewLifecycleOwner(), result -> ResultadoCriterioTipoExistencia(result, altaViewModel, codtex, codigoEtiqueta, codcia, codalg, tmvmag, nmvmag, cscmag, secma2, codtmv, cremag, cencos, ademag, ucrmag,
                caemag, refere, ctdor1, anoor1, nroor1, cscor2, fecmag, pcName, ncrma2));
    }

    private void ResultadoCriterioTipoExistencia(Integer result, AltaViewModel altaViewModel, String codtex, String codigoEtiqueta, int codcia,
                                                 int codalg, String tmvmag, String nmvmag, String cscmag, int secma2, String codtmv, String cremag, String cencos, int ademag, String ucrmag,
                                                 String caemag, String refere, String ctdor1, String anoor1, String nroor1, String cscor2, String fecmag, String pcName, String ncrma2) {

        if(result != null) {
            if(result > 0) {
                ValidaUsoCorrelativo(altaViewModel, codtex, codigoEtiqueta, codcia, codalg, tmvmag, nmvmag, cscmag, secma2, codtmv, cremag, cencos, ademag, ucrmag,
                        caemag, refere, ctdor1, anoor1, nroor1, cscor2, fecmag, pcName, ncrma2);


            }else{
                System.out.println("Registrar el Alta directamente");
                GrabarAlta(altaViewModel, codigoEtiqueta, codcia, codalg, tmvmag, nmvmag, cscmag, secma2, codtmv, cremag, cencos, ademag, ucrmag,
                        caemag, refere, ctdor1, anoor1, nroor1, cscor2, fecmag, pcName, ncrma2);

            }
        }
    }

    public void GrabarAlta(AltaViewModel altaViewModel, String codigoEtiqueta, int codcia,
                           int codalg, String tmvmag, String nmvmag, String cscmag, int secma2, String codtmv, String cremag, String cencos, int ademag, String ucrmag,
                           String caemag, String refere, String ctdor1, String anoor1, String nroor1, String cscor2, String fecmag, String pcName, String ncrma2) {

        altaViewModel.getGrabarAlta().removeObservers(getViewLifecycleOwner());
        altaViewModel.GrabarAlta(codigoEtiqueta, codcia, codalg, tmvmag, nmvmag, cscmag, secma2, codtmv, cremag, cencos, ademag, ucrmag,
                caemag, refere, ctdor1, anoor1, nroor1, cscor2, fecmag, pcName, ncrma2);
        altaViewModel.getGrabarAlta().observe(getViewLifecycleOwner(), result -> ResultadoGrabarAlta(result, nmvmag, altaViewModel, codigoEtiqueta));
    }

    private void ResultadoGrabarAlta(Integer result, String nmvmag, AltaViewModel altaViewModel, String codigoEtiqueta) {
        if(result != null) {
            if(result == -1) {
                System.out.println("Registro grabado satisfactoriamente");

            }else{
                System.out.println("Registro no grabado");
                // mostrarDialog("No se Grabo el registro");
            }
            DesbloquearRegistro(altaViewModel, codigoEtiqueta);
        }
    }

    public void DesbloquearRegistro(AltaViewModel altaViewModel, String codigoEtiqueta) {
        altaViewModel.getDesbloquearRegistro().removeObservers(getViewLifecycleOwner());
        altaViewModel.DesbloquearRegistro(codigoEtiqueta);
        altaViewModel.getDesbloquearRegistro().observe(getViewLifecycleOwner(), result -> ResultadoDesbloquearRegistro(result));
    }

    private void ResultadoDesbloquearRegistro(Integer result) {
        if(result != null) {
            // Actualiza la UI en función del resultado
            switch (result) {
                case 1:
                    // Éxito: Registro desbloqueado
                    Log.d("API ResultadoDesbloquearRegistro", "Registro desbloqueado");

                    break;
                case 0:
                    // Error: No se pudo desbloquear
                    Log.d("API ResultadoDesbloquearRegistro", "No se pudo desbloquear el registro");

                    break;
                case -1:
                    // Error: Hubo un problema con la API
                    Log.d("API ResultadoDesbloquearRegistro", "Error al desbloquear el registro");

                    break;
            }
        }


    }

    public void ValidaUsoCorrelativo(AltaViewModel altaViewModel, String codtex, String codigoEtiqueta, int codcia,
                                     int codalg, String tmvmag, String nmvmag, String cscmag, int secma2, String codtmv, String cremag, String cencos, int ademag, String ucrmag,
                                     String caemag, String refere, String ctdor1, String anoor1, String nroor1, String cscor2, String fecmag, String pcName, String ncrma2) {
        altaViewModel.getValidaUsoCorrelativo().removeObservers(getViewLifecycleOwner());
        altaViewModel.ValidaUsoCorrelativo();
        altaViewModel.getValidaUsoCorrelativo().observe(getViewLifecycleOwner(), result -> ResultadoValidaUsoCorrelativo(result, altaViewModel, codtex, codigoEtiqueta, codcia, codalg, tmvmag, nmvmag, cscmag, secma2, codtmv, cremag, cencos, ademag, ucrmag,
                caemag, refere, ctdor1, anoor1, nroor1, cscor2, fecmag, pcName, ncrma2));
    }

    private void ResultadoValidaUsoCorrelativo(List<DtoValidaUsoCorrelativoInput> result, AltaViewModel altaViewModel, String codtex, String codigoEtiqueta, int codcia,
                                               int codalg, String tmvmag, String nmvmag, String cscmag, int secma2, String codtmv, String cremag, String cencos, int ademag, String ucrmag,
                                               String caemag, String refere, String ctdor1, String anoor1, String nroor1, String cscor2, String fecmag, String pcName, String ncrma2) {
        if(result != null) {

            if(result.size() == 0) {
                System.out.println("USADO");
                mostrarDialog("El correlativo de Pre-consumos está siendo usado por otro equipo");

                return;
            }else{
                List<DtoValidaUsoCorrelativoInput> item = result;
                // String pcName = item.get(0).pcName;
                String correlativo = item.get(0).nCorre;


                altaViewModel.getObtenerFMOVALG2LiveData().removeObservers(getViewLifecycleOwner());
                altaViewModel.ObtenerFMOVALG2(codigoEtiqueta);
                altaViewModel.getObtenerFMOVALG2LiveData().observe(getViewLifecycleOwner(), resultado -> ResultadoObtenerFMOVALG2(resultado, altaViewModel, codtex, codigoEtiqueta, codcia, codalg, tmvmag, nmvmag, cscmag, secma2, codtmv, cremag, cencos, ademag, ucrmag,
                        caemag, refere, ctdor1, anoor1, nroor1, cscor2, fecmag, pcName, ncrma2));


            }


        }
    }

    private void ResultadoObtenerFMOVALG2(List<DtoObtenerFMOVALG2Input> resultado, AltaViewModel altaViewModel, String codtex, String codigoEtiqueta, int codcia2,
                                          int codalg, String tmvmag, String nmvmag, String cscmag, int secma2, String codtmv, String cremag, String cencos, int ademag, String ucrmag,
                                          String caemag, String refere, String ctdor1, String anoor1, String nroor1, String cscor2, String fecmag, String pcName, String ncrma2) {
        if(resultado != null) {
            if(resultado.size() > 0) {
                String codcompania = ddlCompania.getText().toString().split(" - ")[0];
                int codcia = codcia2;
                String tmvma1 = resultado.get(0).tmamag;
                String nmvma1 = String.valueOf(resultado.get(0).nmamag);
                String cscma1 = String.valueOf(resultado.get(0).csamag);
                String secma1 = String.valueOf(resultado.get(0).seamag);
                String almma1 = String.valueOf(resultado.get(0).alamag);
                String codexi = String.valueOf(resultado.get(0).codexi);
                String codprv = resultado.get(0).codprv;
                String nlhmag = resultado.get(0).nlhmag;
                String trhmag = String.valueOf(resultado.get(0).trhmag);
                String urhmag = resultado.get(0).urhmag;
                String csopc2 = String.valueOf(resultado.get(0).canmag);
                String cdepc2 = String.valueOf(resultado.get(0).canmag);

                String aoppc2, noppc2, toppc2;
                aoppc2 = "";// anoor1X;
                noppc2 = ""; // nroor1X;
                toppc2 = "OJ";
                // DesbloquearTABCORRE(altaViewModel,codcia);
                String fempc1 = fecmag;

                GrabarAlta(altaViewModel, codigoEtiqueta, codcia, codalg, tmvmag, nmvmag, cscmag, secma2, codtmv, cremag, cencos, ademag, ucrmag,
                        caemag, refere, ctdor1, anoor1, nroor1, cscor2, fecmag, pcName, ncrma2);


                GrabarODRPCON1(altaViewModel, codcia, anoor1, nroor1, codalg, fempc1, pcName, codtmv, cencos, ucrmag);

                // FALTA IMPLEMENTAR

            }

        }
    }

    public void GrabarODRPCON1(AltaViewModel altaViewModel, int codcia, String anoor1, String nroor1, int codalg, String fempc1, String pcName, String codtmv, String cencos, String ucrmag) {


        altaViewModel.getGrabarODRPCON1().removeObservers(getViewLifecycleOwner());
        altaViewModel.GrabarODRPCON1(codcia, anoor1, nroor1, codalg, fempc1, pcName, codtmv, cencos, ucrmag);
        altaViewModel.getGrabarODRPCON1().observe(getViewLifecycleOwner(), result -> ResultadoGrabarODRPCON1(result));
    }

    private void ResultadoGrabarODRPCON1(Integer result) {
        if(result != null) {
            if(result > 0) {
                System.out.println("Registro grabado satisfactoriamente");
            }else{
                System.out.println("Registro no grabado");
            }

        }
    }


    public void DesbloquearTABCORRE(AltaViewModel altaViewModel, int codcia) {


        altaViewModel.getresultDesbloquearTABCORRE().removeObservers(getViewLifecycleOwner());
        altaViewModel.DesbloquearTABCORRE(codcia);
        altaViewModel.getresultDesbloquearTABCORRE().observe(getViewLifecycleOwner(), result -> ResultadoDesbloquearTABCORRE(result));
    }

    private void ResultadoDesbloquearTABCORRE(Integer result) {
        if(result != null) {
            if(result > 0) {
                System.out.println("Registro desbloqueado");
            }

        }
    }

    public void ActualizarCorrelativoAlmacen(AltaViewModel altaViewModel, String TipMovSeleccionado, int codAlm, String codcia, String pcName) {
        altaViewModel.getObtenerCorelativoAlmacen().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerCorelativoAlmacen(codAlm, Integer.parseInt(codcia), TipMovSeleccionado);
        altaViewModel.getObtenerCorelativoAlmacen().observe(getViewLifecycleOwner(), result -> manejarResultadoActualizarCorelativoAlmacen(result, altaViewModel, TipMovSeleccionado, codAlm, codcia, pcName));
    }

    private void manejarResultadoActualizarCorelativoAlmacen(List<DtoObtenerCorelativoAlmacenInput> result, AltaViewModel altaViewModel, String TipMovSeleccionado, int codAlm, String codcia, String pcName) {
        if(result != null) {
            DtoObtenerCorelativoAlmacenInput item = result.get(0);
            long nota = item.getNota();
            if(nota > 0) {
                TextInputEditTextNota.setText(String.valueOf(nota));

            }
        }
    }

    public void ObtenerCorrelativoAlmacen(AltaViewModel altaViewModel, String TipMovSeleccionado, int codAlm, String codcia, String pcName) {
        altaViewModel.getObtenerCorelativoAlmacen().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerCorelativoAlmacen(codAlm, Integer.parseInt(codcia), TipMovSeleccionado);
        altaViewModel.getObtenerCorelativoAlmacen().observe(getViewLifecycleOwner(), result -> manejarResultadoObtenerCorelativoAlmacen(result, altaViewModel, TipMovSeleccionado, codAlm, codcia, pcName));
    }

    private void manejarResultadoObtenerCorelativoAlmacen(List<DtoObtenerCorelativoAlmacenInput> result, AltaViewModel altaViewModel, String TipMovSeleccionado, int codAlm, String codcia, String pcName) {
        if(result != null) {
            DtoObtenerCorelativoAlmacenInput item = result.get(0);
            long nota = item.getNota();
            if(nota > 0) {
                TextInputEditTextNota.setText(String.valueOf(nota));
                altaViewModel.getUtilizaRegistroCorrelativoAlmacen().removeObservers(getViewLifecycleOwner());
                altaViewModel.UtilizaRegistroCorrelativoAlmacen(codAlm, Integer.parseInt(codcia), TipMovSeleccionado);
                altaViewModel.getUtilizaRegistroCorrelativoAlmacen().observe(getViewLifecycleOwner(), datos -> manejarUtilizacionRegistroCorrelativoAlmacen(datos, altaViewModel, TipMovSeleccionado, pcName, codAlm, codcia));
            }
        }
    }

    public void ObtenerCorrelativoAlmacenNota(AltaViewModel altaViewModel, String TipMovSeleccionado, int codAlm, String codcia) {
        altaViewModel.getObtenerCorelativoAlmacen().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerCorelativoAlmacen(codAlm, Integer.parseInt(codcia), TipMovSeleccionado);
        altaViewModel.getObtenerCorelativoAlmacen().observe(getViewLifecycleOwner(), result -> manejarResultadoObtenerCorelativoAlmacenNota(result, altaViewModel, TipMovSeleccionado, codAlm, codcia));
    }

    private void manejarResultadoObtenerCorelativoAlmacenNota(List<DtoObtenerCorelativoAlmacenInput> result, AltaViewModel altaViewModel, String TipMovSeleccionado, int codAlm, String codcia) {
        if(result != null) {
            DtoObtenerCorelativoAlmacenInput item = result.get(0);
            long nota = item.getNota();
            if(nota > 0) {
                TextInputEditTextNota.setText(String.valueOf(nota));
            }
        }
    }


    private void manejarUtilizacionRegistroCorrelativoAlmacen(List<DtoUtilizaRegistroCorrelativoAlmacenInput> result, AltaViewModel altaViewModel, String TipMovSeleccionado, String pcName, int codAlm, String codcia) {
        if(result != null) {

            String PcNameUsando = result.get(0).getPcName();

            if(!PcNameUsando.equals(pcName)) {

                mostrarDialog("El equipo " + PcNameUsando + " esta utilizando el registro.");
                //falso
            }else{
                System.out.println("No está utilizado");

                BloquearRegistroCorrelativo(altaViewModel, TipMovSeleccionado, pcName, codAlm, codcia);

                List<DtoObtenerDatosEtiqueta> TotalEtiquetas = codigoAdapter.ObtenerTotalEtiquetas();

                int codcompania = Integer.parseInt(codcia);
                int codalg = codAlm;
                String tmvmag = TipMovSeleccionado;
                String nmvmag = TextInputEditTextNota.getText().toString();
                String codtmv = ddlTipoDocumento.getText().toString().split(" - ")[0];
                String cencos = ddlCentroCosto.getText().toString().split(" - ")[0];
                int ademag = (ddlAlmacenDestino.getText().toString().split(" - ")[0].trim().length() == 0) ? 0 : Integer.parseInt(ddlAlmacenDestino.getText().toString().split(" - ")[0].trim());
                String refere = TextInputEditTextNota.getText().toString(); // Nota
                //Ordenes
                String ctdor1 = "";
                String anoor1 = "";
                String nroor1 = "";
                String cscor2 = "";
                String fecmag = "";

                try {
                    String inputDate = dateInput.getText().toString().trim();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    Date date = inputFormat.parse(inputDate);
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    fecmag = outputFormat.format(date);
                } catch (ParseException e) {
                    Log.e("DateParsingError", "Error al parsear la fecha: " + e.getMessage());
                }

                String ncrma2 = ""; // faltaaaaaaa
                // Declaración de variables
                int almacen = 0;
                int almacenX = 0;
                int cCostoX = 0;

                // Diferenciar
                if(tmvmag.equals("T")) {
                    almacen = ademag;
                }else if(tmvmag.equals("S")) {
                    almacen = codalg;
                }

                String fechaMovimiento = fecmag;

                List<DtoObtenerDatosStockEmpaqueInput> etiquetasStock = codigoAdapter.ObtenerTodasEtiquetasStock();
                String ObtenerTodasEtiquetas = codigoAdapter.ObtenerTodasEtiquetas();

             /*   ProcesarGuardado(altaViewModel,TotalEtiquetas,codcompania,codalg,tmvmag,nmvmag,codtmv,cencos,
                        ademag,refere,ctdor1,anoor1,nroor1,cscor2,fecmag,ncrma2,almacen,fechaMovimiento,pcName,etiquetasStock,ObtenerTodasEtiquetas );
*/
           /*     for (DtoObtenerDatosEtiqueta obj : TotalEtiquetas) {
                    String codigoEtiqueta = obj.getCodigoEtiqueta();
                   // int codcompania = Integer.parseInt(codcia);
                    String codigoArticulo = obj.getCodigoArticulo();
                   // int codalg = codAlm;
                   // String tmvmag = TipMovSeleccionado; // movimiento si es "S" o "T"
                    //String nmvmag = TextInputEditTextNota.getText().toString(); // Nota
                    String cscmag = obj.getSecuencia(); // Secuencia
                    int secma2 = 0;
                    //String codtmv = ddlTipoDocumento.getText().toString().split(" - ")[0]; // Tipo de Movimiento seleccionado
                    String cremag = obj.getStock();
                   // String cencos = ddlCentroCosto.getText().toString().split(" - ")[0];
                    //int ademag = (ddlAlmacenDestino.getText().toString().split(" - ")[0].trim().length() == 0) ? 0 : Integer.parseInt(ddlAlmacenDestino.getText().toString().split(" - ")[0].trim());
                    String ucrmag = "";// Falta  usuario de sesion
                    String caemag = obj.cantidad;   //cantidad empaque
                  //  String refere = TextInputEditTextNota.getText().toString(); // Nota

                    //Ordenes
                    String ctdor1 = "";
                    String anoor1 = "";
                    String nroor1 = "";
                    String cscor2 = "";

                    String fecmag = "";

                    try {

                        String inputDate = dateInput.getText().toString().trim();


                        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        Date date = inputFormat.parse(inputDate);


                        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        fecmag = outputFormat.format(date);


                    } catch (ParseException e) {
                        Log.e("DateParsingError", "Error al parsear la fecha: " + e.getMessage());
                    }

                    String ncrma2 = ""; // faltaaaaaaa

                    // Declaración de variables
                    int almacen = 0;
                    int almacenX = 0;
                    int cCostoX = 0;

                    // Diferenciar
                    if(tmvmag.equals("T")) {
                        almacen = ademag;
                    }else if(tmvmag.equals("S")) {
                        almacen = codalg;
                    }

                    String codtex = obj.getCodigoExistencia();
                    String codexiv = obj.getCodigoArticulo();
                    String fechaMovimiento = fecmag;


                    ValidarAlamcenXCcosto(altaViewModel, codalg, cencos,codtex,codexiv,fechaMovimiento,
                            codigoEtiqueta, codcompania,tmvmag, nmvmag, cscmag, secma2, codtmv, cremag,
                            ademag, ucrmag,caemag,refere, ctdor1, anoor1, nroor1, cscor2,
                            fecmag,pcName,ncrma2
                            );

                }

                DesbloquearFABCORRE( altaViewModel,  Integer.parseInt(codcia), codAlm, TipMovSeleccionado) ;
                String nmvmag = TextInputEditTextNota.getText().toString(); // Nota
                mostrarDialog("Registro grabado satisfactoriamente Movimiento Nº " + nmvmag);
*/
            }
        }
    }

    public void ProcesarGuardado(AltaViewModel altaViewModel, DtoProcesarGuardado request) {
        progressDialog.show(getParentFragmentManager(), "progress");
        altaViewModel.getresultProcesarGuardado().removeObservers(getViewLifecycleOwner());
        altaViewModel.ProcesarGuardado(request);
        altaViewModel.getresultProcesarGuardado().observe(getViewLifecycleOwner(), result -> {
            if(result != null) {
                dateInput.setEnabled(true);
                ActualizarCorrelativoAlmacen(altaViewModel, request.getTmvmag(), request.getCodalg(), String.valueOf(request.getCodcompania()), request.getPcName());
                codigoAdapter.clearAdapter();
                TextInputEditTextSeleccionados.setText(String.valueOf(codigoAdapter.cantidadItems()));

                txtTotalConos.setText(String.valueOf(codigoAdapter.cantidadConos()));
                txtTotalKg.setText(df.format(codigoAdapter.cantidadKg()));
                String mensaje = result;
                if(progressDialog != null) {
                    progressDialog.dismiss();
                }
                compania_layout.setEnabled(true);
                tipo_layout.setEnabled(true);
                almacenOrigen_layout.setEnabled(true);
                centroCosto_layout.setEnabled(true);

                ddlCompania.setEnabled(true);
                ddlAlmacenOrigen.setEnabled(true);
                ddlTipoDocumento.setEnabled(true);
                ddlCentroCosto.setEnabled(true);

                BorrarDatosGuardados();
                mostrarDialog(mensaje);
            }
        });
    }

    private String obtenerFechaInput() {
        String inputDate = dateInput.getText().toString().trim();
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = inputFormat.parse(inputDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return outputFormat.format(date);
        } catch (ParseException e) {
            Log.e("DateParsingError", "Error al parsear la fecha: " + e.getMessage());
            return "";
        }
    }

    // Método para obtener la nota
    private String obtenerNota() {
        return TextInputEditTextNota.getText().toString();
    }

    public void ValidarAlamcenXCcosto(AltaViewModel altaViewModel, int codalg, String cencos, String codtex,
                                      String codexiv, String fechaMovimiento, String codigoEtiqueta, int codcompania,
                                      String tmvmag, String nmvmag, String cscmag, int secma2,
                                      String codtmv, String cremag, int ademag, String ucrmag, String caemag,
                                      String refere, String ctdor1, String anoor1, String nroor1, String cscor2,
                                      String fecmag, String pcName, String ncrma2) {
        altaViewModel.getValidarAlamcenXCcostoInput().removeObservers(getViewLifecycleOwner());
        altaViewModel.ValidarAlamcenXCcosto(codalg);
        altaViewModel.getValidarAlamcenXCcostoInput().observe(getViewLifecycleOwner(), result -> {
            if(result != null && !result.isEmpty()) {

                DtoValidarAlamcenXCcostoInput obj_ = result.get(0);

                // Accede a los datos del primer elemento
                int codAlmacen = obj_.getCodAlg();
                String Codcos = obj_.getCodCos();

                if(codalg == codAlmacen && !cencos.equals(Codcos)) {
                    mostrarDialog("El centro de costo no corresponde al almacen");

                    return;
                }else{
                    CriterioTipoExistencia(altaViewModel, codtex, codigoEtiqueta, codcompania, codalg, tmvmag, nmvmag, cscmag, secma2,
                            codtmv, cremag, cencos, ademag, ucrmag,
                            caemag, refere, ctdor1, anoor1, nroor1, cscor2, fecmag, pcName, ncrma2);
                }

            }else{
                // Manejar caso de error o lista vacía
                Log.e("Resultado", "No se encontró ningún almacén");
            }
        });

    }

    public void BloquearRegistroCorrelativo(AltaViewModel altaViewModel, String TipMovSeleccionado, String pcName, int codAlm, String codcia) {


        altaViewModel.getresultBloquearRegistroCorrelativo().removeObservers(getViewLifecycleOwner());
        altaViewModel.BloquearRegistroCorrelativo(pcName, codAlm, Integer.parseInt(codcia), TipMovSeleccionado);
        altaViewModel.getresultBloquearRegistroCorrelativo().observe(getViewLifecycleOwner(), result -> manejarResultadoBloquearRegistroCorrelativo(result, altaViewModel));
    }

    private void manejarResultadoBloquearRegistroCorrelativo(Integer result, AltaViewModel altaViewModel) {
        if(result != null) {
            if(result > 0) {

                System.out.println("Registro Bloqueado Correlativo");
            }else{
                System.out.println("Registro no Bloqueado Correlativo");
            }
        }
    }

    public void BloquearRegistroCorrelativoNull(AltaViewModel altaViewModel, String TipMovSeleccionado, String pcName, int codAlm, String codcia) {


        altaViewModel.getresultBloquearRegistroCorrelativoNull().removeObservers(getViewLifecycleOwner());
        altaViewModel.BloquearRegistroCorrelativoNull(pcName, codAlm, Integer.parseInt(codcia), TipMovSeleccionado);
        altaViewModel.getresultBloquearRegistroCorrelativoNull().observe(getViewLifecycleOwner(), result -> manejarResultadoBloquearRegistroCorrelativoNull(result, altaViewModel));
    }

    private void manejarResultadoBloquearRegistroCorrelativoNull(Integer result, AltaViewModel altaViewModel) {
        if(result != null) {
            if(result > 0) {
                System.out.println("Registro Bloqueado Correlativo Null");
            }else{
                System.out.println("Registro no Bloqueado Correlativo Null");
            }
        }
    }

    private void procesarFecha(AltaViewModel altaViewModel, String fecmagX, ValidacionCallback callback) {
        final String[] fecsis = {""};
        final String[] fecmen = {""};
        final String[] fecact = {""};
        final boolean[] validacion = {false};
        final String[] fecmagX2 = {fecmagX};

        altaViewModel.getObtenerHoraSistema().removeObservers(getViewLifecycleOwner());
        altaViewModel.ObtenerHoraSistema();
        altaViewModel.getObtenerHoraSistema().observe(getViewLifecycleOwner(), datos -> {

            if(datos != null) {
                int hora = datos.getHoraSistema();
                int minuto = datos.getMinutoSistema();
                int segundo = datos.getSegundoSistema();
                int calculo = (hora * 3600) + (minuto * 60) + segundo;

                if(calculo >= 0 && calculo <= 25199) {
                    altaViewModel.getObtenerFechaSistemaAyer().removeObservers(getViewLifecycleOwner());
                    altaViewModel.ObtenerFechaSistemaAyer();
                    altaViewModel.getObtenerFechaSistemaAyer().observe(getViewLifecycleOwner(), datosFechaSistemaAyer -> {

                        if(datosFechaSistemaAyer != null) {
                            fecsis[0] = datosFechaSistemaAyer.getFechaSistema();
                            int fecsisX = Integer.parseInt(fecsis[0].substring(0, 4) + fecsis[0].substring(5, 7) + fecsis[0].substring(8, 10));

                            altaViewModel.getObtenerDiasPermitidosAyer().removeObservers(getViewLifecycleOwner());
                            altaViewModel.ObtenerDiasPermitidosAyer();
                            altaViewModel.getObtenerDiasPermitidosAyer().observe(getViewLifecycleOwner(), datosDiasPermitidosAyer -> {
                                if(datosDiasPermitidosAyer != null) {
                                    fecmen[0] = datosDiasPermitidosAyer.getFecMen();
                                    fecact[0] = datosDiasPermitidosAyer.getFecAct();
                                    fecmagX2[0] = fecmagX2[0].substring(0, 4) + fecmagX2[0].substring(5, 7) + fecmagX2[0].substring(8, 10);

                                    if(Integer.parseInt(fecmagX2[0]) < Integer.parseInt(fecmen[0]) || Integer.parseInt(fecmagX2[0]) > Integer.parseInt(fecact[0])) {
                                        validacion[0] = false;
                                    }else if(fecsisX != Integer.parseInt(fecmagX2[0])) {
                                        validacion[0] = false;
                                    }else{
                                        validacion[0] = true;
                                    }

                                    // Llama al callback pasando el estado final de validacion[0]
                                    callback.onValidacionFinalizada(validacion[0]);
                                }
                            });
                        }
                    });
                }else{
                    // Procesa la fecha de hoy
                    altaViewModel.getObtenerFechaSistema().removeObservers(getViewLifecycleOwner());
                    altaViewModel.ObtenerFechaSistema();
                    altaViewModel.getObtenerFechaSistema().observe(getViewLifecycleOwner(), datosFechaSistema -> {

                        if(datosFechaSistema != null) {
                            fecsis[0] = datosFechaSistema.getFechaSistema();
                            int fecsisX = Integer.parseInt(fecsis[0].substring(0, 4) + fecsis[0].substring(5, 7) + fecsis[0].substring(8, 10));

                            altaViewModel.getObtenerDiasPermitidos().removeObservers(getViewLifecycleOwner());
                            altaViewModel.ObtenerDiasPermitidos();
                            altaViewModel.getObtenerDiasPermitidos().observe(getViewLifecycleOwner(), datosDiasPermitidos -> {
                                if(datosDiasPermitidos != null) {
                                    fecmen[0] = datosDiasPermitidos.getFecMen();
                                    fecact[0] = datosDiasPermitidos.getFecAct();
                                    fecmagX2[0] = fecmagX2[0].substring(0, 4) + fecmagX.substring(5, 7) + fecmagX.substring(8, 10);

                                    if(Integer.parseInt(fecmagX2[0]) < Integer.parseInt(fecmen[0]) || Integer.parseInt(fecmagX2[0]) > Integer.parseInt(fecact[0])) {
                                        validacion[0] = false;
                                    }else if(fecsisX != Integer.parseInt(fecmagX2[0])) {
                                        validacion[0] = false;
                                    }else{
                                        validacion[0] = true;
                                    }

                                    // Llama al callback pasando el estado final de validacion[0]
                                    callback.onValidacionFinalizada(validacion[0]);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    interface ValidacionCallback {
        void onValidacionFinalizada(boolean esValido);
    }

    private void showDatePickerDialog() {
        // Inflar el layout del diálogo
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);

        // Obtener el EditText del layout
        EditText editTextDate = dialogView.findViewById(R.id.edit_text_date);

        // Obtener la fecha actual o la fecha en el EditText
        String dateText = dateInput.getText().toString();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));

        // Configurar la fecha inicial del DatePickerDialog
        if(!TextUtils.isEmpty(dateText)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            try {
                Date date = dateFormat.parse(dateText);
                if(date != null) {
                    calendar.setTime(date);
                    editTextDate.setText(dateText);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Configurar el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Formatear la fecha y establecerla en el EditText
                        String date = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        editTextDate.setText(date);
                    }
                },
                year, month, day
        );

        // Configurar el DatePickerDialog para actualizar el EditText
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // Crear el AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);

        // Configurar los botones
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción cuando se presiona Aceptar
                // Puedes obtener el valor de editTextDate.getText().toString() aquí
                //  dateInput.setText(editTextDate.getText().toString());
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Cierra el diálogo
            }
        });

        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void Cancelar(AltaViewModel altaViewModel) {
        txtcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    int cantidadItems = codigoAdapter.cantidadItems();

                    if(cantidadItems > 0) {
                        mostrarDialog("No se permite borrar los datos, existen etiquetas agregadas.");
                    }else{
                        String xrnoalg = textViewRequiereNroOrden.getText().toString();
                        String xrcoalg = textViewRequiereCscOrden.getText().toString();
                        String xrcnalg = textVierequiereNroCarga.getText().toString();

                        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MisDatos", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("nroOrden");
                        editor.remove("nroCarga");
                        editor.apply(); // Confirmar los cambios

                        // Agregado según condición
                        if(xrcnalg.equals("S")) {
                            txtnrocarga.setText("");
                            txtnrocarga.setEnabled(true);
                        }else{
                            txtnrocarga.setText("");
                            txtnrocarga.setEnabled(false); // Para deshabilitar la edición
                        }

                        if(xrnoalg.equals("S") && xrcoalg.equals("N")) {
                            txtanio.setText("");
                            txtnroorden.setText("");
                            txtnroorden.setEnabled(true);
                            txtnrocargamaximo.setText("");
                            txtproceso.setText("");
                            //txtTipoDoc.setText("");
                            txtanio.setText("");
                            txtarticulo.setText("");

                            txtconsecutivo.setText("");
                            txtconsecutivo.setEnabled(false);
                            txtarticulo.setEnabled(false);

                        }else if(xrnoalg.equals("S") && xrcoalg.equals("S")) {
                            txtanio.setText("");
                            txtnroorden.setText("");
                            txtnroorden.setEnabled(true);
                            txtnrocargamaximo.setText("");
                            txtproceso.setText("");
                            //txtTipoDoc.setText("");
                            txtanio.setText("");
                            txtarticulo.setText("");

                            txtconsecutivo.setText("");
                            txtconsecutivo.setEnabled(true);

                            txtarticulo.setEnabled(true);
                            txtarticulo.setText("");

                        }else if(xrnoalg.equals("N") && xrcoalg.equals("N")) {
                            txtanio.setText("");
                            txtnroorden.setText("");
                            txtnroorden.setEnabled(false);
                            txtnrocargamaximo.setText("");
                            txtproceso.setText("");
                            txtconsecutivo.setText("");
                            txtconsecutivo.setEnabled(false);
                            txtarticulo.setEnabled(false);
                        }
                    }
                } catch (Exception e) {
                    if(progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    Log.e("API Error", "Error al Cancelar : " + e.getMessage(), e);
                }
            }
        });
    }




    public void agregarCodigoACola(AltaViewModel altaViewModel, String codigo) {
        colaCodigos.add(codigo);
        procesarSiguienteCodigo(altaViewModel);
    }

    private void procesarSiguienteCodigo(AltaViewModel altaViewModel) {
        if (estaProcesando || colaCodigos.isEmpty()){
            if(estaProcesando){
                btnGuardar.setEnabled(false); // Bloquear botón
                mostrarLoading(true);
                //btnGuardar.setIconResource(R.drawable.loading_ttsa); // Agregar icono de loading
                btnGuardar.setText("Cargando etiquetas...");
            }else{
                btnGuardar.setEnabled(true); // Desbloquear botón
                //btnGuardar.setIcon(null); // Quitar icono de loading
                mostrarLoading(false);
                btnGuardar.setText("Guardar");
            }
            return;
        }else{
            btnGuardar.setEnabled(false); // Bloquear botón
            mostrarLoading(true);
            //btnGuardar.setIconResource(R.drawable.loading_ttsa); // Agregar icono de loading
            btnGuardar.setText("Cargando etiquetas...");

            estaProcesando = true;
            String codigo = colaCodigos.poll(); // Obtiene y remueve el primer código
            //procesarCodigo(codigo);
            lecturaEtiqueta(altaViewModel, codigo);
        }
    }


    private void mostrarLoading(boolean mostrar) {
        if (mostrar) {
            //imgLoading.setVisibility(View.VISIBLE);
            layoutLoading.setVisibility(View.VISIBLE);
            btnGuardar.setVisibility(View.GONE);
            btnGuardar.setEnabled(false);
            btnGuardar.setText("Guardando...");
            Glide.with(this)
                    .asGif()
                    .load(R.drawable.loading_ttsa)  // Tu archivo GIF en drawable
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgLoading);
        } else {
            //imgLoading.setVisibility(View.GONE);
            layoutLoading.setVisibility(View.GONE);
            btnGuardar.setEnabled(true);
            btnGuardar.setVisibility(View.VISIBLE);
            btnGuardar.setText("Guardar");
            //btnGuardar.setIcon(null); // Remueve el GIF y deja el icono normal
        }
    }

    @Override
    public void onTotalesChanged(int totalEtiquetas, int totalConos, double totalKg) {
        TextInputEditTextSeleccionados.setText(String.valueOf(totalEtiquetas));
        txtTotalConos.setText(String.valueOf(totalConos));
        txtTotalKg.setText(df.format(totalKg));
    }

    private void mostrarAlertaConfirmacionEtiqueta(Context context, DialogInterface.OnClickListener onClickListener){

    }
}