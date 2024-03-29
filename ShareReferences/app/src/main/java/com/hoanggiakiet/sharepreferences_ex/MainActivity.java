package com.hoanggiakiet.sharepreferences_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hoanggiakiet.sharepreferences_ex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static final String PREFERENCES_NAME = "test_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents()
    {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();
                // Attack data
                editor.putInt("numb", 89);
                editor.putFloat("price", 2300000);
                editor.putBoolean("checked", true);

                editor.apply();
            }
        });

        binding.btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
                int numb = preferences.getInt("numb",0);
                float price = preferences.getFloat("price",0.0f);
                boolean checked = preferences.getBoolean("checked",false);

                StringBuffer content = new StringBuffer();

                content.append("Number: " + numb);
                content.append("\nPrce: " + price);
                content.append("\nChecked: " + checked);

                binding.txtContent.setText(content);
            }
        });
    }
}