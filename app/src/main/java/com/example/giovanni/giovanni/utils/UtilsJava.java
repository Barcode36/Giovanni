package com.example.giovanni.giovanni.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

public class UtilsJava {

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

//    public static void hideSoftKeyboardKotlin() {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm != null) {
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
}