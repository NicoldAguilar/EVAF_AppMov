package com.example.exfinalac.servicios;

import com.example.exfinalac.entities.CuentaEnti;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CuentaBanService {
    //todo esto depende del API( GET SET DELETE )
    @GET("cuentaBan")
    Call<List<CuentaEnti>> getListContact();
    @POST("cuentaBan")
    Call<Void> create(@Body CuentaEnti poke);//guardar datos
    /*@PUT("contacts/{id}")
    Call<Void> update (@Body ContactApi contactApi, @Path("id")int id);*/
    @PUT("cuentaBan/{id}")
    Call<CuentaEnti> update(@Body CuentaEnti poke, @Path("id")int id);
    @DELETE("cuentaBan/{id}")
    Call<CuentaEnti> delete (@Path("id")int id);
}
