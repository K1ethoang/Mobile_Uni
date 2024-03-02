package com.example.view_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.view_p2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Bạn đã đánh giá: ";

                if(binding.btnSubmit.isPressed())
                {
                    int id = binding.radGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = binding.getRoot().findViewById(id);
                    s+=radioButton.getText();
                }

                binding.txtSubmit.setText(s);
            }
        });
    }
}