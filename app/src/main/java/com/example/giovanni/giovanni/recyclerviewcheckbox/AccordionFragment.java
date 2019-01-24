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

    private static final String ACCORDION_TYPE = "accordion";
    private static final String CHECKBOX_TYPE = "checkbox";

    private View view;
    private List<Persona> list;
    private List<Persona> collapsedList;
    private RecyclerView recyclerView;
    private AccordionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_accordion, container, false);

        recyclerView = view.findViewById(R.id.accordion_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AccordionAdapter(this);
        recyclerView.setAdapter(adapter);
        list = init();

        collapsedList = new ArrayList<>();
        for (Persona persona : list) {
            if (!persona.getTipo().equals(CHECKBOX_TYPE)) {
                collapsedList.add(persona);
            }
        }
        adapter.setList(collapsedList);
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public boolean onItemClicked(Persona persona, boolean isChecked) {

        if (persona.getTipo().equals(ACCORDION_TYPE)) {
            if (isChecked) {
                adapter.setList(list);
                view.findViewById(R.id.arrow_down).setVisibility(View.VISIBLE);
                view.findViewById(R.id.arrow_up).setVisibility(View.GONE);
            } else {
                adapter.setList(collapsedList);
                view.findViewById(R.id.arrow_down).setVisibility(View.GONE);
                view.findViewById(R.id.arrow_up).setVisibility(View.VISIBLE);
            }
        } else if (persona.getTipo().equals(CHECKBOX_TYPE)) {
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
}