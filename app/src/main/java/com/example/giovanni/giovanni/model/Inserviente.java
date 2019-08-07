package com.example.giovanni.giovanni.model;

import java.io.Serializable;

public class Inserviente extends Dipendente implements Serializable {

    public int idUfficio;

    public Inserviente() {
        super();
        this.idUfficio = 0;
    }

    public Inserviente(String nome, String cognome) {
        super(nome, cognome);
        this.idUfficio = 0;
    }

    public Inserviente(int id, String nome, String cognome, double stipendio, int idUfficio) {
        super(id, nome, cognome, stipendio);
        this.idUfficio = idUfficio;
    }

    public int getIdUfficio() {
        return idUfficio;
    }

    public void setIdUfficio(int idUfficio) {
        this.idUfficio = idUfficio;
    }
}