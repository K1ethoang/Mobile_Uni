package com.hoanggiakiet.sqlite_ex3;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoanggiakiet.adapters.WarrantyAdapter;
import com.hoanggiakiet.databases.WarrantyDB;
import com.hoanggiakiet.models.Warranty;
import com.hoanggiakiet.sqlite_ex3.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    WarrantyAdapter adapter;
    List<Warranty> warranties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getData();
    }

    private void getData()
    {
        adapter = new WarrantyAdapter(MainActivity2.this,R.layout.item_list, loadDataFromDb());
        binding.lvWarranty.setAdapter(adapter);

    }

    private List<Warranty> loadDataFromDb() {
        warranties = new ArrayList<>();
        Cursor cursor = MainActivity.db.getData("SELECT * FROM " + WarrantyDB.TBL_NAME);

        if (cursor != null)
        {
            while(cursor.moveToNext())
            {
                Warranty w = new Warranty(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getBlob(3));

                warranties.add(w);
            }
            cursor.close();
        }

        return warranties;
    }
}