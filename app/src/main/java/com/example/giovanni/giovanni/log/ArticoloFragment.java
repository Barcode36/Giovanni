package com.example.giovanni.giovanni.log;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Articolo;
import com.example.giovanni.giovanni.model.Camicia;
import com.example.giovanni.giovanni.model.Libro;

import java.util.ArrayList;
import java.util.List;

public class ArticoloFragment extends Fragment {

    private static String TAG = "TAGARTICOLO";
    private List<Articolo> listaArticoli;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        TextView textLog = view.findViewById(R.id.text_log);
        textLog.setText(R.string.articolo);

        Articolo[] arrayArticoli = new Articolo[4];

        // Libro libro = new Libro();
        Libro libro1 = new Libro(111, "Matematica", 12.25, 3,22);
        Libro libro2 = new Libro(222, "Italiano", 10.50, 4,22);

        libro2.setDescrizione("Storia");

        // Camicia camicia = new Camicia();
        Camicia camicia1 = new Camicia(333, "Stoffa", 40, 2);
        Camicia camicia2 = new Camicia(444, "Lino", 50, 1);

        // arrayArticoli[0] = new Articolo();
        arrayArticoli[0] = libro1;
        arrayArticoli[1] = libro2;
        arrayArticoli[2] = camicia1;
        arrayArticoli[3] = camicia2;

        stampaInfo(arrayArticoli);

        listaArticoli = new ArrayList<>();
        // listaArticoli.add(new Libro());
        // listaArticoli.add(new Libro(222, "Pelle", 12.25, 2, 22));
        listaArticoli.add(libro1);
        listaArticoli.add(libro2);
        listaArticoli.add(camicia1);
        listaArticoli.add(camicia2);

        getArticoloByID(333);

        return view;
    }

    private void stampaInfo(Articolo[] arrayArticoli) {
        for (int i=0; i<arrayArticoli.length; i++) {
            Log.i(TAG, "Descrizione: "+ arrayArticoli[i].getDescrizione() + ", prezzo: " + arrayArticoli[i].getPrezzo() + ".");
        }
    }

    private void getArticoloByID(int id) {
        for (Articolo articolo : listaArticoli) {
            if (articolo.getId() == id)
                Log.i(TAG, "L'articolo con ID " + articolo.getId() + " è: "+articolo.getDescrizione() + ", " + articolo.getPrezzo() + ".");
        }
    }
}