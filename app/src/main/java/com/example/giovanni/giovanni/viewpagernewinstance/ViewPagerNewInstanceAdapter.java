package com.example.giovanni.giovanni.viewpagernewinstance;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerNewInstanceAdapter extends FragmentPagerAdapter {

    private String[] fragments = {"OGGI", "SETTIMANA", "MESE"};
    private ContentFragment[] contentFragments;

    public ViewPagerNewInstanceAdapter(FragmentManager fm) {
        super(fm);
        contentFragments = new ContentFragment[] {ContentFragment.newInstance(0),
                ContentFragment.newInstance(1), ContentFragment.newInstance(2)};
    }

//    @Override
//    public Fragment getItem(int position) {
//        return ContentFragment.newInstance(position);
//    }

    // Ho riscritto il metodo getItem() in seguito all'aggiunta dell'array contentFragments.
    @Override
    public Fragment getItem(int position) {
        return contentFragments[position];
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