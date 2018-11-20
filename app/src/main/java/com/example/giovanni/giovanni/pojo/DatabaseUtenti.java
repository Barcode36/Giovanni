package com.example.giovanni.giovanni.pojo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtenti {

    public List<Persona> utenti;

    public DatabaseUtenti() {
        this.utenti = new ArrayList<>();
    }

    public DatabaseUtenti(List<Persona> utenti) {
        this.utenti = utenti;
    }

    public List<Persona> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Persona> utenti) {
        this.utenti = utenti;
    }

    public void inserisci(Persona utente) {
        utenti.add(utente);
    }

    public void inserisci(String username, String password) {
        utenti.add(new Persona(username, password));
    }

    public boolean checkLogin(String username, String password) {
        for(Persona utente : utenti) {
            if(utente.getUsername().equals(username) && utente.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaUsername(String username) {
        for(Persona utente : utenti) {
            if (utente.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database{" +
                "utenti=" + utenti +
                '}';
    }
}