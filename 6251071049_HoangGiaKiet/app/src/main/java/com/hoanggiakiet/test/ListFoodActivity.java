package com.hoanggiakiet.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hoanggiakiet.test.adapters.FoodAdapter;
import com.hoanggiakiet.test.databinding.ListFoodBinding;
import com.hoanggiakiet.test.models.Food;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ListFoodActivity extends AppCompatActivity {
    Intent intent = getIntent();
    ListFoodBinding binding;
    FoodAdapter adapter;

    ArrayList<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String title = intent.getStringExtra("title");

        JSONObject pathJson;

        foods = new ArrayList<>();

        try {
            pathJson = new JSONObject("data/data.json");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



        if(title.equals("rice"))
        {
            try {
                JSONObject rice = pathJson.getJSONObject("LUNCH_BOX");
                Iterator iterator = rice.keys();

                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    JSONObject food = rice.getJSONObject(key);

                    Food f = new Food(food.getString("placeName"),food.getString("dishName"),food.getInt("photo"),food.getInt("ratingValue"),food.getString("ratingCount"),food.getString("address"));

                    foods.add(f);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }else
        {

        }

        binding.listFood.setAdapter(adapter);
        binding.title.setText(title);
    }
}