package com.example.giovanni.giovanni.firebase.firebasearticoli;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Articolo;
import com.example.giovanni.giovanni.model.Camicia;
import com.example.giovanni.giovanni.model.Libro;
import com.example.giovanni.giovanni.model.Magazzino;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.InternalStorage;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class FirebaseArticoliActivity extends AppCompatActivity implements ITaskDelegate {

    private ProgressDialog dialog;
    private List<Articolo> articoli;
    private Magazzino magazzino;
    private LinearLayout bodyContainer;
    private ITaskDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_articoli);

        bodyContainer = findViewById(R.id.body_container);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        articoli = new ArrayList<>();
        magazzino = new Magazzino();

        delegate = this;
        callRest(delegate);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FirebaseInsertActivity.class);
            startActivityForResult(intent, 500);
        });
    }

    public void callRest(final ITaskDelegate delegate) {

        dialog = new ProgressDialog(FirebaseArticoliActivity.this);
        dialog.setMessage("Loading");
        dialog.show();

        FirebaseRestClient.get("response.json", null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String(responseBody);
                    try {
                        articoli = JSONParse.getArticoli(text);
                        delegate.taskCompletionResult("Benvenuto");

                        addViews(articoli);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompletionResult("Caricamento fallito");
            }
        });
    }

    @Override
    public void taskCompletionResult(String result) {

        dialog.dismiss();
        dialog.cancel();

        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

        magazzino.setArticoli(articoli);
        InternalStorage.writeObject(this, "fileMagazzino", magazzino);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addViews(List<Articolo> articoli) {
        if (articoli == null)
            return;
        if (articoli.size() == 0)
            return;

        bodyContainer.removeAllViews();
        for (final Articolo articolo : articoli) {
            if (articolo == null)
                continue;

            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_articolo, bodyContainer, false);

            ImageView image = view.findViewById(R.id.image_articolo);
            TextView nome = view.findViewById(R.id.nome_articolo);
            TextView prezzo = view.findViewById(R.id.prezzo_articolo);

            nome.setText(articolo.getNome());
            prezzo.setText(String.valueOf(articolo.getPrezzo()));

            if (articolo instanceof Libro)
                image.setImageResource(R.drawable.libro);
            if (articolo instanceof Camicia)
                image.setImageResource(R.drawable.camicia);
            if (articolo instanceof Articolo.Gelato)
                image.setImageResource(R.drawable.gelato);

            bodyContainer.addView(view);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 500) {
            if (resultCode == Activity.RESULT_OK) {
                callRest(delegate);
            }
        }
    }
}