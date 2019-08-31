package com.example.giovanni.giovanni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.giovanni.giovanni.recyclerview.openfragment.OpenFragmentActivity;
import com.example.giovanni.giovanni.recyclerview.recyclercambiaprezzo.RecyclerArticoliActivity;
import com.example.giovanni.giovanni.recyclerview.recyclerviewadditem.RecyclerViewAddItemActivity;
import com.example.giovanni.giovanni.recyclerview.recyclerviewheaderitem.RecyclerViewHeaderItemActivity;
import com.example.giovanni.giovanni.recyclerview.accordion.AccordionActivity;
import com.example.giovanni.giovanni.recyclerview.recyclerviewproject.LoginActivity;
import com.example.giovanni.giovanni.recyclerview.recyclerviewviewpager.RecyclerViewViewPagerActivity;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        findViewById(R.id.recyclerview_cambiaprezzo).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), RecyclerArticoliActivity.class)));

        findViewById(R.id.recyclerview_project).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)));

        findViewById(R.id.recyclerview_add_item).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), RecyclerViewAddItemActivity.class)));

        findViewById(R.id.recyclerview_header_item).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), RecyclerViewHeaderItemActivity.class)));

        findViewById(R.id.recyclerview_openfragment).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), OpenFragmentActivity.class)));

        findViewById(R.id.recyclerview_viewpager).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), RecyclerViewViewPagerActivity.class)));

        findViewById(R.id.recyclerview_accordion).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AccordionActivity.class)));
    }
}