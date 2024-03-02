package com.example.imagebuttonimageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.imagebuttonimageview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.imgBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imvLogo = binding.imvLogo;
                if(imvLogo.getTag() == "apple"){
                    imvLogo.setImageResource(R.drawable.android);
                    imvLogo.setTag("android");
                }
                else
                {
                    imvLogo.setImageResource(R.drawable.apple);
                    imvLogo.setTag("apple");
                }
            }
        });

        binding.imgBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}