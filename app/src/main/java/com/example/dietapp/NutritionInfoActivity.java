package com.example.dietapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NutritionInfoActivity extends AppCompatActivity {

    TextView textViewNutritionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_info);

        textViewNutritionInfo = findViewById(R.id.textViewNutritionInfo);

        // Get the barcode from the intent
        String barcode = getIntent().getStringExtra("BARCODE");

        // Fetch nutritional information based on the barcode
        if (barcode != null) {
            new FetchNutritionInfoTask().execute(barcode);
        } else {
            Toast.makeText(this, "Invalid barcode", Toast.LENGTH_SHORT).show();
        }
    }

    private class FetchNutritionInfoTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String barcode = params[0];
            String apiUrl = "https://world.openfoodfacts.org/api/v0/product/" + barcode + ".json";

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return response.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject product = jsonObject.getJSONObject("product");

                    // Fetching basic nutritional information
                    String productName = product.getString("product_name");
                    JSONObject nutriments = product.getJSONObject("nutriments");
                    String calories = nutriments.optString("energy-kcal_100g", "N/A");
                    String fat = nutriments.optString("fat_100g", "N/A");
                    String carbohydrates = nutriments.optString("carbohydrates_100g", "N/A");
                    String protein = nutriments.optString("proteins_100g", "N/A");

                    // Displaying the information
                    String nutritionInfo = "Product: " + productName + "\n\n" +
                            "Calories: " + calories + " kcal\n" +
                            "Fat: " + fat + " g\n" +
                            "Carbohydrates: " + carbohydrates + " g\n" +
                            "Protein: " + protein + " g\n";

                    textViewNutritionInfo.setText(nutritionInfo);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(NutritionInfoActivity.this, "Failed to parse nutritional information", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(NutritionInfoActivity.this, "Failed to retrieve nutritional information", Toast.LENGTH_SHORT).show();
            }
        }
    }
}