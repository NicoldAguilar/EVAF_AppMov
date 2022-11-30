package com.example.exfinalac.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.exfinalac.entities.CuentaEnti;

import java.util.List;


@Dao
public interface DaoCuenta {
    @Insert
    void crearCuentaBan(CuentaEnti cuentaEnti);

    @Query("SELECT * FROM cuentas")
    List<CuentaEnti> listarcuentas();
}
