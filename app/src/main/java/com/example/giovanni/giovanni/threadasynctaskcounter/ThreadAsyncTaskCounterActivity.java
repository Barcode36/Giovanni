package com.example.giovanni.giovanni.threadasynctaskcounter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;

public class ThreadAsyncTaskCounterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCounter;
    private AsyncCounter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_asynctask_counter);

        buttonCounter = findViewById(R.id.button_counter);
        buttonCounter.setOnClickListener(this);

        counter = new AsyncCounter();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_counter) {
            if (counter.isCancelled() || counter.getStatus() == AsyncTask.Status.FINISHED)
                counter = new AsyncCounter();
            if (counter.getStatus() == AsyncTask.Status.PENDING)
                counter.execute();
            else
                counter.cancel(true);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncCounter extends AsyncTask<Context, Integer, String> {

        private TextView output;
        private ProgressBar progressBar;

        private AsyncCounter() {
            output = findViewById(R.id.output);
            progressBar = findViewById(R.id.progress_bar);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(Context... contexts) {
            for (int i=0; i<=5; i++) {
                try {
                    publishProgress(i);
                    if (i < 5)
                        Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "Executed";
        }

        @Override
        protected void onProgressUpdate(Integer ... values) {
            super.onProgressUpdate(values);
            if (values != null && values.length > 0) {
                buttonCounter.setText(String.valueOf(values[0]));
                progressBar.setProgress(values[0]);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            String executed = "Executed";
            output.setText(executed);
            buttonCounter.setText(executed);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            String canceled = "Canceled";
            output.setText(canceled);
        }
    }
}