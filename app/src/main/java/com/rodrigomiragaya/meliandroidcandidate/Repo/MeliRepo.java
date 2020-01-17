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
    private static final String BASEURL = "https://api.mercadolibre.com/sites/MLA/";
    private MeliApi meliApi;

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


    private MutableLiveData<List<Producto>> retrofitGetProductos(String productoABuscar){

        final MutableLiveData<List<Producto>> data = new MutableLiveData<>();

        // de RetroFit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        meliApi = retrofit.create(MeliApi.class);

        Call<Resultados> call = meliApi.getResultados(productoABuscar);

        call.enqueue(new Callback<Resultados>() {
            @Override
            public void onResponse(Call<Resultados> call, Response<Resultados> response) {

                if (!response.isSuccessful()){
                    //todo mostrar error
                    return;
                }
                Resultados resultados = response.body();
                data.setValue(resultados.getProductoList());


                // para ver si tenemos respuesta
                Log.d(TAG, "MeliRepo, respuesta size " + resultados.getProductoList().size());
//                ArrayList<Producto>listaProductos = resultados.getProductoList();
//                for (Producto producto : listaProductos){
//                    String content = "";
//                    content += "ID: " + producto.getId() + "\n";
//                    content += "Titulo: " + producto.getTitulo() + "\n";
//                    content += "Estado: " + producto.getEstado() + "\n";
//                    content += "Precio: " + producto.getPrecio().toString() + "\n\n";
//                    Log.d(TAG, "onResponse: " + content); }
                // para ver si tenemos respuesta

            }

            @Override
            public void onFailure(Call<Resultados> call, Throwable t) {
//                resultTextView.setText(t.getMessage());
                //todo mostrar otro error
            }
        });

//        Log.d(TAG, "data sale de melirepo con " + data.getValue().size());
        return data;

    }
}
