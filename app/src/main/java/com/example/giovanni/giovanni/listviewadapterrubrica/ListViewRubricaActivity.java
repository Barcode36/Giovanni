package com.example.giovanni.giovanni.listviewadapterrubrica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;
import com.example.giovanni.giovanni.pojo.Rubrica;

public class ListViewRubricaActivity extends AppCompatActivity {

    private ListView lista;
    private Rubrica rubrica;
    private Persona contatto;
    private String message;
    private AdapterContatto adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_rubrica);

        Intent intent = getIntent();
        rubrica = (Rubrica) intent.getSerializableExtra("KEY");

        lista = findViewById(R.id.lista_rubrica);

        String[] array = rubrica.getArray(); // Non uso l'array, dunque il metodo getArray() non mi serve.

        adapter = new AdapterContatto(getApplicationContext(), R.layout.list_contatto_item, rubrica.getContatti());

        lista.setAdapter(adapter);

        lista.setOnItemClickListener((parent, view, position, id) -> {

            contatto = (Persona) lista.getItemAtPosition(position);
            message = "Hai eliminato " + contatto.getNome() + " " + contatto.getCognome();
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            rubrica.elimina(position);
            adapter.notifyDataSetChanged();
        });
    }
}