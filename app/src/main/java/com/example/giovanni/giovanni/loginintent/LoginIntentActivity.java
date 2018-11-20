package com.example.giovanni.giovanni.loginintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.DatabaseUtenti;
import com.example.giovanni.giovanni.pojo.Persona;

public class LoginIntentActivity extends AppCompatActivity {

    public static String TAG = "LOGINUTENTE";
    public static String alert = "Riempi tutti i campi";
    public static String loginSuccess = "Accesso effettuato";
    public static String loginError = "Accesso negato";
    public static String signupSuccess = "Registrazione effettuata con successo";
    public static String signupError = "Registrazione fallita";

    private EditText eUsername;
    private EditText ePassword;
    private TextView tAccedi;
    private TextView tRegistrati;
    private String username;
    private String password;
    private DatabaseUtenti database;
    private boolean login;
    private boolean signUp;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_login);

        Button bLogin = findViewById(R.id.button_login);
        Button bSignUp = findViewById(R.id.button_signup);
        eUsername = findViewById(R.id.edit_username);
        ePassword = findViewById(R.id.edit_password);
        tAccedi = findViewById(R.id.text_accedi);
        tRegistrati = findViewById(R.id.text_registrati);

        database = new DatabaseUtenti();

        database.inserisci(new Persona("Giovanni", "123"));
        database.inserisci(new Persona("Gianluigi", "234"));
        database.inserisci(new Persona("Mariano", "345"));
        database.inserisci(new Persona("Daniele", "456"));
        database.inserisci(new Persona("Frank", "567"));
        database.inserisci(new Persona("Lino", "678"));
        database.inserisci("Raffaele", "789");

        Log.i(TAG, "" + database.getUtenti());
        Log.i(TAG, "" + database.toString());

        bLogin.setOnClickListener(v -> {
            username = eUsername.getText().toString();
            password = ePassword.getText().toString();

            if (username.equals("") || password.equals("")) {
                tAccedi.setText(alert);
            } else {
                login = database.checkLogin(username, password);
                if (login) {
                    tAccedi.setText(loginSuccess);
                    tRegistrati.setText("");

                    intent = new Intent(getApplicationContext(), PodcastActivity.class);
                    // Oppure:
                    // intent = new Intent();
                    // intent.setClass(getApplicationContext(), PodcastActivity.class);
                    intent.putExtra("KEY", "" + username);
                    startActivity(intent);
                } else {
                    tAccedi.setText(loginError);
                    tRegistrati.setText("");
                }
            }
        });

        bSignUp.setOnClickListener(v -> {
            username = eUsername.getText().toString();
            password = ePassword.getText().toString();

            if (username.equals("") || password.equals("")) {
                tRegistrati.setText(alert);
            } else {
                signUp = database.verificaUsername(username);
                if (signUp) {
                    database.inserisci(username, password);
                    tRegistrati.setText(signupSuccess);
                    tAccedi.setText("");
                } else {
                    tRegistrati.setText(signupError);
                    tAccedi.setText("");
                }
            }
        });
    }
}