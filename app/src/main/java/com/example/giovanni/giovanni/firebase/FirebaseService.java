package com.example.giovanni.giovanni.firebase;

import android.support.annotation.NonNull;
import android.util.Log;
import com.example.giovanni.giovanni.pojo.Persona;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

class FirebaseService {

    private static Persona persona;
    private static String nome;
    private static String nomeCognome;
    private static String lista;

    static String listener1(String utente) {

        // Write a message to the database.
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // L'istanza è l'URL principale.
        DatabaseReference reference = database.getReferenceFromUrl(
                "https://giovanni-740a0.firebaseio.com/response/utenti/" + utente + "/nome"
        );

        // https://giovanni-740a0.firebaseio.com/response/utenti/utente1/nome

        // Read from the database.
        reference.addValueEventListener(new ValueEventListener() {

            // Questo metodo viene chiamato una volta con il valore iniziale e ancora ogni volta che
            // i dati in questa posizione vengono aggiornati.
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nome = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { // Failed to read value.
                Log.w("TAGFIREBASE", "Failed to read value.", error.toException());
            }
        });
        return nome;
    }

    static String listener2(String utente) {

        Log.i("TAGFIREBASE", utente);
        // Write a message to the database.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReferenceFromUrl(
                "https://giovanni-740a0.firebaseio.com/response/utenti/" + utente
        );

        // Read from the database.
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again whenever data at this
                // location is updated.
                persona = dataSnapshot.getValue(Persona.class);

                String nome = null;
                String cognome = null;
                String id = null;

                if (persona != null) {
                    nome = persona.getNome();
                    cognome = persona.getCognome();
                    id = String.valueOf(persona.getId());
                }

                nomeCognome = nome + " " + cognome + " " + id;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("TAGFIREBASE", "Failed to read value.", error.toException());
            }
        });
        return nomeCognome;
    }

    static String listener3() {

        // Write a message to the database.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReferenceFromUrl(
                "https://giovanni-740a0.firebaseio.com/response/utenti"
        );

        // Read from the database.
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<Persona> list = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Persona person = data.getValue(Persona.class);
                    list.add(person);
                }

                lista = list.get(0).getNome() + " " + list.get(0).getCognome() + ", id " + list.get(0).getId() + "\n" +
                        list.get(1).getNome() + " " + list.get(1).getCognome() + ", id " + list.get(1).getId();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("TAGFIREBASE", "Failed to read value.", error.toException());
            }
        });
        return lista;
    }
}
