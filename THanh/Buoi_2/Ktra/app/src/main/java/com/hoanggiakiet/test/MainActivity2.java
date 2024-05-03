package com.hoanggiakiet.test;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.service.voice.VoiceInteractionSession;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoanggiakiet.test.databinding.ActivityMain2Binding;
import com.hoanggiakiet.test.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    Random random = new Random();
    int count = 0;
    TableRow tableRow = null;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            int percent = msg.arg1;
            int numbRand = msg.arg2;

            binding.txtPercent.setText(percent + " %");
            binding.pbPercent.setProgress(percent);

            TableRow.LayoutParams paramsImv = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

            ImageView imv = new ImageView(MainActivity2.this);
            imv.setLayoutParams(paramsImv);

            if (tableRow == null || count >= 4) {
                if (count >= 4)
                    binding.containerLayout.addView(tableRow);
                tableRow = new TableRow(MainActivity2.this);
                count = 0;
            }

            count++;

            if (numbRand % 2 == 0) {
                imv.setImageDrawable(getDrawable(R.drawable.baseline_account_circle_24));
            } else {
                imv.setImageDrawable(getDrawable(R.drawable.baseline_add_moderator_24));
            }

            tableRow.addView(imv);


            // tự động lướt xuống
            binding.scrollView.fullScroll(ScrollView.FOCUS_DOWN);

            if (percent == 100)
                Toast.makeText(MainActivity2.this, "Done!", Toast.LENGTH_LONG).show();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
    }

    private void addEvents() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw();
            }
        });
    }

    private void draw() {
        binding.containerLayout.removeAllViews();
        int numberOfView = Integer.parseInt(binding.edtInput.getText().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= numberOfView; i++) {
                    Message message = handler.obtainMessage();
                    message.arg1 = i * 100 / numberOfView;
                    message.arg2 = random.nextInt(100);
                    handler.sendMessage(message);
                    SystemClock.sleep(50);
                }
            }
        });

        thread.start();
    }

}