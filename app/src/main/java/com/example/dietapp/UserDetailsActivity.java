package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dietapp.GoalSelectionActivity;

import java.util.Arrays;
import java.util.List;

public class UserDetailsActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextWeight, editTextHeight;
    private Spinner spinnerDietPreferences, spinnerFitnessGoals;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);


        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        buttonSubmit = findViewById(R.id.buttonSubmit);


        spinnerDietPreferences = findViewById(R.id.spinnerDietPreferences);
        spinnerFitnessGoals = findViewById(R.id.spinnerFitnessGoals);


        List<String> dietPreferencesList = Arrays.asList("Vegetarian", "Any Diet");
        ArrayAdapter<String> dietAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dietPreferencesList);
        dietAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDietPreferences.setAdapter(dietAdapter);


        List<String> fitnessGoalsList = Arrays.asList("Weight Loss", "Weight Gain", "General Fitness");
        ArrayAdapter<String> fitnessGoalsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fitnessGoalsList);
        fitnessGoalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFitnessGoals.setAdapter(fitnessGoalsAdapter);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String weight = editTextWeight.getText().toString().trim();
                String height = editTextHeight.getText().toString().trim();
                String dietPreferences = spinnerDietPreferences.getSelectedItem().toString().trim();
                String fitnessGoals = spinnerFitnessGoals.getSelectedItem().toString().trim();


                if (name.isEmpty() || age.isEmpty() || weight.isEmpty() || height.isEmpty()) {
                    Toast.makeText(UserDetailsActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(UserDetailsActivity.this, "Details Saved!", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(UserDetailsActivity.this, GoalSelectionActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("weight", weight);
                    intent.putExtra("height", height);
                    intent.putExtra("dietPreferences", dietPreferences);
                    intent.putExtra("fitnessGoals", fitnessGoals);

                    startActivity(intent);
                }
            }
        });
    }
}
