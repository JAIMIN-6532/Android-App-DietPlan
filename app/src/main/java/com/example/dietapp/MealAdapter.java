package com.example.dietapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MealAdapter extends ArrayAdapter<Meal> {

    private final Context context;
    private final List<Meal> meals;

    public MealAdapter(Context context, List<Meal> meals) {
        super(context, 0, meals);
        this.context = context;
        this.meals = meals;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_meal, parent, false);
        }

        Meal meal = getItem(position);

        ImageView mealImage = convertView.findViewById(R.id.mealImageView);
        TextView mealName = convertView.findViewById(R.id.mealNameTextView);
        TextView mealDetails = convertView.findViewById(R.id.mealDetailsTextView);

        if (meal != null) {
            mealName.setText(meal.getStrMeal());
            mealDetails.setText(meal.getStrInstructions());

            // Load image using Picasso
            Picasso.get().load(meal.getStrMealThumb()).into(mealImage);
        }

        return convertView;
    }
}
