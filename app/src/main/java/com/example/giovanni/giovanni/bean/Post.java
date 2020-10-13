package com.example.giovanni.giovanni.bean;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

    private String autore;
    private int id;
    private Date data;
    private String titolo;
    private String utente;
    private String nodo;

    public Post() {
        this.autore = null;
        this.id = 0;
        this.data = new Date();
        this.titolo = null;
        this.utente = null;
        this.nodo = null;
    }

    public Post(String autore, int id, Date data, String titolo, String utente) {
        this.autore = autore;
        this.id = id;
        this.data = data;
        this.titolo = titolo;
        this.utente = utente;
        this.nodo = null;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getNodo() {
        return nodo;
    }

    public void setNodo(String nodo) {
        this.nodo = nodo;
    }
}