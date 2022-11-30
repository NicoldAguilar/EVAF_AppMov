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
import com.example.exfinalac.entities.CuentaEnti;

import java.util.List;

public class CuentaBanAdapter extends RecyclerView.Adapter{

    List<CuentaEnti> datos;
    Button button;

    public CuentaBanAdapter(List<CuentaEnti> datos) {this.datos=datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_nombre,parent,false);
        return new CuentaBanAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final CuentaBanAdapterViewHolder viewHolder = (CuentaBanAdapterViewHolder) holder;
        TextView tvnombre = holder.itemView.findViewById(R.id.tvnombre);
        tvnombre.setText(datos.get(position).nombre);

        button = holder.itemView.findViewById(R.id.btnRMovimientos);
        viewHolder.btnRM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), FormMovimientosActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    static class CuentaBanAdapterViewHolder extends RecyclerView.ViewHolder{

        Button btnRM;

        public CuentaBanAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            btnRM = (Button)itemView.findViewById(R.id.btnRMovimientos);
        }
    }
}
