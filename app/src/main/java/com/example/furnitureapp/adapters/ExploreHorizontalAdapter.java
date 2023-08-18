package com.example.furnitureapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnitureapp.Furniture;
import com.example.furnitureapp.R;

import java.util.ArrayList;

import com.example.furnitureapp.SharedData;
import com.example.furnitureapp.activities.ProductPage;
import com.squareup.picasso.Picasso;

public class ExploreHorizontalAdapter extends RecyclerView.Adapter<ExploreHorizontalAdapter.ExploreHolder> {

    private ArrayList<Furniture> furnitures;
    ArrayList<Furniture> cartItems = new ArrayList<>();
    private Context context;
    private RecyclerViewClickListener listener;

    public ExploreHorizontalAdapter(ArrayList<Furniture> furnitures, RecyclerViewClickListener listener, Context context){
        this.furnitures= furnitures;
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

        SharedData sharedData = SharedData.getInstance();
        cartItems = sharedData.getCartFurnitures();

        Furniture furniture = furnitures.get(position);
        Picasso.get().load(furniture.getImage()).placeholder(R.drawable.ic_profile).into(holder.imageFurniture);
        holder.textName.setText(furniture.getName());
        holder.textPrice.setText("₹"+ furniture.getPrice());

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Furniture item = new Furniture(furniture.getImage(),furniture.getName(),furniture.getPrice());
                cartItems.add(furniture);
                Log.d("Cart add", String.valueOf(cartItems.size()));
                Toast toast = Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return furnitures.size();
    }

    public interface RecyclerViewClickListener {
        void onItemClick(int position);
    }

    class ExploreHolder extends RecyclerView.ViewHolder{
        TextView textName, textPrice;
        ImageView imageFurniture;
        ImageButton buttonAdd;
        public ExploreHolder(@NonNull View itemView, final RecyclerViewClickListener listener) {
            super(itemView);

            imageFurniture = itemView.findViewById(R.id.imageFurniture);
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            buttonAdd  = itemView.findViewById(R.id.buttonAdd);

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
