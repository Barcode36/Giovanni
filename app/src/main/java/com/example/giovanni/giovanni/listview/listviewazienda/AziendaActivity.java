package com.example.giovanni.giovanni.listview.listviewazienda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Azienda;

public class AziendaActivity extends AppCompatActivity {

    private Azienda azienda;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azienda);

        azienda = new Azienda();
        azienda.init();

        Button bAzienda = findViewById(R.id.buttonAzienda);
        Button bInserisci = findViewById(R.id.buttonInserisci);
        Button bCerca = findViewById(R.id.buttonCerca);

        bAzienda.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), ListViewAziendaActivity.class);
            intent.putExtra("AZIENDA", azienda);
            startActivity(intent);
        });

        bInserisci.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), InsertActivity.class);
            intent.putExtra("INSERT", azienda);
            startActivityForResult(intent, 500);
        });

        bCerca.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), FindActivity.class);
            intent.putExtra("FIND", azienda);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 500) {
            if (resultCode == Activity.RESULT_OK) {
                azienda = (Azienda) data.getSerializableExtra("result");
            }
            // Posso anche cancellare il seguente blocco if.
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Back", Toast.LENGTH_SHORT).show();
                // azienda = (Azienda) data.getSerializableExtra("result");
            }
        }
    }
}