package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.listview.databinding.ActivityAutoCompleteBinding;

public class AutoCompleteActivity extends AppCompatActivity {
    ActivityAutoCompleteBinding binding;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAutoCompleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
    }

    private void loadData()
    {
        adapter = new ArrayAdapter<>(AutoCompleteActivity.this, android.R.layout.simple_list_item_1);

        adapter.add("Cơm tấm");
        adapter.add("Cơm sườn");
        adapter.add("Bún bò");
        adapter.add("Cơm chiên");
        adapter.add("Bún mắm");
        adapter.add("Lẩu cá");
        adapter.add("Pizza");
        adapter.add("Cá viên chiên");
        adapter.add("Cơm nấm");

        binding.txtMon.setAdapter(adapter);
    }

}