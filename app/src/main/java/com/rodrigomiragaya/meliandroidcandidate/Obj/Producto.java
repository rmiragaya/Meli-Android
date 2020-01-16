package com.rodrigomiragaya.meliandroidcandidate.Obj;

import com.google.gson.annotations.SerializedName;

public class Producto {

    private String id;

    @SerializedName("title")
    private String titulo;

    @SerializedName("price")
    private Float precio;

    @SerializedName("available_quantity")
    private int stock;

    private String thumbnail;

    @SerializedName("condition")
    private String estado;

    private VendorAddres vendorAddres;

    @SerializedName("accepts_mercadopago")
    private boolean mercadopago;

    public Producto(String id, String titulo, Float precio, int stock, String thumbnail, String estado, VendorAddres vendorAddres, boolean mercadopago) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.stock = stock;
        this.thumbnail = thumbnail;
        this.estado = estado;
        this.vendorAddres = vendorAddres;
        this.mercadopago = mercadopago;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public VendorAddres getVendorAddres() {
        return vendorAddres;
    }

    public void setVendorAddres(VendorAddres vendorAddres) {
        this.vendorAddres = vendorAddres;
    }

    public boolean isMercadopago() {
        return mercadopago;
    }

    public void setMercadopago(boolean mercadopago) {
        this.mercadopago = mercadopago;
    }
}
