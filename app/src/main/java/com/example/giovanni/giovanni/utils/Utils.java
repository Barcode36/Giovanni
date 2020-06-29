package com.example.giovanni.giovanni.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.giovanni.giovanni.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    /*
    public void savePersona(Persona persona) {
        SharedPreferences.Editor editor = getLastUserData().edit();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().create();
        String personaString = gson.toJson(persona);
        editor.putString(KEY_PERSONA, personaString);
        editor.apply();
    }

    public Persona loadPersona() {
        String personaString = getLastUserData().getString(KEY_PERSONA, null);
        Persona persona = null;
        if (personaString != null && !personaString.equals("")) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.serializeNulls().create();
            persona = gson.fromJson(personaString, Persona.class);
        }
        return persona;
    }
    */

    public static String turnToString(List<String> list) {
        String combinations = "";
        for (int i=0; i<list.size(); i++) {
            if (i < list.size() - 1) {
                combinations = combinations + list.get(i) + ", ";
            } else {
                combinations = combinations + list.get(i);
            }
        }
        return combinations;
    }

    // Per permettere alla keyboard di coprire la view, inserire nel blocco activity del file Manifest:
    // android:windowSoftInputMode="adjustNothing"

    public static void openUrl(Context context, String url) {
        if (url != null && !url.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        }
    }

    public static Date formatToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ITALY);
        return format.parse(date, new ParsePosition(0));
    }

    public static String formatToString1(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
        return format.format(date);
    }

    public static String formatToString2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ITALY);
        return format.format(date);
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext()).
                load(url).
                placeholder(R.drawable.ico_favourite_selected).
                into(view);
    }

    @SuppressWarnings("deprecation")
    @BindingAdapter("android:background")
    public static void setSpinnerLogo(ImageView view, Drawable spinner) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        boolean isGreen = preferences.getBoolean("GREEN", true);

        if (isGreen)
            spinner = view.getContext().getResources().getDrawable(R.drawable.spinner_wind_loader);
        else
            spinner = view.getContext().getResources().getDrawable(R.drawable.spinner_direct_loader);

        view.setBackground(spinner);

        AnimationDrawable spinnerAnimation = (AnimationDrawable) view.getBackground();
        view.post(() -> {
            try {
                spinnerAnimation.start();
            } catch (Exception e) {
                // todo
            }
        });
    }
}