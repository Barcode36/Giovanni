package com.example.giovanni.giovanni.viewpagernewinstance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.giovanni.giovanni.R;
import com.google.android.material.tabs.TabLayout;

public class ViewPagerNewInstanceActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_newinstance);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerNewInstanceAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}