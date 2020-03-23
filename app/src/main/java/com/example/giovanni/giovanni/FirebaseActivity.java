package com.example.giovanni.giovanni;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.firebase.firebasearticoli.FirebaseArticoliActivity;
import com.example.giovanni.giovanni.firebase.firebasecommunity.PostLoginActivity;
import com.example.giovanni.giovanni.firebase.firebaseparse.FirebaseParseActivity;
import com.example.giovanni.giovanni.firebase.firebaseparselogin.FirebaseParseLoginActivity;
import com.example.giovanni.giovanni.firebase.firebasepush.FirebasePushActivity;
import com.example.giovanni.giovanni.firebase.firebaserest.FirebaseRestActivity;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        findViewById(R.id.firebase_rest).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseRestActivity.class)));

        findViewById(R.id.firebase_json_parse).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseParseActivity.class)));

        findViewById(R.id.firebase_login).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseParseLoginActivity.class)));

        findViewById(R.id.firebase_articoli).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseArticoliActivity.class)));

        findViewById(R.id.firebase_community).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), PostLoginActivity.class)));

        findViewById(R.id.firebase_push).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebasePushActivity.class)));
    }
}