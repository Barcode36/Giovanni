package com.example.giovanni.giovanni.log;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Persona;
import com.example.giovanni.giovanni.bean.Progetto;

public class DipendenteProgettoFragment extends Fragment {

    private static String TAG = "TAGDIPENDENTEPROGETTO";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        TextView textLog = view.findViewById(R.id.text_log);
        textLog.setText(R.string.dipendente_progetto);

        Persona dipendente = new Persona();
        dipendente.setNome("Giovanni");
        dipendente.setCognome("Petito");

        Progetto progetto1 = new Progetto("Corso Android", 3000);

        Log.i(TAG, dipendente.getNome());
        Log.i(TAG, dipendente.toString());

        progetto1.setDipendente(dipendente);
        Log.i(TAG, progetto1.toString());

        Progetto progetto2 = new Progetto("Corso Java", 2000, dipendente);
        Log.i(TAG, progetto2.toString());

        return view;
    }
}