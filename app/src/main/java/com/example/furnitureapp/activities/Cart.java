package com.example.furnitureapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.furnitureapp.CartItem;
import com.example.furnitureapp.Furniture;
import com.example.furnitureapp.R;
import com.example.furnitureapp.SharedData;
import com.example.furnitureapp.adapters.CartAdapter;

import java.util.ArrayList;

public class Cart extends AppCompatActivity implements CartAdapter.RecyclerViewClickListener {

    ArrayList<Furniture> furnitures = new ArrayList<>();
    ArrayList<CartItem> cartItems = new ArrayList<>();
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    Context context;
    ImageView imageBack;
    TextView textSelectedPrice, textSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SharedData sharedData = SharedData.getInstance();
        furnitures = sharedData.getCartFurnitures();

        imageBack = findViewById(R.id.imageBack);
        textSelectedPrice = findViewById(R.id.textSelectedPrice);
        textSubtotal = findViewById(R.id.textSubtotal);

        Log.d("Cart page Start", String.valueOf(furnitures.size()));

        for (Furniture furniture: furnitures){
            if(!isPresent(furniture.getName())){
                CartItem item = new CartItem(furniture.getImage(),furniture.getName(),furniture.getPrice(),false, 1);
                cartItems.add(item);
            }
        }

        int itemImage = getIntent().getIntExtra("image", 5);
        String itemName = getIntent().getStringExtra("name");
        String itemPrice = getIntent().getStringExtra("price");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.recyclerCart);
        recyclerView.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(cartItems, this,this);
        recyclerView.setAdapter(cartAdapter);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean isPresent(String itemName){
        for (CartItem item : cartItems) {
            if (item.getName().equals(itemName)) {
                item.setQty(item.getQty() + 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onItemClick(int cartValue) {
        Log.d("cart value" , String.valueOf(cartValue));
        textSelectedPrice.setText("₹" + String.valueOf(cartValue));
        textSubtotal.setText("₹" + String.valueOf(cartValue + 30));

    }
}