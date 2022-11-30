package com.example.exfinalac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.exfinalac.database.dataBConfig;
import com.example.exfinalac.entities.CuentaEnti;
import com.example.exfinalac.servicios.CuentaBanService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormuCuentaActivity extends AppCompatActivity {

    EditText etNombre;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formu_cuenta);

        etNombre = findViewById(R.id.etNombreCuenta);
        button = findViewById(R.id.btnRegistrarC);

        CuentaEnti cuentaE = new CuentaEnti();
        dataBConfig data = dataBConfig.getInstance(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cuentaE.nombre = etNombre.getText().toString();
                data.cuentaDao().crearCuentaBan(cuentaE);

                CuentaBanService service = retrofit.create(CuentaBanService.class);
                service.create(cuentaE).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("MAIN_APP", "RESPONSE" + response.code());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            }
        });
    }
}