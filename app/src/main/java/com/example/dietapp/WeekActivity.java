package com.example.dietapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeekActivity extends AppCompatActivity {

    private ExpandableListView dietPlanListView;
    private DietPlanAdapter dietPlanAdapter;
    private List<String> dayList;
    private HashMap<String, HashMap<String, List<MealResponse.Meal>>> mealPlan;
    private MealDbService mealDbService;
    private SharedPreferences sharedPreferences;
    private int weekNumber;
    private ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week); // Create this layout for showing days

        dietPlanListView = findViewById(R.id.dietPlanListView);
        loadingIndicator = findViewById(R.id.loadingIndicator); // ProgressBar reference

        // Get the selected week number
        weekNumber = getIntent().getIntExtra("weekNumber", 1);

        mealPlan = new HashMap<>();
        mealDbService = RetrofitClient.getMealClient().create(MealDbService.class);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("DietAppPrefs", Context.MODE_PRIVATE);

        // Fetch meals for this week
        fetchMealsForWeek();
    }

    private void fetchMealsForWeek() {
        // Show loading indicator
        loadingIndicator.setVisibility(View.VISIBLE);
        dietPlanListView.setVisibility(View.GONE); // Hide the list until data is fetched

        Call<MealResponse> call = mealDbService.getMealsByCategory("Vegetarian");
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MealResponse.Meal> meals = response.body().getMeals();
                    prepareDietPlanForWeek(meals);
                }
                loadingIndicator.setVisibility(View.GONE);
                dietPlanListView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                loadingIndicator.setVisibility(View.GONE);
                dietPlanListView.setVisibility(View.VISIBLE);
                Toast.makeText(WeekActivity.this, "Failed to load meals", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareDietPlanForWeek(List<MealResponse.Meal> meals) {
        // Create 7 days of meals for the selected week
        int startDay = (weekNumber - 1) * 7;
        int mealsPerDay = 3; // Assuming 3 meals per day (breakfast, lunch, dinner)

        dayList = new ArrayList<>();
        mealPlan.clear();

        for (int i = startDay; i < startDay + 7; i++) {
            String day = "Day " + (i + 1);
            dayList.add(day);

            HashMap<String, List<MealResponse.Meal>> mealSections = new HashMap<>();

            List<MealResponse.Meal> breakfast = new ArrayList<>();
            List<MealResponse.Meal> lunch = new ArrayList<>();
            List<MealResponse.Meal> dinner = new ArrayList<>();

            breakfast.add(meals.get((i * mealsPerDay) % meals.size()));
            lunch.add(meals.get((i * mealsPerDay + 1) % meals.size()));
            dinner.add(meals.get((i * mealsPerDay + 2) % meals.size()));

            mealSections.put("Breakfast", breakfast);
            mealSections.put("Lunch", lunch);
            mealSections.put("Dinner", dinner);

            mealPlan.put(day, mealSections);
        }

        dietPlanAdapter = new DietPlanAdapter(this, dayList, mealPlan);
        dietPlanListView.setAdapter(dietPlanAdapter);
    }
}
