package com.example.giovanni.giovanni.listviewadapterrubrica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Rubrica;

public class RubricaActivity extends AppCompatActivity {

    private Rubrica rubrica;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubrica);

        rubrica = new Rubrica();
        rubrica.init();

        Button bContatti = findViewById(R.id.buttonContatti);

        bContatti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), ListViewRubricaActivity.class);
                intent.putExtra("KEY", rubrica);
                startActivity(intent);
            }
        });
    }
}