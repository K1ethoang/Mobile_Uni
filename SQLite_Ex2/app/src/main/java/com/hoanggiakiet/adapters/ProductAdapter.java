package com.hoanggiakiet.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoanggiakiet.SQLite_Ex2.MainActivity;
import com.hoanggiakiet.SQLite_Ex2.R;
import com.hoanggiakiet.models.Product;

import java.text.Format;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    MainActivity context;
    int itemLayout;
    List<Product> products;

    public ProductAdapter(MainActivity context, int itemLayout, List<Product> products) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemLayout, null);

            // Linking views
            holder.txtInfo = view.findViewById(R.id.txtInfo);
            holder.imvEdit = view.findViewById(R.id.imvEdit);
            holder.imvDelete = view.findViewById(R.id.imvDelete);

            view.setTag(holder);
        } else
        {
            holder = (ViewHolder) view.getTag();
        }

        // Binding data
        Product p = products.get(position);
        holder.txtInfo.setText(p.getName() + " - " + p.getPrice());

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Editing data
                context.openEditDialog(p);
            }
        });

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deleting data
                context.openDeleteDialog(p);
            }
        });

        return view;
    }

    public static class ViewHolder{
        TextView txtInfo;
        ImageView imvEdit, imvDelete;
    }
}
