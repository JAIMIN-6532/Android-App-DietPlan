// MealResponse.java
package com.example.dietapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public static class Meal {
        @SerializedName("idMeal")
        private String idMeal;

        @SerializedName("strMeal")
        private String mealName;

        @SerializedName("strMealThumb")
        private String mealImageUrl;

        @SerializedName("strCalories") // Assuming this is the field from the API
        private String calories;

        @SerializedName("strFat") // Assuming this is the field from the API
        private String fat;

        @SerializedName("strProtein") // Assuming this is the field from the API
        private String protein;

        @SerializedName("strCarbohydrates") // Assuming this is the field from the API
        private String carbohydrates;

        // Getters
        public String getIdMeal() {
            return idMeal;
        }

        public String getMealName() {
            return mealName;
        }

        public String getMealImageUrl() {
            return mealImageUrl;
        }

        public String getCalories() {
            return calories;
        }

        public String getFat() {
            return fat;
        }

        public String getProtein() {
            return protein;
        }

        public String getCarbohydrates() {
            return carbohydrates;
        }
    }
}
