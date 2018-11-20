package com.example.giovanni.giovanni.recyclerviewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Azienda;

public class LoginActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private String username;
    private String password;
    private boolean control;
    private String message;
    private Azienda azienda;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button bLogin = findViewById(R.id.buttonLogin);
        eUsername = findViewById(R.id.editUsername);
        ePassword = findViewById(R.id.editPassword);

        azienda = new Azienda();
        azienda.initProgetti();

        bLogin.setOnClickListener(view -> {
            if (eUsername.getText().toString().equals("") || ePassword.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            } else {
                username = eUsername.getText().toString();
                password = ePassword.getText().toString();
                control = azienda.cercaDipendente(username, password);

                if (control) {
                    message = "Benvenuto " + azienda.mostraDipendente(username);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    intent = new Intent(getApplicationContext(), ProjectActivity.class);
                    intent.putExtra("AZIENDA", azienda);
                    intent.putExtra("UTENTE", username);
                    startActivity(intent);
                } else {
                    eUsername.setText("");
                    ePassword.setText("");
                    Toast.makeText(getApplicationContext(), "Accesso negato", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}