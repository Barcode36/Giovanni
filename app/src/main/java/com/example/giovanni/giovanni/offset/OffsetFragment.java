package com.example.giovanni.giovanni.offset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

public class OffsetFragment extends android.support.v4.app.Fragment {

    private TextView textFragment;
    private int position;

    public static OffsetFragment newInstance(int position) {
        OffsetFragment fragment = new OffsetFragment();
        fragment.setPosition(position);

        return fragment;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offset, container, false);

        textFragment = view.findViewById(R.id.text_fragment_offset);

        switchPosition();

        return view;
    }

    private void switchPosition() {
        switch (position) {
            case 0:
                textFragment.setText(R.string.fragment_1);
                textFragment.setTextColor(getResources().getColor(R.color.azzurro));
                break;
            case 1:
                textFragment.setText(R.string.fragment_2);
                textFragment.setTextColor(getResources().getColor(R.color.giada));
                break;
            case 2:
                textFragment.setText(R.string.fragment_3);
                textFragment.setTextColor(getResources().getColor(R.color.red));
                break;
            case 3:
                textFragment.setText(R.string.fragment_4);
                textFragment.setTextColor(getResources().getColor(R.color.yellow));
                break;
        }
    }
}