package com.example.giovanni.giovanni.twolistviewonebuttonadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;
import com.example.giovanni.giovanni.pojo.Rubrica;

public class TwoListActivity extends AppCompatActivity {

    private ListView listaUno;
    private ListView listaDue;
    private Rubrica rubrica1;
    private Rubrica rubrica2;
    private Persona contatto;
    private AdapterContatto adapter1;
    private AdapterContatto adapter2;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twolist);

        rubrica1 = new Rubrica();
        rubrica1.init();

        rubrica2 = new Rubrica();

        Button bSposta = findViewById(R.id.buttonSposta);
        listaUno = findViewById(R.id.list_1);
        listaDue = findViewById(R.id.list_2);

        adapter1 = new AdapterContatto(getApplicationContext(), R.layout.list_contatto_item, rubrica1.getContatti());
        listaUno.setAdapter(adapter1);

        adapter2 = new AdapterContatto(getApplicationContext(), R.layout.list_contatto_item, rubrica2.getContatti());
        listaDue.setAdapter(adapter2);

        i = 0;

        bSposta.setOnClickListener(v -> {

            if(i < adapter1.getCount()) {
                contatto = (Persona) listaUno.getItemAtPosition(i);
                rubrica2.aggiungi(contatto);

                String message = "Hai spostato " + contatto.getNome() + " " + contatto.getCognome();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                rubrica1.elimina(i);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });
    }
}