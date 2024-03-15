package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.listview.databinding.ActivitySpinnerBinding;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {
    ActivitySpinnerBinding binding;
    ArrayAdapter<String> adapter;
    ArrayList<String> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySpinnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
    }

    private void loadData(){
        // Init data
        foodList = new ArrayList<>();
        foodList.add("Bún bò");
        foodList.add("Bún real");
        foodList.add("Bún mắm");
        foodList.add("Bún cá");
        foodList.add("Bún heo");
        foodList.add("Bún gà");
        foodList.add("Bún vịt");
        foodList.add("Bún ếch");
        foodList.add("Bún thái");
        foodList.add("Bún bò");
        foodList.add("Bún real");
        foodList.add("Bún mắm");
        foodList.add("Bún cá");
        foodList.add("Bún heo");
        foodList.add("Bún gà");
        foodList.add("Bún vịt");
        foodList.add("Bún ếch");
        foodList.add("Bún thái");
        foodList.add("Bún bò");
        foodList.add("Bún real");
        foodList.add("Bún mắm");
        foodList.add("Bún cá");
        foodList.add("Bún heo");
        foodList.add("Bún gà");
        foodList.add("Bún vịt");
        foodList.add("Bún ếch");
        foodList.add("Bún thái");

        adapter = new ArrayAdapter<>(SpinnerActivity.this, android.R.layout.simple_list_item_activated_1, foodList);

        binding.spnFood.setAdapter(adapter);
    }
}