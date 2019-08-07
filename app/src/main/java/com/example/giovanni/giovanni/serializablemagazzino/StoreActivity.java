package com.example.giovanni.giovanni.serializablemagazzino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Articolo;
import com.example.giovanni.giovanni.model.Camicia;
import com.example.giovanni.giovanni.model.Libro;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        TextView tArticolo = findViewById(R.id.textArticolo);
        TextView tArticoli = findViewById(R.id.textArticoli);

        Intent intent = getIntent();
        Articolo articolo = (Articolo) intent.getSerializableExtra("ARTICOLO");

        if (articolo instanceof Camicia) {
            Camicia camicia = (Camicia) articolo;
            tArticolo.setText("CAMICIA");
            tArticoli.setText(camicia.stampaCamicia());
        }

        if (articolo instanceof Libro) {
            Libro libro = (Libro) articolo;
            tArticolo.setText("LIBRO");
            tArticoli.setText(libro.stampaLibro());
        }
    }
}