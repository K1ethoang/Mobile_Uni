package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app.databinding.ActivitySub2Binding;

public class SubActivity2 extends AppCompatActivity {
    ActivitySub2Binding binding;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySub2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        addEvents();
    }

    private void getData(){
        intent = getIntent();
        binding.txtNumb.setText(intent.getStringExtra("numb"));
    }

    private void addEvents()
    {
        binding.btnPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numb = Integer.parseInt(binding.txtNumb.getText().toString());
                double pow = Math.pow(numb, 2);
                intent.putExtra("result", pow);
                setResult(RESULT_OK, intent);
                finish(); // Close activity
            }
        });
    }
}