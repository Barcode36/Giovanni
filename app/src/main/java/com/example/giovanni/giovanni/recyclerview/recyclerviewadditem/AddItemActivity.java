package com.example.giovanni.giovanni.recyclerview.recyclerviewadditem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_persona);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Persona> lista = init();
        Persona persona = new Persona("Raffaele","Petito", 789);
        lista.add(persona);

        PersonaAdapter adapter = new PersonaAdapter(lista);
        recyclerView.setAdapter(adapter);
    }

    public List<Persona> init() {

        Persona p1 = new Persona("Giovanni", "Petito", 123);
        Persona p2 = new Persona("Gianluigi", "Santillo", 234);
        Persona p3 = new Persona("Mariano", "Pinto", 345);
        Persona p4 = new Persona("Francesco", "Mongiello", 456);
        Persona p5 = new Persona("Daniele", "Musacchia", 567);
        Persona p6 = new Persona("Pasquale", "Amato", 678);

        List<Persona> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);

        return list;
    }
}