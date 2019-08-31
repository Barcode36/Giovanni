package com.example.giovanni.giovanni.listview.listviewsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;

public class ListViewSimpleActivity extends AppCompatActivity {

    private ListView simpleList;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_listview);

        simpleList = findViewById(R.id.list_1);
        list = findViewById(R.id.list_2);

        String[] array = new String[] {"Giovanni", "Raffaele", "Teresa", "Angela", "Vincenzo", "Salvatore", "Ilenia"};

        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_listview, R.id.listview_item, array);

        simpleList.setAdapter(simpleAdapter);
        list.setAdapter(adapter);

        simpleList.setOnItemClickListener((parent, view, position, id) -> {
            String stringa = simpleList.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
        });

        list.setOnItemClickListener((parent, view, position, id) -> {
            String stringa = list.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
        });
    }
}