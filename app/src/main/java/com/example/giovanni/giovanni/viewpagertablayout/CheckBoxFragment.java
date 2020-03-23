package com.example.giovanni.giovanni.viewpagertablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.giovanni.giovanni.R;
import java.util.ArrayList;
import java.util.List;

import static com.example.giovanni.giovanni.utils.Utils.openUrl;

public class CheckBoxFragment extends Fragment {

    private List<String> lista;
    private TextView tCheck;
    private SwitchCompat switchCompat;

    private Boolean contractConditionsChecked = false;
    private Boolean economicConditionsChecked = false;

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkbox, container, false);

        lista = new ArrayList<>();
        tCheck = view.findViewById(R.id.text_check);
        tCheck.setEnabled(false);

        CheckBox checkbox1 = view.findViewById(R.id.checkbox1);
        CheckBox checkbox2 = view.findViewById(R.id.checkbox2);
        Button buttonCheck = view.findViewById(R.id.button_check);

        CheckBox contractConditions = view.findViewById(R.id.contractConditions);
        CheckBox economicConditions = view.findViewById(R.id.economicConditions);
        switchCompat = view.findViewById(R.id.switch_compat);

        ImageView contractConditionsInfo = view.findViewById(R.id.contractConditionsInfo);
        ImageView economicConditionsInfo = view.findViewById(R.id.economicConditionsInfo);

        checkbox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                lista.add("CheckBox 1");
            } else {
                lista.remove("CheckBox 1");
            }
        });

        checkbox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                lista.add("CheckBox 2");
            else
                lista.remove("CheckBox 2");
        });

        buttonCheck.setOnClickListener(v -> {
            StringBuilder text = new StringBuilder();
            for (String scelta : lista) {
                text.append(scelta).append("\n");
            }
            tCheck.setText(text.toString());
            tCheck.setEnabled(true);
        });

        contractConditions.setOnCheckedChangeListener((buttonView, isChecked) -> {
            contractConditionsChecked = isChecked;
            boolean checked = contractConditionsChecked && economicConditionsChecked;
            switchCompat.setEnabled(checked);
            switchCompat.setChecked(checked);
            if (checked)
                switchCompat.setTextColor(getResources().getColor(R.color.black));
            else
                switchCompat.setTextColor(getResources().getColor(R.color.grey));
        });
        economicConditions.setOnCheckedChangeListener((buttonView, isChecked) -> {
            economicConditionsChecked = isChecked;
            boolean checked = contractConditionsChecked && economicConditionsChecked;
            switchCompat.setEnabled(checked);
            switchCompat.setChecked(checked);
            if (checked)
                switchCompat.setTextColor(getResources().getColor(R.color.black));
            else
                switchCompat.setTextColor(getResources().getColor(R.color.grey));
        });

        switchCompat.setOnClickListener(v -> {
            if (switchCompat.isChecked())
                switchCompat.setTextColor(getResources().getColor(R.color.black));
            else {
                switchCompat.setTextColor(getResources().getColor(R.color.grey));
                contractConditions.setChecked(false);
                economicConditions.setChecked(false);
            }
        });

        contractConditionsInfo.setTag("https://kotlinlang.org/docs/kotlin-docs.pdf");
        contractConditionsInfo.setOnClickListener(v ->
                openUrl(getContext(), (String) v.getTag()));

        economicConditionsInfo.setTag("https://kotlinlang.org/docs/kotlin-docs.pdf");
        economicConditionsInfo.setOnClickListener(v ->
                openUrl(getContext(), (String) v.getTag()));

        return view;
    }
}