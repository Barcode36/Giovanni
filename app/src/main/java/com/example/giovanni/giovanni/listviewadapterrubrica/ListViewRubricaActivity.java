package com.example.giovanni.giovanni.listviewadapterrubrica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import com.example.giovanni.giovanni.model.Rubrica;
import com.example.giovanni.giovanni.utils.InternalStorage;

import java.util.List;

public class ListViewRubricaActivity extends AppCompatActivity {

    private Persona contatto;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_rubrica);

        ListView lista = findViewById(R.id.lista_rubrica);

        Rubrica rubrica = new Rubrica();

        // ------ PRIMO METODO -------
        List<Persona> contatti;
        contatti = Rubrica.init();
        rubrica.setContatti(contatti);
        // ---------------------------

        // ------ SECONDO METODO -----
        rubrica.init2();
        // ---------------------------

        AdapterRubrica adapter = new AdapterRubrica(getApplicationContext(), R.layout.list_contatto_item, rubrica.getContatti());
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // INTERNAL STORAGE (WRITE)
        String fileRubrica = "FileRubrica";
        InternalStorage.writeObject(this, fileRubrica, rubrica);

        lista.setOnItemClickListener((parent, view, position, id) -> {

            // INTERNAL STORAGE (READ)
            Rubrica rubricaFile = (Rubrica) InternalStorage.readObject(this,"FileRubrica");

            contatto = (Persona) lista.getItemAtPosition(position);
            message = "Hai eliminato " + contatto.getNome() + " " + contatto.getCognome() + "\n\n" +
                    "Rubrica aggiornata:" + "\n\n" +
                    rubricaFile.getInfoContatti(contatti);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            rubrica.elimina(position);
            adapter.notifyDataSetChanged();
        });
    }
}