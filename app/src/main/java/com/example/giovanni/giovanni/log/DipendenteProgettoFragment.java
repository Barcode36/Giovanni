package com.example.giovanni.giovanni.log;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import com.example.giovanni.giovanni.model.Progetto;

public class DipendenteProgettoFragment extends Fragment {

    public static String TAG = "TAGDIPENDENTEPROGETTO";

    Persona dipendente;
    Progetto progetto1;
    Progetto progetto2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        TextView textLog = view.findViewById(R.id.text_log);
        textLog.setText(R.string.dipendente_progetto);

        dipendente = new Persona();
        dipendente.setNome("Giovanni");
        dipendente.setCognome("Petito");

        progetto1 = new Progetto("Corso Android", 3000);

        Log.i(TAG, dipendente.getNome());
        Log.i(TAG, dipendente.toString());

        progetto1.setDipendente(dipendente);
        Log.i(TAG, progetto1.toString());

        progetto2 = new Progetto("Corso Java", 2000, dipendente);
        Log.i(TAG, progetto2.toString());

        return view;
    }
}