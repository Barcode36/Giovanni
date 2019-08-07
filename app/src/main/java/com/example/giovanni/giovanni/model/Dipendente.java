package com.example.giovanni.giovanni.model;

import java.io.Serializable;

public class Dipendente implements Serializable {

    public int id;
    public String nome;
    public String cognome;
    private String username;
    private String password;
    public String matricola;
    public double stipendio;
    public double straordinario;

    public Dipendente() {
        this.id = 0;
        this.nome = null;
        this.cognome = null;
        this.username = null;
        this.password = null;
        this.matricola = null;
        this.stipendio = 0.0;
        this.straordinario = 0.0;
    }

    public Dipendente(String nome, String cognome) {
        this.id = 0;
        this.nome = nome;
        this.cognome = cognome;
        this.stipendio = 0.0;
    }

    public Dipendente(int id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.stipendio = 0.0;
    }

    public Dipendente(int id, String nome, String cognome, double stipendio) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.stipendio = stipendio;
    }

    public Dipendente(String matricola, double stipendio, double straordinario) {
        this.matricola = matricola;
        this.stipendio = stipendio;
        this.straordinario = straordinario;
    }

    public Dipendente(String nome, String username, String password) {
        this.nome = nome;
        this.username = username;
        this.password = password;
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }

    public double getStraordinario() {
        return straordinario;
    }

    public void setStraordinario(double straordinario) {
        this.straordinario = straordinario;
    }

    @Override
    public String toString() {
        return "Impiegato {" +
                "nome: " + nome + '\'' +
                ", cognome: " + cognome + '\'' +
                '}';
    }

    public double paga(int oreStraordinario) {
        return stipendio = stipendio + oreStraordinario * straordinario;
    }

    public String stampa() {
        return "Dati dipendente {" +
                "matricola: " + getMatricola() +
                ", stipendio: " + getStipendio() +
                ", straordinario: " + getStraordinario() + '}';
    }
}