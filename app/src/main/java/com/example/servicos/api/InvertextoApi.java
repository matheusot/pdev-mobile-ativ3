package com.example.servicos.api;

import com.example.servicos.model.Logradouro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InvertextoApi {
    @GET("/v1/cep/{cep}")
    Call<Logradouro> getLogradouro(
            @Path("cep") String numero,
            @Query("token") String token
    );

    @GET("/v1/geoip/{ip}")
    Call<Logradouro> getIP(
            @Path("ip") String ip,
            @Query("token") String token
    );
}