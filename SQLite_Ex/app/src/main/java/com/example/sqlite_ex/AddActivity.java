package com.example.sqlite_ex;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlite_ex.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents(){
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert data
                ContentValues values = new ContentValues();
                values.put(MainActivity.HEADER_ROW[1], binding.edtName.getText().toString());
                values.put(MainActivity.HEADER_ROW[2], Double.parseDouble(binding.edtPrice.getText().toString()));

                long numberOfRow = MainActivity.db.insert(MainActivity.TBL_NAME, null, values);

                if(numberOfRow > 0)
                {
                    Toast.makeText(AddActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(AddActivity.this, "Fail!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}