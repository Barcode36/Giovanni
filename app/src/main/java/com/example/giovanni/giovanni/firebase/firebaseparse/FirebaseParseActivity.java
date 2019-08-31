package com.example.giovanni.giovanni.firebase.firebaseparse;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

public class FirebaseParseActivity extends AppCompatActivity implements ITaskDelegate {

    private List<Persona> persone;
    private ProgressDialog dialog;
    private LinearLayout bodyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_parse);

        bodyContainer = findViewById(R.id.body_container);
        persone = new ArrayList<>();

        ITaskDelegate delegate = this; // Associo il contesto attuale all'oggetto interfaccia.
        callRest(delegate);
    }

    public void callRest(final ITaskDelegate delegate) {

        dialog = new ProgressDialog(FirebaseParseActivity.this);
        dialog.setMessage("Loading");
        dialog.show();

        FirebaseRestClient.get("response/.json", null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    String text = new String(responseBody);
                    try {
                        persone = JSONParse.getList(text);
                        addViews(persone);
                        delegate.taskCompletionResult("Caricamento completato");
                    }
                    catch(JSONException ex) {
                        ex.printStackTrace();
                    }
                    Log.i("REST", "text: " + text);
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
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
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
            TextView cognome = view.findViewById(R.id.text_cognome);
            TextView matricola = view.findViewById(R.id.text_matricola);

            nome.setText(persona.getNome());
            cognome.setText(persona.getCognome());
            matricola.setText(String.valueOf(persona.getMatricola()));

            nome.setOnClickListener(v ->
                    Toast.makeText(getApplicationContext(), persona.getNome() + " " + persona.getCognome(), Toast.LENGTH_SHORT).show()
            );
            bodyContainer.addView(view);
        }
    }
}