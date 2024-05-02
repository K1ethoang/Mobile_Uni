package com.hoanggiakiet.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanggiakiet.models.Warranty;
import com.hoanggiakiet.sqlite_ex3.R;

import java.util.List;

public class WarrantyAdapter extends BaseAdapter {
    Context context;
    int itemLayout;
    List<Warranty> warranties;

    public WarrantyAdapter(Context context, int itemLayout, List<Warranty> warranties) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.warranties = warranties;
    }

    @Override
    public int getCount() {
        return warranties.size();
    }

    @Override
    public Object getItem(int position) {
        return warranties.get(position);
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
            convertView = inflater.inflate(itemLayout, null);

            // Linking view
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtDesc = convertView.findViewById(R.id.txtDesc);
            holder.imvPhoto = convertView.findViewById(R.id.imvPhoto);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        // Binding data
        Warranty w = warranties.get(position);
        holder.txtName.setText(w.getName());
        holder.txtDesc.setText(w.getDesc());

        // Converting byte array -> bitmap
        byte[] photo = w.getPhoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length);

        holder.imvPhoto.setImageBitmap(bitmap);

        return convertView;
    }

    public class ViewHolder{
        TextView txtName, txtDesc;
        ImageView imvPhoto;
    }
}
