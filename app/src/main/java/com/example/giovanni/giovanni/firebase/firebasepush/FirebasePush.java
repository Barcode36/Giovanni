package com.example.giovanni.giovanni.firebase.firebasepush;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.giovanni.giovanni.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebasePush extends Service { // La classe è riconosciuta come service.

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference reference = database.getReferenceFromUrl("https://giovanni-740a0.firebaseio.com/response/users");

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Questo metodo viene invocato quando parte il servizio.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("FIREBASE_PUSH", "START SERVICE");
        return Service.START_STICKY;
    }

    // Questo metodo viene invocato quando termina il servizio.
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FIREBASE_PUSH", "STOP SERVICE");
    }

    // Questo metodo forza l'applicazione a non terminare mai il servizio.
    /*
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent intent = new Intent(getApplicationContext(), this.getClass());
        intent.setPackage(getPackageName());
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 1, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        if (alarmService != null)
            alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000, pendingIntent);

        super.onTaskRemoved(rootIntent);
    }
    */

    @Override
    public void onCreate() {
        super.onCreate();

        ChildEventListener listener = new ChildEventListener() { // Dichiaro un listener di Firebase.

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("FIREBASE_PUSH", "ADD: " + dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i("FIREBASE_PUSH", "CHANGE: " + dataSnapshot.getKey());
                if (dataSnapshot.exists()) {
                    activePushValidation(dataSnapshot.getKey());
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE_PUSH", "REMOVE: " + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i("FIREBASE_PUSH", "MOVED: " + dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        };

        reference.addChildEventListener(listener);
    }

    // Questo metodo instanzia un nuovo intent che indica dove verrà mandata la notifica.
    public void activePushValidation(String listener) {
        Intent intent = new Intent(this, FirebasePushActivity.class);
        sendNotification(intent, "Nuovo post", listener);
    }

    // Metodo che permette di inviare le notifiche push.
    @SuppressWarnings("deprecation")
    public void sendNotification(Intent intent, String title, String body) { // title == Nuovo post

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.audioslave_light_blue);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setAutoCancel(true);
        builder.setSound(uri);
        builder.setSmallIcon(R.mipmap.audioslave_light_blue);
        builder.setLargeIcon(bitmap);
        builder.setShowWhen(true);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager != null)
            manager.notify(0, builder.build());
    }
}