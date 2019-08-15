package com.example.giovanni.giovanni.firebaseparselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.PersonaManager;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class LoginSignupActivity extends AppCompatActivity implements ITaskDelegate {

    private EditText eUsername;
    private EditText ePassword;
    private String username;
    private String password;
    private String signedUpUser;
    private boolean control;
    private PersonaManager manager;
    private SharedPreferences preferences;
    private Intent intent;
    private DatabaseReference reference;
    private ITaskDelegate delegate;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        intent = getIntent();
        manager = (PersonaManager) intent.getSerializableExtra("MANAGER");

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReferenceFromUrl("https://giovanni-740a0.firebaseio.com/response");
        delegate = this;

        Button bLogin = findViewById(R.id.buttonLogin);
        Button bSignUp = findViewById(R.id.buttonSignUp);
        eUsername = findViewById(R.id.editUsername);
        ePassword = findViewById(R.id.editPassword);

        username = preferences.getString("USERNAME", manager.returnUsername(username));
        signedUpUser = preferences.getString("SIGNEDUPUSER", "");
        eUsername.setText(username);
        ePassword.setText(password);

        if (eUsername.getText().toString().equals(manager.returnUsername(username)) || !signedUpUser.equals("")) {

            intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
        else {
            eUsername.setText("");
            bLogin.setOnClickListener(view -> {

                if (eUsername.getText().toString().equals("") || ePassword.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Compila tutti i campi", Toast.LENGTH_SHORT).show();
                else {
                    username = eUsername.getText().toString();
                    password = ePassword.getText().toString();
                    control = manager.checkLogin(username, password);

                    if (control) {
                        Toast.makeText(getApplicationContext(), "Benvenuto " + username, Toast.LENGTH_SHORT).show();

                        saveStateToPreferences();

                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        eUsername.setText("");
                        ePassword.setText("");
                        Toast.makeText(getApplicationContext(), "Accesso negato", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            bSignUp.setOnClickListener(view -> {

                if (eUsername.getText().toString().equals("") || ePassword.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Compila tutti i campi", Toast.LENGTH_SHORT).show();
                else {
                    username = eUsername.getText().toString();
                    password = ePassword.getText().toString();
                    signedUpUser = username;
                    control = manager.verificaUsername(username);

                    if (control) {
                        String message = "Spiacente, username già esistente";
                        Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_SHORT).show();
                        eUsername.setText("");
                        ePassword.setText("");
                    }
                    else {
                        // Col metodo saveStateToPreferences() salvo con la chiave "USERNAME" il nome inserito nell'editText che poi
                        // riprendo in HomeActivity.
                        saveStateToPreferences();

                        callRestUtente(delegate);

                        Toast.makeText(getApplicationContext(), "Benvenuto " + username, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void saveStateToPreferences() {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("USERNAME", eUsername.getText().toString());
        editor.putString("PASSWORD", ePassword.getText().toString());
        editor.putString("SIGNEDUPUSER", signedUpUser);
        editor.apply();
    }

    public void callRestUtente(final ITaskDelegate delegate) {

        dialog = new ProgressDialog(LoginSignupActivity.this);
        dialog.setMessage("Caricamento");
        dialog.show();

        FirebaseRestClient.get("response/utenti.json", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    String text = new String(responseBody);
                    int index = JSONParse.getKey(text);

                    reference.child("utenti").child(generaKey(index)).child("password").setValue(password);
                    reference.child("utenti").child(generaKey(index)).child("username").setValue(username);

                    delegate.taskCompletionResult("Utente registrato");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompletionResult("Utente non registrato");
            }
        });
    }

    public String generaKey(int index) {
        String key;
        if (index < 10) key = "0" + index;
        else key = "" + index;
        return key;
    }

    @Override
    public void taskCompletionResult(String result) {

        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

        intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}