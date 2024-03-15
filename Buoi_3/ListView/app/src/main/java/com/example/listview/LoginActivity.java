package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.listview.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
        addEvent();
    }

    private void loadData()
    {
        adapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_list_item_1);

        adapter.add("English");
        adapter.add("Tiếng việt");

        binding.spnLang.setAdapter(adapter);
    }

    private void addEvent()
    {
        binding.spnLang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lang = adapter.getItem(position);

                if(lang.equals("Tiếng việt")){

                }
            }
        });
    }
}