package com.example.giovanni.giovanni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.giovanni.giovanni.listview.ListViewChoiceActivity;
import com.example.giovanni.giovanni.listviewadapterrubrica.ListViewRubricaActivity;
import com.example.giovanni.giovanni.listviewazienda.AziendaActivity;
import com.example.giovanni.giovanni.twolistviewonebutton.TwoListViewOneButtonActivity;
import com.example.giovanni.giovanni.twolistviewonebuttonadapter.TwoListActivity;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        findViewById(R.id.double_listview).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ListViewChoiceActivity.class)));

        findViewById(R.id.twolistview_onebutton).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), TwoListViewOneButtonActivity.class)));

        findViewById(R.id.azienda_listview).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AziendaActivity.class)));

        findViewById(R.id.listview_adapter_rubrica).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ListViewRubricaActivity.class)));

        findViewById(R.id.twolistview_onebutton_adapter).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), TwoListActivity.class)));
    }
}