package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;




public class UserDetailsActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextWeight, editTextHeight, editTextDietPreferences, editTextFitnessGoals, editTextTargetWeight;
    private RadioGroup radioGroupGender;
    private RadioButton selectedGender;
    private Button buttonSubmit;
    private RadioGroup radioGroupGoal;
    private RadioButton selectedGoal;
//        private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextDietPreferences = findViewById(R.id.editTextDietPreferences);
        editTextFitnessGoals = findViewById(R.id.editTextFitnessGoals);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        editTextTargetWeight = findViewById(R.id.editTextTargetWeight);
        radioGroupGoal = findViewById(R.id.radioGroupGoal);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // Get form data
                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String weight = editTextWeight.getText().toString().trim();
                String height = editTextHeight.getText().toString().trim();
                String dietPreferences = editTextDietPreferences.getText().toString().trim();
                String fitnessGoals = editTextFitnessGoals.getText().toString().trim();
                String targetWeight = editTextTargetWeight.getText().toString().trim();

                int selectedGoalId = radioGroupGoal.getCheckedRadioButtonId();
                selectedGoal = findViewById(selectedGoalId);
                String goal = selectedGoal != null ? selectedGoal.getText().toString().trim() : "";

                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                selectedGender = findViewById(selectedGenderId);
                String gender = selectedGender != null ? selectedGender.getText().toString().trim() : "";

                // Validation checks
                if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty() || gender.isEmpty() || targetWeight.isEmpty() || goal.isEmpty()) {
                    Toast.makeText(UserDetailsActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Display success message
                    Toast.makeText(UserDetailsActivity.this, "Details Saved!", Toast.LENGTH_SHORT).show();

                    // Navigate to GoalSelectionActivity (double-check this is the right activity)
                    Intent intent = new Intent(UserDetailsActivity.this, GoalSelectionActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("weight", weight);
                    intent.putExtra("height", height);
                    intent.putExtra("gender", gender);
                    intent.putExtra("dietPreferences", dietPreferences);
                    intent.putExtra("fitnessGoals", fitnessGoals);
                    intent.putExtra("goal", goal);
                    intent.putExtra("targetWeight", targetWeight);

                    startActivity(intent);
                }
            }
        });
    }
}