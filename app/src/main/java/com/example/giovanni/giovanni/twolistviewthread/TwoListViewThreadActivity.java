package com.example.giovanni.giovanni.twolistviewthread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;

public class TwoListViewThreadActivity extends AppCompatActivity {

    private ListView fullList;
    private ListView emptyList;
    private String [] fullArray;
    private String [] emptyArray;
    private ArrayAdapter<String> fullArrayAdapter;
    private ArrayAdapter<String>  emptyArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_listview);

        fullList = findViewById(R.id.list_1);
        emptyList = findViewById(R.id.list_2);

        fullArray = new String [] {"Giovanni", "Raffaele", "Teresa", "Angela", "Vincenzo", "Salvatore", "Ilenia"};
        emptyArray = new String [] {"", "", "", "", "", "", ""};

        fullArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fullArray);
        emptyArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emptyArray);

        showList();

        emptyList.setOnItemClickListener((parent, view, position, id) -> {

            String item = emptyList.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
        });
    }

    public void showList() {

        fullList.setAdapter(fullArrayAdapter);

        new Thread(() -> {
            for(int i=0; i<emptyArray.length; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                emptyArray[i] = fullArray[i];
                TwoListViewThreadActivity.this.runOnUiThread(() ->
                        emptyList.setAdapter(emptyArrayAdapter)
                );
            }
        }).start();
    }
}