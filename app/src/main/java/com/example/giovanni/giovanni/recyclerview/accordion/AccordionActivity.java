package com.example.giovanni.giovanni.recyclerview.accordion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import java.util.ArrayList;
import java.util.List;

public class AccordionActivity extends AppCompatActivity implements AccordionAdapter.OnItemViewClicked {

    private static final String SWITCH_TYPE = "switch";
    private static final String ACCORDION_TYPE = "accordion";
    private static final String CHECKBOX_TYPE = "checkbox";

    private RecyclerView recyclerView;
    private AccordionAdapter adapter;
    private List<Persona> list;
    private List<Persona> collapsedList;
    private List<Persona> cognomi;
    private boolean isCollapsed;

    private EditText editSearch;
    private String sentence;
    private List<Persona> filtered;
    private TextView noResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accordion);

        recyclerView = findViewById(R.id.accordion_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new AccordionAdapter(this);
        recyclerView.setAdapter(adapter);
        list = init();
        cognomi = initCognomi();
        isCollapsed = true;

        updateSwitch();

        editSearch = findViewById(R.id.edit_search);
        ImageView iconSearch = findViewById(R.id.icon_search);
        noResult = findViewById(R.id.no_result);

        editSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filter();
                return true;
            }
            return false;
        });

        iconSearch.setOnClickListener(v -> filter());
    }

    public void updateSwitch() {

        collapsedList = new ArrayList<>();

        for (Persona persona : list) {
            if (!persona.getTipo().equals(CHECKBOX_TYPE))
                collapsedList.add(persona);
        }

        for (Persona persona : list) {
            persona.setChecked(false);
        }

        for (Persona persona : collapsedList) {
            persona.setChecked(false);
        }

        for (Persona cognome : cognomi) {
            for (Persona persona : list) {
                if (persona.getCognome().equalsIgnoreCase(cognome.getCognome()))
                    persona.setChecked(true);
            }
            for (Persona persona : collapsedList) {
                if (persona.getCognome().equalsIgnoreCase(cognome.getCognome()))
                    persona.setChecked(true);
            }
        }
        adapter.setList(collapsedList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemClicked(Persona persona, boolean isChecked) {

        if (persona.getTipo().equals(SWITCH_TYPE)) {
            persona.setChecked(isChecked);
            if (isCollapsed)
                adapter.setList(collapsedList);
            else
                adapter.setList(list);
        }
        if (persona.getTipo().equals(ACCORDION_TYPE)) {
            if (isChecked) {
                adapter.setList(list);
                findViewById(R.id.arrow_down).setVisibility(View.GONE);
                findViewById(R.id.arrow_up).setVisibility(View.VISIBLE);
                isCollapsed = false;
            } else {
                adapter.setList(collapsedList);
                findViewById(R.id.arrow_down).setVisibility(View.VISIBLE);
                findViewById(R.id.arrow_up).setVisibility(View.GONE);
                isCollapsed = true;
            }
        }
        if (persona.getTipo().equals(CHECKBOX_TYPE)) {
            if (isChecked) {
                for (int i=0; i<list.size(); i++) {
                    list.get(i).setChecked(list.get(i).getTipo().equals(CHECKBOX_TYPE) ? list.get(i) == persona : list.get(i).isChecked());
                }
            } else {
                for (int i=0; i<list.size(); i++) {
                    list.get(i).setChecked(!list.get(i).getTipo().equals(CHECKBOX_TYPE) && list.get(i).isChecked());
                }
            }
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    private void filter() {
        // editSearch.clearFocus();
        if (sentence != null && sentence.equalsIgnoreCase(editSearch.getText().toString()))
            return;
        sentence = editSearch.getText().toString();
        if (sentence.length() == 0) {
            sentence = null;
            clearFilteredList();
            noResult.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            return;
        }
        clearFilteredList();
        if (list == null || list.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            noResult.setVisibility(View.VISIBLE);
            noResult.setText(R.string.no_list);
            return;
        }
        for (Persona persona : list) {
            if (persona == null)
                continue;
            if ((persona.getMsisdn() != null && persona.getMsisdn().toLowerCase().contains(sentence.toLowerCase()))
                    || (persona.getNome() != null && persona.getNome().toLowerCase().contains(sentence.toLowerCase())))
                filtered.add(persona);
        }
        if (filtered == null || filtered.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setList(filtered);
            adapter.notifyDataSetChanged();
        }
    }

    private void clearFilteredList() {
        if (filtered == null)
            filtered = new ArrayList<>();
        else
            filtered.clear();
    }

    public List<Persona> init() {

        List<Persona> list = new ArrayList<>();
        list.add(new Persona("Giovanni", "Petito", "3331582355", "switch", false));
        list.add(new Persona("Raffaele", "Petito", "3802689011", "switch", false));
        list.add(new Persona("Angelina", "Basile", "3334392578", "switch", false));
        list.add(new Persona("Vincenzo", "Petito", "3666872262", "switch", false));
        list.add(new Persona("Teresa", "Petito", "3343540536", "switch", false));
        list.add(new Persona("Ilenia", "Pragliola", "3343540536", "switch", false));
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