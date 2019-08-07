package com.example.giovanni.giovanni.model;

import java.io.Serializable;

public class Manager extends Dipendente implements Serializable {

    public int malattia;

    public int bonus;
    public int idProgetto;

    public Manager() {
        super();
        malattia = 0;
        this.bonus = 0;
        this.idProgetto = 0;
    }

    public Manager(String matricola, double stipendio, double straordinario) {
        super(matricola, stipendio, straordinario);
        this.malattia = 0;
    }

    public Manager(String matricola, double stipendio, double straordinario, int malattia) {
        super(matricola, stipendio, straordinario);
        this.malattia = malattia;
    }

    public Manager(int id, String nome, String cognome) {
        super(id, nome, cognome);
        this.bonus = 0;
        this.idProgetto = 0;
    }

    public Manager(int id, String nome, String cognome, double stipendio, int bonus, int idProgetto) {
        super(id, nome, cognome, stipendio);
        this.bonus = bonus;
        this.idProgetto = idProgetto;
    }

    public int getMalattia() {
        return malattia;
    }

    public void setMalattia(int malattia) {
        this.malattia = malattia;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getIdProgetto() {
        return idProgetto;
    }

    public void setIdProgetto(int idProgetto) {
        this.idProgetto = idProgetto;
    }

    public int prendiMalattia(int giorniMalattia) {
        return malattia = malattia + giorniMalattia;
    }

    @Override
    public double paga(int oreStraordinario) {
        double p;
        p = super.paga(oreStraordinario);
        if (this.malattia == 0) {
            return p;
        } else {
            return p = p - this.malattia * 15.0;
        }
    }

    public String stampaMalattia() {
        return "Giorni di malattia: " + malattia;
    }

    // Il metodo activeBonus(), ricevuto un intero indicante il numero di giorni, aumenta lo stipendio di 50 euro per ogni giorno di bonus.
    public void activeBonus(int numeroGiorni) {

        double newStipendio;
        int bonusTotale = numeroGiorni * 50;
        setBonus(bonusTotale);
        newStipendio = getStipendio() + getBonus();
        setStipendio(newStipendio);
    }
}