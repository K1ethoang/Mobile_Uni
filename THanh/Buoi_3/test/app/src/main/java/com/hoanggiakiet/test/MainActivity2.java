package com.hoanggiakiet.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoanggiakiet.test.databinding.ActivityMain2Binding;
import com.hoanggiakiet.test.models.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    List<Category> categories = new ArrayList<>();
    ArrayAdapter<Category> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.btnAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtName.getText().toString();
                String desc = binding.edtDesc.getText().toString();
                Double price = Double.valueOf(binding.edtPrice.getText().toString());
            }
        });
    }

    private void loadData() {
        Intent intent = getIntent();

        categories = (List<Category>) intent.getSerializableExtra("categories");

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        binding.spnCategory.setAdapter(adapter);
    }
}