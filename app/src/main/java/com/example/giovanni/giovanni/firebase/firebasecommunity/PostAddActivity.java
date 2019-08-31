package com.example.giovanni.giovanni.firebase.firebasecommunity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Community;
import com.example.giovanni.giovanni.model.Gruppo;
import com.example.giovanni.giovanni.model.Post;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.example.giovanni.giovanni.utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.Date;

import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class PostAddActivity extends AppCompatActivity implements ITaskDelegate {

    private EditText editTitolo;
    private EditText editAutore;
    private String username;
    private String nomeGruppo;
    private Post newPost;
    private ProgressDialog dialog;
    private ITaskDelegate delegate;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceDate;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_add);

        delegate = this;

        TextView textUsername = findViewById(R.id.text_username);
        editAutore = findViewById(R.id.edit_autore);
        editTitolo = findViewById(R.id.edit_titolo);
        Button buttonInsert = findViewById(R.id.button_insert);

        Intent intent = getIntent();
        Community community = (Community) intent.getSerializableExtra("POSTLISTCOMMUNITY");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username = preferences.getString("FIREBASE_USERNAME","user");
        nomeGruppo = preferences.getString("NomeGruppo","");
        textUsername.setText(username);

        for (Gruppo gruppo : community.getGruppi()) {
            if (nomeGruppo.equalsIgnoreCase(gruppo.getNome())) {
                id = gruppo.getId();
                break;
            }
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String url1 = "https://giovanni-740a0.firebaseio.com/response/community/" + nomeGruppo + "/playlist/";
        String url2 = "https://giovanni-740a0.firebaseio.com/response/community/" + nomeGruppo +"/lastentry";
        databaseReference = database.getReferenceFromUrl(url1);
        databaseReferenceDate = database.getReferenceFromUrl(url2);

        buttonInsert.setOnClickListener(v -> {

            String autore = editAutore.getText().toString();
            String titolo = editTitolo.getText().toString();

            if (titolo.equalsIgnoreCase("") || autore.equalsIgnoreCase(""))
                Toast.makeText(getApplicationContext(),"Inserire tutti i campi", Toast.LENGTH_LONG).show();
            else {
                newPost = new Post(autore, id, new Date(), titolo, username);
                String url = "response/community/" + nomeGruppo + "/playlist.json";
                restCallAddPost(url);
            }
        });
    }

    public void restCallAddPost(String url) {
        dialog = new ProgressDialog(PostAddActivity.this);
        dialog.setMessage("Caricamento");
        dialog.show();
        FirebaseRestClient.get(url, null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String response = new String (responseBody);
                    int index = JSONParse.getKey(response);
                    newPost.setNodo(generaKey(index));
                    String newEntry = Utils.formatToString2(newPost.getData());
                    databaseReference.child(generaKey(index)).child("autore").setValue(newPost.getAutore());
                    databaseReference.child(generaKey(index)).child("id").setValue(newPost.getId());
                    databaseReference.child(generaKey(index)).child("newentry").setValue(newEntry);
                    databaseReference.child(generaKey(index)).child("titolo").setValue(newPost.getTitolo());
                    databaseReference.child(generaKey(index)).child("utente").setValue(username);
                    databaseReferenceDate.setValue(newEntry);
                    delegate.taskCompletionResult("Post aggiunto");

                    Intent intent = new Intent(getApplicationContext(), PostListActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {}
        });
    }

    public String generaKey(int index) {
        return "post" + index;
    }

    @Override
    public void taskCompletionResult(String result) {
        dialog.dismiss();
        dialog.cancel();
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}