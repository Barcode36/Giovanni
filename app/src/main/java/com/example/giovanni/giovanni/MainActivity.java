package com.example.giovanni.giovanni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giovanni.giovanni.firebase.FirebaseActivity;
import com.example.giovanni.giovanni.firebasearticoli.FirebaseArticoliActivity;
import com.example.giovanni.giovanni.firebaseparselogin.FirebaseParseLoginActivity;
import com.example.giovanni.giovanni.firebaseparse.FirebaseParseActivity;
import com.example.giovanni.giovanni.fragmentdialog.MainDialogActivity;
import com.example.giovanni.giovanni.googlemaps.MapsActivity;
import com.example.giovanni.giovanni.highscore.HighScoreActivity;
import com.example.giovanni.giovanni.log.LogActivity;
import com.example.giovanni.giovanni.mvpaddtextchangedlistener.AddTextActivity;
import com.example.giovanni.giovanni.threadasynctaskcounter.ThreadAsyncTaskCounterActivity;
import com.example.giovanni.giovanni.fragments.FragmentsActivity;
import com.example.giovanni.giovanni.loginintent.LoginIntentActivity;
import com.example.giovanni.giovanni.mvploginkotlin.login.LoginKotlinActivity;
import com.example.giovanni.giovanni.navigationdrawer.NavigationDrawerActivity;
import com.example.giovanni.giovanni.offset.OffsetActivity;
import com.example.giovanni.giovanni.serializablemagazzino.SerializableActivity;
import com.example.giovanni.giovanni.textlayout.TextLayoutActivity;
import com.example.giovanni.giovanni.threadpics.ThreadPicsActivity;
import com.example.giovanni.giovanni.threadasynctask.ThreadAsyncTaskActivity;
import com.example.giovanni.giovanni.viewpagernewinstance.ViewPagerNewInstanceActivity;
import com.example.giovanni.giovanni.viewpagertablayout.ViewPagerActivity;

// La classe MainActivity estende la classe AppCompatActivity che è la libreria che permette compatibilità con la versione 7 di Android.
public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override // Questo override indica che il metodo onCreate() si trova nella classe AppCompatActivity.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Richiamo il metodo onCreate() dalla super classe e gli passo il parametro.
        // Il metodo setContentView che prende come parametro un intero indica a MainActivity che deve riferirsi al file
        // xml chiamato activity_main nella cartella layout nella cartella res.
        setContentView(R.layout.activity_main);

        Button buttonLog = findViewById(R.id.log);

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.login_intent).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LoginIntentActivity.class)));

        findViewById(R.id.serializable).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), SerializableActivity.class)));

        findViewById(R.id.listview).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ListViewActivity.class)));

        findViewById(R.id.recyclerview).setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class)));

        findViewById(R.id.fragments).setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), FragmentsActivity.class)));

        findViewById(R.id.viewpager_tablayout).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ViewPagerActivity.class)));

        findViewById(R.id.viewpager_newinstance).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ViewPagerNewInstanceActivity.class)));

        findViewById(R.id.text_layout).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), TextLayoutActivity.class)));

        findViewById(R.id.offset).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), OffsetActivity.class)));

        findViewById(R.id.navigationdrawer).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), NavigationDrawerActivity.class)));

        findViewById(R.id.addtextchangedlistener).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AddTextActivity.class)));

        findViewById(R.id.mvp_kotlin_login).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LoginKotlinActivity.class)));

        findViewById(R.id.fragment_dialog).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), MainDialogActivity.class)));

        findViewById(R.id.google_maps).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), MapsActivity.class)));

        findViewById(R.id.high_score).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), HighScoreActivity.class)));

        findViewById(R.id.firebase).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseActivity.class)));

        findViewById(R.id.json_parse).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseParseActivity.class)));

        findViewById(R.id.firebase_login).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseParseLoginActivity.class)));

        findViewById(R.id.firebase_articoli).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseArticoliActivity.class)));

        findViewById(R.id.thread_pics).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ThreadPicsActivity.class)));

        findViewById(R.id.thread_async_task).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ThreadAsyncTaskActivity.class)));

        findViewById(R.id.thread_async_task_counter).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ThreadAsyncTaskCounterActivity.class)));
    }
}