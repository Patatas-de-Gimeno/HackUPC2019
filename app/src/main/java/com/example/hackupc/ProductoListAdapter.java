package com.example.hackupc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductoListAdapter extends ArrayAdapter<Producto> {
    private Context mContext;
    int mResource;
    public ProductoListAdapter(Context context, int resource, ArrayList<Producto> objects) {
        super(context, resource, objects);
        mContext =context;
        mResource = resource;

    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String url = getItem(position).getUrl();
        String price = getItem(position).getPrice();
        String type = getItem(position).getType();

        Producto producto = new Producto(name, price, type, url);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvName = (TextView) convertView.findViewById(R.id.nameProduct);
        TextView tvUrl = (TextView) convertView.findViewById(R.id.urlProduct);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.priceProduct);

        tvName.setText(name);
        tvPrice.setText(price);
        tvUrl.setText(url);

        return convertView;
    }
}
