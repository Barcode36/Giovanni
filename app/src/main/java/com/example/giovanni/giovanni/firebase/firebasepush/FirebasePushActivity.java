package com.example.giovanni.giovanni.firebase.firebasepush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.giovanni.giovanni.R;

public class FirebasePushActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_push);

        Button stopService = findViewById(R.id.stop_service);

        Intent intent = new Intent(this, FirebasePush.class);
        startService(intent);

        stopService.setOnClickListener(v ->
                stopService(new Intent(getApplicationContext(), FirebasePush.class))
        );
    }
}