package com.example.giovanni.giovanni.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;

public class ListItemActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem);

        lista = findViewById(R.id.list2);

        String[] array = new String[] {"Giovanni", "Raffaele", "Teresa", "Angelina", "Vincenzo", "Gianluigi", "Daniele", "Mariano", "Frank", "Lino", "Francesca", "Martina", "Sonia"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.textItem, array);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener((parent, view, position, id) -> {
            String stringa = lista.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
        });
    }
}