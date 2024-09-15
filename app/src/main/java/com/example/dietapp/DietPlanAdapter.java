// DietPlanAdapter.java
package com.example.dietapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class DietPlanAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> dayList;
    private HashMap<String, List<MealResponse.Meal>> mealPlan;

    public DietPlanAdapter(Context context, List<String> dayList, HashMap<String, List<MealResponse.Meal>> mealPlan) {
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
        return mealPlan.get(dayList.get(groupPosition)).get(childPosition);
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
        String dayTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView dayTextView = convertView.findViewById(R.id.dayTextView);
        dayTextView.setText(dayTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final MealResponse.Meal meal = (MealResponse.Meal) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView mealNameTextView = convertView.findViewById(R.id.mealNameTextView);
        ImageView mealImageView = convertView.findViewById(R.id.mealImageView);
        TextView caloriesTextView = convertView.findViewById(R.id.caloriesTextView);
        TextView fatTextView = convertView.findViewById(R.id.fatTextView);
        TextView proteinTextView = convertView.findViewById(R.id.proteinTextView);
        TextView carbohydratesTextView = convertView.findViewById(R.id.carbohydratesTextView);

        mealNameTextView.setText(meal.getMealName());
        caloriesTextView.setText("Calories: " + (meal.getCalories() != null ? meal.getCalories() : "N/A"));
        fatTextView.setText("Fat: " + (meal.getFat() != null ? meal.getFat() + "g" : "N/A"));
        proteinTextView.setText("Protein: " + (meal.getProtein() != null ? meal.getProtein() + "g" : "N/A"));
        carbohydratesTextView.setText("Carbohydrates: " + (meal.getCarbohydrates() != null ? meal.getCarbohydrates() + "g" : "N/A"));

        Picasso.get().load(meal.getMealImageUrl()).into(mealImageView);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
