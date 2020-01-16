package com.rodrigomiragaya.meliandroidcandidate.Interface;

import com.rodrigomiragaya.meliandroidcandidate.Obj.Resultados;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MeliApi {

    @GET("search")
    Call<Resultados> getResultados(
            @Query("q") String busqueda
    );
}
