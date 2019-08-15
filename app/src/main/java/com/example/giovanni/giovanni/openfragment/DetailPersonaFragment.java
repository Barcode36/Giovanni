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
import com.example.giovanni.giovanni.model.PersonaDetail;
import com.example.giovanni.giovanni.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class DetailPersonaFragment extends Fragment {

    private Persona persona;
    private LinearLayout bodyContainer;
    private List<PersonaDetail> details;

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

    public List<PersonaDetail> init(Persona persona) {

        List<PersonaDetail> list = new ArrayList<>();

        list.add(new PersonaDetail(persona.getNome(), persona.getCognome()));

        list.add(new PersonaDetail("Sesso", persona.getSesso()));
        list.add(new PersonaDetail("Data di nascita", persona.getDataNascita()));
        list.add(new PersonaDetail("Luogo di nascita", persona.getLuogoNascita()));
        list.add(new PersonaDetail("Provincia", persona.getProvincia()));
        list.add(new PersonaDetail("Msisdn", persona.getMsisdn()));
        list.add(new PersonaDetail("Età", "valore"));
        list.add(new PersonaDetail("Username", "valore"));
        list.add(new PersonaDetail("Email", "valore"));
        list.add(new PersonaDetail("Codice Fiscale", "valore"));

        return list;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addViews(List<PersonaDetail> details) {
        if (details == null)
            return;
        if (details.size() == 0)
            return;

        bodyContainer.removeAllViews();
        for (PersonaDetail detail : details) {
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