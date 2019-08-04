package com.example.giovanni.giovanni.firebase;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseService {

    private static String value;

    public static void listenerFirebase(final TextView textview, String utente) {

        // Write a message to the database.
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // L'istanza è l'URL principale.
        DatabaseReference myReference = database.getReferenceFromUrl("https://giovanni-740a0.firebaseio.com/response/utenti/" + utente + "/nome");

        // https://giovanni-740a0.firebaseio.com/response/utenti/utente1/nome

        // Read from the database.
        myReference.addValueEventListener(new ValueEventListener() {

            // Questo metodo viene chiamato una volta con il valore iniziale e ancora ogni volta che i dati in questa posizione vengono aggiornati.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                textview.setText(value);
                Log.d("MYFIREBASE", "Value is: " + value); // Stampa Giovanni
            }

            @Override
            public void onCancelled(DatabaseError error) { // Failed to read value.
                Log.w("MYFIREBASE", "Failed to read value.", error.toException());
            }
        });
    }
}
