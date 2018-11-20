package com.example.giovanni.giovanni.pojo;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Azienda extends Dipendente implements Serializable {

    public String nome;
    public List<Dipendente> dipendenti;
    public List<Progetto> progetti;

    public Azienda() {
        this.nome = null;
        this.dipendenti = new ArrayList<>();
        this.progetti = new ArrayList<>();
    }

    public Azienda(String nome) {
        this.nome = nome;
        this.dipendenti = new ArrayList<>();
        this.progetti = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Dipendente> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(List<Dipendente> dipendenti) {
        this.dipendenti = dipendenti;
    }

    public List<Progetto> getProgetti() {
        return progetti;
    }

    public void setProgetti(List<Progetto> progetti) {
        this.progetti = progetti;
    }

    public void inserisci(Dipendente dipendente) {
        dipendenti.add(dipendente);
    }

    public void elimina(Dipendente dipendente) {
        dipendenti.remove(dipendente);
    }

    public Dipendente cercaPerMatricola(String matricola) {

        for(Dipendente dipendente : dipendenti) {
            if (matricola.equals(dipendente.getMatricola())) {
                return dipendente;
            }
        }
        return null;
    }

    public Dipendente cercaPerStipendioMassimo() {
        Dipendente ricco = null;
        double stipendioMassimo = 0.0;
        for(Dipendente dipendente : dipendenti) {
            if (dipendente.getStipendio() > stipendioMassimo) {
                stipendioMassimo = dipendente.getStipendio();
                ricco = dipendente;
            }
        }
        return ricco;
    }

    public void init() {

        List<String> skillsDeveloper1 = new ArrayList<>();
        List<String> skillsDeveloper2 = new ArrayList<>();

        dipendenti.add(new Manager(11, "Giovanni", "Petito", 2500.00, 100, 1));
        dipendenti.add(new Manager(12, "Gianluigi", "Santillo", 2500.00, 100, 2));
        dipendenti.add(new Developer(21, "Daniele", "Musacchia", 1500.00, 1, skillsDeveloper1));
        dipendenti.add(new Developer(22, "Mariano", "Pinto", 1550.00, 2, skillsDeveloper2));
        dipendenti.add(new Developer(23, "Stefania", "Schettino", 1550.00, 2, skillsDeveloper2));
        dipendenti.add(new Developer(24, "Martina", "Pedrazzoli", 1550.00, 2, skillsDeveloper1));
        dipendenti.add(new Developer(25, "Roberta", "Normano", 1550.00, 2, skillsDeveloper1));
        dipendenti.add(new Inserviente(31, "Francesco", "Mongiello", 1100.00, 12));
        dipendenti.add(new Inserviente(32, "Pasquale", "Amato", 1150.00, 13));

        progetti.add(new Progetto("Progetto Java", 1));
        progetti.add(new Progetto("Progetto Android", 2));
        progetti.add(new Progetto("Progetto PHP", 3));
        progetti.add(new Progetto("Progetto Javascript", 4));
        progetti.add(new Progetto("Progetto SQL", 5));
        progetti.add(new Progetto("Progetto iOS", 6));
        progetti.add(new Progetto("Progetto Swift", 6));
        progetti.add(new Progetto("Progetto Kotlin", 6));
    }

    // Il metodo returnEmployeesOnProject(), ricevuto un intero, ritorna una lista di tutti i dipendenti associati a quel progetto.
    public List<Dipendente> returnEmployeesOnProject(int ID) {

        List<Dipendente> lista = new ArrayList<>();

        for(Dipendente dipendente : dipendenti) {
            if (dipendente instanceof Manager) {
                Manager manager = (Manager) dipendente;
                if (manager.getIdProgetto() == ID){
                    lista.add(manager);
                }
            }
            if (dipendente instanceof Developer) {
                Developer developer = (Developer) dipendente;
                if (developer.getIdProgetto() == ID){
                    lista.add(developer);
                }
            }
        }
        return lista;
    }

    public void inserisciDipendente(Dipendente dipendente){
        dipendenti.add(dipendente);
    }

    public boolean verificaID(int input) {
        for(Progetto progetto : progetti) {
            if (progetto.getId() == input) {
                return true;
            }
        }
        return false;
    }

    public String[] getArrayDipendenti() {

        List<String> lista = new ArrayList<>();

        for(Dipendente dipendente : dipendenti) {
            String stringa = "Dipendente " + dipendente.getId() + ": " + dipendente.getNome() + " " + dipendente.getCognome();
            lista.add(stringa);
        }
        String[] array = new String[lista.size()];
        array = lista.toArray(array);
        return array;
    }

    public String[] getArrayProgetti() {

        List<String> lista = new ArrayList<>();

        for(Progetto progetto : progetti) {
            String stringa = "Progetto " + progetto.getId() + ": " + progetto.getNome();
            lista.add(stringa);
        }

        String[] array = new String[lista.size()];
        array = lista.toArray(array);
        return array;
    }

    public void initProgetti() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);

        Date data = sdf.parse("15/01/2018", new ParsePosition(0));

        Progetto progetto = new Progetto(1,"Supervisionare","Aperti", "giovanni06", data);

        progetti.add(progetto);
        progetti.add(new Progetto(2,"Progettare","Chiusi", "giangio", sdf.parse("06/02/2018", new ParsePosition(0))));
        progetti.add(new Progetto(3,"Implementare","Pending", "lino", sdf.parse("02/03/2018", new ParsePosition(0))));
        progetti.add(new Progetto(4,"Testare","Aperti", "lino", sdf.parse("05/04/2018", new ParsePosition(0))));
        progetti.add(new Progetto(5,"Compilare","Chiusi", "giovanni06", sdf.parse("15/05/2018", new ParsePosition(0))));
        progetti.add(new Progetto(6,"Analizzare","Pending", "giangio", sdf.parse("18/06/2018", new ParsePosition(0))));
        progetti.add(new Progetto(7,"Inviare","Aperti", "giangio", sdf.parse("20/07/2018", new ParsePosition(0))));
        progetti.add(new Progetto(8,"Esportare","Chiusi", "lino", sdf.parse("12/08/2018", new ParsePosition(0))));
        progetti.add(new Progetto(9,"Salvare","Pending", "giovanni06", sdf.parse("29/09/2018", new ParsePosition(0))));
        progetti.add(new Progetto(10,"Eseguire","Aperti", "giovanni06", sdf.parse("07/10/2018", new ParsePosition(0))));
        progetti.add(new Progetto(11,"Programmare","Chiusi", "giangio", sdf.parse("29/11/2018", new ParsePosition(0))));
        progetti.add(new Progetto(12,"Sviluppare","Pending", "lino", sdf.parse("18/12/2018", new ParsePosition(0))));

        dipendenti.add(new Dipendente("Giovanni Petito","giovanni06","123"));
        dipendenti.add(new Dipendente("Gianluigi Santillo","giangio","456"));
        dipendenti.add(new Dipendente("Pasquale Amato","lino","789"));
    }

    // Metodo che riceve in input lo stato del progetto (Aperti/Chiusi/Pending) e restituisce la lista di progetti con quello stato.
    public List<Progetto> getProgettiCompleti(String stato) {

        List<Progetto> lista = new ArrayList<>();
        for(Progetto progetto : progetti) {
            if (progetto.getStato().equals(stato)) {
                lista.add(progetto);
            }
        }
        return lista;
    }

    // Metodo che riceve in input l'username dell'utente loggato e lo stato del progetto (Aperti/Chiusi/Pending) e restituisce
    // la lista di progetti con quello stato associati all'utente.
    public List<Progetto> getProgettiPersonali(String username, String stato) {

        List<Progetto> lista = new ArrayList<>();
        for(Progetto progetto : progetti) {
            if (progetto.getUser().equals(username) && progetto.getStato().equals(stato)) {
                lista.add(progetto);
            }
        }
        return lista;
    }

    // Metodo che riceve username e password e controlla se il dipendente è presente in azienda.
    public boolean cercaDipendente(String username, String password) {
        for(Dipendente dipendente : dipendenti) {
            if (dipendente.getUsername().equals(username) && dipendente.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Metodo che, dato l'username, restituisce il nome dell'utente (se esiste).
    public String mostraDipendente(String username) {
        for(Dipendente dipendente : dipendenti) {
            if (dipendente.getUsername().equals(username)) {
                return dipendente.getNome();
            }
        }
        return null;
    }
}