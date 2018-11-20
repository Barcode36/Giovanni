package com.example.giovanni.giovanni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giovanni.giovanni.fragmentdialog.MainDialogActivity;
import com.example.giovanni.giovanni.log.LogActivity;
import com.example.giovanni.giovanni.mvpaddtextchangedlistener.AddTextActivity;
import com.example.giovanni.giovanni.asynctaskcounter.AsyncTaskCounterActivity;
import com.example.giovanni.giovanni.custombutton.CustomButtonActivity;
import com.example.giovanni.giovanni.fragments.FragmentsActivity;
import com.example.giovanni.giovanni.gradientdrawable.GradientDrawableActivity;
import com.example.giovanni.giovanni.loginintent.LoginIntentActivity;
import com.example.giovanni.giovanni.mvplogin.login.LoginJavaActivity;
import com.example.giovanni.giovanni.mvploginkotlin.login.LoginKotlinActivity;
import com.example.giovanni.giovanni.navigationdrawer.NavigationDrawerActivity;
import com.example.giovanni.giovanni.offset.OffsetActivity;
import com.example.giovanni.giovanni.serializablemagazzino.SerializableActivity;
import com.example.giovanni.giovanni.setlayout.SetLayoutActivity;
import com.example.giovanni.giovanni.settext.SetTextActivity;
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

        findViewById(R.id.settext).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), SetTextActivity.class)));

        findViewById(R.id.login_intent).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LoginIntentActivity.class)));

        findViewById(R.id.serializable).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), SerializableActivity.class)));

        findViewById(R.id.listview).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ListViewActivity.class)));

        findViewById(R.id.custom_button).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), CustomButtonActivity.class)));

        findViewById(R.id.recyclerview).setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class)));

        findViewById(R.id.fragments).setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), FragmentsActivity.class)));

        findViewById(R.id.viewpager_tablayout).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ViewPagerActivity.class)));

        findViewById(R.id.viewpager_newinstance).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), ViewPagerNewInstanceActivity.class)));

        findViewById(R.id.asynctask_counter).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AsyncTaskCounterActivity.class)));

        findViewById(R.id.gradientdrawable).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), GradientDrawableActivity.class)));

        findViewById(R.id.set_layout).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), SetLayoutActivity.class)));

        findViewById(R.id.offset).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), OffsetActivity.class)));

        findViewById(R.id.navigationdrawer).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), NavigationDrawerActivity.class)));

        findViewById(R.id.addtextchangedlistener).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), AddTextActivity.class)));

        findViewById(R.id.mvp_java_login).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LoginJavaActivity.class)));

        findViewById(R.id.mvp_kotlin_login).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), LoginKotlinActivity.class)));

        findViewById(R.id.fragment_dialog).setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), MainDialogActivity.class)));
    }
}