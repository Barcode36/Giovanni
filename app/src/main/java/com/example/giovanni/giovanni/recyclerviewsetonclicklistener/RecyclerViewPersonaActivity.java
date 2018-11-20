package com.example.giovanni.giovanni.recyclerviewsetonclicklistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPersonaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_recyclerview);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_persona);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // layoutManager = new GridLayoutManager(this, 2); Se voglio fare una griglia.
        recyclerView.setLayoutManager(layoutManager);

        List<Persona> lista = init();
        Persona persona = new Persona("Raffaele","Petito", 789);
        lista.add(persona);

        PersonaAdapter adapter = new PersonaAdapter(lista, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    public List<Persona> init() {

        List<Persona> list = new ArrayList<>();
        list.add(new Persona("Giovanni", "Petito", 123));
        list.add(new Persona("Gianluigi", "Santillo", 234));
        list.add(new Persona("Mariano", "Pinto", 345));
        list.add(new Persona("Francesco", "Mongiello", 456));
        list.add(new Persona("Daniele", "Musacchia", 567));
        list.add(new Persona("Pasquale", "Amato", 678));

        return list;
    }
}