package com.example.giovanni.giovanni.recyclerviewcheckbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.ArrayList;
import java.util.List;

public class AccordionFragment extends Fragment implements AccordionAdapter.OnItemViewClicked {

    private static final String SWITCH_TYPE = "switch";
    private static final String ACCORDION_TYPE = "accordion";
    private static final String CHECKBOX_TYPE = "checkbox";

    private View view;
    private AccordionAdapter adapter;
    private List<Persona> list;
    private List<Persona> collapsedList;
    private List<Persona> cognomi;
    private boolean isCollapsed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_accordion, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.accordion_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AccordionAdapter(this);
        recyclerView.setAdapter(adapter);
        list = init();
        cognomi = initCognomi();
        isCollapsed = true;

        updateSwitch();

        return view;
    }

    public void updateSwitch() {

        collapsedList = new ArrayList<>();

        for (Persona persona : list) {
            if (!persona.getTipo().equals(CHECKBOX_TYPE)) {
                collapsedList.add(persona);
            }
        }

        for (Persona persona : list) {
            persona.setChecked(false);
        }

        for (Persona persona : collapsedList) {
            persona.setChecked(false);
        }

        for (Persona cognome : cognomi) {
            for (Persona persona : list) {
                if (persona.getCognome().equalsIgnoreCase(cognome.getCognome())) {
                    persona.setChecked(true);
                }
            }
            for (Persona persona : collapsedList) {
                if (persona.getCognome().equalsIgnoreCase(cognome.getCognome())) {
                    persona.setChecked(true);
                }
            }
        }
        adapter.setList(collapsedList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemClicked(Persona persona, boolean isChecked) {

        if (persona.getTipo().equals(SWITCH_TYPE)) {
            persona.setChecked(isChecked);
            if (isCollapsed) {
                adapter.setList(collapsedList);
            } else {
                adapter.setList(list);
            }
        }
        if (persona.getTipo().equals(ACCORDION_TYPE)) {
            if (isChecked) {
                adapter.setList(list);
                view.findViewById(R.id.arrow_down).setVisibility(View.VISIBLE);
                view.findViewById(R.id.arrow_up).setVisibility(View.GONE);
                isCollapsed = false;
            } else {
                adapter.setList(collapsedList);
                view.findViewById(R.id.arrow_down).setVisibility(View.GONE);
                view.findViewById(R.id.arrow_up).setVisibility(View.VISIBLE);
                isCollapsed = true;
            }
        }
        if (persona.getTipo().equals(CHECKBOX_TYPE)) {
            if (isChecked) {
                for (int i=0; i<list.size(); i++) {
                    list.get(i).setChecked(list.get(i) == persona);
                }
            } else {
                for (int i=0; i<list.size(); i++) {
                    list.get(i).setChecked(false);
                }
            }
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    public List<Persona> init() {

        List<Persona> list = new ArrayList<>();
        list.add(new Persona("Giovanni", "Petito", "3331582355", "switch", false));
        list.add(new Persona("Raffaele", "Petito", "3802689011", "switch", false));
        list.add(new Persona("Teresa", "Petito", "3343540536", "switch", false));
        list.add(new Persona("Angelina", "Basile", "3334392578", "switch", false));
        list.add(new Persona("Vincenzo", "Petito", "3666872262", "switch", false));
        list.add(new Persona("Salvatore", "Pragliola", "3384672609", "accordion", false));
        list.add(new Persona("Giovanni", "Basile", "3884723340", "checkbox", false));
        list.add(new Persona("Marco", "Basile", "3892148853", "checkbox", false));
        list.add(new Persona("Antonio", "D'Ascia", "3315605694", "checkbox", false));
        list.add(new Persona("Giovanni", "D'Ascia", "3331711437", "checkbox", false));
        list.add(new Persona("Mariano", "Pinto", "3397016728", "switch", false));
        list.add(new Persona("Pasquale", "Amato", "3665917760", "switch", false));
        list.add(new Persona("Francesco", "Mongiello", "3299376402", "switch", false));
        list.add(new Persona("Gianluigi", "Santillo", "3386124867", "switch", false));
        list.add(new Persona("Daniele", "Musacchia", "3494977374", "switch", false));

        return list;
    }

    public List<Persona> initCognomi() {

        List<Persona> cognomi = new ArrayList<>();
        cognomi.add(new Persona("Petito"));
        cognomi.add(new Persona("Pinto"));
        cognomi.add(new Persona("Mongiello"));
        cognomi.add(new Persona("Musacchia"));

        return cognomi;
    }
}