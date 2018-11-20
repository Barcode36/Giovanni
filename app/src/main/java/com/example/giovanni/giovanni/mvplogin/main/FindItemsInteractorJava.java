package com.example.giovanni.giovanni.mvplogin.main;

import android.os.Handler;

import java.util.Arrays;
import java.util.List;

public class FindItemsInteractorJava {

    public void findItems(final OnFinishedListener listener) {
        new Handler().postDelayed(() -> listener.onFinished(createArrayList()), 1000);
    }

    private List<String> createArrayList() {
        return Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10");
    }

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }
}