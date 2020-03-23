package com.example.giovanni.giovanni.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;

import static com.example.giovanni.giovanni.sqlite.DatabaseHelper.CONTACTS_COLUMN_COGNOME;
import static com.example.giovanni.giovanni.sqlite.DatabaseHelper.CONTACTS_COLUMN_NOME;

public class SQLiteDetailActivity extends AppCompatActivity {

    private String nome;
    private String cognome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_detail);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        TextView textDetail = findViewById(R.id.text_detail);

        // Leggo dall'intent il contatto cliccato:
        Intent intent = getIntent();
        String contatto = intent.getStringExtra("SHOW");

        // Tramite una query, ricavo tutti i dati del contatto selezionato.
        Cursor cursor = databaseHelper.getDataByName(contatto);
        if (cursor.moveToFirst()) {
            nome = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_NOME));
            cognome = cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_COGNOME));
        }
        String info = nome + "\n" + cognome;
        textDetail.setText(info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sqlite_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_contact:
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                databaseHelper.deleteContact(nome);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}