package com.example.furnitureapp;

import java.util.ArrayList;

public class SharedData {
    private static SharedData instance;
    private ArrayList<Integer> cart = new ArrayList<>();

    private SharedData() {
        // Private constructor to prevent direct instantiation
    }

    static {
        instance = new SharedData();
        instance.cart = new ArrayList<>(); // Initialize your shared value here
    }

    public static SharedData getInstance() {
        return instance;
    }

    public ArrayList<Integer> getSharedValue() {
        return cart;
    }

}

