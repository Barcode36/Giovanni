package com.example.giovanni.giovanni.firebase.firebasecommunity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Community;
import com.example.giovanni.giovanni.model.Gruppo;
import com.example.giovanni.giovanni.model.Post;
import com.example.giovanni.giovanni.utils.FirebaseRestClient;
import com.example.giovanni.giovanni.utils.ITaskDelegate;
import com.example.giovanni.giovanni.utils.InternalStorage;
import com.example.giovanni.giovanni.utils.JSONParse;
import com.example.giovanni.giovanni.utils.Utils;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

@SuppressWarnings("deprecation")
public class PostListActivity extends AppCompatActivity implements ITaskDelegate {

    private LinearLayout bodyContainer;
    private ProgressDialog dialog;
    private ITaskDelegate delegate;
    private Community community;
    private List<Post> listapost;
    private Gruppo gruppo;
    private String nomeGruppo;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String urlLastEntry;
    private List<Integer> listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        // Ricevo l'intent da communityActivity e per quel gruppo mi prendo tutti i post e in una
        // recyclerwiew metto tutti i post relativi con autore, titolo e data.

        Intent intent = getIntent();
        nomeGruppo = intent.getStringExtra("NOMEGRUPPO");
        community = (Community) intent.getSerializableExtra("HOMECOMMUNITY");

        if (community != null) {
            InternalStorage.writeObject(this, "fileNomeGruppo", nomeGruppo);
            InternalStorage.writeObject(this, "fileCommunity", community);
        }

        if (community == null) {
            nomeGruppo = (String) InternalStorage.readObject(this, "fileNomeGruppo");
            community = (Community) InternalStorage.readObject(this, "fileCommunity");
        }

        listId = new ArrayList<>();
        for (Gruppo gruppo : community.getGruppi()) {
            listId.add(gruppo.getId());
        }

        gruppo = community.getGroupByName(nomeGruppo);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = preferences.getString("FIREBASE_USERNAME","user");

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("NomeGruppo", nomeGruppo);
        editor.apply();

        TextView textUsername = findViewById(R.id.text_username);
        textUsername.setText(username);

        bodyContainer = findViewById(R.id.body_container);

        delegate = this;

        urlLastEntry = "response/community/" + nomeGruppo + "/lastentry.json";

        // Controllo, SEMPRE APPENA APRO I POST, se ci sono state modifiche.
        restCallLastEntry(urlLastEntry);

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(() ->
                restCallLastEntry(urlLastEntry)
        );
    }

    public void restCallLastEntry(String url) {

        FirebaseRestClient.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String (responseBody);
                    Date date = Utils.formatToDate(text.replace('"',' '));

                    String url = "response/community/" + nomeGruppo + "/playlist.json";
                    restCallPost(url);
                    gruppo.setDate(date); // Aggiorno la data del gruppo.
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                delegate.taskCompletionResult("");
            }
        });
    }

    public void restCallPost(String url) {

        dialog = new ProgressDialog(PostListActivity.this);
        dialog.setMessage("Loading");
        dialog.show();

        FirebaseRestClient.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String text = new String (responseBody);
                    try {
                        listapost = JSONParse.getListaPost(text);
                        gruppo.setListaPost(listapost);

                        delegate.taskCompletionResult("Post caricati");
                    }
                    catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
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
        addViews(listapost);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }

    public void fabAddPost(View view) {
        Intent intent = new Intent(getApplicationContext(), PostAddActivity.class);
        intent.putExtra("POSTLISTCOMMUNITY", community);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addViews(List<Post> listapost) {
        if (listapost == null)
            return;
        if (listapost.size() == 0)
            return;

        bodyContainer.removeAllViews();
        for (final Post post : listapost) {
            if (post == null)
                continue;

            for (int id : listId) {
                if (post.getId() == id) {

                    View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_post, bodyContainer, false);

                    RelativeLayout itemPost = view.findViewById(R.id.item_post);
                    TextView autore = view.findViewById(R.id.text_autore);
                    TextView titolo = view.findViewById(R.id.text_titolo);
                    TextView data = view.findViewById(R.id.text_data);

                    autore.setText(post.getAutore());
                    titolo.setText(post.getTitolo());
                    data.setText(Utils.formatToString1(post.getData()));

                    itemPost.setOnClickListener(v -> {
                        Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                        intent.putExtra("POST", post);
                        startActivity(intent);
                    });

                    bodyContainer.addView(view);
                }
            }
        }
    }
}