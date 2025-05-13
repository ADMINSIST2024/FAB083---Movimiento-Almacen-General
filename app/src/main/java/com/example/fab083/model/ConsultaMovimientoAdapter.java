package com.example.fab083.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fab083.R;
import com.example.fab083.dtos.DtoMovimientoAlmacenInput;

import java.util.List;

public class ConsultaMovimientoAdapter extends RecyclerView.Adapter<ConsultaMovimientoAdapter.ViewHolder> {



    private List<DtoMovimientoAlmacenInput> dataList;

    // Constructor para pasar la lista de datos al adaptador
    public ConsultaMovimientoAdapter(List<DtoMovimientoAlmacenInput> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_lista_consulta_movimiento, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consulta_movimiento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DtoMovimientoAlmacenInput data = dataList.get(position);

        holder.tvEtiqueta.setText(data.getCodigo());
        holder.tvMovimiento.setText(data.getMov().concat(" - " + data.getCscmag()));
        holder.tvTipoMovimiento.setText(data.getCodtmv() + " - " + data.getDestmv());
        holder.tvAlmOrigen.setText(String.valueOf(data.getCodalg()) + " - " + data.getAlmori());
        holder.tvAlmDestino.setText(String.valueOf(data.getAdemag()) + " - " + data.getAlmdest());
        holder.tvLote.setText(data.getNlhmag());
        holder.tvCantidad.setText(String.valueOf(data.getCaemag()) + " - " + data.getUmemag());
        holder.tvPeso.setText(String.valueOf(data.getCanmag()));
        holder.tvTitulo.setText(String.valueOf(data.getTrhmag()));
        holder.tvUnMedidaReal.setText(data.getUrhmag());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /*public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmovimiento, txtcsc,txttipomovimiento,txtalmacenorigen,txtccosto,txtenvase,txtcodigo,txtproveedor,
                txttitulo,txtalmacendestino,txtordencsc,txtkilos,txthilo,txtlote,txtum,txtetiqueta,txtsecuencia;

        public ViewHolder(View itemView) {
            super(itemView);
            txtmovimiento = itemView.findViewById(R.id.txtmovimiento);
            txtcsc = itemView.findViewById(R.id.txtcsc);
            txttipomovimiento = itemView.findViewById(R.id.txttipomovimiento);
            txtalmacenorigen = itemView.findViewById(R.id.txtalmacenorigen);
            txtccosto = itemView.findViewById(R.id.txtccosto);
            txtenvase = itemView.findViewById(R.id.txtenvase);
            txtcodigo = itemView.findViewById(R.id.txtcodigo);
            txtproveedor = itemView.findViewById(R.id.txtproveedor);
            txttitulo = itemView.findViewById(R.id.txttitulo);
            txtalmacendestino = itemView.findViewById(R.id.txtalmacendestino);
            txtordencsc = itemView.findViewById(R.id.txtordencsc);
            txtkilos = itemView.findViewById(R.id.txtkilos);
            txthilo = itemView.findViewById(R.id.txthilo);
            txtlote = itemView.findViewById(R.id.txtlote);
            txtum = itemView.findViewById(R.id.txtum);
            txtetiqueta = itemView.findViewById(R.id.txtetiqueta);
            txtsecuencia = itemView.findViewById(R.id.txtsecuencia);
        }
    }*/

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEtiqueta, tvMovimiento, tvTipoMovimiento, tvAlmOrigen, tvAlmDestino, tvLote, tvCantidad, tvPeso, tvTitulo, tvUnMedidaReal;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEtiqueta = itemView.findViewById(R.id.tvEtiqueta);
            tvMovimiento = itemView.findViewById(R.id.tvMovimiento);
            tvTipoMovimiento = itemView.findViewById(R.id.tvTipoMovimiento);
            tvAlmOrigen = itemView.findViewById(R.id.tvAlmOrigen);
            tvAlmDestino = itemView.findViewById(R.id.tvAlmDestino);
            tvLote = itemView.findViewById(R.id.tvLote);
            tvCantidad = itemView.findViewById(R.id.tvCantConos);
            tvPeso = itemView.findViewById(R.id.tvPeso);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvUnMedidaReal = itemView.findViewById(R.id.tvUnidadMedReal);
        }
    }
}
