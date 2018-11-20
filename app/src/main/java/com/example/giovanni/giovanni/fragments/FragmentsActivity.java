package com.example.giovanni.giovanni.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.giovanni.giovanni.R;

public class FragmentsActivity extends AppCompatActivity {

    private void log(String string) {
        Log.d("DEBUG", "Main: " + string);
    }

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        fm = getFragmentManager();
        log("onCreate");
    }

    public void inserisciA(View v) {
        FragmentA f = new FragmentA();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentsGroup, f, "fragmentA");
        ft.commit();
    }

    public void inserisciB(View v) {
        FragmentB f = new FragmentB();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentsGroup, f, "fragmentB");
        ft.commit();
    }

    public void inserisciC(View v) {
        FragmentC f = new FragmentC();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentsGroup, f, "fragmentC");
        ft.commit();
    }

    public void rimuoviA(View v) {
        Fragment f = fm.findFragmentByTag("fragmentA");
        FragmentTransaction ft = fm.beginTransaction();
        if (f != null) {
            ft.remove(f);
        }
        ft.commit();
    }

    public void rimuoviB(View v) {
        Fragment f = fm.findFragmentByTag("fragmentB");
        FragmentTransaction ft = fm.beginTransaction();
        if (f != null) {
            ft.remove(f);
        }
        ft.commit();
    }

    public void rimuoviC(View v) {
        Fragment f = fm.findFragmentByTag("fragmentC");
        FragmentTransaction ft = fm.beginTransaction();
        if (f != null) {
            ft.remove(f);
        }
        ft.commit();
    }

    public void daAaB(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        FragmentB f = new FragmentB();
        ft.replace(R.id.fragmentsGroup, f, "fragmentB");
        ft.commit();
    }

    public void daBaA(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        FragmentA f = new FragmentA();
        ft.replace(R.id.fragmentsGroup, f, "fragmentA");
        ft.commit();
    }

    public void attachA(View v) {
        Fragment f = fm.findFragmentByTag("fragmentA");
        if (f != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.attach(f);
            ft.commit();
        }
    }

    public void detachA(View v) {
        Fragment f = fm.findFragmentByTag("fragmentA");
        if (f != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.detach(f);
            ft.commit();
        }
    }
}