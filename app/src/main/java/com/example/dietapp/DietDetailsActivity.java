//package com.example.dietapp;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ListView;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import java.util.List;
//
//public class DietDetailsActivity extends AppCompatActivity {
//
//    private ListView mealListView;
//    private String dietType;
//
//    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_diet_details);
//
//        mealListView = findViewById(R.id.mealListView);
//
//        Intent intent = getIntent();
//        if (intent != null && intent.hasExtra("dietType")) {
//            dietType = intent.getStringExtra("dietType");
//            fetchMealsForDiet(dietType);
//        }
//    }
//
//    private void fetchMealsForDiet(String dietType) {
//        MealDbService mealDbService = RetrofitClient.getMealClient().create(MealDbService.class);
//        Call<MealResponse> call = mealDbService.getMealsByCategory(dietType);
//
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<Meal> meals = response.body().getMeals();
//                    displayMeals(meals);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                // Handle failure
//            }
//        });
//    }
//
//    private void displayMeals(List<Meal> meals) {
//        MealAdapter mealAdapter = new MealAdapter(this, meals);
//        mealListView.setAdapter(mealAdapter);
//    }
//}


// DietDetailsActivity.java
package com.example.dietapp;

import android.os.Bundle;
import android.widget.ExpandableListView;
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
    private HashMap<String, List<MealResponse.Meal>> mealPlan;

    private MealDbService mealDbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_details);

        dietPlanListView = findViewById(R.id.dietPlanListView);
        dayList = new ArrayList<>();
        mealPlan = new HashMap<>();

        mealDbService = RetrofitClient.getMealClient().create(MealDbService.class);

        // Fetch vegetarian meals from API
        fetchMeals();
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
        int totalDays = 10;

        for (int i = 0; i < totalDays; i++) {
            dayList.add("Day " + (i + 1));
            List<MealResponse.Meal> dayMeals = new ArrayList<>();
            for (int j = i * mealsPerDay; j < (i * mealsPerDay) + mealsPerDay; j++) {
                dayMeals.add(meals.get(j % meals.size())); // Reuse meals if fewer than required
            }
            mealPlan.put(dayList.get(i), dayMeals);
        }

        dietPlanAdapter = new DietPlanAdapter(this, dayList, mealPlan);
        dietPlanListView.setAdapter(dietPlanAdapter);
    }
}
