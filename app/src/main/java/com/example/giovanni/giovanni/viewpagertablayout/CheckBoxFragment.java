package com.example.giovanni.giovanni.viewpagertablayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxFragment extends Fragment {

    private List<String> lista;
    private TextView tCheck;
    private SwitchCompat switchCompat;

    private Boolean contractConditionsChecked = false;
    private Boolean economicConditionsChecked = false;

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

        contractConditions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                contractConditionsChecked = isChecked;
                boolean checked = contractConditionsChecked && economicConditionsChecked;
                switchCompat.setEnabled(checked);
                switchCompat.setChecked(checked);
                if (checked)
                    switchCompat.setTextColor(getResources().getColor(R.color.black));
                else
                    switchCompat.setTextColor(getResources().getColor(R.color.grey));
            }
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
            else
                switchCompat.setTextColor(getResources().getColor(R.color.grey));
        });

        contractConditionsInfo.setTag("https://kotlinlang.org/docs/kotlin-docs.pdf");
        contractConditionsInfo.setOnClickListener(v -> {
//          showInfo((String) v.getTag());
            openUrl(getContext(), (String) v.getTag());

        });

        economicConditionsInfo.setTag("https://kotlinlang.org/docs/kotlin-docs.pdf");
        economicConditionsInfo.setOnClickListener(v -> {
//          showInfo((String) v.getTag());
            openUrl(getContext(), (String) v.getTag());
        });

        return view;
    }

    public void openUrl(Context context, String url) {
        if (url != null && !url.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        }
    }

//    private void showInfo(final String url) {
//        mDialog.showInfoDialog(
//                getString(R.string.utility_conditions_title).toUpperCase(),
//                getString(R.string.utility_info_message),
//                getString(R.string.btn_open),
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        openUrl(getContext(), (String) v.getTag());
//                        mDialog.dismissAlertDialog();
//                    }
//                },
//                getString(R.string.btn_cancel),
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDialog.dismissAlertDialog();
//                    }
//                }
//        );
//    }
}