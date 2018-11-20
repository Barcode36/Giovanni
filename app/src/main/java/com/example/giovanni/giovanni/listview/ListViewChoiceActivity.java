package com.example.giovanni.giovanni.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.giovanni.giovanni.R;

public class ListViewChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_listview);

        findViewById(R.id.simple_list_item_1).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SimpleListItemActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.list_item).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ListItemActivity.class);
            startActivity(intent);
        });
    }
}