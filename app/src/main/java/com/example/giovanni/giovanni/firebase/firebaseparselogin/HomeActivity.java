package com.example.giovanni.giovanni.firebase.firebaseparselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Persona;
import com.example.giovanni.giovanni.bean.PersonaManager;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.InternalStorage;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class HomeActivity extends AppCompatActivity implements ITaskDelegate {

    private List<Persona> utenti;
    private PersonaManager manager;
    private SharedPreferences preferences;
    private Intent intent;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        utenti = new ArrayList<>();
        manager = new PersonaManager();

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ITaskDelegate delegate = this;
        callRestUtenti(delegate);

        TextView tOutput = findViewById(R.id.textOutput);
        Button bUtenti = findViewById(R.id.buttonUtenti);
        Button bLogout = findViewById(R.id.buttonLogout);
        Button bSignout = findViewById(R.id.buttonSignOut);

        String username = preferences.getString("USERNAME", "");
        String password = preferences.getString("PASSWORD", "");
        tOutput.setText("Benvenuto " + username);

        bUtenti.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), UtentiActivity.class);
            startActivity(intent);
        });

        bLogout.setOnClickListener(v -> {

            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            intent = new Intent(getApplicationContext(), FirebaseParseLoginActivity.class);
            startActivity(intent);
        });

        bSignout.setOnClickListener(v -> {

            intent = new Intent(getApplicationContext(), SignoutActivity.class);
            startActivity(intent);
        });
    }

    public void callRestUtenti(final ITaskDelegate delegate) {

        dialog = new ProgressDialog(HomeActivity.this);
        dialog.setMessage("Loading");
        dialog.show();

        FirebaseRestClient.get("response/.json", null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String(responseBody);
                    try {
                        utenti = JSONParse.getUtenti(text);
                        delegate.taskCompletionResult("Caricamento utenti completato");
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

        manager.setUtenti(utenti);
        InternalStorage.writeObject(this, "fileUtenti", manager);
    }
}