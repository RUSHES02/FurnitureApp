package com.example.furnitureapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.furnitureapp.Furniture;
import com.example.furnitureapp.R;
import com.example.furnitureapp.SharedData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductPage extends AppCompatActivity {



    ArrayList<Furniture> cartItems = new ArrayList<>();
    ImageView imageBack, imagePageCart, imageProduct, imageLike, imageCart, imageCartDot;
    TextView textPrice, textName;
    Button buttonAddCart;
    boolean like = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        SharedData sharedData = SharedData.getInstance();
        cartItems = sharedData.getCartFurnitures();

        int itemImage = getIntent().getIntExtra("image", 5);
        String itemName = getIntent().getStringExtra("name");
        int itemPrice = getIntent().getIntExtra("price", 2000);

        imageBack = findViewById(R.id.imageBack);
        imagePageCart = findViewById(R.id.imagePageCart);
        imageProduct = findViewById(R.id.imageProduct);
        imageLike = findViewById(R.id.imageLike);
        textName  = findViewById(R.id.textName);
        textPrice = findViewById(R.id.textPrice);
        buttonAddCart = findViewById(R.id.buttonAddCart);
        imageCart = findViewById(R.id.imagePageCart);
        imageCartDot = findViewById(R.id.imageCartDot);

        imageCartDot.setVisibility(View.GONE);
        if(cartItems.size() > 0){
            imageCartDot.setVisibility(View.VISIBLE);
        }

        Picasso.get().load(itemImage).placeholder(R.drawable.ic_profile).into(imageProduct);
        textPrice.setText("₹"+ itemPrice);
        textName.setText(itemName);
        imageLike.setImageResource(R.drawable.ic_heart_outline);

        Log.d("Cart Start", String.valueOf(cartItems.size()));
        imageLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like = !like;
                if(like){
                    imageLike.setImageResource(R.drawable.ic_heart_filled);
                }else {
                    imageLike.setImageResource(R.drawable.ic_heart_outline);
                }
            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        buttonAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Furniture furniture = new Furniture(itemImage,itemName,itemPrice);
                cartItems.add(furniture);
                Log.d("Cart add", String.valueOf(cartItems.size()));
                Toast toast = Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        imageCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Cart.class);
                startActivity(intent);
            }
        });
    }
}