package com.example.giovanni.giovanni.firebase.firebasepush;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.giovanni.giovanni.MainActivity;
import com.example.giovanni.giovanni.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebasePushService extends FirebaseMessagingService {

    private static final String TAG = "FIREBASE_PUSH";

    @Override
    public void onCreate() {
        super.onCreate();

        // sendNotification("Questa è una local notification");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // There are two types of messages, data messages and notification messages. Data messages are
        // handled here in onMessageReceived whether the app is in the foreground or background. Data
        // messages are the type traditionally used with GCM. Notification messages are only received
        // here in onMessageReceived when the app is in the foreground. When the app is in the background
        // an automatically generated notification is displayed. When the user taps on the notification
        // they are returned to the app. Messages containing both notification and data payloads are
        // treated as notification messages. The Firebase console always sends notification messages.

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (true) { // Check if data needs to be processed by long running job.
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


     // Called if InstanceID token is updated. This may occur if the security of the previous token
     // had been compromised. Note that this is called when the InstanceID token is initially
     // generated so this is where you would retrieve the token.
    @Override
    public void onNewToken(@NonNull String token) {

        Log.i(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or manage this apps subscriptions
        // on the server side, send the Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    // Schedule async work using WorkManager.
    @SuppressWarnings("deprecation")
    private void scheduleJob() {
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(PushWorker.class).build();
        WorkManager.getInstance().beginWith(work).enqueue();
    }

    // Handle time allotted to BroadcastReceivers.
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.firebase_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Bitmap logo = new BitmapFactory().decodeResource(getResources(), R.mipmap.audioslave_light_blue);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                // .setContentTitle(getString(R.string.app_name))
                .setContentTitle("Firebase local notification")
                .setContentText(messageBody)
                .setSmallIcon(R.mipmap.audioslave_light_blue)
                .setLargeIcon(logo)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(logo))
                .setPriority(NotificationCompat.PRIORITY_MAX) // Set the intent that will fire when the user taps the notification
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }
}