package com.hoanggiakiet.multi_thread_ex2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoanggiakiet.multi_thread_ex2.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int percent, randNumb;
    Random random = new Random();

    Handler handler = new Handler();

    // Main/UI Thread
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            binding.txtPercent.setText(percent + " %");
            binding.pbPercent.setProgress(percent);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,200);

            params.setMargins(15,20,15,20);

            TextView textView = new TextView(MainActivity.this);
            textView.setText(String.valueOf(randNumb));
            textView.setTextSize(28);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);

            if (randNumb % 2 == 0)
            {
                params.gravity = Gravity.LEFT;
                textView.setBackgroundColor(Color.rgb(0, 150, 146));
            }
            else
            {
                params.gravity = Gravity.RIGHT;
                textView.setBackgroundColor(Color.rgb(231, 150, 46));
            }


            textView.setLayoutParams(params);


            binding.containerLayout.addView(textView);
            binding.scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            if (percent == 100)
                binding.txtPercent.setText("DONE!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execLongRunningTask();
            }
        });
    }

    private void execLongRunningTask() {
        binding.containerLayout.removeAllViews();
        int numberOfViews = Integer.parseInt(binding.edtInput.getText().toString());
        binding.edtInput.setText("");
        // Worker/Background Thread
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= numberOfViews; i++) {
                    percent = i * 100 / numberOfViews;
                    randNumb = random.nextInt(100) + 1;
                    handler.post(foregroundThread);
                    SystemClock.sleep(50);
                }
            }
        });

        backgroundThread.start();
    }

}