package com.example.giovanni.giovanni.firebase.firebasecommunity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.bean.Community;
import com.example.giovanni.giovanni.bean.Gruppo;

import java.util.List;

public class PostHomeActivity extends AppCompatActivity {

    private Community community;
    List<Gruppo> gruppi;
    private SharedPreferences preferences;
    private LinearLayout bodyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_home);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = preferences.getString("FIREBASE_USERNAME","user");

        TextView tUsername = findViewById(R.id.text_username);
        tUsername.setText(username);

        bodyContainer = findViewById(R.id.body_container);

        Button buttonLogout = findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = getIntent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        Intent intent = getIntent();
        community = (Community) intent.getSerializableExtra("LOGINCOMMUNITY");

        if (community != null) {
            gruppi = community.getGruppi();
            addViews(gruppi);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void addViews(List<Gruppo> gruppi) {
        if (gruppi == null)
            return;
        if (gruppi.size() == 0)
            return;

        bodyContainer.removeAllViews();
        for (final Gruppo gruppo : gruppi) {
            if (gruppo == null)
                continue;

            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_button, bodyContainer, false);

            Button buttonPlaylist = view.findViewById(R.id.button_playlist);

            buttonPlaylist.setText(gruppo.getNome());

            buttonPlaylist.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), PostListActivity.class);
                intent.putExtra("NOMEGRUPPO", gruppo.getNome());
                intent.putExtra("HOMECOMMUNITY", community);
                startActivity(intent);
            });

            bodyContainer.addView(view);
        }
    }
}