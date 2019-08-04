package com.example.giovanni.giovanni.highscore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;

public class HighScoreActivity extends AppCompatActivity {

    private TextView tHighScore;
    private EditText eName;
    private TextView tCounter;

    private int counter;
    private int highScore1;
    private int highScore2;
    private int highScore3;
    private String recordman1;
    private String recordman2;
    private String recordman3;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        tHighScore = findViewById(R.id.textHighScore);
        tCounter = findViewById(R.id.textCounter);
        eName = findViewById(R.id.editName);

        // tHighScore.setText("HighScore");

        highScore1 = preferences.getInt("HIGHSCORE1", 0);
        highScore2 = preferences.getInt("HIGHSCORE2", 0);
        highScore3 = preferences.getInt("HIGHSCORE3", 0);

        recordman1 = preferences.getString("RECORDMAN1", "");
        recordman2 = preferences.getString("RECORDMAN2", "");
        recordman3 = preferences.getString("RECORDMAN3", "");

        counter = preferences.getInt("SCORE", 0);
        tCounter.setText(String.valueOf(counter));

        eName.setText(preferences.getString("NAME",""));

        // Il metodo seguente setta la variabile counter a 0 e la textView tCounter alla stringa vuota
        // non appena cambia il valore della editText eName.
        eName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // You can call or do what you want with your EditText here.
                counter = 0;
                tCounter.setText(String.valueOf(counter));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private boolean checkName() {
        if(eName.getText().toString().length() != 0) return true;
        else Toast.makeText(getApplicationContext(), "Inserisci il nome.", Toast.LENGTH_SHORT).show();
        return false;
    }

    // Il metodo seguente è associato al button "+".
    public void buttonIncrease(View v) {
        if (checkName()) {
            counter++;
            tCounter.setText(String.valueOf(counter));

            if(counter > highScore1) {
                String result = eName.getText().toString() + " Updated Highscore: " + counter;
                tHighScore.setText(result);
            }
        }
    }

    // Il metodo seguente è associato al button "-".
    public void buttonDecrease(View v) {
        if (checkName()) {
            counter--;
            tCounter.setText(String.valueOf(counter));
        }
    }

    // Il metodo seguente è associato al button "SHOW RECORD".
    public void launchHighScoreActivity(View v) {
        saveStateToPreferences();
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), RankingActivity.class);
        startActivity(intent);
    }

    private void saveStateToPreferences() {

        SharedPreferences.Editor editor = preferences.edit();

        if (counter > highScore1 || (counter == highScore1 && !eName.getText().toString().equals(recordman1))) {
            highScore3 = highScore2;
            recordman3 = recordman2;
            highScore2 = highScore1;
            recordman2 = recordman1;
            highScore1 = counter;
            recordman1 = eName.getText().toString();
        }
        else if (counter > highScore2 && counter < highScore1 ||
                (counter == highScore2 && !eName.getText().toString().equals(recordman2))) {
            highScore3 = highScore2;
            recordman3 = recordman2;
            highScore2 = counter;
            recordman2 = eName.getText().toString();
        }
        else if (counter > highScore3 && counter < highScore2 && counter < highScore1 ||
                (counter == highScore3 && !eName.getText().toString().equals(recordman3))) {
            highScore3 = counter;
            recordman3 = eName.getText().toString();
        }

        editor.putString("PLAYER1", recordman1);
        editor.putString("PLAYER2", recordman2);
        editor.putString("PLAYER3", recordman3);
        editor.putInt("HIGHSCORE1", highScore1);
        editor.putInt("HIGHSCORE2", highScore2);
        editor.putInt("HIGHSCORE3", highScore3);
        editor.putString("NAME", eName.getText().toString());
        editor.putInt("SCORE", counter);
        editor.apply();
    }
}
