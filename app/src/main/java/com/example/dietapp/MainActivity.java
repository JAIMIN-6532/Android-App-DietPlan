package com.example.dietapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Apply window insets padding


        // Write a message to the database (for testing)
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");

        // Check if the user has completed the setup
        SharedPreferences sharedPreferences = getSharedPreferences("DietAppPrefs", MODE_PRIVATE);

        Intent intent;
        if (sharedPreferences.contains("xyz")) {

            intent = new Intent(MainActivity.this, GoalSelectionActivity.class);

        } else {

            intent = new Intent(MainActivity.this, LoginActivity.class);

        }
        startActivity(intent);
        finish();

    }
}
