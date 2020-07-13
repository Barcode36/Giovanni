package com.example.giovanni.giovanni;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.recyclerview.gridlayout.GridLayoutActivity;
import com.example.giovanni.giovanni.recyclerview.openfragment.OpenFragmentActivity;
import com.example.giovanni.giovanni.recyclerview.recyclercambiaprezzo.ArticoliActivity;
import com.example.giovanni.giovanni.recyclerview.recyclerviewadditem.AddItemActivity;
import com.example.giovanni.giovanni.recyclerview.recyclerviewheaderitem.HeaderItemActivity;
import com.example.giovanni.giovanni.recyclerview.accordion.AccordionActivity;
import com.example.giovanni.giovanni.recyclerview.recyclerviewproject.LoginActivity;
import com.example.giovanni.giovanni.recyclerview.recyclerviewviewpager.RecyclerViewViewPagerActivity;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        findViewById(R.id.recyclerview_cambiaprezzo).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ArticoliActivity.class)));

        findViewById(R.id.recyclerview_project).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)));

        findViewById(R.id.recyclerview_add_item).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AddItemActivity.class)));

        findViewById(R.id.recyclerview_header_item).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), HeaderItemActivity.class)));

        findViewById(R.id.recyclerview_openfragment).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), OpenFragmentActivity.class)));

        findViewById(R.id.recyclerview_viewpager).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), RecyclerViewViewPagerActivity.class)));

        findViewById(R.id.recyclerview_accordion).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AccordionActivity.class)));

        findViewById(R.id.recyclerview_gridlayout).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), GridLayoutActivity.class)));
    }
}