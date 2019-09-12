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

public class AscensoreFragment extends Fragment {

    private static String TAG = "TAGASCENSORE";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        TextView textLog = view.findViewById(R.id.text_log);
        textLog.setText(R.string.ascensore);

        int partenza = 0;
        int destinazione = 10;

        Ascensore ascensore = new Ascensore();
        ascensore.chiamaAscensore(partenza, destinazione);

        return view;
    }

    public class Ascensore {

        private int partenza;
        private int destinazione;

        public Ascensore() {
            this.partenza = 0;
            this.destinazione = 0;
        }

        public Ascensore(int partenza, int destinazione) {
            this.partenza = partenza;
            this.destinazione = destinazione;
        }

        public void chiamaAscensore(int p, int d) {
            String message;
            int pianiRimanenti = d;
            while (p != d) {
                if (p < d) {
                    message = "Piano " + p + ": devo salire altri " + pianiRimanenti + " piani.";
                    Log.i("" + TAG, "" + message);
                    p++;
                    pianiRimanenti--;
                } else {
                    pianiRimanenti = p;
                    message = "Piano " + p + ": devo scendere altri " + pianiRimanenti + " piani.";
                    Log.i("" + TAG, "" + message);
                    p--;
                }
            }
            Log.i("" + TAG, "Stazionamento al " + d + "° piano.");
        }

        public int getPartenza() {
            return partenza;
        }

        public void setPartenza(int partenza) {
            this.partenza = partenza;
        }

        public int getDestinazione() {
            return destinazione;
        }

        public void setDestinazione(int destinazione) {
            this.destinazione = destinazione;
        }
    }
}