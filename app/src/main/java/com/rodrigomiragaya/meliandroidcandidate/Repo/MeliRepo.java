package com.rodrigomiragaya.meliandroidcandidate.Repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rodrigomiragaya.meliandroidcandidate.Interface.MeliApi;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Resultados;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeliRepo {
    private static final String TAG = "MeliRepo";



    private static MeliRepo instance;
    private List<Producto> productoArrayList = new ArrayList<>();


    public static MeliRepo getInstance(){
        if (instance==null){
            instance = new MeliRepo();
        }
        return instance;
    }

    public MutableLiveData<List<Producto>> getProductos(String productoABuscar){

        return retrofitGetProductos(productoABuscar);
    }

    public MutableLiveData<List<Producto>> retrofitGetProductos(String productoABuscar){

        final MutableLiveData<List<Producto>> data = new MutableLiveData<>();







        return data;

    }
}
