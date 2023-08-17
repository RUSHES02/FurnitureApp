package com.example.furnitureapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnitureapp.R;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class SuggetionAdapter extends RecyclerView.Adapter<SuggetionAdapter.SuggetionHolder> {

    private ArrayList<Integer> itemImages;
    private ArrayList<String> itemNames;
    private ArrayList<String> itemPrices;
    private Context context;

    public SuggetionAdapter(ArrayList<Integer> itemImages, ArrayList<String> itemNames,ArrayList<String> itemPrices, Context context){
        this.itemImages = itemImages;
        this.itemNames = itemNames;
        this.itemPrices = itemPrices;
        this.context = context;
    }

    @NonNull
    @Override
    public SuggetionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_furniture, parent, false);
        return new SuggetionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggetionHolder holder, int position) {
        Picasso.get().load(itemImages.get(position)).placeholder(R.drawable.ic_profile).into(holder.imageFurniture);
        holder.textName.setText(itemNames.get(position));
        holder.textPrice.setText("₹"+ itemPrices.get(position));
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    static class SuggetionHolder extends RecyclerView.ViewHolder{
        TextView textName, textPrice;
        ImageView imageFurniture;
        public SuggetionHolder(@NonNull View itemView) {
            super(itemView);

            imageFurniture = itemView.findViewById(R.id.imageFurniture);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);

        }
    }
}
