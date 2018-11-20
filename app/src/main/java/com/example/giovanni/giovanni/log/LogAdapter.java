package com.example.giovanni.giovanni.log;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class LogAdapter extends FragmentPagerAdapter {

    private String [] fragments = {"Array", "Articolo", "Ascensore", "Dipendente e progetto", "Dipendente, manager e azienda"};

    private ArrayFragment arrayFragment = new ArrayFragment();
    private ArticoloFragment articoloFragment = new ArticoloFragment();
    private AscensoreFragment ascensoreFragment = new AscensoreFragment();
    private DipendenteProgettoFragment dipendenteProgettoFragment = new DipendenteProgettoFragment();
    private DipendenteManagerAziendaFragment dipendenteManagerAziendaFragment = new DipendenteManagerAziendaFragment();

    LogAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return arrayFragment;
            case 1: return articoloFragment;
            case 2: return ascensoreFragment;
            case 3: return dipendenteProgettoFragment;
            case 4: return dipendenteManagerAziendaFragment;
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