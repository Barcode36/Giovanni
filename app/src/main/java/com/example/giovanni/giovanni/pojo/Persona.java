package com.example.giovanni.giovanni.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Persona implements Serializable {

    public int id;
    private String nome;
    private String cognome;
    private int numero;
    private String sesso;
    private String dataNascita;
    private String luogoNascita;
    private String provincia;
    private String msisdn;
    private String eta;
    private String username;
    private String email;
    private String codiceFiscale;
    private String password;
    private List<Gruppo> lista;
    private String tipo;
    private boolean checked;

    public Persona() {
        this.id = 0;
        this.nome = null;
        this.cognome = null;
        this.numero = 0;
        this.sesso = null;
        this.dataNascita = null;
        this.luogoNascita = null;
        this.provincia = null;
        this.msisdn = null;
        this.eta = null;
        this.username = null;
        this.email = null;
        this.codiceFiscale = null;
        this.password = null;
        this.lista = new ArrayList<>();
        this.checked = false;
    }

    public Persona(int id, String nome, String cognome, String msisdn) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.msisdn = msisdn;
    }

    public Persona(String nome, String cognome, int numero) {
        this.nome = nome;
        this.cognome = cognome;
        this.numero = numero;
    }

    public Persona(String nome, String cognome, String sesso, String dataNascita, String luogoNascita, String provincia, String msisdn) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.provincia = provincia;
        this.msisdn = msisdn;
    }

    public Persona(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Persona(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Persona(int id, String email, String password, List<Gruppo> lista) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.lista = lista;
    }

    public Persona(String nome, String cognome, String msisdn, boolean checked) {
        this.nome = nome;
        this.cognome = cognome;
        this.msisdn = msisdn;
        this.checked = checked;
    }

    public Persona(String nome, String cognome, String msisdn, String tipo, boolean checked) {
        this.nome = nome;
        this.cognome = cognome;
        this.msisdn = msisdn;
        this.tipo = tipo;
        this.checked = checked;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Gruppo> getLista() {
        return lista;
    }

    public void setLista(List<Gruppo> lista) {
        this.lista = lista;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void init() {

        inserisci(new Gruppo(1, "Nirvana", 3));
        inserisci(new Gruppo(2, "Pearl Jam", 5));
        inserisci(new Gruppo(3, "Soundgarden", 4));
        inserisci(new Gruppo(4, "Stone Temple Pilots", 4));
        inserisci(new Gruppo(5, "Audioslave", 4));
    }

    private void inserisci(Gruppo gruppo) {
        lista.add(gruppo);
    }

    public boolean cercaGruppo(String nome) {
        for(Gruppo gruppo : lista) {
            if (nome.equalsIgnoreCase(gruppo.getNome())) {
                return true;
            }
        }
        return false;
    }

    public String returnGruppo(String input) {
        for(Gruppo gruppo : lista) {
            if (input.equalsIgnoreCase(gruppo.getNome())) {
                if (gruppo.getNome().equals("Stone Temple Pilots") || gruppo.getNome().equals("Audioslave")) {
                    return "gli " + gruppo.getNome() + ", numero componenti: " + gruppo.getComponenti();
                } else {
                    return "i " + gruppo.getNome() + ", numero componenti: " + gruppo.getComponenti();
                }
            }
        }
        return null;
    }
}