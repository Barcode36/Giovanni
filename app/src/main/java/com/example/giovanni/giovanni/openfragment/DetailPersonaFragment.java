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

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.DetailPersona;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.ArrayList;
import java.util.List;

public class DetailPersonaFragment extends Fragment {

    private Persona persona;
    private LinearLayout bodyContainer;
    private List<DetailPersona> details;
    private TextView textNome;
    private TextView textCognome;

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

        textNome = view.findViewById(R.id.textNome);
        textCognome = view.findViewById(R.id.textCognome);
        textNome.setText(persona.getNome());
        textCognome.setText(persona.getCognome());

        details = init(persona);
        addViews(details);

        return view;
    }

    public List<DetailPersona> init(Persona persona) {

        List<DetailPersona> list = new ArrayList<>();
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
        for (DetailPersona detail : details) {
            if (detail == null)
                continue;
            View view = LayoutInflater.from(getContext()).inflate(
                    R.layout.detail_persona_item,
                    bodyContainer,
                    false);
            ((TextView) view.findViewById(R.id.detail_label)).setText(detail.getChiave());
            ((TextView) view.findViewById(R.id.detail_value)).setText(detail.getValore());
            bodyContainer.addView(view);
        }
        bodyContainer.setVisibility(View.VISIBLE);
    }
}