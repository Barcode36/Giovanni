package com.example.giovanni.giovanni.threadasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;

public class ThreadAsyncTaskActivity extends AppCompatActivity {

    private ImageView imageThread;
    private TextView textViewCounter;
    private ProgressBar progressBar;
    private int index = 1;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        imageThread = findViewById(R.id.image_thread);
        textViewCounter = findViewById(R.id.textview_counter);
        progressBar = findViewById(R.id.progressbar_thread);
    }

    public void loadPics(View v) {

        int imageId = 0;

        switch (index) {

            case 1:
                imageId = R.drawable.homer;
                index = 2;
                break;
            case 2:
                imageId = R.drawable.bart;
                index = 3;
                break;
            case 3:
                imageId = R.drawable.guitar;
                index = 1;
                break;
        }

        new LoadPicsTask().execute(imageId);
    }

    public void increase(View v) {
        counter++;
        textViewCounter.setText(String.valueOf(counter));
    }

    class LoadPicsTask extends AsyncTask<Integer, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Integer... idImages) {

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), idImages[0]);

            sleep(); // Simulo un ritardo del caricamento delle immagini di 3 secondi.

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) { // Incrementare la progress bar.
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            progressBar.setProgress(0);
            // result è il valore di bitmap restituito dal metodo doInBackground.
            imageThread.setImageBitmap(result);
        }

        private void sleep() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}