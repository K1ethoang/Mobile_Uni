package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Half;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listview.R;
import com.example.models.Beer;

import java.util.ArrayList;

public class BeerAdapter extends BaseAdapter {
    Activity activity;
    int item_layout;
    ArrayList<Beer> beers;

    // Constructor


    public BeerAdapter(Activity activity, int item_layout, ArrayList<Beer> beers) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.beers = beers;
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int i) {
        return beers.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Làm 2 nvu:
    // nv1: ánh xạ đến các view
    // nv2: đổ dữ liệu
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);

            // Link item view
            holder.imvThumb = view.findViewById(R.id.imvThumb);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        // Binding data
        Beer b = beers.get(i);
        holder.imvThumb.setImageResource(b.getThumb());
        holder.txtName.setText(b.getName());
        holder.txtPrice.setText(String.valueOf(b.getPrice()));

        return view;
    }

    public class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice;
    }
}
