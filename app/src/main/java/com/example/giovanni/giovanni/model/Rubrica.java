package com.example.giovanni.giovanni.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rubrica implements Serializable {

    private List<Persona> contatti;

    public Rubrica() {
        this.contatti = new ArrayList<>();
    }

    public Rubrica(List<Persona> contatti) {
        this.contatti = contatti;
    }

    public List<Persona> getContatti() {
        return contatti;
    }

    public void setContatti(List<Persona> contatti) {
        this.contatti = contatti;
    }

    public static List<Persona> init() {

        List<Persona> contatti = new ArrayList<>();
        contatti.add(new Persona(1, "Giovanni", "Petito", "3331582355"));
        contatti.add(new Persona(2, "Gianluigi", "Santillo", "3386124867"));
        contatti.add(new Persona(3, "Mariano", "Pinto", "3397016728"));
        contatti.add(new Persona(4, "Daniele", "Musacchia", "3494977374"));
        contatti.add(new Persona(5, "Francesco", "Mongiello", "3299376402"));
        contatti.add(new Persona(6, "Pasquale", "Amato", "3665917760"));

        return contatti;
    }

    public void init2() {

        contatti.add(new Persona(7, "Giovanni", "Petito", "3331582355"));
        contatti.add(new Persona(8, "Gianluigi", "Santillo", "3386124867"));
        contatti.add(new Persona(9, "Mariano", "Pinto", "3397016728"));
        contatti.add(new Persona(10, "Daniele", "Musacchia", "3494977374"));
        contatti.add(new Persona(11, "Francesco", "Mongiello", "3299376402"));
        contatti.add(new Persona(12, "Pasquale", "Amato", "3665917760"));
    }

    public String[] getArray() {

        String stringa;
        List<String> lista = new ArrayList<>();
        for(Persona contatto : contatti) {
            stringa = contatto.getId() +") " + contatto.getNome() + " " + contatto.getCognome() +
                    "\nCellulare: " + contatto.getNumero();
            lista.add(stringa);
        }
        String[] array = new String[lista.size()];
        array = lista.toArray(array);
        return array;
    }

    public void elimina(int index) {
        if(index < contatti.size()) {
            contatti.remove(index);
        }
    }

    public Persona aggiungi(Persona contatto) {
        contatti.add(contatto);
        return contatto;
    }

    public String getInfoContatti(List<Persona> contatti) {
        String info = "";
        for(Persona persona : contatti) {
            info = info + (persona.getNome() + " " + persona.getCognome() + "\n");
        }
        return info;
    }

    // Metodo che ritorna un array contenente le matricole dei contatti.
    public List<Long> getListaMatricole() {

        List<Long> matricole = new ArrayList<>();
        long matricola;
        for(Persona persona : contatti) {
            matricola = persona.getMatricola();
            matricole.add(matricola);
        }
        return matricole;
    }

    public Persona getDipendenteFromMatricola(long matricola) {
        Persona persona = new Persona();
        for (Persona temp : contatti) {
            if(matricola == temp.getMatricola()) {
                persona = temp;
            }
        }
        return persona;
    }

    public String getInfoDipendenti(List<Persona> listaDipendenti) {
        String info = "";
        for(Persona persona : listaDipendenti) {
            info = info + (persona.getNome() + persona.getCognome() + persona.getMatricola());
        }
        return info;
    }
}