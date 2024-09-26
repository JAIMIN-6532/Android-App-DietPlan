package com.example.dietapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietPlanAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> dayList;
    private HashMap<String, HashMap<String, List<MealResponse.Meal>>> mealPlan;

    public DietPlanAdapter(Context context, List<String> dayList, HashMap<String, HashMap<String, List<MealResponse.Meal>>> mealPlan) {
        this.context = context;
        this.dayList = dayList;
        this.mealPlan = mealPlan;
    }

    @Override
    public int getGroupCount() {
        return dayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mealPlan.get(dayList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String day = dayList.get(groupPosition);
        List<String> mealTypes = new ArrayList<>(mealPlan.get(day).keySet());
        return mealTypes.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String day = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }
        TextView dayTextView = (TextView) convertView.findViewById(android.R.id.text1);
        dayTextView.setText(day);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String mealType = (String) getChild(groupPosition, childPosition);
        String day = dayList.get(groupPosition);
        List<MealResponse.Meal> meals = mealPlan.get(day).get(mealType);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_meal, null);
        }

        TextView mealTypeTextView = convertView.findViewById(R.id.mealTypeTextView);
        TextView mealDetailsTextView = convertView.findViewById(R.id.mealDetailsTextView);
        TextView nutritionInfoTextView = convertView.findViewById(R.id.nutritionInfoTextView);
        ImageView mealImageView = convertView.findViewById(R.id.mealImageView);

        // Set meal type (e.g., Breakfast, Lunch, Dinner)
        mealTypeTextView.setText(mealType);

        // Assuming only one meal per type (for simplicity)
        MealResponse.Meal meal = meals.get(0);

        // Set meal name
        mealDetailsTextView.setText(meal.getMealName());

        // Set nutrition info (you can customize based on the meal object)
        nutritionInfoTextView.setText("Calories: " + meal.getCalories() + "\nCarbs: " + meal.getCarbohydrates() + "\nProtein: " + meal.getProtein());

        // Load meal image using Picasso or Glide
        Picasso.get().load(meal.getMealImageUrl()).into(mealImageView);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
