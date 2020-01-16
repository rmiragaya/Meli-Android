package com.rodrigomiragaya.meliandroidcandidate.ViewModels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.Repo.MeliRepo;

import java.util.List;

public class MainActivityVM extends ViewModel {

    private MutableLiveData<List<Producto>> listMutableLiveData;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private MeliRepo meliRepo;



    public void init(){
        if (listMutableLiveData != null){
            return;
        }
        meliRepo = MeliRepo.getInstance();
        listMutableLiveData = meliRepo.getProductos("casa");
    }

    public void search(String busquedaNueva){
        mIsUpdating.setValue(true);
        listMutableLiveData = meliRepo.getProductos(busquedaNueva);

    }

    public LiveData<List<Producto>> getProductos(){
        return listMutableLiveData;
    }
}
