package com.cs2340.shoppingwithfriends;

import java.io.Serializable;

/**
 * Created by Kevin Bang on 3/4/2015.
 */
public class Item implements Serializable {
    private String name;
    private String location;
    private double price;
    private boolean found;

    public Item(String name, String location, double price, boolean found) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.found = found;
    }

    public String getItemName() { return this.name;}

    public String getItemLocation() { return this.location;}

    public double getItemPrice() { return this.price;}

    public boolean getItemFound() { return this.found;}

    public void setItemFound(boolean b) { found = b;}
}
