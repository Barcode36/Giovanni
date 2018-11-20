package com.example.giovanni.giovanni.viewpagertablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;

public class FragmentPin extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pin, container, false);

        CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.collapsing_toolbar_pin);
        collapsingToolbar.setTitle("San Martino");
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.green));
        collapsingToolbar.setBackgroundColor(getResources().getColor(R.color.giallo_pastello));

        FloatingActionButton floatingActionButtonPin = view.findViewById(R.id.floating_action_button_pin);

        floatingActionButtonPin.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getContext(), "Pin effect", Toast.LENGTH_SHORT);
            toast.show();
        });

        return view;
    }
}
