package com.example.giovanni.giovanni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.giovanni.giovanni.listview.ListViewSimpleActivity;
import com.example.giovanni.giovanni.listviewadapterrubrica.ListViewRubricaActivity;
import com.example.giovanni.giovanni.listviewazienda.AziendaActivity;
import com.example.giovanni.giovanni.twolistviewcopy.TwoListViewCopyActivity;
import com.example.giovanni.giovanni.twolistviewadapter.TwoListViewAdapterActivity;
import com.example.giovanni.giovanni.twolistviewthread.TwoListViewThreadActivity;

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