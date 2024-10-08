//package com.example.dietapp;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class UserDetailsActivity extends AppCompatActivity {
//
//    private EditText editTextName, editTextAge, editTextWeight, editTextHeight, editTextDietPreferences, editTextFitnessGoals, editTextTargetWeight;
//    private RadioGroup radioGroupGender, radioGroupGoal;
//    private RadioButton selectedGender, selectedGoal;
//    private Button buttonSubmit;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_details);
//
//        // Initialize views
//        editTextName = findViewById(R.id.editTextName);
//        editTextAge = findViewById(R.id.editTextAge);
//        editTextWeight = findViewById(R.id.editTextWeight);
//        editTextHeight = findViewById(R.id.editTextHeight);
//        editTextDietPreferences = findViewById(R.id.editTextDietPreferences);
//        editTextFitnessGoals = findViewById(R.id.editTextFitnessGoals);
//        editTextTargetWeight = findViewById(R.id.editTextTargetWeight);
//        radioGroupGender = findViewById(R.id.radioGroupGender);
//        radioGroupGoal = findViewById(R.id.radioGroupGoal);
//        buttonSubmit = findViewById(R.id.buttonSubmit);
//
//        buttonSubmit.setOnClickListener(v -> {
//            // Get form data
//            String name = editTextName.getText().toString().trim();
//            String age = editTextAge.getText().toString().trim();
//            String weight = editTextWeight.getText().toString().trim();
//            String height = editTextHeight.getText().toString().trim();
//            String dietPreferences = editTextDietPreferences.getText().toString().trim();
//            String fitnessGoals = editTextFitnessGoals.getText().toString().trim();
//            String targetWeight = editTextTargetWeight.getText().toString().trim();
//
//            int selectedGoalId = radioGroupGoal.getCheckedRadioButtonId();
//            selectedGoal = findViewById(selectedGoalId);
//            String goal = selectedGoal != null ? selectedGoal.getText().toString().trim() : "";
//
//            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
//            selectedGender = findViewById(selectedGenderId);
//            String gender = selectedGender != null ? selectedGender.getText().toString().trim() : "";
//
//            // Validation checks
//            if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() || gender.isEmpty() || targetWeight.isEmpty() || goal.isEmpty()) {
//                Toast.makeText(UserDetailsActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
//            } else {
//                // Display success message
//                Toast.makeText(UserDetailsActivity.this, "Details Saved!", Toast.LENGTH_SHORT).show();
//
//                // Save flag in SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences("DietAppPrefs", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("isFirstLogin", false);
//                editor.apply();
//
//                // Navigate to GoalSelectionActivity
//                Intent intent = new Intent(UserDetailsActivity.this, GoalSelectionActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}
package com.example.dietapp;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class UserDetailsActivity extends AppCompatActivity {
//
//    private EditText editTextName, editTextAge, editTextWeight, editTextHeight, editTextDietPreferences, editTextFitnessGoals, editTextTargetWeight;
//    private RadioGroup radioGroupGender, radioGroupGoal;
//    private RadioButton selectedGender, selectedGoal;
//    private Button buttonSubmit;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_details);
//
//        // Initialize views
//        editTextName = findViewById(R.id.editTextName);
//        editTextAge = findViewById(R.id.editTextAge);
//        editTextWeight = findViewById(R.id.editTextWeight);
//        editTextHeight = findViewById(R.id.editTextHeight);
////        editTextDietPreferences = findViewById(R.id.editTextDietPreferences);
////        editTextFitnessGoals = findViewById(R.id.editTextFitnessGoals);
//        editTextTargetWeight = findViewById(R.id.editTextTargetWeight);
//        radioGroupGender = findViewById(R.id.radioGroupGender);
////        radioGroupGoal = findViewById(R.id.radioGroupGoal);
//        buttonSubmit = findViewById(R.id.buttonSubmit);
//
//        buttonSubmit.setOnClickListener(v -> {
//            // Get form data
//            String name = editTextName.getText().toString().trim();
//            String age = editTextAge.getText().toString().trim();
//            String weight = editTextWeight.getText().toString().trim();
//            String height = editTextHeight.getText().toString().trim();
////            String dietPreferences = editTextDietPreferences.getText().toString().trim();
////            String fitnessGoals = editTextFitnessGoals.getText().toString().trim();
//            String targetWeight = editTextTargetWeight.getText().toString().trim();
//
////            int selectedGoalId = radioGroupGoal.getCheckedRadioButtonId();
////            selectedGoal = findViewById(selectedGoalId);
////            String goal = selectedGoal != null ? selectedGoal.getText().toString().trim() : "";
//
//            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
//            selectedGender = findViewById(selectedGenderId);
//            String gender = selectedGender != null ? selectedGender.getText().toString().trim() : "";
//
//            // Validation checks
//            if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() || gender.isEmpty() || targetWeight.isEmpty() ) {
//                Toast.makeText(UserDetailsActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
//            } else {
//                // Display success message
//                Toast.makeText(UserDetailsActivity.this, "Details Saved!", Toast.LENGTH_SHORT).show();
//
//                // Save flag in SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences("DietAppPrefs", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("isFirstLogin", false);
//                editor.apply();
//
//                // Navigate to GoalSelectionActivity with weights
//                Intent intent = new Intent(UserDetailsActivity.this, DaySelectionActivity.class);
//                intent.putExtra("currentWeight", weight);
//                intent.putExtra("targetWeight", targetWeight);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dietapp.Database.Callback.InsertModel;
import com.example.dietapp.Database.DataManager;
import com.example.dietapp.Database.DataModel.UserDetails;

public class UserDetailsActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextWeight, editTextHeight, editTextTargetWeight;
    private RadioGroup radioGroupGender, radioGroupMonthlyPlan;
    private Button buttonSubmit;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextTargetWeight = findViewById(R.id.editTextTargetWeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        radioGroupMonthlyPlan = findViewById(R.id.radioGroupMonthlyPlan);

        // Button click listener to submit user details
        buttonSubmit.setOnClickListener(v -> {
            // Get form data
            String name = editTextName.getText().toString().trim();
            String age = editTextAge.getText().toString().trim();
            String weight = editTextWeight.getText().toString().trim();
            String height = editTextHeight.getText().toString().trim();
            String targetWeight = editTextTargetWeight.getText().toString().trim();
            int selectedId = radioGroupMonthlyPlan.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);

            int days = 30;  // Default to 30 days
            if (radioButton != null) {
                // Convert the selected plan to an integer using if-else
                if (selectedId == R.id.radioButton1Month) {
                    days = 30;
                } else if (selectedId == R.id.radioButton2Months) {
                    days = 45;
                } else if (selectedId == R.id.radioButton3Months) {
                    days = 60;
                }
            }

            // Get the selected gender
            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender != null ? selectedGender.getText().toString().trim() : "";

            // Validation checks for empty fields
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(weight) ||
                    TextUtils.isEmpty(height) || TextUtils.isEmpty(targetWeight) || TextUtils.isEmpty(gender)) {
                Toast.makeText(UserDetailsActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save user details to Firebase and SharedPreferences
            String userkey = getSharedPreferences("DietAppPrefs", MODE_PRIVATE).getString("key", "A1");
            UserDetails userDetails = new UserDetails(name, age, weight, height, gender, targetWeight, userkey, days);
            saveUserDetails(userDetails);
        });
    }

    // Method to save user details in Firebase and store selected days
    private void saveUserDetails(UserDetails userDetails) {
        // Call the static method of DataManager to save user details
        DataManager.insertUserDetails(userDetails, new InsertModel() {
            @Override
            public void onComplete() {
                // Notify the user that details were saved successfully
                Toast.makeText(UserDetailsActivity.this, "Details Saved Successfully", Toast.LENGTH_SHORT).show();

                // Store the selected number of days in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("DietAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("selectedDays", userDetails.getDays());  // Store the number of days
                editor.commit();

                // Navigate to GoalSelectionActivity
                Intent intent = new Intent(UserDetailsActivity.this, GoalSelectionActivity.class);
                startActivity(intent);
                finish();  // Close the current activity
            }

            @Override
            public void onError() {
                Toast.makeText(UserDetailsActivity.this, "Failed to save details", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
