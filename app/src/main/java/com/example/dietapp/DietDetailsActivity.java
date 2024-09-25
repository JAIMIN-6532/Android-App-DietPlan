package com.example.dietapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietDetailsActivity extends AppCompatActivity {

    private ExpandableListView dietPlanListView;
    private DietPlanAdapter dietPlanAdapter;
    private List<String> dayList;
    private HashMap<String, HashMap<String, List<MealResponse.Meal>>> mealPlan;
    private MealDbService mealDbService;
    private NumberPicker dayPicker;
    private Button showDietPlanButton;
    private int selectedDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_details);

        dietPlanListView = findViewById(R.id.dietPlanListView);
        dayPicker = findViewById(R.id.dayPicker);
        showDietPlanButton = findViewById(R.id.showDietPlanButton);
        dayList = new ArrayList<>();
        mealPlan = new HashMap<>();
        mealDbService = RetrofitClient.getMealClient().create(MealDbService.class);

        // Set min and max value for NumberPicker
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(30);

        // Set default selected days
        selectedDays = 1;

        // Listen for changes in the dayPicker
        dayPicker.setOnValueChangedListener((picker, oldVal, newVal) -> selectedDays = newVal);

        // Set button click listener
        showDietPlanButton.setOnClickListener(v -> fetchMeals());
    }

    private void fetchMeals() {
        Call<MealResponse> call = mealDbService.getMealsByCategory("Vegetarian");
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MealResponse.Meal> meals = response.body().getMeals();
                    prepareDietPlan(meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Toast.makeText(DietDetailsActivity.this, "Failed to load meals", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareDietPlan(List<MealResponse.Meal> meals) {
        // Assuming 3 meals per day (breakfast, lunch, dinner)
        int mealsPerDay = 3;

        dayList.clear();
        mealPlan.clear();

        for (int i = 0; i < selectedDays; i++) {
            String day = "Day " + (i + 1);
            dayList.add(day);

            HashMap<String, List<MealResponse.Meal>> mealSections = new HashMap<>();

            List<MealResponse.Meal> breakfast = new ArrayList<>();
            List<MealResponse.Meal> lunch = new ArrayList<>();
            List<MealResponse.Meal> dinner = new ArrayList<>();

            // Divide meals into breakfast, lunch, and dinner
            breakfast.add(meals.get((i * mealsPerDay) % meals.size())); // Get the meal for breakfast
            lunch.add(meals.get((i * mealsPerDay + 1) % meals.size())); // Get the meal for lunch
            dinner.add(meals.get((i * mealsPerDay + 2) % meals.size())); // Get the meal for dinner

            mealSections.put("Breakfast", breakfast);
            mealSections.put("Lunch", lunch);
            mealSections.put("Dinner", dinner);

            mealPlan.put(day, mealSections); // Add to meal plan
        }

        dietPlanAdapter = new DietPlanAdapter(this, dayList, mealPlan);
        dietPlanListView.setAdapter(dietPlanAdapter);

        // Make the ExpandableListView visible after meals are fetched
        dietPlanListView.setVisibility(View.VISIBLE);
    }
}