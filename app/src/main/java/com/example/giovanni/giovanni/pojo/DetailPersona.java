package com.example.giovanni.giovanni.pojo;

import java.io.Serializable;

public class DetailPersona implements Serializable {

    public String chiave;
    public String valore;

    public DetailPersona() {
        this.chiave = null;
        this.valore = null;
    }

    public DetailPersona(String chiave, String valore) {
        this.chiave = chiave;
        this.valore = valore;
    }

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }
}