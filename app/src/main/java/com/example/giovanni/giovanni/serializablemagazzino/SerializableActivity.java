package com.example.giovanni.giovanni.serializablemagazzino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Magazzino;

public class SerializableActivity extends AppCompatActivity {

    private Magazzino magazzino;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);

        magazzino = new Magazzino();
        Button bCamicia = findViewById(R.id.buttonCamicia);
        Button bLibro = findViewById(R.id.buttonLibro);

        bCamicia.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), CamiciaActivity.class);
            intent.putExtra("CAMICIA", magazzino);
            startActivity(intent);
        });

        bLibro.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), LibroActivity.class);
            intent.putExtra("LIBRO", magazzino);
            startActivity(intent);
        });
    }
}