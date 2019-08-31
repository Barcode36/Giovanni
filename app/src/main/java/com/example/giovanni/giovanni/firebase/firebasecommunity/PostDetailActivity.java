package com.example.giovanni.giovanni.firebase.firebasecommunity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.model.Post;
import com.example.giovanni.giovanni.utils.Utils;

import java.util.Date;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        TextView textAutore = findViewById(R.id.text_autore);
        TextView textTitolo = findViewById(R.id.text_titolo);
        TextView textData = findViewById(R.id.text_data);
        TextView textUtente = findViewById(R.id.text_utente);

        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("POST");

        String autore = post.getAutore();
        String titolo = post.getTitolo();
        Date newEntry = post.getData();

        textAutore.setText(autore);
        textTitolo.setText(titolo);
        textData.setText(Utils.formatToString1(newEntry));
        textUtente.setText(post.getUtente());
    }
}