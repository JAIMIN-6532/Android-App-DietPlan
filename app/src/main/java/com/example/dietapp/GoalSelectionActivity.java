//package com.example.dietapp;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ImageButton;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.navigation.NavigationView;
//
//public class GoalSelectionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//    private DrawerLayout drawerLayout;
//    private ImageButton buttonScanner;
//    private ListView dietTypeListView;
//    private String[] dietTypes = {"Vegetarian","Non-Vegetarian"};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_goal_selection);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        buttonScanner = findViewById(R.id.buttonScanner);
//        dietTypeListView = findViewById(R.id.dietTypeListView);
//
//        // Set up ListView with diet types
//        ArrayAdapter<String> dietTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dietTypes);
//        dietTypeListView.setAdapter(dietTypeAdapter);
//
//        // Handle diet type selection
//        dietTypeListView.setOnItemClickListener((parent, view, position, id) -> {
//            String selectedDietType = dietTypes[position];
//            Intent intent = new Intent(GoalSelectionActivity.this, DietDetailsActivity.class);
//            intent.putExtra("dietType", selectedDietType);
//            startActivity(intent);
//        });
//
//        // Handle scanner button click
//        buttonScanner.setOnClickListener(v -> {
//            Intent intent = new Intent(GoalSelectionActivity.this, BarcodeScannerActivity.class);
//            startActivity(intent);
//        });
//    }
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.nav_home) {
//            // Handle Home click
//            Intent intentHome = new Intent(GoalSelectionActivity.this, GoalSelectionActivity.class);
//            startActivity(intentHome);
//        } else if (item.getItemId() == R.id.nav_bmi) {
//            // Handle BMI Calculator click
//            Intent intentBMI = new Intent(GoalSelectionActivity.this, BMICalculatorActivity.class);
//            startActivity(intentBMI);
//        } else if (item.getItemId() == R.id.nav_logout) {
//            // Handle Logout click
//            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.remove("userLoggedIn"); // Clear login status
//            editor.apply();
//
//            // Redirect to Login page
//            Intent intentLogout = new Intent(GoalSelectionActivity.this, LoginActivity.class);
//            intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intentLogout);
//            finish();
//        } else {
//            // Handle other menu item clicks here
//            return false; // Return false for items that are not handled
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//}
//
//

//
//package com.example.dietapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//public class GoalSelectionActivity extends AppCompatActivity {
//
//    private CardView cardDietPlan, cardLogout, cardBMICalculator, cardScanner;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_goal_selection);
//
//        // Initialize the CardViews
//        cardDietPlan = findViewById(R.id.cardDietPlan);
//        cardLogout = findViewById(R.id.cardLogout);
//        cardBMICalculator = findViewById(R.id.cardBMICalculator);
//        cardScanner = findViewById(R.id.cardScanner);
//
//        // Set click listeners for each CardView
//        cardDietPlan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the action for Diet Plan
//                Intent intent = new Intent(GoalSelectionActivity.this, DietDetailsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        cardLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the logout action
//                Intent intent = new Intent(GoalSelectionActivity.this, LoginActivity.class);
//                startActivity(intent);
//                // Code for logout
//            }
//        });
//
//        cardBMICalculator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the BMI Calculator action
//                Intent intent = new Intent(GoalSelectionActivity.this, BMICalculatorActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        cardScanner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the scanner action
//                Intent intent = new Intent(GoalSelectionActivity.this, BarcodeScannerActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}

//
//package com.example.dietapp;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//public class GoalSelectionActivity extends AppCompatActivity {
//
//    private CardView cardDietPlan, cardLogout, cardBMICalculator, cardScanner;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_goal_selection);
//
//        // Initialize the CardViews
//        cardDietPlan = findViewById(R.id.cardDietPlan);
//        cardLogout = findViewById(R.id.cardLogout);
//        cardBMICalculator = findViewById(R.id.cardBMICalculator);
//        cardScanner = findViewById(R.id.cardScanner);
//
//        // Get the number of selected days
//        int selectedDays = getIntent().getIntExtra("selectedDays", 1);
//
//        // Set click listeners for each CardView
//        cardDietPlan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the action for Diet Plan
//                Intent intent = new Intent(GoalSelectionActivity.this, DietDetailsActivity.class);
//                intent.putExtra("selectedDays", selectedDays);
//                startActivity(intent);
//            }
//        });
//
//        cardLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the logout action
//                Intent intent = new Intent(GoalSelectionActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        cardBMICalculator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the BMI Calculator action
//                Intent intent = new Intent(GoalSelectionActivity.this, BMICalculatorActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        cardScanner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the scanner action
//                Intent intent = new Intent(GoalSelectionActivity.this, BarcodeScannerActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}


package com.example.dietapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
                Intent intent = new Intent(GoalSelectionActivity.this, DietDetailsActivity.class);
                intent.putExtra("selectedDays", selectedDays);
                startActivity(intent);
            }
        });

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the logout action
                Intent intent = new Intent(GoalSelectionActivity.this, LoginActivity.class);
//                sharedPreferences.edit().clear().commit();

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove("xyz");
                editor.commit();
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

