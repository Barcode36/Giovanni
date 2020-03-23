package com.example.giovanni.giovanni;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.listview.listviewsimple.ListViewSimpleActivity;
import com.example.giovanni.giovanni.listview.listviewadapterrubrica.ListViewRubricaActivity;
import com.example.giovanni.giovanni.listview.listviewazienda.AziendaActivity;
import com.example.giovanni.giovanni.listview.twolistviewcopy.TwoListViewCopyActivity;
import com.example.giovanni.giovanni.listview.twolistviewadapter.TwoListViewAdapterActivity;
import com.example.giovanni.giovanni.listview.twolistviewthread.TwoListViewThreadActivity;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        findViewById(R.id.istview_simple).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ListViewSimpleActivity.class)));

        findViewById(R.id.two_listview_copy).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), TwoListViewCopyActivity.class)));

        findViewById(R.id.two_listview_adapter).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), TwoListViewAdapterActivity.class)));

        findViewById(R.id.two_listview_thread).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), TwoListViewThreadActivity.class)));

        findViewById(R.id.listview_adapter_rubrica).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ListViewRubricaActivity.class)));

        findViewById(R.id.listview_azienda).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AziendaActivity.class)));
    }
}