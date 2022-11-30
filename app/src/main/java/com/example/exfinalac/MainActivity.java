package com.example.exfinalac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnRegistrarCuenta);
        button.setOnClickListener(v -> {
            onClick(v);
        });

        button2 = findViewById(R.id.btnMostrarCuentas);
        button2.setOnClickListener(v -> {
            onClick2(v);
        });

    }

    private void onClick(View v) {
        Intent intent = new Intent(this, FormuCuentaActivity.class);
        startActivity(intent);
    }

    private void onClick2(View v) {
        Intent intent2 = new Intent(this, ListarCuentasActivity.class);
        startActivity(intent2);
    }
}