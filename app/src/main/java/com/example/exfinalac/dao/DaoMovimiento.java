package com.example.exfinalac.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.exfinalac.entities.MovimientoBEnti;

@Dao
public interface DaoMovimiento {
    @Insert
    void crearMovimiento(MovimientoBEnti movimientoBEnti);
}
