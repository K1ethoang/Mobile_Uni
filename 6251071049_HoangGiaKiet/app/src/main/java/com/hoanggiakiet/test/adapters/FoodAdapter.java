package com.hoanggiakiet.test.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanggiakiet.test.R;
import com.hoanggiakiet.test.models.Food;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    Activity activity;
    int item_layout;
    ArrayList<Food> foods;

    // Constructor


    public FoodAdapter(Activity activity, int item_layout, ArrayList<Food> foods) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.foods = foods;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Food getItem(int i) {
        return foods.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);

            // Link item view
            holder.photo = view.findViewById(R.id.photo);
            holder.placeName = view.findViewById(R.id.placeName);
            holder.dishName = view.findViewById(R.id.dishName);
            holder.ratingValue = view.findViewById(R.id.ratingValue);
            holder.ratingCount = view.findViewById(R.id.ratingCount);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        // Binding data
        Food f = foods.get(i);
        holder.photo.setImageResource(f.getPhoto());
        holder.placeName.setText(f.getPlaceName());
        holder.dishName.setText(f.getDishName());
        holder.ratingValue.setText((int) f.getRatingValue());
        holder.ratingCount.setText(f.getRatingCount());

        return view;
    }

    public class ViewHolder{
        ImageView photo;
        TextView placeName, dishName,ratingValue,ratingCount;
    }
}
