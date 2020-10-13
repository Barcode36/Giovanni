package com.example.giovanni.giovanni.listview.listviewazienda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Azienda;
import com.example.giovanni.giovanni.bean.Developer;
import com.example.giovanni.giovanni.bean.Inserviente;
import com.example.giovanni.giovanni.bean.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertActivity extends AppCompatActivity {

    private Spinner spinnerDipendenti;
    private EditText eIDProgetto;
    private EditText eNome;
    private EditText eCognome;

    private String tipo;
    private String IDProgetto;
    private int IDProgettoInt;
    private String nome;
    private String cognome;

    private Azienda azienda;
    private Manager manager;
    private Developer developer;
    private Inserviente inserviente;
    private List<String> skills;
    private Intent intent;

    private final static String INT_REGEX = "\\d";
    private final static String STRING_REGEX = "[A-Za-z]+|([A-Za-z]+\\s[A-Za-z]+)+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        intent = getIntent();
        azienda = (Azienda) intent.getSerializableExtra("INSERT");

        Button bConferma = findViewById(R.id.buttonConferma);
        spinnerDipendenti = findViewById(R.id.spinner);
        eIDProgetto = findViewById(R.id.editID);
        eNome = findViewById(R.id.editNome);
        eCognome = findViewById(R.id.editCognome);

        bConferma.setOnClickListener(v -> {

            if (eIDProgetto.getText().toString().equals("") ||
                    eNome.getText().toString().equals("") ||
                    eCognome.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Riempi tutti i campi", Toast.LENGTH_LONG).show();
            } else {
                tipo = spinnerDipendenti.getSelectedItem().toString();

                IDProgetto = eIDProgetto.getText().toString();
                Pattern pIDProgetto = Pattern.compile(INT_REGEX);
                Matcher mIDProgetto = pIDProgetto.matcher(IDProgetto);
                if (!mIDProgetto.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore ID non valido", Toast.LENGTH_SHORT).show();
                    eIDProgetto.setText("");
                } else
                    IDProgettoInt = Integer.parseInt(IDProgetto);

                nome = eNome.getText().toString();
                Pattern pNome = Pattern.compile(STRING_REGEX);
                Matcher mNome = pNome.matcher(nome);
                if (!mNome.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore nome non valido", Toast.LENGTH_SHORT).show();
                    eNome.setText("");
                }

                cognome = eCognome.getText().toString();
                Pattern pCognome = Pattern.compile(STRING_REGEX);
                Matcher mCognome = pCognome.matcher(cognome);
                if (!mCognome.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore cognome non valido", Toast.LENGTH_SHORT).show();
                    eCognome.setText("");
                }

                if (tipo.equalsIgnoreCase("Manager") && mIDProgetto.matches() && mNome.matches() && mCognome.matches()) {

                    manager = new Manager(123, "" + nome, "" + cognome,2400,0, IDProgettoInt);
                    if (azienda.verificaID(IDProgettoInt)) {
                        azienda.inserisciDipendente(manager);
                        Toast.makeText(getApplicationContext(), "Add Complete", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Project ID", Toast.LENGTH_LONG).show();
                        eIDProgetto.setText("");
                    }
                }

                if (tipo.equalsIgnoreCase("Developer") && mIDProgetto.matches() && mNome.matches() && mCognome.matches()) {
                    skills = new ArrayList<>();
                    developer = new Developer(55, "" + nome, "" + cognome, 1500, IDProgettoInt, skills);
                    if (azienda.verificaID(IDProgettoInt)) {
                        azienda.inserisciDipendente(developer);
                        Toast.makeText(getApplicationContext(), "Add Complete", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Project ID", Toast.LENGTH_LONG).show();
                        eIDProgetto.setText("");
                    }
                }
                if (tipo.equalsIgnoreCase("Inserviente")) {

                    inserviente = new Inserviente(0, "" + nome, "" + cognome, 110, 55);
                    azienda.inserisciDipendente(inserviente);
                    Toast.makeText(getApplicationContext(), "Add Complete", Toast.LENGTH_LONG).show();
                }

                if (azienda.verificaID(IDProgettoInt) && mIDProgetto.matches() && mNome.matches() && mCognome.matches()) {
                    intent.putExtra("result", azienda);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}