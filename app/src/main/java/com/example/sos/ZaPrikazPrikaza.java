package com.example.sos;

import java.io.Serializable;

public class ZaPrikazPrikaza  implements Serializable {

    private String naziv;

    private int podsetnik_id;

    public ZaPrikazPrikaza() {

    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


    public int getPodsetnik_id() {
        return podsetnik_id;
    }

    public void setPodsetnik_id(int podsetnik_id) {
        this.podsetnik_id = podsetnik_id;
    }

}
