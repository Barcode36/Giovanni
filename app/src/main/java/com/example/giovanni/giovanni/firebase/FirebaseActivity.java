package com.example.giovanni.giovanni.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        TextView hello = findViewById(R.id.text_nome);
        TextView textPersona = findViewById(R.id.text_persona);
        TextView textLista = findViewById(R.id.text_lista);

        FirebaseService.listener1(hello, "utente1");
        FirebaseService.listener2(textPersona, "utente1");
        FirebaseService.listener3(textLista);

        /*
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // L'istanza è l'URL principale
        // DatabaseReference myRef = database.getReference("message"); // Prende la chiave del mio database su Firebase. Nel caso non la trova, la crea.
        DatabaseReference myRef = database.getReferenceFromUrl("https://fir-test-6dcd2.firebaseio.com/Utenti/Utente/Utente1/Nome");
        // myRef.setValue("Raffaele"); // prende il valore della chiave sul database.

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("MAINACTIVITY", "Value is: " + value);

                textView.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MAINACTIVITY", "Failed to read value.", error.toException());
            }
        });
        */
    }
}