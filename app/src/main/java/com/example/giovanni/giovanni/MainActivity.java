package com.example.giovanni.giovanni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.databinding.dblayout.LayoutActivity;
import com.example.giovanni.giovanni.databinding.dbmeteo.MeteoActivity;
import com.example.giovanni.giovanni.databinding.dburl.UrlActivity;
import com.example.giovanni.giovanni.fragmentdialog.MainDialogActivity;
import com.example.giovanni.giovanni.googlemaps.MapsActivity;
import com.example.giovanni.giovanni.highscore.HighScoreActivity;
import com.example.giovanni.giovanni.log.LogActivity;
import com.example.giovanni.giovanni.mvpaddtextchangedlistener.AddTextActivity;
import com.example.giovanni.giovanni.pagination.pagination1.Pagination1Activity;
import com.example.giovanni.giovanni.pagination.pagination2.Pagination2Activity;
import com.example.giovanni.giovanni.sqlite.SQLiteActivity;
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

    /*
    - Click on Gradle (from right side panel)
    - Click on your project
    - Click on Tasks
    - Click on Android
    - Double click on signingReport
    You will get SHA1 and MD5 in Run Tab:

    Variant: debug
    Config: debug
    Store: /Users/Giovanni/.android/debug.keystore
    Alias: AndroidDebugKey
    MD5: C3:9B:CE:AC:C2:C5:4B:4C:6C:24:56:F3:17:73:37:C1
    SHA1: 03:29:32:E7:87:94:51:CA:67:F5:33:0E:53:50:BD:69:66:2F:F0:B0
    SHA-256: ED:C1:9D:E9:CD:57:86:E6:1B:83:B0:28:39:99:32:0C:FF:A1:C0:25:68:DA:E4:95:3A:CD:94:DA:65:73:D8:37
    Valid until: mercoledì 13 febbraio 2047
    */

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

        findViewById(R.id.thread_pics).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ThreadPicsActivity.class)));

        findViewById(R.id.thread_async_task).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ThreadAsyncTaskActivity.class)));

        findViewById(R.id.thread_async_task_counter).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ThreadAsyncTaskCounterActivity.class)));

        findViewById(R.id.firebase).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), FirebaseActivity.class)));

        findViewById(R.id.sqlite_database).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), SQLiteActivity.class)));

        findViewById(R.id.data_binding_meteo).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), MeteoActivity.class)));

        findViewById(R.id.data_binding_url).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), UrlActivity.class)));

        findViewById(R.id.data_binding_layout).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LayoutActivity.class)));

        findViewById(R.id.pagination_1).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), Pagination1Activity.class)));

        findViewById(R.id.pagination_2).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), Pagination2Activity.class)));
    }
}