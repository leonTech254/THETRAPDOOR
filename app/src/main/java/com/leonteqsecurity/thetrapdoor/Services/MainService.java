package com.leonteqsecurity.thetrapdoor.Services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.leonteqsecurity.thetrapdoor.R;

public class MainService extends Service {
    private static  int NOTIFICATION_ID = 1;
    private static final String NOTIFICATION_CHANNEL_ID = "TRACK MATE";

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your background logic here
        Log.d("MyService", "Service is running in the backgroundn");

        // Check for an internet connection
        if (isConnectedToInternet()) {
            // Send the file
            SendData();
        }
        notificationcall("START");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Create a handler to run the background logic every 5 minutes
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Add your background logic here
                Log.d("MyService", "Service is running in the background");
                // Check for an internet connection
                if (isConnectedToInternet()) {
                    // Send the file
                    SendData();
                }
                // Schedule the next update in 5 minutes
                handler.postDelayed(this, 5 * 60 * 1000);
                notificationcall("FROM BEHID");
            }
        }, 5 * 60 * 1000); // Run the initial update after 5 minutes

        // Return START_STICKY to make the service sticky
        return START_STICKY;
    }




    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(
                getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmService = (AlarmManager) getApplicationContext()
                .getSystemService(Context.ALARM_SERVICE);

        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);

        super.onTaskRemoved(rootIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    private boolean isConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void SendData()
    {

        System.out.println("hello wotlf");
        Log.d("MyService", "Service is running in the backgroundn");
    }
    public void notificationcall(String source)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create notification channel for Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Usage App Reminder", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("TRACKMATE")
                .setContentText("THIS APPLICATION IS CALLED FROM "+source)
                .setSmallIcon(R.drawable.ic_launcher_background);

        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }


}

