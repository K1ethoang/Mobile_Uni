package com.example.view_p1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.view_p1.databinding.ActivityMainBinding;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Bạn chọn: ";

                if(binding.chkNetflix.isChecked())
                    s +=(binding.chkNetflix.getText().toString() + ", ");
                if(binding.chkFptPlay.isChecked())
                    s+=(binding.chkFptPlay.getText().toString() + ", ");
                if(binding.chkClipTv.isChecked())
                    s+=(binding.chkClipTv.getText().toString() + ", ");

                binding.txtVote.setText(s.substring(0, s.length()-2));
            }
        });
    }
}