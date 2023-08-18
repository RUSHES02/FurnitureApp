package com.example.furnitureapp;

import java.util.ArrayList;

public class SharedData {
    private static SharedData instance;
    private ArrayList<Furniture> cartItems;
    private ArrayList<CartItem> selctedItems;


    private SharedData() {
    }

    static {
        instance = new SharedData();
        instance.cartItems = new ArrayList<>();
        instance.selctedItems = new ArrayList<>();
    }

    public static SharedData getInstance() {
        return instance;
    }

    public ArrayList<Furniture> getCartFurnitures() {
        return cartItems;
    }

    public ArrayList<CartItem> getSelectedFunitures() {
        return selctedItems;
    }


}

