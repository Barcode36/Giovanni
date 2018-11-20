package com.example.giovanni.giovanni.openfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.ArrayList;
import java.util.List;

public class OpenFragmentActivity extends AppCompatActivity implements OpenFragmentAdapter.OnItemViewClicked {

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openfragment);

        RecyclerView recyclerView = findViewById(R.id.openfragment_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        OpenFragmentAdapter adapter = new OpenFragmentAdapter(this);
        recyclerView.setAdapter(adapter);
        List<Persona> list = init();
        adapter.setList(list);
        adapter.notifyDataSetChanged();

        fm = getFragmentManager();
    }

    @Override
    public void onItemClicked(Persona persona) {
        openFragment(persona);
    }

    public void openFragment(Persona persona) {
        DetailPersonaFragment fragment = DetailPersonaFragment.newInstance(persona);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentsGroup, fragment);
        ft.commit();
    }

    public List<Persona> init() {

        List<Persona> list = new ArrayList<>();
        Persona persona1 = new Persona("Giovanni", "Petito", "M", "06/02/1988", "Mugnano di Napoli", "Napoli", "3331582355");
        list.add(persona1);
        list.add(new Persona("Raffaele", "Petito", "M", "06/02/1988", "Mugnano di Napoli", "Napoli", "3802689011"));
        list.add(new Persona("Teresa", "Petito", "F", "18/06/1984", "Mugnano di Napoli", "Napoli", "3343540536"));
        list.add(new Persona("Angelina", "Basile", "F", "05/04/1959", "Villaricca", "Napoli", "3334392578"));
        list.add(new Persona("Vincenzo", "Petito", "M", "20/07/1958", "Giugliano in Campania", "Napoli", "3666872262"));

        return list;
    }
}