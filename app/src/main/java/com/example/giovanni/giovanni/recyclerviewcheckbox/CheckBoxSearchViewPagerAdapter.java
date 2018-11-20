package com.example.giovanni.giovanni.recyclerviewcheckbox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CheckBoxSearchViewPagerAdapter extends FragmentPagerAdapter {

    private String [] fragments = {"Search & Checkbox", "Check 3 items"};

    private CheckBoxSearchFragment checkBoxSearchFragment = new CheckBoxSearchFragment();
    private MaxCheckBoxSearchFragment maxCheckBoxSearchFragment = new MaxCheckBoxSearchFragment();

    CheckBoxSearchViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return checkBoxSearchFragment;
            case 1: return maxCheckBoxSearchFragment;
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