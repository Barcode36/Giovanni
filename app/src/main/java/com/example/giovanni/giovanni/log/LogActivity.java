package com.example.giovanni.giovanni.log;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.giovanni.giovanni.R;
import com.google.android.material.tabs.TabLayout;

public class LogActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new LogAdapter(getSupportFragmentManager()));

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        Toast toast = Toast.makeText(getApplicationContext(), "onPageSelected: " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_SHORT);
        toast.show();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getApplicationContext(), "onPageSelected: " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}