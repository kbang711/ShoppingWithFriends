package com.cs2340.shoppingwithfriends;

/**
 * Created by Kevin Bang on 3/4/2015.
 */
public class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getItemName() { return this.name;}
}
