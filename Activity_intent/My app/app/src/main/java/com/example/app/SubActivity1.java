package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.app.databinding.ActivitySub1Binding;
import com.example.models.Product;

public class SubActivity1 extends AppCompatActivity {
    ActivitySub1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySub1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
    }

    private void getData()
    {
        Intent intent = getIntent();

        // M1: receiving data using getType
//        int numb = intent.getIntExtra("numb", 0);
//        float grade = intent.getFloatExtra("grade", 1.0f);
//        boolean checked = intent.getBooleanExtra("checked", false);
//        String say = intent.getStringExtra("say");

        // M2: using Bundle
        Bundle bundle = intent.getBundleExtra("data");
        assert bundle != null;
        int numb = bundle.getInt("numb", 0);
        float grade = bundle.getFloat("grade", 1.0f);
        boolean checked = bundle.getBoolean("checked", false);
        String say = bundle.getString("say");

        Product p = (Product)bundle.getSerializable("product");


        // Showing data
        binding.txtContent.setText("");
        binding.txtContent.append("Numb: " + numb + "\n");
        binding.txtContent.append("Grade: " + grade + "\n");
        binding.txtContent.append("Checked: " + checked + "\n");
        binding.txtContent.append("Say: " + say + "\n");
        assert p != null;
        binding.txtContent.append("Product: " + p.toString());
    }
}