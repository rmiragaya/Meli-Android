package com.rodrigomiragaya.meliandroidcandidate.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rodrigomiragaya.meliandroidcandidate.Interface.MeliApi;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Resultados;


import org.xml.sax.ErrorHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/** MainActivity ViewModel */
public class MainActivityVM extends ViewModel {
    private static final String TAG = "MainActivityVM";
    private static final String BASEURL = "https://api.mercadolibre.com/sites/MLA/";

    private MutableLiveData<List<Producto>> listMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();

    public void init(){
        if (listMutableLiveData != null){
            return;
        }
    }


    //call Retrofit to update List of Products
    public void search(final String busquedaNueva){
        mIsLoading.setValue(true);
        final MutableLiveData<List<Producto>> data = new MutableLiveData<>();


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();


        // de RetroFit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MeliApi meliApi = retrofit.create(MeliApi.class);

        Call<Resultados> call = meliApi.getResultados(busquedaNueva);

        call.enqueue(new Callback<Resultados>() {
            @Override
            public void onResponse(Call<Resultados> call, Response<Resultados> response) {
                Log.d(TAG, "responseCode: " + response.code());
                if (response.code()>400)
                if (!response.isSuccessful()){
                    listMutableLiveData.postValue(new ArrayList<Producto>());
                    return;
                }
                Resultados resultados = response.body();
                data.setValue(resultados.getProductoList());

                // for Debug
                Log.d(TAG, "Respuesta size " + resultados.getProductoList().size());
                List<Producto> currentList =  data.getValue();
                listMutableLiveData.postValue(currentList);
                mIsLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Resultados> call, Throwable t) {
                listMutableLiveData.postValue(new ArrayList<Producto>());
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
