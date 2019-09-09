package com.example.giovanni.giovanni.recyclerview.recyclerviewheaderitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.InitList;

public class RecyclerViewHeaderItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_header_item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        HeaderItemAdapter adapter = new HeaderItemAdapter(InitList.getData());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_headeritem);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // ?
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}