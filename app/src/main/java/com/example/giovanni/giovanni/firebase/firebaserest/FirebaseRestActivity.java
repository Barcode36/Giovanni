package com.example.giovanni.giovanni.firebase.firebaserest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.FirebaseService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

public class FirebaseRestActivity extends AppCompatActivity {

    private static String stringRest1;
    private static String stringRest2;
    private static String stringRest3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_rest);

        TextView textNome = findViewById(R.id.text_nome);
        TextView textPersona = findViewById(R.id.text_persona);
        TextView textLista = findViewById(R.id.text_lista);

        TextView textRest1 = findViewById(R.id.text_rest_1);
        TextView textRest2 = findViewById(R.id.text_rest_2);
        TextView textRest3 = findViewById(R.id.text_rest_3);

        textNome.setText(FirebaseService.listener1("01"));
        textPersona.setText(FirebaseService.listener2("01"));
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
        textRest3.setText(callRest3());
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

    public String callRest3() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://giovanni-740a0.firebaseio.com/response.json", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    stringRest3 = new String(responseBody);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                stringRest3 = new String(responseBody);
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
            }
        });
        return stringRest3;
    }
}