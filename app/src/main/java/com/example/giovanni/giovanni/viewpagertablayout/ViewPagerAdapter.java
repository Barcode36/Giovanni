package com.example.giovanni.giovanni.viewpagertablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String [] fragments = {"Calcolatrice", "Parallax effect", "Pin effect", "Pull to refresh", "Open Url"};

    private FragmentCalcolatrice fragmentCalcolatrice = new FragmentCalcolatrice();
    private FragmentParallax fragmentParallax = new FragmentParallax();
    private FragmentPin fragmentPin = new FragmentPin();
    private FragmentPull fragmentPull = new FragmentPull();
    private CheckBoxFragment checkBoxFragment = new CheckBoxFragment();

    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return fragmentCalcolatrice;
            case 1: return fragmentParallax;
            case 2: return fragmentPin;
            case 3: return fragmentPull;
            case 4: return checkBoxFragment;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemPosition(Object object) {
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