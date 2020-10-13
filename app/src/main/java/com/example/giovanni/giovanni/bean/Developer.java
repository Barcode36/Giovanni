package com.example.giovanni.giovanni.bean;

import java.io.Serializable;
import java.util.List;

public class Developer extends Persona implements Serializable {

    private int idProgetto;
    private List<String> skills;

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