package com.example.giovanni.giovanni.viewpagertablayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import com.example.giovanni.giovanni.utils.BaseFragment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FragmentPull extends BaseFragment {

    private TextView dataAttuale;
    private TextView randomResult1;
    private TextView randomResult2;
    private TextView randomResult3;
    private List<Persona> lista1;
    private List<Persona> lista2;
    private Random randomGenerator = new Random();

    private static final String[] FAMILY = new String[] {
            "Giovanni",
            "Raffaele",
            "Teresa & Ilenia",
            "Angela",
            "Vincenzo",
            "Salvatore"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pull, container, false);

        dataAttuale = view.findViewById(R.id.text_data_attuale);
        randomResult1 = view.findViewById(R.id.text_random_result_1);
        randomResult2 = view.findViewById(R.id.text_random_result_2);
        randomResult3 = view.findViewById(R.id.text_random_result_3);

        lista1 = new ArrayList<>();
        lista1 = init();
        lista2 = new ArrayList<>();
        lista2 = init();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        multiSwipeRefreshLayout = view.findViewById(R.id.multi_swipe_refresh_layout);
        multiSwipeRefreshLayout.setSwipeableChildren(R.id.linear_layout_child);
        multiSwipeRefreshLayout.setOnRefreshListener(() -> {

            // PER TRASFORMARE LA DATA ATTUALE DAL TIPO DATE AL TIPO STRING
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.ITALY);
            String data = sdf.format(new Date());
            dataAttuale.setText(data);

            // NOTA: la a fa apparire AM o PM a seconda dei casi.

            String dataModificata = data.replaceFirst("01", "02");
            Log.i("TAG", "Data: " + data + "\nData modificata: " + dataModificata);

            stopSwipeRefresh();

            int random1 = randomGenerator.nextInt(6); // Genera un numero casuale che va da 0 a 5.
            int random2 = randomGenerator.nextInt(6); // Genera un numero casuale che va da 0 a 5.

            String risultato1 = sorteggio1(lista1, random1);
            String risultato2 = sorteggio2(lista2, random2);
            String risultato3 = sorteggio3();

            randomResult1.setText(risultato1);
            randomResult2.setText(risultato2);
            randomResult3.setText(risultato3);
        });
    }

    private List<Persona> init() {

        List<Persona> list = new ArrayList<>();

        list.add(new Persona("Giovanni",0));
        list.add(new Persona("Raffaele",1));
        list.add(new Persona("Teresa & Ilenia",2));
        list.add(new Persona("Angela",3));
        list.add(new Persona("Vincenzo",4));
        list.add(new Persona("Salvatore",5));

        return list;
    }

    public String sorteggio1(List<Persona> lista, int numero) {
        Persona persona = new Persona();
        for (Persona temp : lista) {
            if (temp.getId() == numero) {
                persona = temp;
                break;
            }
        }
        return persona.getNome();
    }

    public String sorteggio2(List<Persona> lista, int numero) {
        Persona persona = new Persona();
        for (Persona temp : lista) {
            if (temp.getId() == numero) {
                persona = temp;
                lista.remove(temp);
                break;
            }
        }
        return persona.getNome();
    }

    public String sorteggio3() {
        return FAMILY[randomGenerator.nextInt(FAMILY.length)];
    }
}