package com.example.giovanni.giovanni.bean;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.giovanni.giovanni.BR;
import com.example.giovanni.giovanni.R;

public class DataBindingModel extends BaseObservable {

    private String luogo;
    private String temperatura;
    private String url;
    private boolean sfondo;
    private int colore;
    private Drawable logo;
    private Drawable spinner;
    private int visibilita;

    public DataBindingModel(String luogo, String temperatura) {
        this.luogo = luogo;
        this.temperatura = temperatura;
    }

    public DataBindingModel(String luogo, String temperatura, boolean sfondo) {
        this.luogo = luogo;
        this.temperatura = temperatura;
        this.sfondo = sfondo;
    }

    public DataBindingModel(String luogo, String temperatura, String url) {
        this.luogo = luogo;
        this.temperatura = temperatura;
        this.url = url;
    }

    public DataBindingModel(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isGreen = preferences.getBoolean("GREEN", true);

        if (isGreen) {
            colore = ContextCompat.getColor(context, R.color.verde_2);
            logo = ContextCompat.getDrawable(context, R.drawable.homer);
            visibilita = View.VISIBLE;
        } else {
            colore = ContextCompat.getColor(context, R.color.rosso_2);
            logo = ContextCompat.getDrawable(context, R.drawable.bart);
            visibilita = View.GONE;
        }
    }

    @Bindable
    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
        notifyPropertyChanged(BR.luogo);
    }

    @Bindable
    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
        notifyPropertyChanged(BR.temperatura);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public boolean isSfondo() {
        return sfondo;
    }

    public void setSfondo(boolean sfondo) {
        this.sfondo = sfondo;
        notifyPropertyChanged(BR.sfondo);
    }

    @Bindable
    public int getColore() {
        return colore;
    }

    public void setColore(int colore) {
        this.colore = colore;
        notifyPropertyChanged(BR.colore);
    }

    @Bindable
    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
        notifyPropertyChanged(BR.logo);
    }

    @Bindable
    public Drawable getSpinner() {
        return spinner;
    }

    public void setSpinner(Drawable spinner) {
        this.spinner = spinner;
        notifyPropertyChanged(BR.spinner);
    }

    @Bindable
    public int getVisibilita() {
        return visibilita;
    }

    public void setVisibilita(int visibilita) {
        this.visibilita = visibilita;
        notifyPropertyChanged(BR.visibilita);
    }
}