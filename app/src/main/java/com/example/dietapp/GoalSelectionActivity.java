package com.example.dietapp;

import static com.example.dietapp.R.*;
import com.example.dietapp.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class GoalSelectionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button buttonRecipes, buttonExercises;
    private DrawerLayout drawerLayout;
    private ImageButton buttonScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_selection);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Button initialization
        buttonRecipes = findViewById(R.id.buttonRecipes);
        buttonExercises = findViewById(R.id.buttonExercises);
        buttonScanner = findViewById(R.id.buttonScanner);

        // Button click listeners
        buttonRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(GoalSelectionActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        buttonExercises.setOnClickListener(v -> {
            Intent intent = new Intent(GoalSelectionActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Handle scanner button click
        buttonScanner.setOnClickListener(v -> {
            Intent intent = new Intent(GoalSelectionActivity.this, BarcodeScannerActivity.class);
            startActivity(intent);
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            // Handle Home click
            Intent intentHome = new Intent(GoalSelectionActivity.this, GoalSelectionActivity.class);
            startActivity(intentHome);
        } else if (item.getItemId() == R.id.nav_bmi) {
            // Handle BMI Calculator click
            Intent intentBMI = new Intent(GoalSelectionActivity.this, BMICalculatorActivity.class);
            startActivity(intentBMI);
        } else {
            // Handle other menu item clicks here
            return false; // Return false for items that are not handled
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}