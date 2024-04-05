package com.example.sqlite_ex;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.Product;
import com.example.sqlite_ex.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {
    ActivityEditBinding binding;
    Intent intent;
    Product p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        addEvents();
    }

    private void getData()
    {
        intent = getIntent();
        p = (Product) intent.getSerializableExtra("product");

        binding.edtName.setText(p.getName());
        binding.edtPrice.setText(String.valueOf(p.getPrice()));
    }

    private void addEvents()
    {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MainActivity.HEADER_ROW[1], binding.edtName.getText().toString());
                values.put(MainActivity.HEADER_ROW[2], Double.parseDouble(binding.edtPrice.getText().toString()));

                int numberOfRows = MainActivity.db.update(MainActivity.TBL_NAME,values,MainActivity.HEADER_ROW[0] + "=?",new String[]{String.valueOf(p.getId())});

                if(numberOfRows > 0)
                {
                    Toast.makeText(EditActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(EditActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}