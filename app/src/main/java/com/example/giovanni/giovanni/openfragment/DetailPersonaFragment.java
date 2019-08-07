package com.example.giovanni.giovanni.openfragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.DetailPersona;
import com.example.giovanni.giovanni.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class DetailPersonaFragment extends Fragment {

    private Persona persona;
    private LinearLayout bodyContainer;
    private List<DetailPersona> details;

    public static DetailPersonaFragment newInstance(Persona persona) {
        DetailPersonaFragment fragment = new DetailPersonaFragment();
        fragment.setPersona(persona);
        return fragment;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_persona, container, false);

        bodyContainer = view.findViewById(R.id.bodyContainer);

        details = init(persona);
        addViews(details);

        return view;
    }

    public List<DetailPersona> init(Persona persona) {

        List<DetailPersona> list = new ArrayList<>();

        list.add(new DetailPersona(persona.getNome(), persona.getCognome()));

        list.add(new DetailPersona("Sesso", persona.getSesso()));
        list.add(new DetailPersona("Data di nascita", persona.getDataNascita()));
        list.add(new DetailPersona("Luogo di nascita", persona.getLuogoNascita()));
        list.add(new DetailPersona("Provincia", persona.getProvincia()));
        list.add(new DetailPersona("Msisdn", persona.getMsisdn()));
        list.add(new DetailPersona("Età", "valore"));
        list.add(new DetailPersona("Username", "valore"));
        list.add(new DetailPersona("Email", "valore"));
        list.add(new DetailPersona("Codice Fiscale", "valore"));

        return list;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addViews(List<DetailPersona> details) {
        if (details == null)
            return;
        if (details.size() == 0)
            return;

        bodyContainer.removeAllViews();
        for (DetailPersona detail : details) {
            if (detail == null)
                continue;

            if (detail.getChiave().equalsIgnoreCase(persona.getNome())) {

                View view = LayoutInflater.from(getContext()).inflate(R.layout.detail_persona_header, bodyContainer, false);

                TextView nome = view.findViewById(R.id.text_nome);
                TextView cognome = view.findViewById(R.id.text_cognome);

                nome.setText(detail.getChiave());
                cognome.setText(detail.getValore());

                bodyContainer.addView(view);
            } else {

                View view = LayoutInflater.from(getContext()).inflate(R.layout.detail_persona_item, bodyContainer, false);

                TextView label = view.findViewById(R.id.detail_label);
                TextView value = view.findViewById(R.id.detail_value);

                label.setText(detail.getChiave());
                value.setText(detail.getValore());

                value.setOnClickListener(v ->
                        Toast.makeText(getContext(), detail.getValore(), Toast.LENGTH_SHORT).show()
                );

                bodyContainer.addView(view);
            }
        }
        bodyContainer.setVisibility(View.VISIBLE);
    }
}