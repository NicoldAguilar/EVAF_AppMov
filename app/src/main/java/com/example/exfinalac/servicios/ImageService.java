package com.example.exfinalac.servicios;

import com.example.exfinalac.entities.Image;
import com.example.exfinalac.entities.ImageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ImageService {
    @Headers("Authorization: Client-ID 8bcc638875f89d9")
    @POST("3/image")
    Call<ImageResponse> sendImage(@Body Image imgPok);
}
