package com.example.giovanni.giovanni.listviewazienda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Azienda;

public class ListActivity extends AppCompatActivity {

    private ListView listaDipendenti;
    private ListView listaProgetti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        Azienda azienda = (Azienda) intent.getSerializableExtra("AZIENDA");

        listaDipendenti = findViewById(R.id.listaDipendenti);
        listaProgetti = findViewById(R.id.listaProgetti);

        String[] arrayDipendenti = azienda.getArrayDipendenti();
        String[] arrayProgetti = azienda.getArrayProgetti();

        ArrayAdapter<String> adapterDipendenti = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayDipendenti);

        listaDipendenti.setAdapter(adapterDipendenti);
        listaDipendenti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String stringa = listaDipendenti.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> adapterProgetti = new ArrayAdapter<>(this,
                R.layout.list_progetti_item, R.id.textItem, arrayProgetti);

        listaProgetti.setAdapter(adapterProgetti);
        listaProgetti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String stringa = listaProgetti.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
            }
        });
    }
}