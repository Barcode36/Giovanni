package com.example.giovanni.giovanni.firebase.firebasepush;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giovanni.giovanni.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class FirebasePushActivity extends AppCompatActivity {

    private static final String TAG = "FIREBASE_PUSH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_push);

        Button stopService = findViewById(R.id.stop_service);

        Intent intent = new Intent(this, FirebasePush.class);
        startService(intent);

        stopService.setOnClickListener(v ->
                stopService(new Intent(getApplicationContext(), FirebasePush.class))
        );

        // |-------------------------------------------------------------------|
        // |---------------------- FIREBASE NOTIFICATION ----------------------|
        // |-------------------------------------------------------------------|

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.firebase_notification_channel_id);
            String channelName = getString(R.string.firebase_notification_channel_name);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(
                    new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            );
        }

        // If a notification message is tapped, any data accompanying the notification message is
        // available in the intent extras. In this sample the launcher intent is fired when the
        // notification is tapped, so any accompanying data would be handled here. If you want a
        // different intent fired, set the click_action field of the notification message to the
        // desired intent. The launcher intent is used when no click_action is specified.
        // Handle possible data accompanying notification message.
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.i(TAG, "Intent Key: " + key + ", Intent Value: " + value);
            }
        }

        findViewById(R.id.get_token_button).setOnClickListener(v ->

                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {
                        Log.i(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    String token = task.getResult().getToken();

                    // Log and toast
                    String message = getString(R.string.firebase_token, token);
                    Log.i(TAG, message);
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                })
        );

        // Subscribe topics
        findViewById(R.id.subscribe_topic_button).setOnClickListener(v ->

                FirebaseMessaging.getInstance().subscribeToTopic("topic_name").addOnCompleteListener(task -> {

                    String message = getString(R.string.firebase_topic_subscription_success);
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                    if (!task.isSuccessful()) {
                        message = getString(R.string.firebase_topic_subscription_failed);
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                    }
                    Log.i(TAG, message);
                })
        );

        // FirebaseMessaging.getInstance().unsubscribeFromTopic("topic_name"); // Per sottoscrivere il client dal topic.
    }

    public void runtimeEnableAutoInit() {
        // [START fcm_runtime_enable_auto_init]
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        // [END fcm_runtime_enable_auto_init]
    }

    public void deviceGroupUpstream() {
        // [START fcm_device_group_upstream]
        String to = "a_unique_key"; // the notification key
        AtomicInteger msgId = new AtomicInteger();
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder(to)
                .setMessageId(String.valueOf(msgId.get()))
                .addData("hello", "world")
                .build());
        // [END fcm_device_group_upstream]
    }

    public void deviceGroupUpstream2() {
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        fm.send(new RemoteMessage.Builder("SENDER_ID" + "@fcm.googleapis.com")
                .setMessageId(Integer.toString(123))
                .addData("Hello", "World")
                .build());
    }

    // [START fcm_get_account]
    public String getAccount() {
        // This call requires the Android GET_ACCOUNTS permission
        Account[] accounts = AccountManager.get(this /* activity */).getAccountsByType("com.google");
        if (accounts.length == 0) {
            return null;
        }
        return accounts[0].name;
    }
    // [END fcm_get_account]

    public void getAuthToken() {
        // [START fcm_get_token]
        String accountName = getAccount();

        // Initialize the scope using the client ID you got from the Console.
        final String scope = "audience:server:client_id:" + "1262xxx48712-9qs6n32447mcj9dirtnkyrejt82saa52.apps.googleusercontent.com";

        String idToken = null;
        try {
            // idToken = GoogleAuthUtil.getToken(this, accountName, scope);
        } catch (Exception e) {
            Log.i(TAG, "Exception while getting idToken: " + e);
        }
        // [END fcm_get_token]
    }

    // [START fcm_add_to_group]
    public String addToGroup(String senderId, String userEmail, String registrationId, String idToken) throws IOException, JSONException {

        URL url = new URL("https://fcm.googleapis.com/fcm/googlenotification");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);

        // HTTP request header
        con.setRequestProperty("project_id", senderId);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");
        con.connect();

        // HTTP request
        JSONObject data = new JSONObject();
        data.put("operation", "add");
        data.put("notification_key_name", userEmail);
        data.put("registration_ids", new JSONArray(Arrays.asList(registrationId)));
        data.put("id_token", idToken);

        OutputStream os = con.getOutputStream();
        os.write(data.toString().getBytes("UTF-8"));
        os.close();

        // Read the response into a string
        InputStream is = con.getInputStream();
        String responseString = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
        is.close();

        // Parse the JSON string and return the notification key
        JSONObject response = new JSONObject(responseString);
        return response.getString("notification_key");
    }
    // [END fcm_add_to_group]

    public void removeFromGroup(String userEmail, String registrationId, String idToken) throws JSONException {
        // [START fcm_remove_from_group]
        // HTTP request
        JSONObject data = new JSONObject();
        data.put("operation", "remove");
        data.put("notification_key_name", userEmail);
        data.put("registration_ids", new JSONArray(Arrays.asList(registrationId)));
        data.put("id_token", idToken);
        // [END fcm_remove_from_group]
    }

    public void sendUpstream() {
        final String SENDER_ID = "YOUR_SENDER_ID";
        final int messageId = 0; // Increment for each
        // [START fcm_send_upstream]
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        fm.send(new RemoteMessage.Builder(SENDER_ID + "@fcm.googleapis.com")
                .setMessageId(Integer.toString(messageId))
                .addData("my_message", "Hello World")
                .addData("my_action","SAY_HELLO")
                .build());
        // [END fcm_send_upstream]
    }

    @Override
    protected void onResume() {
        super.onResume();

        // GoogleApiAvailability.makeGooglePlayServicesAvailable();
    }
}