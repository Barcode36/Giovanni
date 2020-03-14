package com.example.giovanni.giovanni.serializablemagazzino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Libro;
import com.example.giovanni.giovanni.model.Magazzino;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibroActivity extends AppCompatActivity {

    private EditText eID;
    private EditText eNome;
    private EditText ePrezzo;
    private EditText eAutore;
    private String ID;
    private int idInt;
    private String nome;
    private String prezzo;
    private double prezzoDouble;
    private String autore;

    private Intent intent;
    private Libro libro;
    private Magazzino magazzino;

    private final static String AUTORE_REGEX = "[A-Za-z]+|([A-Za-z]+\\s[A-Za-z]+)+";
    private final static String NOME_REGEX = "[a-zA-Z_0-9]+|(\\w+\\s\\w+)+";
    private final static String INT_REGEX = "\\d{2}";
    private final static String PREZZO_REGEX = "\\d+|(\\d+.\\d+)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);

        intent = getIntent();
        magazzino = (Magazzino) intent.getSerializableExtra("LIBRO");

        Button bAggiungi = findViewById(R.id.buttonAggiungi);
        eID = findViewById(R.id.editID);
        eNome = findViewById(R.id.editNome);
        ePrezzo = findViewById(R.id.editPrezzo);
        eAutore = findViewById(R.id.editAutore);

        bAggiungi.setOnClickListener(v -> {

            if (eID.getText().toString().equals("") ||
                    eNome.getText().toString().equals("") ||
                    ePrezzo.getText().toString().equals("") ||
                    eAutore.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Riempi tutti i campi", Toast.LENGTH_LONG).show();
            } else {
                ID = eID.getText().toString();
                Pattern pID = Pattern.compile(INT_REGEX);
                Matcher mID = pID.matcher(ID);
                if (!mID.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore ID non valido", Toast.LENGTH_SHORT).show();
                    eID.setText("");
                } else
                    idInt = Integer.parseInt(ID);

                nome = eNome.getText().toString();
                Pattern pNome = Pattern.compile(NOME_REGEX);
                Matcher mNome = pNome.matcher(nome);
                if (!mNome.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore nome non valido", Toast.LENGTH_SHORT).show();
                    eNome.setText("");
                }

                prezzo = ePrezzo.getText().toString();
                Pattern pPrezzo = Pattern.compile(PREZZO_REGEX);
                Matcher mPrezzo = pPrezzo.matcher(prezzo);
                if (!mPrezzo.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore prezzo non valido", Toast.LENGTH_SHORT).show();
                    ePrezzo.setText("");
                } else
                    prezzoDouble = Double.parseDouble(prezzo);

                autore = eAutore.getText().toString();
                Pattern pAutore = Pattern.compile(AUTORE_REGEX);
                Matcher mAutore = pAutore.matcher(autore);
                if (!mAutore.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore autore non valido", Toast.LENGTH_SHORT).show();
                    eAutore.setText("");
                }

                if (mID.matches() && mNome.matches() && mPrezzo.matches() && mAutore.matches()) {

                    libro = new Libro(idInt, nome, prezzoDouble, autore);
                    magazzino.addArticoli(libro);

                    intent = new Intent(getApplicationContext(), StoreActivity.class);
                    intent.putExtra("ARTICOLO", libro);
                    startActivity(intent);
                }
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.out_left_to_right, R.anim.out_right_to_left);
    }
}
