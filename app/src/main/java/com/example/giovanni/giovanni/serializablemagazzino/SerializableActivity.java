package com.example.giovanni.giovanni.serializablemagazzino;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.customview.SpinnerHintAdapter;
import com.example.giovanni.giovanni.bean.Magazzino;
import com.example.giovanni.giovanni.utils.IntentAnimationManager;

public class SerializableActivity extends AppCompatActivity {

    private Magazzino magazzino;
    private Spinner spinnerAnimations;
    private Intent intent;

    private static final String SELECTED_ITEM_POSITION = "ItemPosition";
    private int position;

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of item position
        outState.putInt(SELECTED_ITEM_POSITION, position);
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Read the state of item position
        position = savedInstanceState.getInt(SELECTED_ITEM_POSITION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);

        spinnerAnimations = findViewById(R.id.spinner_animations);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.animations, R.layout.spinner_row); // android.R.layout.simple_spinner_item

        // TODO) Nota: Invece di ArrayAdapter<CharSequence> adapter che usa R.array.animations, potrei utilizzare anche ArrayAdapter adapter che usa String[] arrayAnimations, il risultato non cambia:
        // String[] arrayAnimations = new String[] {"left to right", "right to left", "bottom to up", "up to bottom", "fade in to fade out", "rotate out to rotate in"};
        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayAnimations);
        // Kotlin:
        // var arrayAnimations: Array<String> = arrayOf("left to right", "right to left", "bottom to up", "up to bottom", "fade in to fade out", "rotate out to rotate in")
        // val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, arrayAnimations)

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnimations.setAdapter(new SpinnerHintAdapter(this, adapter, R.layout.spinner_row_hint, "Scegli tra le seguenti opzioni"));

        Button bCamicia = findViewById(R.id.buttonCamicia);
        Button bLibro = findViewById(R.id.buttonLibro);

        magazzino = new Magazzino();

        bCamicia.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), CamiciaActivity.class);
            intent.putExtra("CAMICIA", magazzino);
            startActivity(intent);
            IntentAnimationManager.handleIntentAnimation(SerializableActivity.this, spinnerAnimations.getSelectedItem().toString());
        });

        bLibro.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), LibroActivity.class);
            intent.putExtra("LIBRO", magazzino);

            ActivityOptions options = ActivityOptions.makeCustomAnimation(SerializableActivity.this, R.anim.left_to_right, R.anim.right_to_left);
            startActivity(intent, options.toBundle());
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}