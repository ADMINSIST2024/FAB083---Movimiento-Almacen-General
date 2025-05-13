package com.example.fab083.model;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fab083.R;
import com.example.fab083.dtos.DtoEtiquetas;
import com.example.fab083.dtos.DtoObtenerDatosEtiqueta;
import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;
import com.example.fab083.dtos.DtoObtenerDatosStockEmpaqueInput;
import com.example.fab083.ui.home.AltaFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Console;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CodigoAdapter extends RecyclerView.Adapter<CodigoAdapter.CodigoViewHolder> {

    private int highlightedPosition = -1;
    TextInputEditText TextInputEditTextCodigo, EtTotalEtiquetas, EtTotalConos, EtTotalKg;
    //public List<String> codigoList;
    public List<DtoObtenerDatosEtiquetaInput> codigoList;
    public int contadorSecuencia;

    private OnTotalesChangeListener totalesChangeListener;
    public CodigoAdapter(TextInputEditText _textInputEditTextCodigo, OnTotalesChangeListener listener) {
        this.codigoList = new ArrayList<>();
        this.contadorSecuencia = 1;
        this.TextInputEditTextCodigo = _textInputEditTextCodigo;
        this.totalesChangeListener = listener;
    }
    public void addValores(DtoObtenerDatosEtiquetaInput dtoEtiquetas, int secuencia){
        dtoEtiquetas.setSecuencia(secuencia);
        dtoEtiquetas.setSalida_stock(dtoEtiquetas.getCremang());
        dtoEtiquetas.setSalida_cantidad((int)dtoEtiquetas.getCaemag());
        codigoList.add(dtoEtiquetas);
        notifyItemInserted(codigoList.size() -1);
    }
    public void addValoresGuardados(DtoObtenerDatosEtiquetaInput valor)
    {
        codigoList.add(valor);
        notifyItemInserted(codigoList.size() -1);
        //notifyDataSetChanged();
    }

    public void editVarlores(int posicion, String cantidad){
        //codigoList[posicion] =
    }
    public boolean contains(String codigo_etiqueta) {
        for (DtoObtenerDatosEtiquetaInput item : codigoList) {
            if (item.getCodigo().startsWith(codigo_etiqueta)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public CodigoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_lista, parent, false);
        return new CodigoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CodigoViewHolder holder, int position) {
        //int adapterPosition = holder.getAdapterPosition();
        int adapterPosition = position;
        holder.salida_cantidad.removeTextChangedListener(holder.textWatcher);

        if (adapterPosition != RecyclerView.NO_POSITION){
            String codigoEtiqueta = codigoList.get(adapterPosition).getCodigo();
            String codigoArticulo = codigoList.get(adapterPosition).getCodexi();
            String descripcion_articulo = codigoList.get(adapterPosition).getDesexi();
            String lote = codigoList.get(adapterPosition).getNlhmag();
            String um = codigoList.get(adapterPosition).getUnimed();
            String tipo_existencia = codigoList.get(adapterPosition).getDestipexi();
            String umreal = codigoList.get(adapterPosition).getUrhmag();
            String cod_prov = codigoList.get(adapterPosition).getCodprv();
            String secuencia = String.valueOf(adapterPosition + 1);
            String maquina = codigoList.get(adapterPosition).getDesmaq();

            double stock_peso = codigoList.get(adapterPosition).getCremang();

            int stock_cantidad = (int) codigoList.get(adapterPosition).getCaemag();

            //codigoList.get(adapterPosition).setSalida_cantidad((int)(codigoList.get(adapterPosition).getSalida_stock() == 0 ? codigoList.get(adapterPosition).getCaemag() :  codigoList.get(adapterPosition).getSalida_stock()));

            int salida_cantidad = (int) (codigoList.get(adapterPosition).getSalida_cantidad() == 0 ? codigoList.get(adapterPosition).getCaemag() :  codigoList.get(adapterPosition).getSalida_cantidad());
            double salida_peso = (codigoList.get(adapterPosition).getSalida_stock() == 0 ? codigoList.get(adapterPosition).getCremang() :  codigoList.get(adapterPosition).getSalida_stock());  ;
            double salida_peso_unitario = codigoList.get(adapterPosition).getPeso_unitario();

            // Configurar el separador decimal como punto
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            symbols.setDecimalSeparator('.');
            DecimalFormat df = new DecimalFormat("#.###",symbols);

            holder.codigo_etiqueta.setText(codigoEtiqueta);
            holder.codigo_articulo.setText(codigoArticulo);
            //holder.color_hilado.setText(color);
            holder.nro_lote.setText(lote);
            holder.nro_stock.setText(String.valueOf(stock_peso));
            holder.um.setText(um);
            holder.codigoExistencia.setText(tipo_existencia);
            holder.umreal.setText(umreal);
            holder.cod_prov.setText(cod_prov);
            holder.cantidad.setText(String.valueOf(stock_cantidad));
            holder.secuencia.setText(secuencia);
            holder.mMenus.setImageResource(R.drawable.codbarra3); // Cambia la imagen según tus necesidades
            holder.descripcion_articulo.setText(descripcion_articulo.toString());
            holder.maquina.setText(maquina);

            holder.salida_stock.setText((df.format(salida_peso)).concat(" " + um));
            holder.salida_cantidad.setText(String.valueOf(salida_cantidad));

            // Eliminar cualquier TextWatcher previo para evitar acumulaciones
            /*if (holder.salida_cantidad != null) {
                holder.salida_cantidad.removeTextChangedListener(holder.textWatcher);
            }*/

            holder.textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try{
                        int salida_cant = Integer.parseInt(charSequence.toString());

                        if(salida_cant == 0){
                            holder.salida_stock.setTextColor(Color.RED);
                            holder.salida_cantidad.setError("Debes ingresar una cantidad mayor a 0");
                            codigoList.get(holder.getAdapterPosition()).setSalida_cantidad(salida_cant);
                        }else {
                            codigoList.get(holder.getAdapterPosition()).setSalida_cantidad(salida_cant);

                            //validacion para mostrar error y cambiar de color al texto
                            if(salida_cant > stock_cantidad){
                                holder.salida_stock.setTextColor(Color.RED);
                                holder.salida_cantidad.setError("Cantidad ingresada excede al stock");
                            }else {
                                holder.salida_stock.setTextColor(Color.WHITE);
                                if(salida_cant == stock_cantidad){
                                    holder.salida_stock.setText(String.valueOf(codigoList.get(holder.getAdapterPosition()).getCremang()));
                                    codigoList.get(holder.getAdapterPosition()).setSalida_stock(codigoList.get(holder.getAdapterPosition()).getCremang());
                                }else{
                                    Double num = (salida_peso_unitario * salida_cant);

                                    String calculo = df.format(num);
                                    holder.salida_stock.setText( calculo.concat(" " + um));
                                    codigoList.get(holder.getAdapterPosition()).setSalida_stock(Double.parseDouble(calculo));
                                }
                            }
                        }
                        if (totalesChangeListener != null) {
                            totalesChangeListener.onTotalesChanged(cantidadItems(),cantidadConos(), cantidadKg());
                        }

                    }catch (Exception e){

                        holder.salida_stock.setText("0".concat(" " + um));
                        codigoList.get(holder.getAdapterPosition()).setSalida_stock(0);
                        codigoList.get(holder.getAdapterPosition()).setSalida_cantidad(0);
                        //holder.salida_cantidad.setText(String.valueOf(salida_cantidad));
                        //holder.salida_cantidad.selectAll();
                        holder.salida_cantidad.hasFocus();

                        if (totalesChangeListener != null) {
                            totalesChangeListener.onTotalesChanged(cantidadItems(),cantidadConos(), cantidadKg());
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {}
            };

            holder.salida_cantidad.addTextChangedListener(holder.textWatcher);

            holder.salida_cantidad.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if(i == EditorInfo.IME_ACTION_DONE ||
                            (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN)){

                        InputMethodManager imm = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);

                        new Handler(Looper.getMainLooper()).postDelayed(() -> {
                            TextInputEditTextCodigo.requestFocus();
                            imm.hideSoftInputFromWindow(TextInputEditTextCodigo.getWindowToken(), 0);
                        }, 150);

                        return true;
                    }

                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return codigoList.size();
    }

    public static class CodigoViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView mMenus;
        TextView codigo_etiqueta, codigo_articulo, umreal, cantidad, nro_lote, nro_stock, um,codigoExistencia,cod_prov,secuencia, salida_stock, descripcion_articulo, maquina;
        Button btnEditar;
        TextInputEditText salida_cantidad;
        TextWatcher textWatcher;

        public CodigoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            mMenus = itemView.findViewById(R.id.mMenus);
            codigo_etiqueta = itemView.findViewById(R.id.codigo_etiqueta);
            codigo_articulo = itemView.findViewById(R.id.codigo_articulo);
            //color_hilado = itemView.findViewById(R.id.color_hilado);
            nro_lote = itemView.findViewById(R.id.nro_lote);
            nro_stock = itemView.findViewById(R.id.nro_stock);
            //salida = itemView.findViewById(R.id.salida);
            um = itemView.findViewById(R.id.um);
            codigoExistencia=itemView.findViewById(R.id.existencia);
            umreal=itemView.findViewById(R.id.umreal);
            cod_prov=itemView.findViewById(R.id.cod_prov);
            cantidad=itemView.findViewById(R.id.cantidad);
            secuencia=itemView.findViewById(R.id.secuencia);
            btnEditar = itemView.findViewById(R.id.btneditar);
            salida_stock = itemView.findViewById(R.id.label_salida_stock);
            salida_cantidad = itemView.findViewById(R.id.txt_salida_cantidad);
            descripcion_articulo = itemView.findViewById(R.id.descripcion_articulo);
            maquina = itemView.findViewById(R.id.maquina);
        }
    }

    public List<String> getAllCodigoEtiquetas() {
        List<String> etiquetas = new ArrayList<>();
        for (DtoObtenerDatosEtiquetaInput item : codigoList) {
            etiquetas.add(String.valueOf(item.codtex)); // `codigos[0]` es `codigoEtiqueta`
        }
        return etiquetas;
    }

    public String ObtenerTodasEtiquetas() {
        List<String> etiquetas = new ArrayList<>();
        StringBuilder res = new StringBuilder();

        for (DtoObtenerDatosEtiquetaInput item : codigoList) {
            if (res.length() > 0) { // Agregar coma solo si no es el primer elemento
                res.append(",");
            }
            res.append("'").append(item.getCodigo()).append("'");
        }

        return res.toString();
    }

    public List<DtoObtenerDatosStockEmpaqueInput> ObtenerTodasEtiquetasStock() {

        List<DtoObtenerDatosStockEmpaqueInput> etiquetas = new ArrayList<>();

        for (DtoObtenerDatosEtiquetaInput item : codigoList) {
            // Separar el código en partes
            DtoObtenerDatosStockEmpaqueInput dto = new DtoObtenerDatosStockEmpaqueInput();
            dto.setCodigoEtiqueta(item.getCodigo()); // codigos[0] es codigoEtiqueta
            dto.setStockreal(item.getCremang()); // Asumiendo que codigos[5] es un número
            etiquetas.add(dto);
        }

        return etiquetas;
    }

    public List<DtoObtenerDatosEtiqueta> ObtenerTotalEtiquetas() {

        List<DtoObtenerDatosEtiqueta> etiquetas = new ArrayList<>();

        for (DtoObtenerDatosEtiquetaInput items : codigoList) {
            DtoObtenerDatosEtiqueta dto = new DtoObtenerDatosEtiqueta();

            dto.setSecuencia(String.valueOf(items.getSecuencia()));
            dto.setCodigoEtiqueta(items.getCodigo()); // codigos[0] es codigoEtiqueta
            dto.setCodigoArticulo(items.getCodexi());
            dto.setDesMaq(items.getDesmaq());
            dto.setLote(items.getLtomag());
            dto.setCod_prov(items.getCodprv()) ;
            dto.setCodigoExistencia(String.valueOf(items.getCodtex()));
            dto.setUm(items.getUnimed());
            dto.setUmreal(items.getUrhmag()) ;



            dto.setColor(items.getCodchi());
            //dto.setCodigoExistencia(String.valueOf(items.getCodtex()));
            dto.setSalida_cantidad(items.getSalida_cantidad());
            dto.setSalida_peso(items.getSalida_stock());
            dto.setStock_cantidad((int)items.getCaemag());
            dto.setStock_peso(items.getCremang());

            etiquetas.add(dto);
        }

        return etiquetas;
    }

    public void clearAdapter() {
        codigoList.clear(); // Limpiar la lista
        contadorSecuencia = 1; // Reiniciar el contador si es necesario
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }
    public int cantidadItems()
    {
        return codigoList.size();
    }

    public int cantidadConos()
    {
        int cant = 0;
        for (DtoObtenerDatosEtiquetaInput  item : codigoList) {
            cant = (int) (cant + item.getSalida_cantidad());
        }

        return cant;
    }

    public double cantidadKg()
    {
        double cant = 0;
        for (DtoObtenerDatosEtiquetaInput  item : codigoList) {
            cant = (cant + item.getSalida_stock());
        }

        return cant;
    }
    public int getNextSecuencia() {
        if (codigoList.isEmpty()) {
            return 1;
        }
        return codigoList.size()+1;
    }
    public int BuscarCodigo(String codigo){
        int res = -1;
        for (int i = 0; i < codigoList.size(); i++) {
            if (codigoList.get(i).getCodigo().equals(codigo)) {
                res =  i; // Devuelve la posición si encuentra el código
            }
        }

        return res;
    }
    public void highlightItem(int position) {
        int previousHighlight = highlightedPosition;
        highlightedPosition = position;

        // Notificar cambios en las posiciones afectadas
        if (previousHighlight != -1) {
            notifyItemChanged(previousHighlight);
        }
        notifyItemChanged(highlightedPosition);
    }

    public interface OnTotalesChangeListener {
        void onTotalesChanged(int totalEtiquetas, int totalConos, double totalKg);
    }
}
