package com.example.giovanni.giovanni.highscore;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        // SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        // SharedPreferences preferences = getSharedPreferences("filename", MODE_PRIVATE);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        TextView tHighScore1 = findViewById(R.id.textHighScore1);
        TextView tHighScore2 = findViewById(R.id.textHighScore2);
        TextView tHighScore3 = findViewById(R.id.textHighScore3);

        int highScore1 = preferences.getInt("HIGHSCORE1", 0);
        int highScore2 = preferences.getInt("HIGHSCORE2", 0);
        int highScore3 = preferences.getInt("HIGHSCORE3", 0);

        String player1 = preferences.getString("PLAYER1", "");
        String player2 = preferences.getString("PLAYER2", "");
        String player3 = preferences.getString("PLAYER3", "");

        String result = "Highscore " + highScore1 + " by " + player1;
        tHighScore1.setText(result);

        result = "Highscore " + highScore2 + " by " + player2;
        tHighScore2.setText(result);

        result = "Highscore " + highScore3 + " by " + player3;
        tHighScore3.setText(result);
    }
}