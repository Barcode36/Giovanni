package com.example.giovanni.giovanni.viewpagertablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxFragment extends Fragment {

    private List<String> lista;
    private TextView tCheck;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkbox, container, false);

        lista = new ArrayList<>();
        tCheck = view.findViewById(R.id.text_check);
        tCheck.setEnabled(false);

        CheckBox checkbox1 = view.findViewById(R.id.checkbox1);
        CheckBox checkbox2 = view.findViewById(R.id.checkbox2);
        Button buttonCheck = view.findViewById(R.id.button_check);

        checkbox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                lista.add("CheckBox 1");
            } else {
                lista.remove("CheckBox 1");
            }
        });

        checkbox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                lista.add("CheckBox 2");
            } else {
                lista.remove("CheckBox 2");
            }
        });

        buttonCheck.setOnClickListener(v -> {
            String text = "";
            for(String scelta : lista) {
                text = text + scelta + "\n";
            }
            tCheck.setText(text);
            tCheck.setEnabled(true);
        });

        return view;
    }
}