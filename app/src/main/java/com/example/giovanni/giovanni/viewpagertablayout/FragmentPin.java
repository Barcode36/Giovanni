package com.example.giovanni.giovanni.viewpagertablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.giovanni.giovanni.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentPin extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pin, container, false);

        CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsing_toolbar_pin);
        collapsingToolbar.setTitle("San Martino");
        collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(requireContext(), R.color.verde_2));
        collapsingToolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.giallo_2));

        FloatingActionButton floatingActionButtonPin = view.findViewById(R.id.floating_action_button_pin);

        floatingActionButtonPin.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getContext(), "Pin effect", Toast.LENGTH_SHORT);
            toast.show();
        });

        return view;
    }
}
