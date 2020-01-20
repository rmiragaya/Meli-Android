package com.rodrigomiragaya.meliandroidcandidate.Obj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
/**
 *
 * Object class of Productos
 * Parcelable implemented to pass object from "MainActivity" to "DetalleProducto"
 *
 * */
public class Producto implements Parcelable {

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

    @SerializedName("address")
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


    protected Producto(Parcel in) {
        id = in.readString();
        titulo = in.readString();
        if (in.readByte() == 0) {
            precio = null;
        } else {
            precio = in.readFloat();
        }
        stock = in.readInt();
        thumbnail = in.readString();
        estado = in.readString();
        vendorAddres = in.readParcelable(VendorAddres.class.getClassLoader());
        mercadopago = in.readByte() != 0;
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Float getPrecio() {
        return precio;
    }


    public String getThumbnail() {
        return thumbnail;
    }


    public VendorAddres getVendorAddres() {
        return vendorAddres;
    }


    public boolean isMercadopago() {
        return mercadopago;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", thumbnail='" + thumbnail + '\'' +
                ", estado='" + estado + '\'' +
                ", vendorAddres=" + vendorAddres +
                ", mercadopago=" + mercadopago +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(titulo);
        if (precio == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(precio);
        }
        parcel.writeInt(stock);
        parcel.writeString(thumbnail);
        parcel.writeString(estado);
        parcel.writeParcelable(vendorAddres, i);
        parcel.writeByte((byte) (mercadopago ? 1 : 0));
    }
}
