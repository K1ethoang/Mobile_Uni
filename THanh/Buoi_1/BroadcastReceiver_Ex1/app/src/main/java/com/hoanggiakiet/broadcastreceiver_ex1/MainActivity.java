package com.hoanggiakiet.broadcastreceiver_ex1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hoanggiakiet.broadcastreceiver_ex1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//
//            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
//                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                    binding.imvState.setImageResource(R.drawable.wificonnectionsignalsymbol_104927);
//                    binding.txtState.setText("Connected with WIFI");
//                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
//                    binding.imvState.setImageResource(R.drawable.baseline_4g_mobiledata_24);
//                    binding.txtState.setText("Connected with Mobile Data");
//                }
//            } else
//            {
//                binding.imvState.setImageResource(R.drawable.no_wifi_svgrepo_com);
//                binding.txtState.setText("No internet connection");
//            }

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            Network network = connectivityManager.getActiveNetwork();
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);

            if(networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)))
            {
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    binding.imvState.setImageResource(R.drawable.wificonnectionsignalsymbol_104927);
                    binding.txtState.setText("Connected with WIFI");
                } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                {
                    binding.imvState.setImageResource(R.drawable.baseline_4g_mobiledata_24);
                    binding.txtState.setText("Connected with Mobile Data");
                }
            }
            else
            {
                binding.imvState.setImageResource(R.drawable.no_wifi_svgrepo_com);
                binding.txtState.setText("No internet connection");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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