package com.rodrigomiragaya.meliandroidcandidate.Task;

import android.os.AsyncTask;

import com.rodrigomiragaya.meliandroidcandidate.Obj.Producto;
import com.rodrigomiragaya.meliandroidcandidate.Obj.Resultados;

import java.util.ArrayList;

   public class LoadResultados extends AsyncTask<Void, Void, ArrayList<Producto>> {

    private Resultados resultados;
    private final OnLoadProducComplete mCallback;


    public LoadResultados(OnLoadProducComplete callback, Resultados resultados) {
        this.mCallback = callback;
        this.resultados = resultados;
    }

    @Override
    protected ArrayList<Producto> doInBackground(Void... voids) {
        return resultados.getProductoList();
    }


    @Override
    protected void onPostExecute(ArrayList<Producto> productos) {

        if(mCallback != null) {
            mCallback.onSearchProductComplete(productos);
        }
    }

    public interface OnLoadProducComplete {
        void onSearchProductComplete (ArrayList<Producto> listaProductosEncontrados);
    }
}
