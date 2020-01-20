package com.rodrigomiragaya.meliandroidcandidate.Obj;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class VendorAddres implements Parcelable {

    @SerializedName("state_name")
    private String provincia;

    @SerializedName("city_name")
    private String ciudadOBarrio;

    public VendorAddres(String provincia, String ciudadOBarrio) {
        this.provincia = provincia;
        this.ciudadOBarrio = ciudadOBarrio;
    }

    protected VendorAddres(Parcel in) {
        provincia = in.readString();
        ciudadOBarrio = in.readString();
    }


    public static final Creator<VendorAddres> CREATOR = new Creator<VendorAddres>() {
        @Override
        public VendorAddres createFromParcel(Parcel in) {
            return new VendorAddres(in);
        }

        @Override
        public VendorAddres[] newArray(int size) {
            return new VendorAddres[size];
        }
    };

    public String getProvincia() {
        return provincia;
    }


    public String getCiudadOBarrio() {
        return ciudadOBarrio;
    }


    @Override
    public String toString() {
        return "VendorAddres{" +
                "provincia='" + provincia + '\'' +
                ", ciudadOBarrio='" + ciudadOBarrio + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(provincia);
        parcel.writeString(ciudadOBarrio);
    }
}
