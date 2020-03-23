package com.example.giovanni.giovanni.recyclerview.recyclercambiaprezzo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CambiaPrezzoActivity extends AppCompatActivity {

    private EditText ePrezzo;
    private Intent intent;
    private String prezzoString;
    private int input;
    private Double prezzo;
    private final static String PREZZO_REGEX = "\\d+|(\\d+.\\d+)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambia_prezzo);

        intent = getIntent();
        input = intent.getIntExtra("INSERT",-1);

        Button bConferma = findViewById(R.id.buttonConferma);
        ePrezzo = findViewById(R.id.editPrezzo);

        bConferma.setOnClickListener(v -> {

            if (ePrezzo.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Inserisci il prezzo", Toast.LENGTH_SHORT).show();
            } else {
                prezzoString = ePrezzo.getText().toString();
                Pattern pPrezzo = Pattern.compile(PREZZO_REGEX);
                Matcher mPrezzo = pPrezzo.matcher(prezzoString);
                if (!mPrezzo.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore prezzo non valido", Toast.LENGTH_SHORT).show();
                    ePrezzo.setText("");
                } else prezzo = Double.parseDouble(prezzoString);

                if (mPrezzo.matches()) {
                    intent.putExtra("POSIZIONE", input);
                    intent.putExtra("PREZZO", prezzo);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}