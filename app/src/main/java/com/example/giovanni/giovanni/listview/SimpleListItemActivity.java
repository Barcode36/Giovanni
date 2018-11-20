package com.example.giovanni.giovanni.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;

public class SimpleListItemActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplelistitem);

        lista = findViewById(R.id.list1);

        String[] array = new String[] {"Giovanni", "Raffaele", "Teresa", "Angelina", "Vincenzo", "Gianluigi", "Daniele", "Mariano", "Frank", "Lino", "Francesca", "Martina", "Sonia"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener((parent, view, position, id) -> {
            String stringa = lista.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
        });
    }
}