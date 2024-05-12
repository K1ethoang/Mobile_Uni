package com.hoanggiakiet.test.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanggiakiet.test.R;
import com.hoanggiakiet.test.models.Dish;

import java.util.List;

public class DishAdapter extends BaseAdapter {
    Context context;
    int itemLayout;
    List<Dish> dishes;

    public DishAdapter(Context context, int itemLayout, List<Dish> dishes) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.dishes = dishes;
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int position) {
        return dishes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(itemLayout, null);

            // Linking view
            holder.txtName =convertView.findViewById(R.id.txtName);
            holder.txtPrice =convertView.findViewById(R.id.txtPrice);
            holder.imvPhoto =convertView.findViewById(R.id.imvPhoto);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        // Binding data
        Dish dish = dishes.get(position);
        holder.txtName.setText(dish.getDishName());
        holder.txtPrice.setText(dish.getDishPrice().toString());

        // Converting byte array -> bitmap
        byte[] photo = dish.getDishImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length);

        holder.imvPhoto.setImageBitmap(bitmap);

        return convertView;
    }



    public class ViewHolder {
        TextView txtName, txtPrice;
        ImageView imvPhoto;
    }
}
