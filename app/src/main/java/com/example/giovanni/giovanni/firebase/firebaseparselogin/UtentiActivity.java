package com.example.giovanni.giovanni.firebase.firebaseparselogin;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import com.example.giovanni.giovanni.model.PersonaManager;
import com.example.giovanni.giovanni.utils.InternalStorage;
import java.util.List;

public class UtentiActivity extends AppCompatActivity {

    private LinearLayout bodyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utenti);

        PersonaManager database = (PersonaManager) InternalStorage.readObject(this, "fileUtenti");
        List<Persona> utenti = database.getUtenti();
        bodyContainer = findViewById(R.id.body_container);
        addViews(utenti);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addViews(List<Persona> persone) {
        if (persone == null)
            return;
        if (persone.size() == 0)
            return;

        bodyContainer.removeAllViews();
        for (final Persona persona : persone) {
            if (persona == null)
                continue;

            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.persona_item, bodyContainer, false);

            TextView nome = view.findViewById(R.id.text_nome);
            TextView matricola = view.findViewById(R.id.text_matricola);

            nome.setText(persona.getUsername());
            matricola.setText(String.valueOf(persona.getPassword()));

            view.findViewById(R.id.text_cognome).setVisibility(View.GONE);

            nome.setOnClickListener(v ->
                    Toast.makeText(getApplicationContext(),
                            persona.getUsername() + " " + persona.getPassword(), Toast.LENGTH_SHORT).show()
            );

            bodyContainer.addView(view);
        }
    }
}