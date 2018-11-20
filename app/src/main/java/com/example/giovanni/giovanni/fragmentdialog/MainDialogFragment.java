package com.example.giovanni.giovanni.fragmentdialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.giovanni.giovanni.R;

import java.io.Serializable;

public class MainDialogFragment extends Fragment {

    public static class MenuItem implements Serializable {

        public int theme;
        public int style;
        public String title;
        public String description;
    }

    public MenuItem item = new MenuItem();
    public DialogItemListener listener;

    public static MainDialogFragment getMenuFragment(final DialogItemListener listener) {
        MainDialogFragment fragment = new MainDialogFragment();
        fragment.setDialogItemListener(listener);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_dialog, container, false);

        item.style = DialogFragment.STYLE_NO_TITLE;
        item.theme = android.R.style.Theme_Holo_Light_Dialog;
        item.title = "DIALOG TITLE";
        item.description = "Dialog description";

        Button button = view.findViewById(R.id.dialog_button);
        button.setOnClickListener(v -> {
            if (listener != null) {
                listener.itemSelected(item);
            }
        });

        return view;
    }

    // Utility method to save the listener.
    public void setDialogItemListener(final DialogItemListener listener) {
        this.listener = listener;
    }

    // Callback interface that the listening activity should implement to receive selection notification from this Fragment.
    public interface DialogItemListener {
        void itemSelected(MenuItem item);
    }
}