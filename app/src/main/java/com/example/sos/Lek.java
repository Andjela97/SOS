package com.example.sos;

import java.io.Serializable;

class Lek implements Serializable {
    private int lek_id;
    private String naziv;
    private String genericko_ime;


    public Lek(int lek_id, String naziv, String genericko_ime) {
        this.lek_id = lek_id;
        this.naziv = naziv;
        this.genericko_ime = genericko_ime;

    }
    public Lek(){}

    public int getLek_id() {
        return lek_id;
    }

    public void setLek_id(int lek_id) {
        this.lek_id = lek_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGenericko_ime() {
        return genericko_ime;
    }

    public void setGenericko_ime(String genericko_ime) {
        this.genericko_ime = genericko_ime;
    }

    @Override
    public String toString() {
        return "Lek{" +
                "lek_id=" + lek_id +
                ", naziv='" + naziv + '\'' +
                ", genericko_ime='" + genericko_ime + '\'' +
                '}';
    }
}
