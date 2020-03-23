package com.example.giovanni.giovanni.fragmentdialog;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.giovanni.giovanni.R;

public class MainDialogActivity extends AppCompatActivity implements MainDialogFragment.DialogItemListener {

    private static final String DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT"; // The tag for the FragmentDialog into the layout.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dialog);

        // If the fragment is not into the state, we create and insert into the main layout.
        if (savedInstanceState == null) {
            MainDialogFragment mMainDialogFragment = MainDialogFragment.getMenuFragment(this);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mMainDialogFragment).commit();
        }
    }

    @Override
    public void itemSelected(final MainDialogFragment.MenuItem menuItem) {

        // We create a Fragment Transaction.
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // And check if a FragmentDialog is already present.
        Fragment previousFragment = getSupportFragmentManager().findFragmentByTag(DIALOG_FRAGMENT_TAG);
        if (previousFragment != null) {
            // If it is, we remove it into the same transaction
            fragmentTransaction.remove(previousFragment);
        }
        // Uncomment the next instruction to test what happen with the back key and transaction into the Back Stack
        // fragmentTransaction.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newDialogFragment = FragmentDialog.getStyledDialogFragment(menuItem);
        // Be carefull that this show also commit the transaction.
        newDialogFragment.show(fragmentTransaction, DIALOG_FRAGMENT_TAG);
    }
}