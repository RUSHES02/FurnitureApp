package com.example.furnitureapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.furnitureapp.R;
import com.squareup.picasso.Picasso;

public class ProductPage extends AppCompatActivity {

    ImageView imageBack, imagePageCart, imageProduct, imageLike;
    TextView textPrice, textName;
    boolean like = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        int itemImage = getIntent().getIntExtra("image", 5);
        String itemName = getIntent().getStringExtra("name");
        String itemPrice = getIntent().getStringExtra("price");

        imageBack = findViewById(R.id.imageBack);
        imagePageCart = findViewById(R.id.imagePageCart);
        imageProduct = findViewById(R.id.imageProduct);
        imageLike = findViewById(R.id.imageLike);
        textName  = findViewById(R.id.textName);
        textPrice = findViewById(R.id.textPrice);

        Picasso.get().load(itemImage).placeholder(R.drawable.ic_profile).into(imageProduct);
        textPrice.setText("₹"+ itemPrice);
        textName.setText(itemName);
        imageLike.setImageResource(R.drawable.ic_heart_outline);

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
    }
}