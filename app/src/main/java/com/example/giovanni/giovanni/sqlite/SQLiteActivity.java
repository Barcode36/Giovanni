package com.example.giovanni.giovanni.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;

import java.util.ArrayList;

public class SQLiteActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private ListView lista;
    private String contatto;
    ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        Button buttonInsert = findViewById(R.id.button_insert);
        lista = findViewById(R.id.list);

        /*
         * Popolare una listview con tutti i nomi presenti in rubrica leggendo dal database.
         * La lista al primo avvio sarà vuota.
         * */

        helper = new DatabaseHelper(getApplicationContext());

        helper.insertContact("Giovanni", "Petito");
        helper.insertContact("Raffaele", "Petito");
        helper.insertContact("Teresa", "Petito");
        helper.insertContact("Angela", "Basile");
        helper.insertContact("Vincenzo", "Petito");
        helper.insertContact("Salvatore", "Pragliola");
        helper.insertContact("Ilenia", "Pragliola");

        arrayList = helper.getAllCotacts();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        lista.setAdapter(arrayAdapter);

        lista.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(getApplicationContext(), SQLiteDetailActivity.class);
            contatto = lista.getItemAtPosition(position).toString();

            intent.putExtra("SHOW", contatto);
            startActivity(intent);
        });

        buttonInsert.setOnClickListener(view -> {
            intent = new Intent(getApplicationContext(), SQLiteInsertActivity.class);
            startActivityForResult(intent, 50);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_OK) {
            arrayList = helper.getAllCotacts();
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            lista.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}