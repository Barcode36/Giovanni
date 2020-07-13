package com.example.giovanni.giovanni.recyclerview.gridlayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutActivity extends AppCompatActivity implements GridLayoutAdapter.OnItemViewClicked {

    private List<Persona> list = new ArrayList<>();
    private GridLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlayout);

        RecyclerView mRecyclerView = findViewById(R.id.gridlayout_recyclerview);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        list = Persona.initPersone();

        adapter = new GridLayoutAdapter(this, this);
        adapter.setList(list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Persona persona, boolean isChecked) {
        if (isChecked) {
            for (int i=0; i<list.size(); i++) {
                list.get(i).setChecked(list.get(i) == persona);
            }
        } else {
            for (int i=0; i<list.size(); i++) {
                list.get(i).setChecked(list.get(i).isChecked());
            }
        }
        adapter.notifyDataSetChanged();
    }
}