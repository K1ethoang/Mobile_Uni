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
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoanggiakiet.multi_thread_ex2.databinding.ActivityMain2Binding;
import com.hoanggiakiet.multi_thread_ex2.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        executorService = Executors.newSingleThreadExecutor();

        addEvents();
    }

    private void addEvents() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.containerLayout.removeAllViews();
                execLongRunningTask();
            }
        });
    }

    private void execLongRunningTask() {
        executorService.execute(new Runnable() {
            int numberOfViews = Integer.parseInt(binding.edtInput.getText().toString());
            int percent, randNumb;
            Random random = new Random();
            @Override
            public void run() {
                for (int i = 1; i <= numberOfViews; i++) {
                    percent = i * 100 / numberOfViews;
                    randNumb = random.nextInt(100) + 1;

                    // Updating UI
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.txtPercent.setText(percent + " %");
                            binding.pbPercent.setProgress(percent);

                            int width = binding.containerLayout.getWidth() - (binding.containerLayout.getPaddingLeft() + binding.containerLayout.getPaddingRight());

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width/2, 100);

                            params.setMargins(0,20,0,20);

                            TextView textView = new TextView(MainActivity2.this);
                            textView.setText(String.valueOf(randNumb));
                            textView.setTextSize(28);
                            textView.setTextColor(Color.WHITE);
                            textView.setGravity(Gravity.CENTER);

                            if (randNumb % 2 == 0) {
                                params.gravity = Gravity.LEFT;
                                textView.setBackgroundColor(Color.rgb(0, 150, 146));
                            } else {
                                params.gravity = Gravity.RIGHT;
                                textView.setBackgroundColor(Color.rgb(231, 150, 46));
                            }

                            textView.setLayoutParams(params);

                            binding.containerLayout.addView(textView);
                            binding.scrollView.fullScroll(ScrollView.FOCUS_DOWN);

                            if (percent == 100)
                                binding.txtPercent.setText("DONE!");
                        }
                    });

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        Toast.makeText(MainActivity2.this, "Have error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(executorService != null) executorService.shutdown();
    }
}