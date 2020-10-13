package com.example.giovanni.giovanni.loginintent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Persona;

public class PodcastActivity extends AppCompatActivity {

    private EditText eInput;
    private Persona utente;
    private String input;
    private boolean control;
    private String message;
    private Intent intent;
    private TextView textResult;

    String output;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);

        TextView tOutput = findViewById(R.id.textOutput);
        Button bEnjoy = findViewById(R.id.buttonEnjoy);
        eInput = findViewById(R.id.editInput);
        textResult = findViewById(R.id.textResult);
        Button bLogout = findViewById(R.id.buttonLogout);

        intent = getIntent();
        output = "ciao " + intent.getStringExtra("KEY") + "!";

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        output = "ciao " + preferences.getString("USERNAME", "") + "!";

        tOutput.setText(output.toUpperCase());

        utente = new Persona();
        utente.initBand();

        bEnjoy.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), ResultActivity.class);

            if (eInput.getText().toString().equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Inserisci un gruppo", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                input = eInput.getText().toString();
                control = utente.cercaGruppo(input);
                if (control) {
                    message = "Goditi " + utente.returnGruppo(input);
                    intent.putExtra("KEY", "" + message);
                    intent.putExtra("BAND", input);
                    startActivityForResult(intent, 500);
                } else {
                    message = "Gruppo non trovato";
                    intent.putExtra("KEY", "" + message);
                    startActivity(intent);
                }
            }
        });

        bLogout.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), LoginIntentActivity.class);

            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 500) {
            if (resultCode == Activity.RESULT_OK) {
                String message = "Hai ascoltato i " + data.getStringExtra("RESULT") + ", ottima scelta!";
                textResult.setText(message);
            }
        }
    }
}