package com.example.giovanni.giovanni.listview.listviewazienda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Azienda;

public class ListViewAziendaActivity extends AppCompatActivity {

    private ListView listaDipendenti;
    private ListView listaProgetti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_listview);

        Intent intent = getIntent();
        Azienda azienda = (Azienda) intent.getSerializableExtra("AZIENDA");

        listaDipendenti = findViewById(R.id.list_1);
        listaProgetti = findViewById(R.id.list_2);

        String[] arrayDipendenti = azienda.getArrayDipendenti();
        String[] arrayProgetti = azienda.getArrayProgetti();

        ArrayAdapter<String> adapterDipendenti = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayDipendenti);

        listaDipendenti.setAdapter(adapterDipendenti);
        listaDipendenti.setOnItemClickListener((parent, view, position, id) -> {

            String stringa = listaDipendenti.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
        });

        ArrayAdapter<String> adapterProgetti = new ArrayAdapter<>(this,
                R.layout.list_progetti_item, R.id.textItem, arrayProgetti);

        listaProgetti.setAdapter(adapterProgetti);
        listaProgetti.setOnItemClickListener((parent, view, position, id) -> {

            String stringa = listaProgetti.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
        });
    }
}