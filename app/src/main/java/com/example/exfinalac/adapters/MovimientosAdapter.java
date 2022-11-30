package com.example.exfinalac.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exfinalac.FormMovimientosActivity;
import com.example.exfinalac.R;
import com.example.exfinalac.entities.MovimientoBEnti;

import java.util.List;

public class MovimientosAdapter extends RecyclerView.Adapter{

    List<MovimientoBEnti> datos;
    Button button;

    public MovimientosAdapter(List<MovimientoBEnti> datos) {this.datos=datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_nombre,parent,false);
        return new CuentaBanAdapter.CuentaBanAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //final CuentaBanAdapter.CuentaBanAdapterViewHolder viewHolder = (CuentaBanAdapter.CuentaBanAdapterViewHolder) holder;

        TextView tvmonto = holder.itemView.findViewById(R.id.tvnombre);
        tvmonto.setText((int) datos.get(position).monto);

        TextView tvmotivo = holder.itemView.findViewById(R.id.tvnombre);
        tvmotivo.setText(datos.get(position).motivo);

        TextView tvtipo = holder.itemView.findViewById(R.id.tvnombre);
        tvtipo.setText(datos.get(position).tipoCuenta);

        /*button = holder.itemView.findViewById(R.id.btnRMovimientos);
        viewHolder.btnRM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), FormMovimientosActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    static class MovimientosAdapterViewHolder extends RecyclerView.ViewHolder{

        //Button btnRM;

        public MovimientosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            //btnRM = (Button)itemView.findViewById(R.id.btnRMovimientos);
        }
    }
}
