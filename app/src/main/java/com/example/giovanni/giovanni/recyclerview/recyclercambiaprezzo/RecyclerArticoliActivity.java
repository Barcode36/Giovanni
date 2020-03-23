package com.example.giovanni.giovanni.recyclerview.recyclercambiaprezzo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Articolo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerArticoliActivity extends AppCompatActivity {

    private ArticoliAdapter mAdapter;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_articoli);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        data = sdf.format(new Date());

        RecyclerView mRecyclerView = findViewById(R.id.articoli_recyclerview);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        /*
        O anche solo:
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        Se voglio una griglia:
        mLayoutManager = new GridLayoutManager(this, 2);
        */

        mRecyclerView.setLayoutManager(mLayoutManager);

        final List<Articolo> lista = init();
        Articolo articolo = new Articolo("Frutta", 1.00, data);
        lista.add(articolo);

        mAdapter = new ArticoliAdapter(lista, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 50) {
            if (resultCode == Activity.RESULT_OK) {
                int position = data.getIntExtra("POSIZIONE", -1);
                double prezzo = data.getDoubleExtra("PREZZO", -1);
                mAdapter.cambiaPrezzo(position, prezzo);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public List<Articolo> init() {

        List<Articolo> list = new ArrayList<>();
        list.add(new Articolo("Latte", 1.50, data));
        list.add(new Articolo("Uova", 3.00, data));
        list.add(new Articolo("Pesce", 10.50, data));
        list.add(new Articolo("Pasta", 1.00, data));
        list.add(new Articolo("Pane", 1.00, data));
        list.add(new Articolo("Acqua", 0.60, data));
        list.add(new Articolo("Carne", 12.00, data));
        list.add(new Articolo("Formaggio", 5.00, data));

        return list;
    }
}