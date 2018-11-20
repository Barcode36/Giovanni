package com.example.giovanni.giovanni.pojo;

import java.io.Serializable;
import java.util.Date;

public class Progetto implements Serializable {

    public int id;
    private String nome;
    private int budget;
    private Dipendente dipendente;
    private String stato;
    private String user;
    private Date data;

    public Progetto() {
        this.id = 0;
        this.nome = null;
        this.budget = 0;
        this.dipendente = new Dipendente();
        this.stato = null;
        this.user = null;
        this.data = null;
    }

    public Progetto(String nome, int budget) {
        this.nome = nome;
        this.budget = budget;
    }

    public Progetto(String nome, int budget, Dipendente dipendente) {
        this.nome = nome;
        this.budget = budget;
        this.dipendente = dipendente;
    }

    public Progetto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Progetto(int id, String nome, String stato, String user, Date data) {
        this.id = id;
        this.nome = nome;
        this.stato = stato;
        this.user = user;
        this.data = data;
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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Dipendente getDipendente() {
        return dipendente;
    }

    public void setDipendente(Dipendente dipendente) {
        this.dipendente = dipendente;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Progetto {" +
                "nome: " + nome + '\'' +
                ", budget: " + budget +
                ", impiegato: " + dipendente +
                '}';
    }
}