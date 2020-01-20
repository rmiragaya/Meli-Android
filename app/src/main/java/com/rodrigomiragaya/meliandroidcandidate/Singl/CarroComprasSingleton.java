package com.rodrigomiragaya.meliandroidcandidate.Singl;

import android.util.Log;

import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;

import java.util.ArrayList;


/** Sigleton pattern for get and modify "Carro" from any activity in the future */
public class CarroComprasSingleton {
    private static final String TAG = "CarroComprasSingleton";

    private static CarroComprasSingleton mInstance;
    private ArrayList<Producto> listaCarro;

    private CarroComprasSingleton(){
        listaCarro = new ArrayList<>();
    }

    public static synchronized CarroComprasSingleton getInstance(){
        if (mInstance == null){
            mInstance = new CarroComprasSingleton();
        } return mInstance;
    }

    public void addProductoAlCarro (Producto producto){
        listaCarro.add(producto);
    }

    public ArrayList<Producto> getListaCarro() {
        return listaCarro;
    }

    public void vaciarCarro(){
        listaCarro.clear();
    }

    //sum all the product on the cart
    public int sumarCuenta(){
        int suma= 0;
        for (Producto p: listaCarro){
            suma += p.getPrecio();
        }
        Log.d(TAG, "sumarCuenta: " + suma);
        return suma;


    }
}
