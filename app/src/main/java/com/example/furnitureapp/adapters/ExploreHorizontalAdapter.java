package com.example.furnitureapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnitureapp.R;

import java.util.ArrayList;

import com.example.furnitureapp.activities.ProductPage;
import com.squareup.picasso.Picasso;

public class ExploreHorizontalAdapter extends RecyclerView.Adapter<ExploreHorizontalAdapter.ExploreHolder> {

    private ArrayList<Integer> itemImages;
    private ArrayList<String> itemNames;
    private ArrayList<String> itemPrices;
    private Context context;
    private RecyclerViewClickListener listener;

    public ExploreHorizontalAdapter(ArrayList<Integer> itemImages, ArrayList<String> itemNames,ArrayList<String> itemPrices, RecyclerViewClickListener listener, Context context){
        this.itemImages = itemImages;
        this.itemNames = itemNames;
        this.itemPrices = itemPrices;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExploreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_furniture, parent, false);
        return new ExploreHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreHolder holder, int position) {
        Picasso.get().load(itemImages.get(position)).placeholder(R.drawable.ic_profile).into(holder.imageFurniture);
        holder.textName.setText(itemNames.get(position));
        holder.textPrice.setText("₹"+ itemPrices.get(position));
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    public interface RecyclerViewClickListener {
        void onItemClick(int position);
    }

    class ExploreHolder extends RecyclerView.ViewHolder{
        TextView textName, textPrice;
        ImageView imageFurniture;
        public ExploreHolder(@NonNull View itemView, final RecyclerViewClickListener listener) {
            super(itemView);

            imageFurniture = itemView.findViewById(R.id.imageFurniture);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
