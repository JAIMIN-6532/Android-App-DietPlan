package com.example.dietapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText editTextWeight, editTextHeight;
    private Button buttonCalculate;
    private TextView textViewResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator); // Make sure to create activity_bmi_calculator.xml in the layout directory

        // Initialize UI elements
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = editTextWeight.getText().toString().trim();
                String heightStr = editTextHeight.getText().toString().trim();

                if (weightStr.isEmpty() || heightStr.isEmpty()) {
                    Toast.makeText(BMICalculatorActivity.this, "Please enter your weight and height", Toast.LENGTH_SHORT).show();
                } else {
                    float weight = Float.parseFloat(weightStr);
                    float height = Float.parseFloat(heightStr) / 100; // Assuming height is entered in centimeters
                    float bmi = calculateBMI(weight, height);
                    displayBMIResult(bmi);
                }
            }
        });
    }

    private float calculateBMI(float weight, float height) {
        return weight / (height * height);
    }

    private void displayBMIResult(float bmi) {
        String resultText;
        if (bmi < 18.5) {
            resultText = "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            resultText = "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            resultText = "Overweight";
        } else {
            resultText = "Obesity";
        }
        textViewResult.setText(String.format("BMI: %.1f - %s", bmi, resultText));
    }
}