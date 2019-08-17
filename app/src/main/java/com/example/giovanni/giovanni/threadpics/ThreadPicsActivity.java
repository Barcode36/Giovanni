package com.example.giovanni.giovanni.threadpics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;

public class ThreadPicsActivity extends AppCompatActivity {

    private ImageView imageThread;
    private TextView textViewCounter;
    private Bitmap bitmap;
    private int index = 1;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pics);

        imageThread = findViewById(R.id.image_thread);
        textViewCounter = findViewById(R.id.textview_counter);
    }

    public void loadImages(View v) {

        new Thread(() -> {

            // Ritardo di 3 secondi
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            bitmap = null;
            switch (index) {

                case 1:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.homer);
                    index = 2;
                    break;
                case 2:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bart);
                    index = 3;
                    break;
                case 3:
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guitar);
                    index = 1;
                    break;
            }

            // Per interagire con l'interfaccia grafica nel main thread.
            this.runOnUiThread(() ->
                    imageThread.setImageBitmap(bitmap)
            );

        }).start();
    }

    public void increase(View v) {
        counter++;
        textViewCounter.setText(String.valueOf(counter));
    }
}