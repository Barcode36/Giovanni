package com.example.giovanni.giovanni.firebase.firebaseparselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class SignoutActivity extends AppCompatActivity implements ITaskDelegate {

    private EditText ePwd;
    private String password;
    private Intent intent;
    private SharedPreferences preferences;
    private DatabaseReference databaseReference;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signout);

        intent = getIntent();
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReferenceFromUrl("https://giovanni-740a0.firebaseio.com/response");

        TextView tUser = findViewById(R.id.textUser);
        ePwd = findViewById(R.id.editPwd);
        Button bConferma = findViewById(R.id.buttonConferma);

        String username = preferences.getString("USERNAME", "");
        password = preferences.getString("PASSWORD", "");
        tUser.setText(username);

        bConferma.setOnClickListener(view -> {

            if (ePwd.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Inserisci la password", Toast.LENGTH_SHORT).show();
            else {
                if (ePwd.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "Password corretta", Toast.LENGTH_SHORT).show();

                    // callRestEliminaUtente(delegate); Da decommentare solo quando l'app non crasha.
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password errata", Toast.LENGTH_SHORT).show();
                    ePwd.setText("");
                }
            }
        });
    }

    public void callRestEliminaUtente(final ITaskDelegate delegate) {

        dialog = new ProgressDialog(SignoutActivity.this);
        dialog.setMessage("Caricamento");
        dialog.show();

        FirebaseRestClient.get("response/utenti.json", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200) {
                    String text = new String(responseBody);
                    String[] array = text.split("\\{|\"|:|password|,|username|\\}");

                    List<String> lista = new ArrayList<>();
                    for (int i=0; i<array.length; i++) {
                        if (!array[i].equals(""))
                            lista.add(array[i]);
                    }
                    Object[] array2 = lista.toArray();

                    String indice = null;
                    for (int i=0; i<array2.length; i++) {
                        if (array2[i].equals(password))
                            indice = (String) array2[i - 1];
                    }

                    databaseReference.child("utenti").child(indice).child("password").removeValue();
                    databaseReference.child("utenti").child(indice).child("username").removeValue();

                    delegate.taskCompletionResult("Cancellazione avvenuta con successo");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompletionResult("Cancellazione fallita");
            }
        });
    }

    @Override
    public void taskCompletionResult(String result) {

        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        intent = new Intent(getApplicationContext(), FirebaseParseLoginActivity.class);
        startActivity(intent);
    }
}