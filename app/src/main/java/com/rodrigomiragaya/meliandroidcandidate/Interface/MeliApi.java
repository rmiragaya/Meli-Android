package com.rodrigomiragaya.meliandroidcandidate.Interface;

import com.rodrigomiragaya.meliandroidcandidate.Obj.Resultados;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/** interface for Retrofit */
public interface MeliApi {

    @GET("search")
    Call<Resultados> getResultados(
            @Query("q") String busqueda,
            @Query("category") String category
    );
}
