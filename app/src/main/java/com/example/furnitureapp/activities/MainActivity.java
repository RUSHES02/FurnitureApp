package com.example.furnitureapp.activities;

import static java.security.AccessController.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.furnitureapp.Furniture;
import com.example.furnitureapp.R;
import com.example.furnitureapp.SharedData;
import com.example.furnitureapp.adapters.ExploreHorizontalAdapter;

import java.security.AccessController;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExploreHorizontalAdapter.RecyclerViewClickListener {

    ArrayList<Furniture> cartItems = new ArrayList<>();
    private ArrayList<Furniture> furnitures = new ArrayList<>();
    RecyclerView recyclerView;
    ExploreHorizontalAdapter exploreHorizontalAdapter;
    Context context;
    private Handler handler;
    
    ImageView imageFurniture, imageCart, imageCartDot;
    TextView textName, textPrice;
    ImageButton imageButtonOpen;
    private int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedData sharedData = SharedData.getInstance();
        cartItems = sharedData.getCartFurnitures();

        Furniture furniture = new Furniture(R.drawable.furniture_1, "Cupboard", 15000);
        furnitures.add(furniture);

        furniture = new Furniture(R.drawable.furniture_2, "Pink Couch",25000);
        furnitures.add(furniture);

        furniture = new Furniture(R.drawable.furniture_3, "Table",100000);
        furnitures.add(furniture);

        furniture = new Furniture(R.drawable.furniture_4, "Bed",30000);
        furnitures.add(furniture);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView = findViewById(R.id.recycleViewHorizontal);
        recyclerView.setLayoutManager(layoutManager);
        exploreHorizontalAdapter = new ExploreHorizontalAdapter(furnitures, this,this);
        recyclerView.setAdapter(exploreHorizontalAdapter);

        imageFurniture = findViewById(R.id.imageFurniture);
        textName = findViewById(R.id.textItemName);
        textPrice = findViewById(R.id.textPrice);
        imageButtonOpen = findViewById(R.id.imageButtonOpen);
        imageCart = findViewById(R.id.imageCart);
        imageCartDot = findViewById(R.id.imageCartDot);

        imageCartDot.setVisibility(View.GONE);
        if(cartItems.size() > 0){
            imageCartDot.setVisibility(View.VISIBLE);
        }
        handler = new Handler();
        updateImage();

        Log.d("Cart Start", String.valueOf(cartItems.size()));
        imageCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Cart.class);
                startActivity(intent);
            }
        });
    }

    private void updateImage() {
        currentIndex = (currentIndex + 1) % 4;
        Furniture item = furnitures.get(currentIndex);
        imageFurniture.setImageResource(item.getImage());
        textName.setText(item.getName());
        textPrice.setText("₹" + item.getPrice());

        imageButtonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Furniture item = furnitures.get(currentIndex);
                Intent intent = new Intent(getApplicationContext(), ProductPage.class);
                intent.putExtra("image", item.getImage());
                intent.putExtra("name", item.getName());
                intent.putExtra("price", item.getPrice());
                startActivity(intent);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateImage();
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    public void onItemClick(int position) {
        Furniture item = furnitures.get(position);
        Intent intent = new Intent(this, ProductPage.class);
        intent.putExtra("image", item.getImage());
        intent.putExtra("name", item.getName());
        intent.putExtra("price", item.getPrice());
        startActivity(intent);
    }
}