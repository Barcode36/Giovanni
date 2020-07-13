package com.example.giovanni.giovanni.recyclerview.recyclerviewheaderitem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.InitList;

public class HeaderItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        HeaderItemAdapter adapter = new HeaderItemAdapter(InitList.getData());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_headeritem);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // ?
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}