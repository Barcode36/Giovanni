package com.example.giovanni.giovanni.recyclerviewcheckbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.ArrayList;
import java.util.List;

public class MaxCheckBoxSearchFragment extends Fragment implements MaxCheckBoxSearchAdapter.OnItemViewClicked {

    public static int THRESHOLD = 3;
    private List<Persona> list;
    private RecyclerView recyclerView;
    private MaxCheckBoxSearchAdapter adapter;

    Button button;
    private List<Persona> checkedList;

    private EditText editSearch;
    private String sentence;
    private List<Persona> filtered;
    private TextView noResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_checkbox_max, container, false);

        recyclerView = view.findViewById(R.id.search_checkbox_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MaxCheckBoxSearchAdapter(this);
        recyclerView.setAdapter(adapter);
        list = init();
        adapter.setList(list);
        adapter.notifyDataSetChanged();

        button = view.findViewById(R.id.button_enabled);
        checkedList = new ArrayList<>();

        editSearch = view.findViewById(R.id.edit_search);
        ImageView iconSearch = view.findViewById(R.id.icon_search);
        noResult = view.findViewById(R.id.no_result);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editSearch.getText().length() >= 3) {
                    filter();
                } else if (editSearch.getText().length() == 0) {
                    resetList();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iconSearch.setOnClickListener(v -> {
            editSearch.setText("");
            resetList();
        });

        button.setOnClickListener(v -> resetList());

        return view;
    }

    @Override
    public boolean onItemClicked(Persona persona, boolean isChecked) {
        if (isChecked && checkedList.size() < THRESHOLD) {
            checkedList.add(persona);
            button.setEnabled(checkedList.size() > 0 && checkedList.size() <= THRESHOLD);
            return true;
        } else if (!isChecked) {
            checkedList.remove(persona);
            button.setEnabled(checkedList.size() > 0 && checkedList.size() <= THRESHOLD);
            return true;
        } else if (checkedList.size() == THRESHOLD) {
            Toast.makeText(getContext(), "Seleziona al massimo 3 elementi.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void filter() {
        editSearch.clearFocus();
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
                    || (persona.getNome() != null && persona.getNome().toLowerCase().contains(sentence.toLowerCase()))) {
                filtered.add(persona);
            }
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

    private void resetList() {
        checkedList = new ArrayList<>();
        list = init();
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        button.setEnabled(false);
        noResult.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public List<Persona> init() {

        List<Persona> list = new ArrayList<>();
        list.add(new Persona("Giovanni", "Petito", "3331582355", false));
        list.add(new Persona("Raffaele", "Petito", "3802689011", false));
        list.add(new Persona("Teresa", "Petito", "3343540536", false));
        list.add(new Persona("Salvatore", "Pragliola", "3384672609", false));
        list.add(new Persona("Angelina", "Basile", "3334392578", false));
        list.add(new Persona("Vincenzo", "Petito", "3666872262", false));
        list.add(new Persona("Giovanni", "Basile", "3884723340", false));
        list.add(new Persona("Marco", "Basile", "3892148853", false));
        list.add(new Persona("Antonio", "D'Ascia", "3315605694", false));
        list.add(new Persona("Giovanni", "D'Ascia", "3331711437", false));
        list.add(new Persona("Mariano", "Pinto", "3397016728", false));
        list.add(new Persona("Pasquale", "Amato", "3665917760", false));
        list.add(new Persona("Francesco", "Mongiello", "3299376402", false));
        list.add(new Persona("Gianluigi", "Santillo", "3386124867", false));
        list.add(new Persona("Daniele", "Musacchia", "3494977374", false));

        return list;
    }
}