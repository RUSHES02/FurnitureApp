package com.example.furnitureapp.activities;

import static java.security.AccessController.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.furnitureapp.R;
import com.example.furnitureapp.adapters.ExploreHorizontalAdapter;

import java.security.AccessController;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExploreHorizontalAdapter.RecyclerViewClickListener {

    private ArrayList<Integer> itemImages = new ArrayList<>();
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<String> itemPrices = new ArrayList<>();
    RecyclerView recyclerView;
    ExploreHorizontalAdapter exploreHorizontalAdapter;
    Context context;
    private Handler handler;
    
    ImageView imageFurniture;
    TextView textName, textPrice;
    ImageButton imageButtonOpen;
    private int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemImages.add(R.drawable.furniture_1);
        itemNames.add("Cupboard");
        itemPrices.add("15000");

        itemImages.add(R.drawable.furniture_2);
        itemNames.add("Pink Couch");
        itemPrices.add("25000");

        itemImages.add(R.drawable.furniture_3);
        itemNames.add("Table");
        itemPrices.add("1000000");

        itemImages.add(R.drawable.furniture_4);
        itemNames.add("Bed");
        itemPrices.add("30000");


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView = findViewById(R.id.recycleViewHorizontal);
        recyclerView.setLayoutManager(layoutManager);
        exploreHorizontalAdapter = new ExploreHorizontalAdapter(itemImages, itemNames, itemPrices, this,this);
        recyclerView.setAdapter(exploreHorizontalAdapter);

        imageFurniture = findViewById(R.id.imageFurniture);
        textName = findViewById(R.id.textItemName);
        textPrice = findViewById(R.id.textPrice);
        imageButtonOpen = findViewById(R.id.imageButtonOpen);

        handler = new Handler();
        updateImage();
    }

    private void updateImage() {
        currentIndex = (currentIndex + 1) % 4;
        imageFurniture.setImageResource(itemImages.get(currentIndex));
        textName.setText(itemNames.get(currentIndex));
        textPrice.setText("₹" + itemPrices.get(currentIndex));

        imageButtonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductPage.class);
                intent.putExtra("image", itemImages.get(currentIndex));
                intent.putExtra("name", itemNames.get(currentIndex));
                intent.putExtra("price", itemPrices.get(currentIndex));
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
        // Handle item click here, e.g., start a new activity
        Intent intent = new Intent(this, ProductPage.class);
        intent.putExtra("image", itemImages.get(position));
        intent.putExtra("name", itemNames.get(position));
        intent.putExtra("price", itemPrices.get(position));
        startActivity(intent);
    }
}