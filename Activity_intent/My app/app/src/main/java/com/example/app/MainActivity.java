package com.example.app;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.app.databinding.ActivityMainBinding;
import com.example.models.Product;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ActivityResultLauncher<Intent> laucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i("MainActivity", "onCreate");

        laucher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                double res = result.getData().getDoubleExtra("result", 0.0f);
                binding.txtResult.setText(String.valueOf(res));
            }
        });

        addEvents();
    }

    private void addEvents()
    {
        binding.btnOpenAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Opening Activity #2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        binding.btnOpenDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity3.class);
                startActivity(intent);
            }
        });

        binding.btnOpenSub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity1.class);
                // Attach data
                // M1: Using putExtra(key, data);
//                intent.putExtra("numb", 23);
//                intent.putExtra("grade", 2.3);
//                intent.putExtra("checked", true);
//                intent.putExtra("say", "Hello");


                // M2: Using Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("numb", 23);
                bundle.putFloat("grade", 2.3f);
                bundle.putBoolean("checked", true);
                bundle.putString("say", "Welcome");

                Product p = new Product(1,"Tiger",21000);
                // Gửi tổng quát
                bundle.putSerializable("product", p);
                // Gửi object
//                bundle.putParcelable("product", p);

                bundle.putBundle("data", bundle);

                startActivity(intent);
            }
        });

        binding.btnOpenSub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity2.class);
                // Attach data
                intent.putExtra("numb", binding.edtNumber.getText().toString());

//                startActivity(intent);

                laucher.launch(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }
}