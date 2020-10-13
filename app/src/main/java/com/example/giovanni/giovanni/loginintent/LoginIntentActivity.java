package com.example.giovanni.giovanni.loginintent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.PersonaManager;
import com.example.giovanni.giovanni.bean.Persona;
import com.example.giovanni.giovanni.customview.CustomButton;

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
    private CustomButton buttonOne;
    private CustomButton buttonTwo;
    private String username;
    private String password;
    private PersonaManager database;
    private boolean login;
    private boolean signUp;
    private Intent intent;

    private SharedPreferences preferences;

    private int count = 0;
    private long startMillis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_login);

        // preferences = getPreferences(MODE_PRIVATE);
        // preferences = getSharedPreferences("File", MODE_PRIVATE);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Button bLogin = findViewById(R.id.button_login);
        Button bSignUp = findViewById(R.id.button_signup);
        eUsername = findViewById(R.id.edit_username);
        ePassword = findViewById(R.id.edit_password);
        tAccedi = findViewById(R.id.text_accedi);
        tRegistrati = findViewById(R.id.text_registrati);
        buttonOne = findViewById(R.id.button_one);
        buttonTwo = findViewById(R.id.button_two);

        database = new PersonaManager();
        database.inserisci(new Persona("Giovanni", "123"));
        database.inserisci("Raffaele", "234");
        database.init();

        Log.i(TAG, "" + database.getUtenti());
        Log.i(TAG, "" + database.toString());

        username = preferences.getString("USERNAME", "" + database.returnUsername(username));
        eUsername.setText(username);

        if (eUsername.getText().toString().equals(database.returnUsername(username))) {

            intent = new Intent(getApplicationContext(), PodcastActivity.class);
            startActivity(intent);
        } else {
            eUsername.setText("");
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

                        saveStateToPreferences();

                        intent = new Intent(getApplicationContext(), PodcastActivity.class);
                        // Oppure:
                        // intent = new Intent();
                        // intent.setClass(getApplicationContext(), PodcastActivity.class);
                        intent.putExtra("KEY", "" + username);
                        startActivity(intent);
                    } else {
                        eUsername.setText("");
                        ePassword.setText("");
                        tAccedi.setText(loginError);
                        tRegistrati.setText("");
                    }
                }
            });
        }

        bSignUp.setOnClickListener(v -> {
            username = eUsername.getText().toString();
            password = ePassword.getText().toString();

            if (username.equals("") || password.equals("")) {
                tRegistrati.setText(alert);
            } else {
                signUp = database.checkUsername(username);
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

        buttonOne.setOnClickListener(view -> {
            boolean selection = !buttonOne.isSelected();
            buttonOne.setSelected(selection);
            if (selection) {
                buttonTwo.setSelected(false);
            }
        });

        buttonTwo.setOnClickListener(view -> {
            boolean selection = !buttonTwo.isSelected();
            buttonTwo.setSelected(selection);
            if (selection) {
                buttonOne.setSelected(false);
            }
        });
    }

    private void saveStateToPreferences() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("USERNAME", eUsername.getText().toString());
        editor.apply();
    }

    // Detect any touch event in the screen (instead of an specific view).
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventaction = event.getAction();
        if (eventaction == MotionEvent.ACTION_UP) {

            // Get system current milliseconds.
            long time = System.currentTimeMillis();

            // If it is the first time, or if it has been more than 3 seconds since the first tap, we reset everything.
            if (startMillis == 0 || (time - startMillis > 3000) ) {
                startMillis = time;
                count = 1;
            }
            // It is not the first tap, and it has been less than 3 seconds since the first tap (time-startMillis < 3000).
            else {
                count++;
            }

            if (count == 5) {
                Toast.makeText(getApplicationContext(), "onTouchEvent", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return false;
    }
}