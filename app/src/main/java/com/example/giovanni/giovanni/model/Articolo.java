package com.example.giovanni.giovanni.model;

import java.io.Serializable;

public class Articolo implements Serializable {

    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private String data;

    public Articolo() {
        this.id = 0;
        this.nome = null;
        this.descrizione = null;
        this.prezzo = 0.0;
        this.quantita = 0;
        this.data = null;
    }

    public Articolo(int id, String descrizione, double prezzo, int quantita) {
        this.id = id;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public Articolo(String nome, double prezzo, String data) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.data = data;
    }

    public Articolo(int id, String nome, double prezzo) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzoTotale() {
        return getPrezzo() * getQuantita();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
