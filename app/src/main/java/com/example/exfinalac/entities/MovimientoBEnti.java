package com.example.exfinalac.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MovimientoBan")
public class MovimientoBEnti {
    @PrimaryKey(autoGenerate = true)
    public int idMovimientoBan;
    public String tipoCuenta;
    public float monto;
    public String motivo;
    public String imagenCuenta;
    public String latitud;
    public String longitud;
}
