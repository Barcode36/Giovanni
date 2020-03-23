package com.example.giovanni.giovanni.databinding.dblayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.giovanni.giovanni.R;

public class LayoutActivity extends AppCompatActivity {

    private FragmentManager fm;
    RelativeLayout container;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        container = findViewById(R.id.activity_direct_container);
        Button open = findViewById(R.id.button_open_fragment);
        fm = getSupportFragmentManager();

        open.setOnClickListener(v -> {
            container.setVisibility(View.GONE);
            openFragment();
        });
    }

    public void openFragment() {
        LayoutFragment fragment = LayoutFragment.newInstance();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragments_group, fragment);
        ft.commit();
    }
}