package com.example.giovanni.giovanni.recyclerview.recyclerviewviewpager;

import android.animation.LayoutTransition;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.LinearLayout;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewViewPagerActivity extends AppCompatActivity implements OnItemViewClicked {

    private List<Persona> lista;
    private RecyclerView recyclerView;
    private PersonaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_viewpager);

        LinearLayout linearContainer = findViewById(R.id.linear_container);
        recyclerView = findViewById(R.id.recyclerview_persona);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        // layoutManager = new GridLayoutManager(this, 2); Se voglio fare una griglia.
        recyclerView.setLayoutManager(layoutManager);

        // Le due righe di codice seguenti servono a conferire alla RecyclerView il comportamento del ViewPager.
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        lista = init();
        Persona persona = new Persona("Raffaele","Petito", 789);
        lista.add(persona);

        adapter = new PersonaAdapter(this, lista);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new LinePagerIndicatorDecoration());

        FastOutSlowInInterpolator interpolator = new FastOutSlowInInterpolator();
        LayoutTransition transition = new LayoutTransition();
        transition.setInterpolator(LayoutTransition.CHANGE_APPEARING, interpolator);
        transition.setInterpolator(LayoutTransition.CHANGE_DISAPPEARING, interpolator);
        transition.setInterpolator(LayoutTransition.CHANGING, interpolator);
        transition.setInterpolator(LayoutTransition.APPEARING, interpolator);
        transition.setInterpolator(LayoutTransition.DISAPPEARING, interpolator);
        transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
        transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
        transition.enableTransitionType(LayoutTransition.CHANGING);
        transition.enableTransitionType(LayoutTransition.APPEARING);
        transition.enableTransitionType(LayoutTransition.DISAPPEARING);

        linearContainer.setLayoutTransition(transition);
    }

    @Override
    public void onItemClicked(Persona persona, int position) {

        for(int i=0; i<lista.size(); i++) {
            if (persona.equals(lista.get(i))) {
                position = i;
                break;
            }
        }

        lista.remove(persona);
        adapter.notifyItemRemoved(position);

        if (lista.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
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