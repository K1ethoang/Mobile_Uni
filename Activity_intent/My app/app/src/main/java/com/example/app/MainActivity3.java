package com.example.app;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.app.databinding.ActivityMain3Binding;

import java.net.URI;

public class MainActivity3 extends AppCompatActivity {
    ActivityMain3Binding binding;
    ActivityResultLauncher<Intent> launcher;

    boolean captureState = true; // open camera: true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null)
            {
                if(captureState)
                {
                    Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                    binding.image.setImageBitmap(bitmap);
                }
                else
                {
                    Uri selectedPhoto = result.getData().getData();
                    binding.image.setImageURI(selectedPhoto);
                }
            }
        });

        addEvents();
    }

    private void addEvents()
    {
        binding.btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel:090123123123");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:090123123123");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        binding.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                launcher.launch(intent);

                // Open bottom sheet (Gallery/open camera)
                Dialog dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.bottom_sheet_capture);

                LinearLayout loCam = dialog.findViewById(R.id.layoutCamera);
                loCam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        captureState = true;
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        launcher.launch(intent);
                        dialog.dismiss();
                    }
                });

                LinearLayout loGal = dialog.findViewById(R.id.layoutGallery);
                loGal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        captureState = false;
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        launcher.launch(intent);
                        dialog.dismiss();
                    }
                });


                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }

}