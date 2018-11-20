package com.example.giovanni.giovanni.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Magazzino implements Serializable {

    public List<Articolo> articoli;

    public Magazzino() {
        this.articoli = new ArrayList<>();
    }

    public Magazzino(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public void addArticoli(Articolo articolo) {
        articoli.add(articolo);
    }
}