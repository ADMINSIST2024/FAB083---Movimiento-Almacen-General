package com.example.fab083.ui.slideshow;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fab083.R;
import com.example.fab083.api.ApiResponseMovimiento;
import com.example.fab083.databinding.FragmentSlideshowBinding;
import com.example.fab083.dtos.DtoAlmacen;
import com.example.fab083.dtos.DtoMovimientoAlmacenInput;
import com.example.fab083.dtos.DtoMovimientoAlmacenOutput;
import com.example.fab083.model.CodigoAdapter;
import com.example.fab083.model.ConsultaMovimientoAdapter;
import com.example.fab083.ui.home.AltaViewModel;
import com.example.fab083.ui.progres.ProgressDialogFragment;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SlideshowFragment extends Fragment {
    private ProgressDialogFragment progressDialog;
    private RecyclerView recyclerView;
    private CodigoAdapter codigoAdapter;
    private TextInputEditText TextInputEditTextFecha,TextInputEditTextCodigo,TextInputEditTextSeleccionados;
    private TextInputLayout TextInputLayoutCodigo;
    private FragmentSlideshowBinding binding;
    private ArrayAdapter<DtoAlmacen> adapterAlmacenOrigen;
    private List<DtoAlmacen> almacenList;
    private AutoCompleteTextView ddlAlmacenOrigen;

    private ConsultaMovimientoAdapter adapter;
    private List<DtoMovimientoAlmacenInput> dataList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);
        AltaViewModel altaViewModel = new ViewModelProvider(this).get(AltaViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextInputEditTextSeleccionados= root.findViewById(R.id.TextInputEditTextSeleccionados);
        TextInputEditTextCodigo= root.findViewById(R.id.TextInputEditTextCodigo);
        TextInputLayoutCodigo = root.findViewById(R.id.TextInputLayoutCodigo);
        TextInputEditTextFecha = root.findViewById(R.id.TextInputEditTextFecha);
        ddlAlmacenOrigen = root.findViewById(R.id.ddlAlmacenOrigen);
        progressDialog = new ProgressDialogFragment();
        CargarCboAlmacenOrigen(altaViewModel);
        ConfigurarFecha();
        ConfigurarBusqueda(slideshowViewModel);

        recyclerView = root.findViewById(R.id.listamovimientos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Configurar el idioma en español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        getResources().getConfiguration().setLocale(locale);
        getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());

        return root;
    }
public void ConfigurarBusqueda( SlideshowViewModel slideshowViewModel )
{

    // Configura el OnClickListener para el ícono del teclado
    TextInputLayoutCodigo.setEndIconOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            progressDialog.show(getParentFragmentManager(), "progress");


            boolean respuestavalidar;
            respuestavalidar= validarCampos();

            if (!respuestavalidar)
            {
                String fecha= String.valueOf(TextInputEditTextFecha.getText());
                int codigo = TextUtils.isEmpty(TextInputEditTextCodigo.getText().toString()) ? 0 : Integer.parseInt(TextInputEditTextCodigo.getText().toString());
                int almacen = !ddlAlmacenOrigen.getText().toString().trim().isEmpty() ? Integer.parseInt(ddlAlmacenOrigen.getText().toString().split(" - ")[0]) : 0;

                DtoMovimientoAlmacenOutput obj=new DtoMovimientoAlmacenOutput();
                obj.fecmag=fecha;
                obj.codalg=almacen;
                obj.codexi=codigo;

                Consultar(slideshowViewModel,obj);
            }
            else {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            }






        }



    });


}
public void Consultar(SlideshowViewModel slideshowViewModel,DtoMovimientoAlmacenOutput obj)
{
    slideshowViewModel.getresultConsultaMovimiento().removeObservers(getViewLifecycleOwner());
    slideshowViewModel.ConsultarMovimiento(obj);
    slideshowViewModel.getresultConsultaMovimiento().observe(getViewLifecycleOwner(), result -> manejarResultadoConsultar(result));

}
    private void manejarResultadoConsultar(ApiResponseMovimiento result) {
        if(result != null) {

            boolean resultSuccess = result.isSuccess();
            String mensaje = result.getMessage();
            List<DtoMovimientoAlmacenInput> Lista = result.getResult();


              adapter = new ConsultaMovimientoAdapter(Lista);
             recyclerView.setAdapter(adapter);
            TextInputEditTextSeleccionados.setText(String.valueOf(adapter.getItemCount()));

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    public void ConfigurarFecha()
    {
        TextInputEditTextFecha.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();

            String dateText = TextInputEditTextFecha.getText().toString();
            if (!dateText.isEmpty()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
                    Date date = sdf.parse(dateText);
                    if (date != null) {
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
                        TextInputEditTextFecha.setText(sdf.format(calendar.getTime()));
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            // Muestra el DatePickerDialog
            datePickerDialog.show();

           /* MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
            builder.setTitleText("Seleccione una fecha");

            CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
            builder.setCalendarConstraints(constraintsBuilder.build());

            final MaterialDatePicker<Long> materialDatePicker = builder.build();

            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(selection);
                TextInputEditTextFecha.setText(sdf.format(calendar.getTime()));
            });

            materialDatePicker.show(getChildFragmentManager(), "DATE_PICKER");*/
        });
        // Obtén la fecha actual
        Calendar fec = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(fec.getTime());

        // Establece la fecha actual en el TextInputEditText
        TextInputEditTextFecha.setText(currentDate);

    }

    private void CargarCboAlmacenOrigen(AltaViewModel altaViewModel) {
        altaViewModel.getAlmacen().observe(getViewLifecycleOwner(), new Observer<List<DtoAlmacen>>() {
            @Override
            public void onChanged(List<DtoAlmacen> items) {
                almacenList = items;
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
                ddlAlmacenOrigen.setAdapter(adapterAlmacenOrigen);


            }
        });
    }


    public boolean validarCampos()
    {
        boolean hayErrores = false;

        String codAlm = ddlAlmacenOrigen.getText().toString().split(" - ")[0];




      if(codAlm == null || codAlm.trim().isEmpty()) {
            mostrarDialog("Debe seleccionar el Almacen de Origen");
            hayErrores = true;
        }

        return  hayErrores;
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}