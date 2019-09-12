package com.example.giovanni.giovanni.listview.listviewazienda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Azienda;
import com.example.giovanni.giovanni.model.Persona;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindActivity extends AppCompatActivity {

    private EditText eCerca;
    private TextView tElenco;
    private ListView listaRicerca;
    private String ID;
    private int idInt;
    private String textElenco;
    private String listElenco;
    private final static String INT_REGEX = "\\d";
    private Azienda azienda;
    private String[] array;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        Intent intent = getIntent();
        azienda = (Azienda) intent.getSerializableExtra("FIND");

        Button bConferma = findViewById(R.id.buttonConferma);
        eCerca = findViewById(R.id.editCerca);
        tElenco = findViewById(R.id.textElenco);
        listaRicerca = findViewById(R.id.listaRicerca);
        listElenco = "";

        bConferma.setOnClickListener(v -> {

            tElenco.setText("");
            if (eCerca.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Inserisci l'ID", Toast.LENGTH_SHORT).show();
            else {
                textElenco = tElenco.getText().toString();

                ID = eCerca.getText().toString();
                Pattern pID = Pattern.compile(INT_REGEX);
                Matcher mID = pID.matcher(ID);
                if (!mID.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore ID non valido", Toast.LENGTH_SHORT).show();
                    eCerca.setText("");
                } else
                    idInt = Integer.parseInt(ID);

                if (azienda.verificaID(idInt)) {

                    List<Persona> dipendenti = azienda.returnEmployeesOnProject(idInt);
                    for (Persona dipendente : dipendenti) {
                        textElenco = textElenco + dipendente.getNome() + " " + dipendente.getCognome() + "\n";
                        listElenco = listElenco + dipendente.getNome() + " " + dipendente.getCognome() + ",";
                    }
                    array = listElenco.split(",");

                    if (textElenco.equals(""))
                        Toast.makeText(getApplicationContext(), "Non esistono dipendenti per il progetto con ID " + ID, Toast.LENGTH_SHORT).show();
                    else {
                        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, array);
                        listaRicerca.setAdapter(arrayAdapter);
                        tElenco.setText(textElenco);
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Non esistono progetti con ID " + ID, Toast.LENGTH_SHORT).show();
            }
        });

        listaRicerca.setOnItemClickListener((parent, view, position, id) -> {

            String stringa = listaRicerca.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), stringa, Toast.LENGTH_SHORT).show();
        });
    }
}