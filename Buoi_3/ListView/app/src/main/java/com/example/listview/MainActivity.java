package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.ObjectsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.listview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
        addEvent();
    }

    private void loadData()
    {
        // String[] drinks =  {"Coca-Cola", "Pepsi", "Tiger", "Sai Gon", "Heineken", "Sapporo", "1664", "Milk Tea", "Sting", "Olong Tea", "C2", "Beer 333", "Ha Noi", "Volka", "7up", "Lavie", "Boncha","Coca-Cola", "Pepsi", "Tiger"};

        String[] foods = getResources().getStringArray(R.array.foodList);

        adapter= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, foods);

        binding.lvDrinks.setAdapter(adapter);
    }

    private void addEvent()
    {
        binding.lvDrinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item: click " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        binding.lvDrinks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Item: long click " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
                // return true: để không bị click sau khi long click
                return true;
            }
        });

    }

}