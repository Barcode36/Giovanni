package com.example.giovanni.giovanni.settext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giovanni.giovanni.R;

public class SetTextActivity extends AppCompatActivity {

    private TextView tHello;
    private TextView tComeStai;
    private EditText eNome;
    private String nome;

    private EditText eNumero;
    private TextView tNumero;
    private String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settext);

        Button bInvioNome = findViewById(R.id.button_invio_nome);
        tHello = findViewById(R.id.text_hello);
        tComeStai = findViewById(R.id.text_come_stai);
        eNome = findViewById(R.id.edit_nome);

        TextView longText = findViewById(R.id.long_text);
        String longMessage = getResources().getString(R.string.long_text);
        longText.setText(longMessage);

        Button bInvioNumero = findViewById(R.id.button_invio_numero);
        eNumero = findViewById(R.id.edit_numero);
        tNumero = findViewById(R.id.text_numero);

        tHello.setText(R.string.hello_world);
        // Il metodo setAllCaps() rende maiuscolo il contenuto di una TextView.
        tHello.setAllCaps(true);
        // Il metodo setTextSize() setta la grandezza del testo programmaticamente.
        tHello.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);

        bInvioNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = eNome.getText().toString();
                if(!nome.equals("")) {
                    String hello = "CIAO " + nome.toUpperCase() + "!";
                    String howAreYou = "come stai?";
                    tHello.setText(hello);
                    tComeStai.setText(howAreYou);
                    eNome.setText("");
                }
            }
        });

        bInvioNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = eNumero.getText().toString();
                tNumero.setText(formatCount(numero));
            }
        });
    }

    private String formatCount(String string) {
        char[] array = string.toCharArray();
        String formatted = "";
        int index = 0;
        if(array.length > 0) {
            for(int i = array.length - 1; i >= 0; i --) {
                char c = array[i];
                formatted = c + formatted;
                index ++;
                if(index == 3) {
                    index = 0;
                    formatted = "." + formatted;
                }
            }
            if(formatted.startsWith(".")) {
                formatted = formatted.substring(1);
            }
        }
        return formatted;
    }
}