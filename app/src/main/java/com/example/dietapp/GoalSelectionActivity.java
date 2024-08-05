package com.example.dietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GoalSelectionActivity extends AppCompatActivity {

    private Button buttonRecipes, buttonExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_selection);

        buttonRecipes = findViewById(R.id.buttonRecipes);
        buttonExercises = findViewById(R.id.buttonExercises);

        buttonRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoalSelectionActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoalSelectionActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}