package com.example.giovanni.giovanni.viewpagertablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.giovanni.giovanni.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FragmentParallax extends Fragment {

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_parallax, container, false);

        CollapsingToolbarLayout collapsingToolbar = v.findViewById(R.id.collapsing_toolbar_parallax);
        collapsingToolbar.setTitle("San Martino");
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.green));
        collapsingToolbar.setBackgroundColor(getResources().getColor(R.color.giallo_pastello));

        ImageView imageParallax = v.findViewById(R.id.imageview_parallax);

        imageParallax.setOnClickListener(view ->
                Toast.makeText(getContext(), "Parallax effect", Toast.LENGTH_LONG).show()
        );

        return v;
    }
}