package com.example.dietapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class WaterReminderActivity extends AppCompatActivity {

    private NumberPicker numberPicker;
    private Button setReminderButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_reminder);

        numberPicker = findViewById(R.id.numberPicker);
        setReminderButton = findViewById(R.id.setReminderButton);

        // Set number picker range (e.g., 1 to 12 glasses)
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(12);
        numberPicker.setValue(8); // Default value

        setReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfGlasses = numberPicker.getValue();
                setDailyWaterReminder(numberOfGlasses);

                // Display a success message with the number of glasses
//                Toast.makeText(WaterReminderActivity.this, "Reminder set for " + numberOfGlasses + " glasses of water", Toast.LENGTH_SHORT).show();

                // Close the activity after setting the reminder
                finish();
            }
        });
    }

//    private void setDailyWaterReminder(int numberOfGlasses) {
//        // Set alarm to remind user to drink water
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        if (alarmManager == null) {
//            Toast.makeText(this, "Failed to access AlarmManager", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Intent to trigger the WaterReminderReceiver when the alarm is fired
//        Intent intent = new Intent( WaterReminderActivity.this, WaterReminderReceiver.class);
//        Toast.makeText(this, "Daily water reminder set for 555555555555" + numberOfGlasses + " glasses", Toast.LENGTH_SHORT).show();
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(WaterReminderActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        Toast.makeText(this, "Daily water reminder set for 666666666" + numberOfGlasses + " glasses", Toast.LENGTH_SHORT).show();
//
//        // Set the reminder to start at a specific time (e.g., 9 AM every day)
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 18);  // Change this to the time you prefer
//        calendar.set(Calendar.MINUTE, 47);
//        calendar.set(Calendar.SECOND, 0);
//        Toast.makeText(this, "Daily water reminder set for 7777777777777" + numberOfGlasses + " glasses", Toast.LENGTH_SHORT).show();
//
//        // Schedule a repeating alarm for daily reminders
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//
//        // Log or debug message (optional)
//        Toast.makeText(this, "Daily water reminder set for " + numberOfGlasses + " glasses", Toast.LENGTH_SHORT).show();
//    }
private void setDailyWaterReminder(int numberOfGlasses) {
    // Get AlarmManager system service
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    if (alarmManager == null) {
        Toast.makeText(this, "Failed to access AlarmManager", Toast.LENGTH_SHORT).show();
        return;
    }

    // Intent to trigger the WaterReminderReceiver
    Intent intent = new Intent(WaterReminderActivity.this, WaterReminderReceiver.class);
    intent.putExtra("numberOfGlasses", numberOfGlasses);  // Passing data to receiver

    // Create the PendingIntent with appropriate flags
//    Toast.makeText(this, "Daily water reminder set for 555555555555" + numberOfGlasses + " glasses", Toast.LENGTH_SHORT).show();

    PendingIntent pendingIntent;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        pendingIntent = PendingIntent.getBroadcast(WaterReminderActivity.this, 0, intent, PendingIntent.FLAG_MUTABLE);
    } else {
        pendingIntent = PendingIntent.getBroadcast(WaterReminderActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
//    Toast.makeText(this, "Daily water reminder set for 6666666666666666" + numberOfGlasses + " glasses", Toast.LENGTH_SHORT).show();
//
    // Set the reminder to start at a specific time (e.g., 9 AM every day)
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 19);  // Set the desired hour
    calendar.set(Calendar.MINUTE, 4);
    calendar.set(Calendar.SECOND, 0);

    // Ensure the time is in the future
    if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
        calendar.add(Calendar.DAY_OF_YEAR, 1);  // Set to the next day if the time has passed today
    }

    // Schedule the repeating alarm
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    // Toast or log to confirm the alarm is set
    Toast.makeText(this, "Daily water reminder set for " + numberOfGlasses + " glasses at " + calendar.getTime(), Toast.LENGTH_SHORT).show();
 }

}
