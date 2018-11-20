package com.example.giovanni.giovanni.recyclerviewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Azienda;
import com.example.giovanni.giovanni.pojo.Dipendente;
import com.example.giovanni.giovanni.pojo.Progetto;

import java.util.List;

public class ResultProjectActivity extends AppCompatActivity {

    List<Progetto> progettiPersonali;
    List<Progetto> progettiCompleti;
    private static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_project);

        Intent intent = getIntent();
        Azienda azienda = (Azienda) intent.getSerializableExtra("AZIENDA");
        List<Dipendente> dipendenti = azienda.getDipendenti();
        List<Progetto> progetti = azienda.getProgetti(); // Ho ricavato pure la lista di task anche se non mi serve.

        username = intent.getStringExtra("UTENTE");
        String stato = intent.getStringExtra("SPINNER");
        boolean checkPersonali = intent.getBooleanExtra("CHECKPERSONALI", false);
        boolean checkCompleti = intent.getBooleanExtra("CHECKCOMPLETI", false);

        TextView tUserLogged = findViewById(R.id.textUserLogged);
        tUserLogged.setText(username);

        RecyclerView recyclerPersonali = findViewById(R.id.recyclerViewPersonali);
        RecyclerView recyclerCompleti = findViewById(R.id.recyclerViewCompleti);
        recyclerPersonali.setLayoutManager(new LinearLayoutManager(this));
        recyclerCompleti.setLayoutManager(new LinearLayoutManager(this));

        if (checkPersonali && checkCompleti) {
            progettiCompleti = azienda.getProgettiCompleti(stato);
            progettiPersonali = azienda.getProgettiPersonali(username, stato);
            recyclerPersonali.setAdapter(new AdapterPersonali(progettiPersonali));
            recyclerCompleti.setAdapter(new AdapterCompleti(progettiCompleti, dipendenti, getApplicationContext()));
        }

        if (checkPersonali && !checkCompleti) {
            progettiPersonali = azienda.getProgettiPersonali(username, stato);
            recyclerPersonali.setAdapter(new AdapterPersonali(progettiPersonali));
        }

        if (checkCompleti && !checkPersonali) {
            progettiCompleti = azienda.getProgettiCompleti(stato);
            recyclerCompleti.setAdapter(new AdapterCompleti(progettiCompleti, dipendenti, getApplicationContext()));
        }
    }

    public static String getUserLogged() {
        return username;
    }
}