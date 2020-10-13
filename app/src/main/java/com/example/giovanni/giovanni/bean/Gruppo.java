package com.example.giovanni.giovanni.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gruppo implements Serializable {

    private String nome;
    private int componenti;
    private List<Post> listaPost;
    private Date date;
    private int id;

    public Gruppo() {
        this.nome = null;
        this.componenti = 0;
        this.listaPost = new ArrayList<>();
        this.date = new Date();
        this.id = 0;
    }

    public Gruppo(String nome, int componenti, int id) {
        this.nome = nome;
        this.componenti = componenti;
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

    public List<Post> getListaPost() {
        return listaPost;
    }

    public void setListaPost(List<Post> listaPost) {
        this.listaPost = listaPost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}