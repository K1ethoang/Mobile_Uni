package com.hoanggiakiet.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hoanggiakiet.test.adapters.DishAdapter;
import com.hoanggiakiet.test.databinding.ActivityMainBinding;
import com.hoanggiakiet.test.models.Category;
import com.hoanggiakiet.test.models.Dish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DishAdapter adapter;
    Database db;

    List<Dish> dishes;
    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareDb();
        getData();
        addEvents();
    }

    private void addEvents() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("categoris", (Serializable) categories);
                startActivity(intent);
            }
        });
    }

    private void prepareDb()
    {
        db = new Database(this);
        db.createSampleData();
    }

    private void getData()
    {
        adapter = new DishAdapter(this, R.layout.dish_item, loadDataFromDb());
        binding.lvDish.setAdapter(adapter);
    }

    private List<Dish> loadDataFromDb() {
        dishes = new ArrayList<>();
        Cursor cursor = db.queryData("SELECT * FROM " + Database.TBL_NAME_DISH + " d, " + Database.TBL_NAME_CATEGORY + " c WHERE d." + Database.DISH_CODE + " = c." + Database.CATEGORY_CODE);

        if (cursor != null)
        {
            while(cursor.moveToNext())
            {
                Dish dish = new Dish(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getDouble(4), cursor.getBlob(3), cursor.getInt(5));

                dishes.add(dish);
            }
            cursor.close();
        }

        return dishes;
    }

    private List<Category> loadCategory()
    {
        categories = new ArrayList<>();
        Cursor cursor = db.queryData("SELECT * FROM " + Database.TBL_NAME_CATEGORY);

        if (cursor != null)
        {
            while(cursor.moveToNext())
            {
                Category category = new Category(cursor.getInt(0), cursor.getString(1));

                categories.add(category);
            }
            cursor.close();
        }

        return categories;
    }


}