package com.example.giovanni.giovanni.utils;

import com.example.giovanni.giovanni.model.Articolo;
import com.example.giovanni.giovanni.model.Camicia;
import com.example.giovanni.giovanni.model.Libro;
import com.example.giovanni.giovanni.model.Persona;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONParse {

    public static List<Persona> getList(String json) throws JSONException {

        List<Persona> persone = new ArrayList<>();

        try {
            JSONObject jsonObject1 = new JSONObject(json);
            Iterator iterator1 = jsonObject1.keys();

            while (iterator1.hasNext()) {

                String key1 = (String) iterator1.next();
                JSONObject jsonObject2 = jsonObject1.getJSONObject(key1);
                Iterator<String> iterator2 = jsonObject2.keys();

                while (iterator2.hasNext()) {

                    Persona persona = new Persona();
                    String key2 = iterator2.next();
                    JSONObject jsonObject3 = jsonObject2.getJSONObject(key2);
                    Iterator<String> iterator3 = jsonObject3.keys();

                    while (iterator3.hasNext()) {

                        String key3 = iterator3.next();
                        if (key1.equals("list")) {

                            if (key3.equals("cognome")) {
                                String cognome = jsonObject3.getString(key3);
                                persona.setCognome(cognome);
                            }
                            if (key3.equals("nome")) {
                                String nome = jsonObject3.getString(key3);
                                persona.setNome(nome);
                            }
                            if (key3.equals("matricola")) {
                                long matricola = jsonObject3.getLong(key3);
                                persona.setMatricola(matricola);
                            }
                        }
                    }
                    if (key1.equals("list")) {
                        persone.add(persona);
                    }
                }
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        return persone;
    }

    public static List<Persona> getUtenti(String json) throws JSONException {

        List<Persona> utenti = new ArrayList<>();

        try {
            JSONObject jsonObject1 = new JSONObject(json);
            Iterator iterator1 = jsonObject1.keys();

            while (iterator1.hasNext()) {

                String key1 = (String) iterator1.next();
                JSONObject jsonObject2 = jsonObject1.getJSONObject(key1);
                Iterator<String> iterator2 = jsonObject2.keys();

                while (iterator2.hasNext()) {

                    Persona persona = new Persona();
                    String key2 = iterator2.next();
                    JSONObject jsonObject3 = jsonObject2.getJSONObject(key2);
                    Iterator<String> iterator3 = jsonObject3.keys();

                    while (iterator3.hasNext()) {

                        String key3 = iterator3.next();
                        if (key1.equals("utenti")) {

                            if (key3.equals("username")) {
                                String username = jsonObject3.getString(key3);
                                persona.setUsername(username);
                            }
                            if (key3.equals("password")) {
                                String password = jsonObject3.getString(key3);
                                persona.setPassword(password);
                            }
                        }
                    }
                    if (key1.equals("utenti")) {
                        utenti.add(persona);
                    }
                }
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        return utenti;
    }

    public static List<Articolo> getArticoli(String json) throws JSONException {

        List<Articolo> articoli = new ArrayList<>();

        try {
            JSONObject jsonObject1 = new JSONObject(json);
            Iterator<String> iterator1 = jsonObject1.keys();

            while (iterator1.hasNext()) {
                String key1 = iterator1.next();
                JSONObject jsonObject2 = jsonObject1.getJSONObject(key1);

                Iterator<String> iterator2 = jsonObject2.keys();
                while (iterator2.hasNext()) {

                    String key2 = iterator2.next();
                    JSONObject jsonObject3 = jsonObject2.getJSONObject(key2);

                    Iterator<String> iterator3 = jsonObject3.keys();
                    while (iterator3.hasNext()) {

                        Libro libro = new Libro();
                        Camicia camicia = new Camicia();
                        Articolo.Gelato gelato = new Articolo.Gelato();

                        String key3 = iterator3.next();
                        JSONObject jsonObject4 = jsonObject3.getJSONObject(key3);

                        Iterator<String> iterator4 = jsonObject4.keys();
                        while (iterator4.hasNext()) {
                            String key4 = iterator4.next();
                            if (key2.equals("camicia")) {
                                if (key4.equals("nome")) {
                                    String marca = jsonObject4.getString(key4);
                                    camicia.setNome(marca);
                                }
                                else if (key4.equals("prezzo")) {
                                    String prezzoString = jsonObject4.getString(key4);
                                    long prezzo = Long.parseLong(prezzoString);
                                    camicia.setPrezzo(prezzo);
                                }
                            }
                            if (key2.equals("libro")) {
                                if (key4.equals("nome")) {
                                    String marca = jsonObject4.getString(key4);
                                    libro.setNome(marca);
                                }
                                else if (key4.equals("prezzo")) {
                                    String prezzoString = jsonObject4.getString(key4);
                                    long prezzo = Long.parseLong(prezzoString);
                                    libro.setPrezzo(prezzo);
                                }
                            }
                            if (key2.equals("gelato")) {
                                if (key4.equals("nome")) {
                                    String marca = jsonObject4.getString(key4);
                                    gelato.setNome(marca);
                                }
                                else if (key4.equals("prezzo")) {
                                    String prezzoString = jsonObject4.getString(key4);
                                    long prezzo = Long.parseLong(prezzoString);
                                    gelato.setPrezzo(prezzo);
                                }
                            }
                        }
                        if (key2.equals("libro"))
                            articoli.add(libro);
                        if (key2.equals("camicia"))
                            articoli.add(camicia);
                        if (key2.equals("gelato"))
                            articoli.add(gelato);
                    }
                }
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        return articoli;
    }

    public static int getKey(String json) {
        int index = 1;
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                index++;
                iterator.next();
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        return index;
    }

    public static int getProductKey(String json) {

        int index = 1;
        try {
            JSONObject jsonObject1 = new JSONObject(json);
            Iterator<String> iterator1 = jsonObject1.keys();

            while (iterator1.hasNext()) {
                String key1 = iterator1.next();
                JSONObject jsonObject2 = jsonObject1.getJSONObject(key1);
                Iterator iterator2 = jsonObject2.keys();

                while (iterator2.hasNext()) {
                    index++;
                    iterator2.next();
                }

                // index++;
                // iterator1.next();
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        return index;
    }
}
