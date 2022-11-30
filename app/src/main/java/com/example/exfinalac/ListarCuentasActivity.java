package com.example.exfinalac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.exfinalac.adapters.CuentaBanAdapter;
import com.example.exfinalac.database.dataBConfig;
import com.example.exfinalac.entities.CuentaEnti;
import com.example.exfinalac.servicios.CuentaBanService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarCuentasActivity extends AppCompatActivity {

    private RecyclerView cuentasBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cuentas);

        dataBConfig data = dataBConfig.getInstance(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CuentaBanService services = retrofit.create(CuentaBanService.class);
        //List<CuentaEnti> call = data.cuentaDao().listarcuentas();
        services.getListContact().enqueue(new Callback<List<CuentaEnti>>() {
            @Override
            public void onResponse(Call<List<CuentaEnti>> call, Response<List<CuentaEnti>> response) {
                List<CuentaEnti> datos = response.body();
                cuentasBan=findViewById(R.id.rvCuentasBan);
                cuentasBan.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                cuentasBan.setAdapter(new CuentaBanAdapter(datos));
                Log.i("MAIN_APP", "funciona");
            }

            @Override
            public void onFailure(Call<List<CuentaEnti>> call, Throwable t) {
                Log.i("MAIN_APP", "no funciona");
            }
        });
    }

}