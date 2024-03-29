package com.hoanggiakiet.assets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MicrophoneDirection;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hoanggiakiet.assets.databinding.ActivityMainBinding;

import java.io.IOException;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFonts();
        addEvents();
        playSound();
    }

    private void loadFonts()
    {
        AssetManager manager = getAssets();
        try {
            String[] fontList = manager.list("fonts");
            assert fontList != null;

            adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, fontList);

            binding.lv.setAdapter(adapter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addEvents()
    {
        binding.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/" + adapter.getItem(i));

//                binding.txtContent.setTypeface(tf);
                playSound();
            }
        });
    }

    private void playSound()
    {
        try {
            AssetFileDescriptor descriptor = getAssets().openFd("musics/ting.mp3");
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
            descriptor.close();
            player.prepare();
            player.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}