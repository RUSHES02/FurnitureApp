package com.example.furnitureapp;

public class CartItem {

    int image;
    String name;
    int price, qty;
    boolean checked;

    public CartItem(int image, String name, int price, boolean checked, int qty){
        this.image= image;
        this.name = name;
        this.price = price;
        this.checked = checked;
        this. qty = qty;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
