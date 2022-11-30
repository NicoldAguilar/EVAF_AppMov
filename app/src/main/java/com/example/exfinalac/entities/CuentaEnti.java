package com.example.exfinalac.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cuentas")
public class CuentaEnti {
    @PrimaryKey(autoGenerate = true)
    public int idCuentaE;
    public String nombre;
    public int saldoBan;
}
