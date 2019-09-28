package com.example.giovanni.giovanni.viewpagertablayout;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String [] fragments = {"Calcolatrice", "Pin effect", "Parallax effect", "CheckBox + Open Url", "Pull to refresh + Random"};

    private FragmentCalcolatrice fragmentCalcolatrice = new FragmentCalcolatrice();
    private FragmentPin fragmentPin = new FragmentPin();
    private FragmentParallax fragmentParallax = new FragmentParallax();
    private CheckBoxFragment checkBoxFragment = new CheckBoxFragment();
    private FragmentPull fragmentPull = new FragmentPull();

    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return fragmentCalcolatrice;
            case 1: return fragmentPin;
            case 2: return fragmentParallax;
            case 3: return checkBoxFragment;
            case 4: return fragmentPull;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}