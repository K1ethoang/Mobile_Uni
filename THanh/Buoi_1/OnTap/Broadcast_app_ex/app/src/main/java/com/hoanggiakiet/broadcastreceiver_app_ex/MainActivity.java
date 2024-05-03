package com.hoanggiakiet.broadcastreceiver_app_ex;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoanggiakiet.broadcastreceiver_app_ex.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Random random = new Random();

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            Network network = connectivityManager.getActiveNetwork();
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);

            if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)))
            {
                if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                {
                    binding.txtState.setText("Connected");
                }
                else if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    binding.txtState.setText("3G/4G");
                }
            }
            else
            {
                binding.txtState.setText("Disconnected");
            }
        }
    };

    // Main/UI Thread
    Handler handler = new Handler(new Handler.Callback() {
        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            int percent = msg.arg1;
            int randNum = (int) msg.obj;

            binding.txtPercent.setText(percent + " %");
            binding.pbPercent.setProgress(percent);
            // Update UI

            ImageView imv = new ImageView(MainActivity.this);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imv.setLayoutParams(params);

            if(randNum % 2 == 0)
                imv.setImageDrawable(getDrawable(R.drawable.baseline_add_moderator_24));
            else
                imv.setImageDrawable(getDrawable(R.drawable.baseline_access_time_filled_24));
            binding.scrollView.fullScroll(ScrollView.FOCUS_DOWN);

            binding.containerLayout.addView(imv);

            if(percent == 100)
            {
                Toast.makeText(MainActivity.this, "Done render!", Toast.LENGTH_LONG).show();
                binding.btnSubmit.setEnabled(true);
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

    private void addEvents() {
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderBackground();
            }
        });
    }

    private void renderBackground() {
        int number = Integer.parseInt(binding.edtInput.getText().toString());
        binding.btnSubmit.setEnabled(false);
        binding.containerLayout.removeAllViews();
        // Worker/Background Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= number; i++)
                {
                    Message message = handler.obtainMessage();
                    message.arg1 = i * 100 / number; // computing percent
                    message.obj = random.nextInt(100) + 1; // random in [1; 100]
                    handler.sendMessage(message);
                    SystemClock.sleep(50); // 0.5 s
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}