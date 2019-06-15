package com.example.giovanni.giovanni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.giovanni.giovanni.openfragment.OpenFragmentActivity;
import com.example.giovanni.giovanni.recyclercambiaprezzo.RecyclerArticoliActivity;
import com.example.giovanni.giovanni.recyclerviewadditem.RecyclerViewAddItemActivity;
import com.example.giovanni.giovanni.recyclerviewheaderitem.RecyclerViewHeaderItemActivity;
import com.example.giovanni.giovanni.recyclerviewcheckbox.CheckBoxSearchActivity;
import com.example.giovanni.giovanni.recyclerviewstickyheader.RecyclerViewStickyHeaderActivity;
import com.example.giovanni.giovanni.recyclerviewproject.LoginActivity;
import com.example.giovanni.giovanni.recyclerviewviewpager.RecyclerViewViewPagerActivity;

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

        findViewById(R.id.recyclerview_sticky_header).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), RecyclerViewStickyHeaderActivity.class)));

        findViewById(R.id.recyclerview_openfragment).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), OpenFragmentActivity.class)));

        findViewById(R.id.recyclerview_viewpager).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), RecyclerViewViewPagerActivity.class)));

        findViewById(R.id.recyclerview_search_checkbox).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), CheckBoxSearchActivity.class)));
    }
}