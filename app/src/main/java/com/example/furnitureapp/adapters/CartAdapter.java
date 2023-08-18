package com.example.furnitureapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnitureapp.CartItem;
import com.example.furnitureapp.Furniture;
import com.example.furnitureapp.R;

import java.util.ArrayList;

import com.example.furnitureapp.SharedData;
import com.example.furnitureapp.activities.ProductPage;
import com.squareup.picasso.Picasso;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    ArrayList<CartItem> selectedItems;

    private ArrayList<CartItem> furnitures;
    private Context context;
    private RecyclerViewClickListener listener;

    public CartAdapter(ArrayList<CartItem> furnitures, CartAdapter.RecyclerViewClickListener listener, Context context){
        this.furnitures = furnitures;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false);
        return new CartHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {

        SharedData sharedData = SharedData.getInstance();
        selectedItems = sharedData.getSelectedFunitures();

        CartItem furniture = furnitures.get(position);

        Picasso.get().load(furniture.getImage()).placeholder(R.drawable.ic_profile).into(holder.imageFurniture);
        holder.textName.setText(furniture.getName());
        holder.textPrice.setText("₹"+ furniture.getPrice());
        holder.textQty.setText(String.valueOf(furniture.getQty()));
        if(furniture.isChecked()){
            holder.buttonCheck.setImageResource(R.drawable.ic_checkbox_checked);
        }else {
            holder.buttonCheck.setImageResource(R.drawable.ic_checkbox_unchecked);
        }

        holder.buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                furniture.setChecked(!furniture.isChecked());
                furnitures.get(position).setChecked(furniture.isChecked());
                if(furniture.isChecked()){
                    holder.buttonCheck.setImageResource(R.drawable.ic_checkbox_checked);
                }else {
                    holder.buttonCheck.setImageResource(R.drawable.ic_checkbox_unchecked);
                }
                if (listener != null) {
                    listener.onItemClick(calculateTotal());
                }
            }
        });

        holder.imageInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                furniture.setQty(furniture.getQty() + 1);
                furnitures.get(position).setQty(furniture.getQty());
                holder.textQty.setText(String.valueOf(furniture.getQty()));
                if (listener != null) {
                    listener.onItemClick(calculateTotal());
                }
            }
        });

        holder.imageDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(furniture.getQty() == 0 || furniture.getQty() == 1){
                    furniture.setQty(0);
                    holder.buttonCheck.setImageResource(R.drawable.ic_checkbox_unchecked);
                }else{
                    furniture.setQty(furniture.getQty() - 1);
                }
                furnitures.get(position).setQty(furniture.getQty());
                holder.textQty.setText(String.valueOf(furniture.getQty()));
                if (listener != null) {
                    listener.onItemClick(calculateTotal());
                }
            }
        });
    }

    int sum = 0;
    int calculateTotal(){
        sum = 0;
        for (CartItem item : furnitures){
            if(item.isChecked()){
                sum = sum + item.getPrice() * item.getQty();
            }
        }
        return sum;
    }
    @Override
    public int getItemCount() {
        return furnitures.size();
    }

    public interface RecyclerViewClickListener {
        void onItemClick(int totalPrice);
    }

    class CartHolder extends RecyclerView.ViewHolder{
        TextView textName, textPrice, textQty;
        ImageView imageFurniture, buttonCheck, imageInc, imageDec;
        public CartHolder(@NonNull View itemView, final RecyclerViewClickListener listener) {
            super(itemView);

            imageFurniture = itemView.findViewById(R.id.imageFurniture);
            textName = itemView.findViewById(R.id.textItemName);
            textPrice = itemView.findViewById(R.id.textPrice);
            textQty = itemView.findViewById(R.id.textQty);
            buttonCheck = itemView.findViewById(R.id.imageCheck);
            imageDec = itemView.findViewById(R.id.imageDec);
            imageInc = itemView.findViewById(R.id.imageInc);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }
    }
}
