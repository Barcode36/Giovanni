package com.example.giovanni.giovanni.recyclerview.recyclerviewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Azienda;

public class ProjectActivity extends AppCompatActivity {

    private Spinner statiSpinner;
    private String statoSpinner;
    private CheckBox checkPersonali;
    private CheckBox checkCompleti;
    private boolean checkedPersonali;
    private boolean checkedCompleti;
    private Azienda azienda;
    private String username;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        intent = getIntent();
        azienda = (Azienda) intent.getSerializableExtra("AZIENDA");
        username = (String) intent.getSerializableExtra("UTENTE");

        statiSpinner = findViewById(R.id.spinner);
        checkPersonali = findViewById(R.id.checkBoxPersonali);
        checkCompleti = findViewById(R.id.checkBoxCompleti);
        Button bGo = findViewById(R.id.buttonGo);
        TextView tUsername = findViewById(R.id.textUsername);
        tUsername.setText(username);

        bGo.setOnClickListener(view -> {

            intent = new Intent(getApplicationContext(), ResultProjectActivity.class);

            statoSpinner = statiSpinner.getSelectedItem().toString();
            intent.putExtra("SPINNER", statoSpinner);

            checkedPersonali = checkPersonali.isChecked();
            checkedCompleti = checkCompleti.isChecked();
            setResultTaskActivity(checkedPersonali, checkedCompleti);

            intent.putExtra("AZIENDA", azienda);
            intent.putExtra("UTENTE", username);
            startActivity(intent);
        });
    }

    public void setResultTaskActivity(boolean bool1, boolean bool2) {
        if (bool1 && !bool2) {
            intent.putExtra("CHECKPERSONALI", checkedPersonali);
        }
        if (!bool1 && bool2) {
            intent.putExtra("CHECKCOMPLETI", checkedCompleti);
        }
        if (bool1 && bool2) {
            intent.putExtra("CHECKPERSONALI", checkedPersonali);
            intent.putExtra("CHECKCOMPLETI", checkedCompleti);
        }
        if (!bool1 && !bool2) {
            intent.putExtra("CHECKPERSONALI", checkedPersonali);
        }
    }
}