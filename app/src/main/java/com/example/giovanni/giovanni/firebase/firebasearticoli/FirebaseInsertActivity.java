package com.example.giovanni.giovanni.firebase.firebasearticoli;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Articolo;
import com.example.giovanni.giovanni.model.Camicia;
import com.example.giovanni.giovanni.model.Libro;
import com.example.giovanni.giovanni.model.Magazzino;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.InternalStorage;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class FirebaseInsertActivity extends AppCompatActivity implements ITaskDelegate {

    private Spinner tipoProdotto;
    private String valoreSpinner;
    private EditText eNomeProdotto;
    private EditText ePrezzoProdotto;
    private String nome;
    private String prezzo;
    private Magazzino magazzino;
    private List<Articolo> articoli;
    private ITaskDelegate delegate;
    private ProgressDialog dialog;
    private DatabaseReference reference;
    private LinearLayout bodyContainer;

    private final static String NOME_REGEX = "[A-Za-z]+|([A-Za-z]+\\s[A-Za-z]+)+";
    private final static String PREZZO_REGEX = "\\d+|(\\d+.\\d+)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_insert);

        eNomeProdotto = findViewById(R.id.edit_nome_prodotto);
        ePrezzoProdotto = findViewById(R.id.edit_prezzo_prodotto);
        tipoProdotto = findViewById(R.id.spinner);
        Button bInserisci = findViewById(R.id.button_inserisci);
        bodyContainer = findViewById(R.id.body_container);

        nome = "";
        prezzo = "";

        magazzino = (Magazzino) InternalStorage.readObject(this, "fileMagazzino");
        articoli = magazzino.getArticoli();
        addViews(articoli);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReferenceFromUrl("https://giovanni-740a0.firebaseio.com/response/articoli");
        delegate = this;

        // Per creare un nuovo articolo e inserirlo su Firebase:
        bInserisci.setOnClickListener(v -> {

            valoreSpinner = tipoProdotto.getSelectedItem().toString().toLowerCase();

            if (eNomeProdotto.getText().toString().equals("") || ePrezzoProdotto.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Riempi tutti i campi", Toast.LENGTH_SHORT).show();
            else {
                nome = eNomeProdotto.getText().toString();
                Pattern pMarca = Pattern.compile(NOME_REGEX);
                Matcher mMarca = pMarca.matcher(nome);
                if (!mMarca.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore nome non valido", Toast.LENGTH_SHORT).show();
                    eNomeProdotto.setText("");
                }

                prezzo = ePrezzoProdotto.getText().toString();
                Pattern pPrezzo = Pattern.compile(PREZZO_REGEX);
                Matcher mPrezzo = pPrezzo.matcher(prezzo);
                if (!mPrezzo.matches()) {
                    Toast.makeText(getApplicationContext(), "Valore prezzo non valido", Toast.LENGTH_SHORT).show();
                    ePrezzoProdotto.setText("");
                }

                if (mMarca.matches() && mPrezzo.matches()) {

                    if (valoreSpinner.equalsIgnoreCase("Libro")) {
                        Libro libro = new Libro(nome, Double.parseDouble(prezzo));
                        articoli.add(libro);
                    }
                    else if (valoreSpinner.equalsIgnoreCase("Camicia")) {
                        Camicia camicia = new Camicia(nome, Double.parseDouble(prezzo));
                        articoli.add(camicia);
                    }
                    else if (valoreSpinner.equalsIgnoreCase("Gelato")) {
                        Articolo.Gelato gelato = new Articolo.Gelato(nome, Double.parseDouble(prezzo));
                        articoli.add(gelato);
                    }
                    magazzino.setArticoli(articoli);
                    restCallAddArticolo(delegate);
                    Toast.makeText(getApplicationContext(), "Articolo inserito", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void restCallAddArticolo(final ITaskDelegate delegate) {

        dialog = new ProgressDialog(FirebaseInsertActivity.this);
        dialog.setMessage("Caricamento");
        dialog.show();

        FirebaseRestClient.get("response/articoli/" + valoreSpinner + ".json", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String(responseBody);
                    int index = JSONParse.getKey(text);
                    Log.i("PARSETAG", "" + reference.child(valoreSpinner).child(generaKey(index)).child("nome").setValue(nome));
                    reference.child(valoreSpinner).child(generaKey(index)).child("nome").setValue(nome);
                    reference.child(valoreSpinner).child(generaKey(index)).child("prezzo").setValue(prezzo);
                    delegate.taskCompletionResult("Articolo Registrato");

                    Intent intent = getIntent();
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompletionResult("NO!");
            }
        });
    }

    @Override
    public void taskCompletionResult(String result) {
        dialog.dismiss();
        dialog.cancel();
        InternalStorage.writeObject(this,"fileMagazzino", magazzino);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }

    public String generaKey(int index) {
        String key;
        if (index < 10) key = "0" + index;
        else key = "" + index;
        return key;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addViews(List<Articolo> prodotti) {
        if (prodotti == null)
            return;
        if (prodotti.size() == 0)
            return;

        bodyContainer.removeAllViews();
        for (final Articolo prodotto : prodotti) {
            if (prodotto == null)
                continue;

            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_articolo, bodyContainer, false);

            ImageView image = view.findViewById(R.id.image_articolo);
            TextView nome = view.findViewById(R.id.nome_articolo);
            TextView prezzo = view.findViewById(R.id.prezzo_articolo);

            nome.setText(prodotto.getNome());
            prezzo.setText(String.valueOf(prodotto.getPrezzo()));

            if (prodotto instanceof Libro)
                image.setImageResource(R.drawable.libro);
            if (prodotto instanceof Camicia)
                image.setImageResource(R.drawable.camicia);
            if (prodotto instanceof Articolo.Gelato)
                image.setImageResource(R.drawable.gelato);

            bodyContainer.addView(view);
        }
    }
}