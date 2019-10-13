package com.example.hackupc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public List<Producto> list_productos;

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name;
        TextView price;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.itemshopname);
            this.price = itemView.findViewById(R.id.priceitemshop);

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.itempricerow, parent, false);
        return new MyAdapter.MyViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        final Producto a = list_productos.get(position);
        holder.name.setText(a.getName());
        holder.price.setText(a.getPrice());
        /*
        holder.edit.setOnClickListener((v) -> {
            Intent myIntent = new Intent (v.getContext(), ActivityEditViatje.class);
            myIntent.putExtra("VIATJE_ID", a.id);
            v.GetContext.startActivity(myIntent);
        });
        */


    }

    @Override
    public int getItemCount() {
        return list_productos.size();
    }

}
