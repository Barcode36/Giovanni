package com.example.giovanni.giovanni.model;

import java.io.Serializable;

public class PersonaDetail implements Serializable {

    private String chiave;
    private String valore;

    public PersonaDetail(String chiave, String valore) {
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