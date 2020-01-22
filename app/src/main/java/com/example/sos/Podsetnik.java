package com.example.sos;

import java.io.Serializable;
import java.util.Objects;

public class Podsetnik implements Serializable {

    private int podsetnik_id;
    private Vreme_terapije vreme_terapije;
    private int lek_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podsetnik podsetnik = (Podsetnik) o;
        return lek_id == podsetnik.lek_id &&
                vreme_terapije == podsetnik.vreme_terapije;
    }



    public enum Vreme_terapije{
        jutro,
        podne,
        vece
    }

    public Podsetnik(int podsetnik_id, Vreme_terapije vreme_terapije, int lek_id) {
        this.podsetnik_id = podsetnik_id;
        this.vreme_terapije = vreme_terapije;
        this.lek_id = lek_id;
    }

    public Podsetnik() {
    }

    public int getPodsetnik_id() {
        return podsetnik_id;
    }

    public void setPodsetnik_id(int podsetnik_id) {
        this.podsetnik_id = podsetnik_id;
    }

    public Vreme_terapije getVreme_terapije() {
        return vreme_terapije;
    }

    public void setVreme_terapije(Vreme_terapije vreme_terapije) {
        this.vreme_terapije = vreme_terapije;
    }

    public int getLek_id() {
        return lek_id;
    }

    public void setLek_id(int lek_id) {
        this.lek_id = lek_id;
    }

    @Override
    public String toString() {
        return "Podsetnik{" +
                "podsetnik_id=" + podsetnik_id +
                ", vreme_terapije='" + vreme_terapije + '\'' +
                ", lek_id=" + lek_id +
                '}';
    }
}
