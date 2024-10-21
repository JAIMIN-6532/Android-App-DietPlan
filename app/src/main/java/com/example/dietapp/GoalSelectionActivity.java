
package com.example.dietapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GoalSelectionActivity extends AppCompatActivity {

    private CardView cardDietPlan, cardLogout, cardBMICalculator, cardScanner,cardWaterReminder;
    private SharedPreferences sharedPreferences;
    private int selectedDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_selection);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("DietAppPrefs", Context.MODE_PRIVATE);

        // Retrieve saved selected days from SharedPreferences
        selectedDays = sharedPreferences.getInt("selectedDays", 1);

        // Initialize the CardViews
        cardDietPlan = findViewById(R.id.cardDietPlan);
        cardLogout = findViewById(R.id.cardLogout);
        cardBMICalculator = findViewById(R.id.cardBMICalculator);
        cardScanner = findViewById(R.id.cardScanner);
        cardWaterReminder = findViewById(R.id.cardWater);  // New Water Reminder Card

        // Set click listener for Water Reminder
        cardWaterReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoalSelectionActivity.this, WaterReminderActivity.class);
                startActivity(intent);
            }
        });

        // Set click listeners for each CardView
        cardDietPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action for Diet Plan
                String userKey = sharedPreferences.getString("key", "");

                if (!userKey.isEmpty()) {
                    // Store the selected days in Firebase under the user's key
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference userRef = database.getReference("users").child(userKey);

                    // Assuming selectedDays is already set based on user input
                    userRef.child("selectedDays").setValue(selectedDays);

                    // Proceed to DietDetailsActivity
                    Intent intent = new Intent(GoalSelectionActivity.this, DietDetailsActivity.class);
                    intent.putExtra("selectedDays", selectedDays);
                    startActivity(intent);
                } else {
                    Toast.makeText(GoalSelectionActivity.this, "User key not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();  // Clear all data in SharedPreferences
                editor.apply();

                // Navigate to LoginActivity after logout
                Intent intent = new Intent(GoalSelectionActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardBMICalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the BMI Calculator action
                Intent intent = new Intent(GoalSelectionActivity.this, BMICalculatorActivity.class);
                startActivity(intent);
            }
        });

        cardScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the scanner action
                Intent intent = new Intent(GoalSelectionActivity.this, BarcodeScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}





