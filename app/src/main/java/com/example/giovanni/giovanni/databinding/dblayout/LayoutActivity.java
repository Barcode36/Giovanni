package com.example.giovanni.giovanni.databinding.dblayout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.giovanni.giovanni.R;

@SuppressWarnings("deprecation")
public class LayoutActivity extends AppCompatActivity {

    private FragmentManager fm;
    RelativeLayout container;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        container = findViewById(R.id.activity_direct_container);
        Button open = findViewById(R.id.button_open_fragment);
        fm = getFragmentManager();

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