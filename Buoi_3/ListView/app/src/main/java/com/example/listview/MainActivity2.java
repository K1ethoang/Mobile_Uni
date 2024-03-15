package com.example.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.listview.databinding.ActivityMain2Binding;
import com.example.listview.databinding.ActivityMainBinding;
import com.example.models.Product;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    ArrayAdapter<Product> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();

        addEvent();
    }

    private void initAdapter(){
        adapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1);
        binding.lvProducts.setAdapter(adapter);
    }


    private void addEvent() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // init data
                String name = binding.edtName.getText().toString();
                double price = Double.parseDouble(binding.edtPrice.getText().toString());
                Product p = new Product(name, price);
                adapter.add(p);
            }
        });

        binding.lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapter.getItem(position);

                assert selectedProduct != null;
                Toast.makeText(MainActivity2.this, selectedProduct.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.lvProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapter.getItem(position);

                adapter.remove(selectedProduct);
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity2.this, selectedProduct.toString() + " Đã được xoá", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}