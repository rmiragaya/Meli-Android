package com.rodrigomiragaya.meliandroidcandidate.Obj;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Resultados {

    @SerializedName("results")
    private ArrayList<Producto> productoList;

    public Resultados(ArrayList<Producto> productoList) {
        this.productoList = productoList;
    }

    public ArrayList<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(ArrayList<Producto> productoList) {
        this.productoList = productoList;
    }
}
