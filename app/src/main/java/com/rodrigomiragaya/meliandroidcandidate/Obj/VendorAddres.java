package com.rodrigomiragaya.meliandroidcandidate.Obj;

public class VendorAddres {
    private String provincia;
    private String ciudadOBarrio;

    public VendorAddres(String provincia, String ciudadOBarrio) {
        this.provincia = provincia;
        this.ciudadOBarrio = ciudadOBarrio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudadOBarrio() {
        return ciudadOBarrio;
    }

    public void setCiudadOBarrio(String ciudadOBarrio) {
        this.ciudadOBarrio = ciudadOBarrio;
    }
}
