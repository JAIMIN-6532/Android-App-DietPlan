package com.example.dietapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealDbService {
    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

}