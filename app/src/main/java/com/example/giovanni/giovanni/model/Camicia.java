package com.example.giovanni.giovanni.model;

public class Camicia extends Articolo {

    public int taglia;
    public String colore;

    public Camicia() {
        super(); // Richiamo il costruttore della classe padre.
        this.taglia = 0;
        this.colore = null;
    }

    public Camicia(int id, String descrizione, double prezzo, int quantita) {
        super(id, descrizione, prezzo, quantita); // Richiamo il costruttore della classe padre.
    }

    public Camicia(int id, String nome, double prezzo, int taglia, String colore) {
        super(id, nome, prezzo);
        this.taglia = taglia;
        this.colore = colore;
    }

    public int getTaglia() {
        return taglia;
    }

    public void setTaglia(int taglia) {
        this.taglia = taglia;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String stampaCamicia() {
        return "ID: " + getId() + "\nNome: " + getNome() + "\nPrezzo: " + getPrezzo() + "\nTaglia: " + getTaglia() + "\nColore: " + getColore();
    }
}