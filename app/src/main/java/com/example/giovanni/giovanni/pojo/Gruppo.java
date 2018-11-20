package com.example.giovanni.giovanni.pojo;

public class Gruppo {

    public int id;
    public String nome;
    public int componenti;

    public Gruppo() {
        this.id = 0;
        this.nome = null;
        this.componenti = 0;
    }

    public Gruppo(int id, String nome, int componenti) {
        this.id = id;
        this.nome = nome;
        this.componenti = componenti;
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

    public int getComponenti() {
        return componenti;
    }

    public void setComponenti(int componenti) {
        this.componenti = componenti;
    }
}