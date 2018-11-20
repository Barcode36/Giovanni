package com.example.giovanni.giovanni.recyclerviewstickyheader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.ListMaking;

public class RecyclerViewStickyHeaderActivity extends AppCompatActivity {

    StickyHeaderAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_sticky_header);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new StickyHeaderAdapter(ListMaking.getData());
        mLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerview_sticky_header);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // TODO: capire se serve o no.
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new HeaderItemDecoration(recyclerView, (StickyHeaderInterface) adapter));
    }
}