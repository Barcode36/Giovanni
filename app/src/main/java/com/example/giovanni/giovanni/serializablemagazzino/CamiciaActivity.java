package com.example.giovanni.giovanni.serializablemagazzino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Camicia;
import com.example.giovanni.giovanni.pojo.Magazzino;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamiciaActivity extends AppCompatActivity {

    private Button buttonAggiungi;
    private EditText eID;
    private EditText eNome;
    private EditText ePrezzo;
    private EditText eTaglia;
    private EditText eColore;
    private String ID;
    private int idInt;
    private String nome;
    private String prezzo;
    private double prezzoDouble;
    private String taglia;
    private int tagliaInt;
    private String colore;
    private ImageView imageView;

    private Intent intent;
    private Camicia camicia;
    private Magazzino magazzino;

    private final static String COLORE_REGEX = "[A-Za-z]+|([A-Za-z]+\\s[A-Za-z]+)+";
    private final static String NOME_REGEX = "[a-zA-Z_0-9]+|(\\w+\\s\\w+)+";
    private final static String INT_REGEX = "\\d{2}";
    private final static String PREZZO_REGEX = "\\d+|(\\d+.\\d+)"; // Il punto serve a rappresentare i numeri decimali.

    /*
    [0-9]:           espressione regolare che individua un numero intero.                                Corrisponde a \d
    [0-9]+:          regex che individua uno o più numeri interi.                                        Corrisponde a \d+
    [0-9]{5}:        regex che individua tutti i numeri interi da 0 a 9 composti esattamente da 5 cifre. Corrisponde a \d{5}
    [0-9]{5, 10}:    regex che individua tutti i mumeri interi da 0 a 9 composti da almeno 5 cifre fino ad un massimo di 10 cifre.
    [0-9]{5,}:       regex che individua tutti i numeri interi da 0 a 9 composti da almeno 5 cifre.
    [a-z]:           regex che individua una lettera minuscola.                                          Corrisponde a \p{Lower}
    [a-z]+:          regex che individua una o più lettere minuscole.
    [a-z]{5}:        regex che individua 5 lettere minuscole.
    [A-Z]:           regex che individua una lettera maiuscola.                                          Corrisponde a \p{Upper}
    [A-Z]+:          regex che individua una o più lettere maiuscole.
    [A-Z]{5}:        regex che individua 5 lettere maiuscole.
    [A-Za-z]:        regex che individua una lettera sia minuscola che maiuscola.
    [A-Za-z]+:       regex che individua una o più lettere sia minuscole che maiuscole.
    [A-Za-záéíóú]+:  regex che individua una o più lettere sia minuscole che maiuscole, comprese le 5 lettere accentate minuscole.
    [A-Za-z]{5}:     regex che individua 5 lettere sia minuscole che maiuscole.
    [a-zA-Z_0-9]:    regex che individua il carattere alfanumerico (word).                               Corrisponde a \w
    [a-zA-Z_0-9]+:   regex che individua uno o più caratteri alfanumerici.                               Corrisponde a \w+
    [a-zA-Z_0-9]{5}: regex che individua 5 caratteri word.                                               Corrisponde a \w{5}
    \s:              regex che individua un carattere vuoto.
    [a-zA-Z_0-9]+\s[a-zA-Z_0-9]+: regex che individua una parola word, uno spazio vuoto, e un'altra parola word. Corrisponde a \w+\s\w+
    (\w+\s\w+)+:     regex che individua una parola word, uno spazio vuoto, e un'altra parola word infinite volte.
    |: OR logico, indica che posso utilizzare o la regex a sinistra di | o la regex a destra di |.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camicia);

        intent = getIntent();
        magazzino = (Magazzino) intent.getSerializableExtra("CAMICIA");

        buttonAggiungi = findViewById(R.id.buttonAggiungi);
        eID = findViewById(R.id.editID);
        eNome = findViewById(R.id.editNome);
        ePrezzo = findViewById(R.id.editPrezzo);
        eTaglia = findViewById(R.id.editTaglia);
        eColore = findViewById(R.id.editColore);
        imageView = findViewById(R.id.imageCamicia);

        buttonAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eID.getText().toString().equals("") ||
                        eNome.getText().toString().equals("") ||
                        ePrezzo.getText().toString().equals("") ||
                        eTaglia.getText().toString().equals("") ||
                        eColore.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Riempi tutti i campi", Toast.LENGTH_SHORT).show();
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

                    taglia = eTaglia.getText().toString();
                    Pattern pTaglia = Pattern.compile(INT_REGEX);
                    Matcher mTaglia = pTaglia.matcher(taglia);
                    if (!mTaglia.matches()) {
                        Toast.makeText(getApplicationContext(), "Valore taglia non valido", Toast.LENGTH_SHORT).show();
                        eTaglia.setText("");
                    } else
                        tagliaInt = Integer.parseInt(taglia);

                    colore = eColore.getText().toString();
                    Pattern pColore = Pattern.compile(COLORE_REGEX);
                    Matcher mColore = pColore.matcher(colore);
                    if (!mColore.matches()) {
                        Toast.makeText(getApplicationContext(), "Valore colore non valido", Toast.LENGTH_SHORT).show();
                        eColore.setText("");
                    }

                    if(mID.matches() && mNome.matches() && mPrezzo.matches() && mTaglia.matches() && mColore.matches()) {

                        camicia = new Camicia(idInt, nome, prezzoDouble, tagliaInt, colore);
                        magazzino.addArticoli(camicia);

                        intent = new Intent(getApplicationContext(), StoreActivity.class);
                        intent.putExtra("ARTICOLO", camicia);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}