package com.example.giovanni.giovanni.viewpagernewinstance;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerNewInstanceAdapter extends FragmentPagerAdapter {

    private String[] fragments = {"OGGI", "SETTIMANA", "MESE"};
    private ContentFragment[] contentFragments;

    ViewPagerNewInstanceAdapter(FragmentManager fm) {
        super(fm);
        contentFragments = new ContentFragment[] {ContentFragment.newInstance(0),
                ContentFragment.newInstance(1), ContentFragment.newInstance(2)};
    }

//    @Override
//    public Fragment getItem(int position) {
//        return ContentFragment.newInstance(position);
//    }

    // Ho riscritto il metodo getItem() in seguito all'aggiunta dell'array contentFragments.
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return contentFragments[position];
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