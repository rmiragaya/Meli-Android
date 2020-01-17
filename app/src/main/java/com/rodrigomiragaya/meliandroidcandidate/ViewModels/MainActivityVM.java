package com.rodrigomiragaya.meliandroidcandidate.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rodrigomiragaya.meliandroidcandidate.Interface.MeliApi;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Resultados;
import com.rodrigomiragaya.meliandroidcandidate.Repo.MeliRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityVM extends ViewModel {
    private static final String TAG = "MainActivityVM";
    private static final String BASEURL = "https://api.mercadolibre.com/sites/MLA/";

    private MutableLiveData<List<Producto>> listMutableLiveData;
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private MeliRepo meliRepo;

    public void init(){
        if (listMutableLiveData != null){
            return;
        }
        meliRepo = MeliRepo.getInstance();
        listMutableLiveData = meliRepo.getProductos("");
    }

    public void search(final String busquedaNueva){
        mIsLoading.setValue(true);

        //aca armar asynck o retro para buscar nuevos articulos

        final MutableLiveData<List<Producto>> data = new MutableLiveData<>();
        // de RetroFit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MeliApi meliApi = retrofit.create(MeliApi.class);

        Call<Resultados> call = meliApi.getResultados(busquedaNueva);

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

                //        Log.d(TAG, "data sale de melirepo con " + data.getValue().size());
                List<Producto> currentList = listMutableLiveData.getValue();
                currentList.clear();
                currentList = data.getValue();

                //aca armar asynck o retro para buscar nuevos articulos
                mIsLoading.setValue(false);
                listMutableLiveData.postValue(currentList);

            }

            @Override
            public void onFailure(Call<Resultados> call, Throwable t) {
//                resultTextView.setText(t.getMessage());
                //todo mostrar otro error
            }
        });



    }

    public LiveData<List<Producto>> getProductos(){
        return listMutableLiveData;
    }

    public LiveData<Boolean> getIsLoading(){
        return mIsLoading;
    }
}
