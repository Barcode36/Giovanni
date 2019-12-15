package com.example.giovanni.giovanni.mvpaddtextchangedlistener;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class AddTextActivity extends AppCompatActivity implements AddTextPresenter.View {

    private AddTextPresenter presenter;
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtext);

        presenter = new AddTextPresenter(this);

        textView = findViewById(R.id.textView);
        EditText username = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        initProgressBar();

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateUsername(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                hideProgressBar();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                hideProgressBar();
            }
        });

        AutoCompleteTextView actv1 = findViewById(R.id.search_family_1);
        AutoCompleteTextView actv2 = findViewById(R.id.search_family_2);

        List<Persona> list = Persona.initPersone();
        List<String> listaNomi = new ArrayList<>();

        for (Persona persona : list) {
            listaNomi.add(persona.getNome());
        }

        String[] arrayNomi = listaNomi.toArray(new String[list.size()]);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrayNomi);
        actv1.setAdapter(adapter1);

        PersonaArrayAdapter adapter2 = new PersonaArrayAdapter(this, list);
        actv2.setAdapter(adapter2);

        Button dropDown = findViewById(R.id.button_drop_down);
        dropDown.setOnClickListener(view ->
                actv2.showDropDown()
        );
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels, 250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
        showProgressBar();
    }

    @Override
    public void updateUserInfoTextView(String info) {
        textView.setText(info);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}