package com.example.dietapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
                Toast.makeText(WaterReminderActivity.this, "Reminder set for " + numberOfGlasses + " glasses of water", Toast.LENGTH_SHORT).show();

                // Close the activity after setting the reminder
                finish();
            }
        });
    }

    private void setDailyWaterReminder(int numberOfGlasses) {
        // Set alarm to remind user to drink water

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) {
            Toast.makeText(this, "Failed to access AlarmManager", Toast.LENGTH_SHORT).show();
            return;
        }

        // Intent to trigger the WaterReminderReceiver when the alarm is fired
        Intent intent = new Intent(this, WaterReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the reminder to start at a specific time (e.g., 9 AM every day)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);  // Change this to the time you prefer
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Schedule a repeating alarm for daily reminders
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        // Log or debug message (optional)
        Toast.makeText(this, "Daily water reminder set for " + numberOfGlasses + " glasses", Toast.LENGTH_SHORT).show();
    }
}
