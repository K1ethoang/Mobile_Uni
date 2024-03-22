package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.app.databinding.ActivityDialog3Binding;

public class DialogActivity3 extends AppCompatActivity {
    ActivityDialog3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialog3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}