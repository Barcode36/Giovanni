package com.example.giovanni.giovanni.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Developer extends Dipendente implements Serializable {

    public int idProgetto;
    public List<String> skills;

    public Developer() {
        super();
        this.idProgetto = 0;
        this.skills = new ArrayList<>();
    }

    public Developer(int id, String nome, String cognome) {
        super(id, nome, cognome);
        this.idProgetto = 0;
        this.skills = new ArrayList<>();
    }

    public Developer(int id, String nome, String cognome, double stipendio, int idProgetto, List<String> skills) {
        super(id, nome, cognome, stipendio);
        this.idProgetto = idProgetto;
        this.skills = skills;
    }

    public int getIdProgetto() {
        return idProgetto;
    }

    public void setIdProgetto(int idProgetto) {
        this.idProgetto = idProgetto;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}