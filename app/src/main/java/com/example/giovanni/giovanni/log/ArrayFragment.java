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

import java.util.ArrayList;
import java.util.List;

import static com.example.giovanni.giovanni.utils.Utils.turnToString;

public class ArrayFragment extends Fragment {

    public static String TAG = "TAGARRAY";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        TextView textLog = view.findViewById(R.id.text_log);
        textLog.setText(R.string.array);

        int primo = 3;
        int ultimo = 7;
        int somma = sommatoria(primo, ultimo);

        Log.i(TAG, "" + somma); // Concateno una stringa alla variabile intera somma perché il metodo Log.i() stampa solo le stringhe.

        // Se non avessi voluto concatenare una stringa a somma avrei dovuto fare nel modo seguente:
        // String sommaString = String.valueOf(somma);
        // Log.i(TAG, sommaString);

        Log.i(TAG, "La somma tra " + primo + " e " + ultimo + " vale " + somma + ".");

        String[] articoli = new String[] {"camicia", "pantaloni", "scarpe", "calzini"};
        int k = 1;

        Log.i(TAG, "Articoli disponibili:");
        for(int i=0; i<articoli.length; i++) {
            Log.i(TAG, k + "° articolo: "+articoli[i]);
            k++;
        }
        k = 1;

        int scelta = 3;
        Log.i(TAG, "Hai acquistato il prodotto: " + articoli[scelta] + "\nArticoli disponibili:");
        for(int i=0; i<articoli.length; i++) {
            if (i == scelta) {
                articoli[i] = "acquistato";
            }
            Log.i(TAG, k + "° articolo: "+articoli[i]);
            k++;
        }

        String frase = "supercalifragilistichespiralidoso";

        rimpiazza(frase);
        eliminaEstremi(frase);
        occorrenzeA(frase);
        occorrenzeVocali(frase);
        occorrenzeOgniVocale(frase);

        List<String> listaArticoli = new ArrayList<>();
        for (String articolo : articoli) {
            listaArticoli.add(articolo);
        }
        String stringaArticoli = turnToString(listaArticoli);
        Log.i(TAG, "stringaArticoli: " + stringaArticoli);

        return view;
    }

    public int sommatoria(int a, int b) {
        return a + b;
    }

    public void rimpiazza(String stringa) {
        Log.i(TAG, stringa.replace("fragili", "")); // Il metodo replace() rimpiazza in una stringa il target con il replacement.
    }

    public void eliminaEstremi(String stringa) {
        Log.i(TAG, stringa.substring(1, stringa.length() - 1)); // Il metodo substring() restituisce una stringa a partire da un indice all'altro indicati.
    }

    public void occorrenzeA(String stringa) {
        int occorrenze = 0;
        String array[] = stringa.split(""); // Il metodo split() divide una stringa in base alla regex, in tal caso divide ogni carattere della
        // stringa dall'altro e lo inserisce in un array di stringhe che alla fine sarà popolato di tutti i caratteri della stringa.
        for (String character : array) {
            if (character.equals("a")) {
                occorrenze++;
            }
        }
        Log.i(TAG, "La lettera a si ripete " + occorrenze + " volte.");
    }

    public void occorrenzeVocali(String stringa) {
        int occorrenze = 0;
        String array[] = stringa.split("");
        for(int i=0; i<array.length; i++) {
            if (array[i].equals("a") ||
                    array[i].equals("e") ||
                    array[i].equals("i") ||
                    array[i].equals("o") ||
                    array[i].equals("u")) {
                occorrenze++;
            }
        }
        Log.i(TAG, "Le vocali si ripetono " + occorrenze + " volte.");
    }

    public void occorrenzeOgniVocale(String stringa) {
        int occ[] = new int[] {0, 0, 0, 0 , 0};
        String array[] = stringa.split("");

        for(int i=0; i<array.length; i++) {
            switch(array[i]) {
                case "a": occ[0] = occ[0] + 1;
                    break;
                case "e": occ[1] = occ[1] + 1;
                    break;
                case "i": occ[2] = occ[2] + 1;
                    break;
                case "o": occ[3] = occ[3] + 1;
                    break;
                case "u": occ[4] = occ[4] + 1;
                    break;
            }
        }
        Log.i(TAG, "La a si ripete " + occ[0] + " volte.\n" +
                "La e si ripete " + occ[1] + " volte.\n" +
                "La i si ripete " + occ[2] + " volte.\n" +
                "La o si ripete " + occ[3] + " volte.\n" +
                "La u si ripete " + occ[4] + " volte.\n");
    }
}