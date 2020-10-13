package com.example.giovanni.giovanni.listview.twolistviewadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Persona;
import com.example.giovanni.giovanni.bean.Rubrica;

public class TwoListViewAdapterActivity extends AppCompatActivity {

    private ListView lista1;
    private Rubrica rubrica1;
    private Rubrica rubrica2;
    private Persona contatto;
    private ContattoAdapter adapter1;
    private ContattoAdapter adapter2;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_listview);

        findViewById(R.id.button_container).setVisibility(View.VISIBLE);

        rubrica1 = new Rubrica();
        rubrica1.init2();

        rubrica2 = new Rubrica();

        Button buttonCopy = findViewById(R.id.button_copy);
        lista1 = findViewById(R.id.list_1);
        ListView lista2 = findViewById(R.id.list_2);

        adapter1 = new ContattoAdapter(getApplicationContext(), R.layout.list_contatto_item, rubrica1.getContatti());
        lista1.setAdapter(adapter1);

        adapter2 = new ContattoAdapter(getApplicationContext(), R.layout.list_contatto_item, rubrica2.getContatti());
        lista2.setAdapter(adapter2);

        i = 0;

        buttonCopy.setOnClickListener(v -> {

            if (i < adapter1.getCount()) {
                contatto = (Persona) lista1.getItemAtPosition(i);
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