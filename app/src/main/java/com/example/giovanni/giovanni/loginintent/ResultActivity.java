package com.example.giovanni.giovanni.loginintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tOutput = findViewById(R.id.textOutput);
        Button back = findViewById(R.id.buttonBack);

        Intent intent = getIntent();
        String output = intent.getStringExtra("KEY");
        String band = intent.getStringExtra("BAND");

        tOutput.setText(output);

        back.setOnClickListener(v -> {

            intent.putExtra("RESULT", band);
            setResult(Activity.RESULT_OK, intent);
            finish(); // Torna indietro.
        });
    }
}