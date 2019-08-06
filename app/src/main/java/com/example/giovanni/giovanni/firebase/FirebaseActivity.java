package com.example.giovanni.giovanni.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

public class FirebaseActivity extends AppCompatActivity {

    private String stringRest1;
    private String stringRest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        TextView textNome = findViewById(R.id.text_nome);
        TextView textPersona = findViewById(R.id.text_persona);
        TextView textLista = findViewById(R.id.text_lista);

        TextView textRest1 = findViewById(R.id.text_rest_1);
        TextView textRest2 = findViewById(R.id.text_rest_2);
        TextView textRest3 = findViewById(R.id.text_rest_3);

        textNome.setText(FirebaseService.listener1("utente1"));
        textPersona.setText(FirebaseService.listener2("utente1"));
        textLista.setText(FirebaseService.listener3());

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

        textRest1.setText(callRest1("response.json"));
        textRest2.setText(callRest2());
        textRest3.setText(FirebaseRestClient.callRest3());
    }

    public String callRest1(String url) {

        FirebaseRestClient.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    stringRest1 = new String(responseBody);
                    // JSONParse.getList(text);
                    // delegate.taskCompletionResult(null);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {}
        });
        return stringRest1;
    }

    public String callRest2() {

        FirebaseRestClient.get("response.json", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    stringRest2 = new String(responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {}
        });
        return stringRest2;
    }
}