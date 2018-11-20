package com.example.giovanni.giovanni.recyclerviewcheckbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.ArrayList;
import java.util.List;

public class MaxCheckBoxSearchFragment extends Fragment implements MaxCheckBoxSearchAdapter.OnItemViewClicked {

    public static int THRESHOLD = 3;
    private List<Persona> list;
    private MaxCheckBoxSearchAdapter adapter;

    Button button;
    private List<Persona> checkedList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_checkbox_max, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.search_checkbox_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MaxCheckBoxSearchAdapter(this);
        recyclerView.setAdapter(adapter);
        list = init();
        adapter.setList(list);
        adapter.notifyDataSetChanged();

        button = view.findViewById(R.id.button_enabled);
        checkedList = new ArrayList<>();

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

    public List<Persona> init() {

        List<Persona> list = new ArrayList<>();
        list.add(new Persona("Giovanni", "Petito", "3331582355", false));
        list.add(new Persona("Raffaele", "Petito", "3802689011", false));
        list.add(new Persona("Teresa", "Petito", "3343540536", false));
        list.add(new Persona("Angelina", "Basile", "3334392578", false));
        list.add(new Persona("Vincenzo", "Petito", "3666872262", false));

        return list;
    }
}