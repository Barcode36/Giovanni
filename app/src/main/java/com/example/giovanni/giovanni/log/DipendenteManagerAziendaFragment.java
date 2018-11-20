package com.example.giovanni.giovanni.log;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Azienda;
import com.example.giovanni.giovanni.pojo.Dipendente;
import com.example.giovanni.giovanni.pojo.Manager;

public class DipendenteManagerAziendaFragment extends Fragment {

    public static String TAG = "TAGDIPENDENTEMANAGERAZIENDA";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        TextView textLog = view.findViewById(R.id.text_log);
        textLog.setText(R.string.dipendente_manager_azienda);

        Dipendente dipendente1 = new Dipendente("001", 1000.00, 7.50);
        Dipendente dipendente2 = new Dipendente("002", 1050.00, 5.50);
        Dipendente dipendente3 = new Dipendente("003", 1150.00, 4.50);
        Dipendente dipendente4 = new Dipendente("004", 1100.00, 6.50);

        Log.i(TAG, "" + dipendente1.getStipendio());
        Log.i(TAG, "" + dipendente1.paga(10));

        Manager manager1 = new Manager("005", 1500.0, 8.50);
        manager1.prendiMalattia(5);
        Log.i(TAG, "Primo Manager: " + manager1.stampaMalattia());
        Log.i(TAG, "Primo Manager: " + manager1.paga(3));

        Manager manager2 = new Manager("006", 1500.0, 8.50, 0);
        Log.i(TAG, "Secondo Manager: " + manager2.stampaMalattia());
        Log.i(TAG, "Secondo Manager: " + manager2.paga(3));

        Azienda azienda = new Azienda("DS Group");

        azienda.inserisci(dipendente1);
        azienda.inserisci(dipendente2);
        azienda.inserisci(dipendente3);
        azienda.inserisci(dipendente4);
        azienda.elimina(dipendente3);

        Dipendente temp = azienda.cercaPerMatricola("001");

        if (temp != null) {
            Log.i(TAG, "" + temp.stampa());
        }

        temp = azienda.cercaPerMatricola("005");

        if (temp != null) {
            Log.i(TAG, "" + temp.stampa());
        }

        temp = azienda.cercaPerStipendioMassimo();
        Log.i(TAG, "Il dipendente con lo stipendio massimo è: " + temp.stampa());

        return view;
    }
}
