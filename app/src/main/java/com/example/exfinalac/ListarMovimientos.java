package com.example.exfinalac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.exfinalac.adapters.CuentaBanAdapter;
import com.example.exfinalac.adapters.MovimientosAdapter;
import com.example.exfinalac.database.dataBConfig;
import com.example.exfinalac.entities.CuentaEnti;
import com.example.exfinalac.entities.MovimientoBEnti;
import com.example.exfinalac.servicios.CuentaBanService;
import com.example.exfinalac.servicios.MovimientosService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarMovimientos extends AppCompatActivity {

    private RecyclerView movimientosB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_movimientos);

        dataBConfig data = dataBConfig.getInstance(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovimientosService services = retrofit.create(MovimientosService.class);
        //List<CuentaEnti> call = data.cuentaDao().listarcuentas();
        services.getListContact().enqueue(new Callback<List<MovimientoBEnti>>() {
            @Override
            public void onResponse(Call<List<MovimientoBEnti>> call, Response<List<MovimientoBEnti>> response) {
                List<MovimientoBEnti> datos = response.body();
                movimientosB=findViewById(R.id.rvMovimientos);
                movimientosB.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                movimientosB.setAdapter(new MovimientosAdapter(datos));
                Log.i("MAIN_APP", "funciona");
            }

            @Override
            public void onFailure(Call<List<MovimientoBEnti>> call, Throwable t) {
                Log.i("MAIN_APP", "no funciona");
            }
        });
    }
}