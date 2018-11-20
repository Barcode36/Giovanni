package com.example.giovanni.giovanni.twolistviewonebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;

public class TwoListViewOneButtonActivity extends AppCompatActivity {

    private ListView lista1;
    private ListView lista2;
    private String [] array1;
    private String [] array2;
    private ArrayAdapter<String> arrayAdapter1;
    private ArrayAdapter<String> arrayAdapter2;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2listview_1button);

        Button button = findViewById(R.id.one_button);
        lista1 = findViewById(R.id.list_1);
        lista2 = findViewById(R.id.list_2);
        i = 0;

        array1 = new String [] {"Giovanni", "Raffaele", "Teresa", "Angela", "Vincenzo",
                "Francesco", "Mariano", "Daniele", "Gianluigi", "Pasquale"};
        arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array1);
        lista1.setAdapter(arrayAdapter1);

        array2 = new String [] {"", "", "", "", "", "", "", "", "", ""};
        arrayAdapter2 = new ArrayAdapter<>(this, R.layout.list_item, R.id.textItem, array2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringa;
                if (i < array1.length) {
                    stringa = lista1.getItemAtPosition(i).toString();
                    array2[i] = stringa;
                    array1[i] = "";
                    arrayAdapter2 = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, R.id.textItem, array2);

                    Toast.makeText(getApplicationContext(), "Hai spostato " + stringa, Toast.LENGTH_SHORT).show();

                    lista2.setAdapter(arrayAdapter2);

                    arrayAdapter2.notifyDataSetChanged();
                    arrayAdapter1.notifyDataSetChanged();
                    i++;
                } else
                    Toast.makeText(getApplicationContext(), "Non hai più nessuno da spostare ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}