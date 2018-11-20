package com.example.giovanni.giovanni.fragmentdialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

public class FragmentDialog extends DialogFragment {

    private static final String MENU_ITEM_KEY = "MENU_ITEM_KEY"; // The Key for the MenuItem in the arguments.

    public static FragmentDialog getStyledDialogFragment(MainDialogFragment.MenuItem menuItem) {

        FragmentDialog fragment = new FragmentDialog();
        // We create the arguments.
        Bundle args = new Bundle();
        args.putSerializable(MENU_ITEM_KEY, menuItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We set the style and theme.
        MainDialogFragment.MenuItem menuItem = (MainDialogFragment.MenuItem) getArguments().getSerializable(MENU_ITEM_KEY);
        setStyle(menuItem.style, menuItem.theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View dialogLayout = inflater.inflate(R.layout.fragment_dialog, container, false);
        TextView styleView = dialogLayout.findViewById(R.id.dialog_title);
        TextView themeView = dialogLayout.findViewById(R.id.dialog_description);

        MainDialogFragment.MenuItem menuItem = (MainDialogFragment.MenuItem) getArguments().getSerializable(MENU_ITEM_KEY);
        styleView.setText(menuItem.title);
        themeView.setText(menuItem.description);

        // Manage the Button.
        dialogLayout.findViewById(R.id.dialog_button).setOnClickListener(v -> {
            dismiss(); // We have to dismiss the Dialog.
        });
        return dialogLayout; // Return the created layout.
    }
}