package com.example.giovanni.giovanni.pojo;

public class Libro extends Articolo {

    public String autore;
    private double sconto;

    public Libro() {
        super(); // Richiamo il costruttore della classe padre.
        this.autore = null;
        this.sconto = 0.0d;
    }

    public Libro(int id, String nome, double prezzo, String autore) {
        super(id, nome, prezzo);
        this.autore = autore;
    }

    public Libro (int id, String descrizione, double prezzo, int quantita, double sconto) {
        super(id, descrizione, prezzo, quantita); // Richiamo il costruttore della classe padre.
        this.sconto = sconto;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public String stampaLibro() {
        return "ID: " + getId() + "\nNome: " + getNome() + "\nPrezzo: " + getPrezzo() + "\nAutore: " + getAutore();
    }

    public double getPrezzoScontato() {
        return super.getPrezzo() * super.getQuantita() * getSconto();
    }
}