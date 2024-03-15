package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapters.BeerAdapter;
import com.example.listview.databinding.ActivityMain3Binding;
import com.example.models.Beer;
import com.example.models.Product;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ActivityMain3Binding binding;
    BeerAdapter adapter;
    ArrayList<Beer> beers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadData();
        addEvent();
    }

    public void loadData()
    {
        // Init data
        beers = new ArrayList<>();
        beers.add(new Beer(R.drawable.heineken, "Heineken", 19000));
        beers.add(new Beer(R.drawable.tiger, "Tiger", 19000));
        beers.add(new Beer(R.drawable.saigon, "SaiGon", 19000));
        beers.add(new Beer(R.drawable.hanoi, "HaNoi", 19000));
        beers.add(new Beer(R.drawable.beer333, "Beer333", 19000));
        beers.add(new Beer(R.drawable.larue, "Larue", 19000));
        beers.add(new Beer(R.drawable.heineken, "Heineken", 19000));
        beers.add(new Beer(R.drawable.tiger, "Tiger", 19000));
        beers.add(new Beer(R.drawable.saigon, "SaiGon", 19000));
        beers.add(new Beer(R.drawable.hanoi, "HaNoi", 19000));
        beers.add(new Beer(R.drawable.beer333, "Beer333", 19000));
        beers.add(new Beer(R.drawable.larue, "Larue", 19000));
        beers.add(new Beer(R.drawable.heineken, "Heineken", 19000));
        beers.add(new Beer(R.drawable.tiger, "Tiger", 19000));
        beers.add(new Beer(R.drawable.saigon, "SaiGon", 19000));
        beers.add(new Beer(R.drawable.hanoi, "HaNoi", 19000));
        beers.add(new Beer(R.drawable.beer333, "Beer333", 19000));
        beers.add(new Beer(R.drawable.larue, "Larue", 19000));

        adapter = new BeerAdapter(MainActivity3.this, R.layout.item_list_view, beers);
        binding.lvBeer.setAdapter(adapter);
    }

    private void addEvent(){
        binding.lvBeer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Beer beerSelected = (Beer) adapter.getItem(position);

                Dialog dialog = new Dialog(MainActivity3.this);
                dialog.setContentView(R.layout.item_dialog);

                //event
                Button btnBack = dialog.findViewById(R.id.btnBack);
                ImageView imvThumb = dialog.findViewById(R.id.imvThumb);
                TextView txtName = dialog.findViewById(R.id.txtName);

                imvThumb.setImageResource(beerSelected.getThumb());
                txtName.setText(beerSelected.getName());

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                // show dialog
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setWindowAnimations(R.style.DiaglogAnimation);
                dialog.show();
            }
        });
    }
}