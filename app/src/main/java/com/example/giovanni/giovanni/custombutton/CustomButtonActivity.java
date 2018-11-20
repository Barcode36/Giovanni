package com.example.giovanni.giovanni.custombutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.giovanni.giovanni.R;

public class CustomButtonActivity extends AppCompatActivity {

    private View buttonOne;
    private View buttonTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_button);

        buttonOne = findViewById(R.id.button_one);
        buttonTwo = findViewById(R.id.button_two);

        buttonOne.setOnClickListener(view -> {
            boolean selection = !buttonOne.isSelected();
            buttonOne.setSelected(selection);
            if (selection) {
                buttonTwo.setSelected(false);
            }
        });

        buttonTwo.setOnClickListener(view -> {
            boolean selection = !buttonTwo.isSelected();
            buttonTwo.setSelected(selection);
            if (selection) {
                buttonOne.setSelected(false);
            }
        });
    }
}