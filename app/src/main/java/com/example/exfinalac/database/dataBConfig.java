package com.example.exfinalac.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.exfinalac.dao.DaoCuenta;
import com.example.exfinalac.dao.DaoMovimiento;
import com.example.exfinalac.entities.CuentaEnti;
import com.example.exfinalac.entities.MovimientoBEnti;

@Database(entities = {CuentaEnti.class, MovimientoBEnti.class}, version = 1)
public abstract class dataBConfig extends RoomDatabase {
    public abstract DaoCuenta cuentaDao();
    public abstract DaoMovimiento moviemientoDao();

    public static dataBConfig getInstance(Context context){
        return Room.databaseBuilder(context, dataBConfig.class, "basedatos1").allowMainThreadQueries().build();
    }

}
