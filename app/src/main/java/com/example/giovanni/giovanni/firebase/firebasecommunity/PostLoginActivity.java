package com.example.giovanni.giovanni.firebase.firebasecommunity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Community;
import com.example.giovanni.giovanni.model.Gruppo;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class PostLoginActivity extends AppCompatActivity implements ITaskDelegate {

    private EditText eUsername;
    private EditText ePassword;
    private String username;
    private String password;
    private Intent intent;
    private ProgressDialog dialog;
    private SharedPreferences preferences;
    private ITaskDelegate delegate;

    private List<Gruppo> gruppi;
    private Community community;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        eUsername = findViewById(R.id.edit_username);
        ePassword = findViewById(R.id.edit_password);
        Button bLogin = findViewById(R.id.button_login);
        delegate = this;

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = preferences.getString("FIREBASE_USERNAME","LOGIN_FAILED");
        Log.i("GIOTAG", "username: " + username);

        if (!username.equals("LOGIN_FAILED")) {
            eUsername.setText(username);
            String url = "response/users/" + username + "/generi.json";
            callRestGeneri(url, true);
        }

        bLogin.setOnClickListener(v -> {
            if (eUsername.getText().toString().equals("") || ePassword.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Riempi tutti i campi", Toast.LENGTH_SHORT).show();
            else {
                username = eUsername.getText().toString();
                password = ePassword.getText().toString();

                String url = "response/users/" + username + "/password.json";
                callRestLogin(url);
            }
        });
    }

    public void callRestLogin(String url) {

        dialog = new ProgressDialog(PostLoginActivity.this);
        dialog.setMessage("Loading");
        dialog.show();

        FirebaseRestClient.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String response = new String(responseBody);
                    String text = response.replace("\"", "");

                    if (text.equals(password)) {

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("FIREBASE_USERNAME", username);
                        editor.apply();

                        String url = "response/users/" + username + "/generi.json";
                        callRestGeneri(url, false);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompletionResult("Caricamento fallito");
            }
        });
    }

    public void callRestGeneri(String url, boolean show) {

        if (show) {
            dialog = new ProgressDialog(PostLoginActivity.this);
            dialog.setMessage("Loading");
            dialog.show();
        }

        FirebaseRestClient.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String response = new String (responseBody);
                    try {
                        gruppi = JSONParse.getListaGeneri(response); // Qui in automatico si setta la data di creazione del gruppo.
                        community = new Community(gruppi);
                        intent = new Intent(getApplicationContext(), PostHomeActivity.class);
                        intent.putExtra("LOGINCOMMUNITY", community);
                        startActivity(intent);
                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                    delegate.taskCompletionResult("Accesso effettuato");
                } else
                    delegate.taskCompletionResult("Accesso negato");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompletionResult("");
            }
        });
    }

    @Override
    public void taskCompletionResult(String result) {
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}