package com.example.exfinalac.servicios;

import com.example.exfinalac.entities.CuentaEnti;
import com.example.exfinalac.entities.MovimientoBEnti;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MovimientosService {
    //todo esto depende del API( GET SET DELETE )
    @GET("movimientos")
    Call<List<CuentaEnti>> getListContact();
    @POST("movimientos")
    Call<Void> create(@Body MovimientoBEnti poke);//guardar datos
    /*@PUT("contacts/{id}")
    Call<Void> update (@Body ContactApi contactApi, @Path("id")int id);*/
    @PUT("movimientos/{id}")
    Call<CuentaEnti> update(@Body MovimientoBEnti poke, @Path("id")int id);
    @DELETE("movimientos/{id}")
    Call<CuentaEnti> delete (@Path("id")int id);
}
