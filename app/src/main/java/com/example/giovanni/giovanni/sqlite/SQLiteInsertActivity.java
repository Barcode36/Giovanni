package com.example.giovanni.giovanni.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;

public class SQLiteInsertActivity extends AppCompatActivity {

    private EditText eNome;
    private EditText eCognome;
    private Intent intent;
    private String nome;
    private String cognome;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert);

        helper = new DatabaseHelper(getApplicationContext());
        intent = getIntent();

        Button bConferma = findViewById(R.id.button_conferma);
        eNome = findViewById(R.id.edit_nome);
        eCognome = findViewById(R.id.edit_cognome);

        bConferma.setOnClickListener(view -> {

            if (eNome.getText().toString().equals("") || eCognome.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Riempi tutti i campi", Toast.LENGTH_SHORT).show();
            } else {
                nome = eNome.getText().toString();
                cognome = eCognome.getText().toString();

                helper.insertContact(nome, cognome);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}