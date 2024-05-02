package com.hoanggiakiet.multi_threads_ex;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoanggiakiet.multi_threads_ex.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Random random = new Random();

    // Main/UI Thread
    Handler handler = new Handler(new Handler.Callback() {
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            int percent = msg.arg1;
            int randNumb = (int) msg.obj;

            binding.txtPercent.setText(percent + " %");
            binding.pbPercent.setProgress(percent);

            // Update UI
            binding.btnDraw.setClickable(false);

            ImageView imageView = new ImageView(MainActivity.this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);
            if(randNumb % 2 == 0)
                imageView.setImageDrawable(getDrawable(R.drawable.baseline_1k_24));
            else
                imageView.setImageDrawable(getDrawable(R.drawable.baseline_album_24));

            binding.containerLayout.addView(imageView);

            if(percent == 100)
            {
                binding.txtPercent.setText("DONE!");
                binding.btnDraw.setClickable(true);
            }

            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents(){
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBackground();
            }
        });
    }

    private void drawBackground() {
        int numberOfViews = Integer.parseInt(binding.edtNumbsOfView.getText().toString());
        binding.containerLayout.removeAllViews();
        // Worker/Background Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= numberOfViews; i++)
                {
                    Message message = handler.obtainMessage();
                    message.arg1 = i * 100 / numberOfViews; // computing percent
                    message.obj = random.nextInt(100) + 1; // random number [1;100]
                    handler.sendMessage(message);
                    SystemClock.sleep(50);
                }
            }
        });
        thread.start();
    }
}