package com.example.giovanni.giovanni.firebase.firebaseparselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Persona;
import com.example.giovanni.giovanni.model.PersonaManager;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONException;
import java.util.List;
import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class FirebaseParseLoginActivity extends AppCompatActivity implements ITaskDelegate {

    private List<Persona> utenti;
    private PersonaManager manager;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_parse_login);

        manager = new PersonaManager();
        ITaskDelegate delegate = this;
        callRestUtenti(delegate);
    }

    public void callRestUtenti(final ITaskDelegate delegate) {

        dialog = new ProgressDialog(FirebaseParseLoginActivity.this);
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

        Intent intent = new Intent(getApplicationContext(), LoginSignupActivity.class);
        intent.putExtra("MANAGER", manager);
        startActivity(intent);
    }
}