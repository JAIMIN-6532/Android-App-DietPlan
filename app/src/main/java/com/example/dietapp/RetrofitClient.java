package com.example.dietapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit mealRetrofit = null;
    private static Retrofit exerciseRetrofit = null;
    private static final String MEAL_BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String EXERCISE_BASE_URL = "https://api.example.com/exercises/"; // Replace with actual exercise API base URL

    public static Retrofit getMealClient() {
        if (mealRetrofit == null) {
            mealRetrofit = new Retrofit.Builder()
                    .baseUrl(MEAL_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mealRetrofit;
    }

    public static Retrofit getExerciseClient() {
        if (exerciseRetrofit == null) {
            exerciseRetrofit = new Retrofit.Builder()
                    .baseUrl(EXERCISE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return exerciseRetrofit;
    }
}

