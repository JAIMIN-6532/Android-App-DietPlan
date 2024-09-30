package com.example.dietapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import android.app.PendingIntent;
import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import android.os.Build;
public class WaterReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Retrieve the number of glasses from the intent extras
        int numberOfGlasses = intent.getIntExtra("numberOfGlasses", 0);

        // Create an intent to open the app when the notification is tapped
        Intent activityIntent = new Intent(context, GoalSelectionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "waterReminderChannel")
                .setSmallIcon(R.drawable.water)
                .setContentTitle("Water Reminder")
                .setContentText("Time to drink water! You've set a goal of " + numberOfGlasses + " glasses today.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Create notification channel for Android 8.0+ (Oreo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "waterReminderChannel", "Water Reminder", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        // Display the notification
        notificationManager.notify(1, builder.build());
    }
}
