package com.example.dietapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DaySelectionActivity extends AppCompatActivity {

    private NumberPicker dayPicker;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_selection);

        dayPicker = findViewById(R.id.dayPicker);
        confirmButton = findViewById(R.id.confirmButton);

        // Set min and max value for NumberPicker
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(30);

        // Load previously selected days from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int savedDays = sharedPreferences.getInt("selectedDays", 1);
        dayPicker.setValue(savedDays); // Set the picker to previously selected days

        confirmButton.setOnClickListener(v -> {
            int selectedDays = dayPicker.getValue();

            // Save the selected days in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("selectedDays", selectedDays);
            editor.apply();

            // Start GoalSelectionActivity
            Intent intent = new Intent(DaySelectionActivity.this, GoalSelectionActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
