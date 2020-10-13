package com.example.giovanni.giovanni.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonaManager implements Serializable {

    private List<Persona> utenti;

    public PersonaManager() {
        this.utenti = new ArrayList<>();
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
        for (Persona utente : utenti) {
            if (utente.getUsername().equalsIgnoreCase(username) && utente.getPassword().equalsIgnoreCase(password))
                return true;
        }
        return false;
    }

    public boolean checkUsername(String username) {
        for (Persona utente : utenti) {
            if (utente.getUsername().equalsIgnoreCase(username))
                return false;
        }
        return true;
    }

    public String returnUsername(String username) {
        for (Persona utente : utenti) {
            if (utente.getUsername().equalsIgnoreCase(username))
                return username;
        }
        return null;
    }

    public boolean verificaUsername(String username) {
        for (Persona persona : utenti) {
            if (persona.getUsername().equalsIgnoreCase(username))
                return true;
        }
        return false;
    }

    @NonNull
    public String toString() {
        return "Database{" +
                "utenti=" + utenti +
                '}';
    }

    public void init() {

        Persona utente = new Persona("Gianluigi", "345");

        utenti.add(utente);
        utenti.add(new Persona("Mariano", "456"));
        utenti.add(new Persona("Daniele", "567"));
        utenti.add(new Persona("Frank", "678"));
        utenti.add(new Persona("Lino", "789"));
    }
}