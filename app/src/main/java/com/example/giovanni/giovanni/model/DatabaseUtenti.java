package com.example.giovanni.giovanni.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtenti {

    private List<Persona> utenti;

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

    public boolean checkUsername(String username) {
        for(Persona utente : utenti) {
            if (utente.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public String returnUsername(String username) {
        for(Persona utente : utenti) {
            if(utente.getUsername().equals(username)) {
                return username;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Database{" +
                "utenti=" + utenti +
                '}';
    }

    public void init() {

        Persona utente1 = new Persona("Gianluigi", "345");
        Persona utente2 = new Persona("Mariano", "456");
        Persona utente3 = new Persona("Daniele", "567");
        Persona utente4 = new Persona("Frank", "678");
        Persona utente5 = new Persona("Lino", "789");

        utenti.add(utente1);
        utenti.add(utente2);
        utenti.add(utente3);
        utenti.add(utente4);
        utenti.add(utente5);
    }
}