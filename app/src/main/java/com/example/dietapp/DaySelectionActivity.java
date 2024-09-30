//package com.example.dietapp;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.NumberPicker;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.dietapp.GoalSelectionActivity;
//import com.example.dietapp.R;
//
//public class DaySelectionActivity extends AppCompatActivity {
//
//    private NumberPicker dayPicker;
//    private Button selectDaysButton;
//    private int selectedDays;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_day_selection);
//
//        dayPicker = findViewById(R.id.dayPicker);
//        selectDaysButton = findViewById(R.id.selectDaysButton);
//
//        // Set min and max value for NumberPicker
//        dayPicker.setMinValue(1);
//        dayPicker.setMaxValue(30);
//        selectedDays = 1;
//
//        // Listen for changes in the dayPicker
//        dayPicker.setOnValueChangedListener((picker, oldVal, newVal) -> selectedDays = newVal);
//
//        // Handle click on select days button
//        selectDaysButton.setOnClickListener(v -> {
//            // Pass selected days to GoalSelectionActivity
//            Intent intent = new Intent(DaySelectionActivity.this, GoalSelectionActivity.class);
//            intent.putExtra("selectedDays", selectedDays);
//            startActivity(intent);
//            finish();
//        });
//    }
//}

package com.example.dietapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dietapp.Database.Callback.FindByModel;
import com.example.dietapp.Database.DataManager;
import com.example.dietapp.Database.DataModel.User;
import com.example.dietapp.Database.DataModel.UserDetails;

public class DaySelectionActivity extends AppCompatActivity {

    private NumberPicker dayPicker;
    private Button selectDaysButton;
    private int selectedDays;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_selection);

        dayPicker = findViewById(R.id.dayPicker);
        selectDaysButton = findViewById(R.id.selectDaysButton);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("DietAppPrefs", Context.MODE_PRIVATE);

        // Set min and max value for NumberPicker
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(30);

        // Load saved selected days from SharedPreferences
        selectedDays = sharedPreferences.getInt("selectedDays", 1);  // Default to 1 day if no value is saved
        dayPicker.setValue(selectedDays);

        // Listen for changes in the dayPicker
        dayPicker.setOnValueChangedListener((picker, oldVal, newVal) -> selectedDays = newVal);

        // Handle click on select days button
        selectDaysButton.setOnClickListener(v -> {
            // Save selected days in SharedPreferences
            String userkey = getSharedPreferences("DietAppPrefs",MODE_PRIVATE).getString("key","A1");
//            DataManager.FindByUserkey(userkey, new FindByModel() {
//                @Override
//                public void onSuccess(Object model) {
//                    User user = (User)model;
//
//                }
//
//                @Override
//                public void onFailure() {
//
//                }
//            });

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("selectedDays", selectedDays);
            editor.apply();

           // Pass selected days to GoalSelectionActivity
            Intent intent = new Intent(DaySelectionActivity.this, GoalSelectionActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

