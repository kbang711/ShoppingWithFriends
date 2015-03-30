package com.cs2340.shoppingwithfriends.activies;

import java.io.Serializable;

/**
 * This is the item class with a name, location name, price, and if it's found.
 */
@SuppressWarnings("SameParameterValue")
class Item implements Serializable {
    private final String name;
    private final String location;
    private final double price;
    private boolean found;

    public Item(String name, String location, double price, boolean found) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.found = found;
    }

    /**
     * Gets the item name
     * @return item name
     */
    public String getItemName() { return this.name;}

    /**
     * Gets the item location name
     * @return item location name
     */
    public String getItemLocation() { return this.location;}

    /**
     * Gets the item price
     * @return item price
     */
    public double getItemPrice() { return this.price;}

    /**
     * Gets if item is found
     * @return true if item is found
     */
    public boolean getItemFound() { return this.found;}

    /**
     * Sets the item to be found
     * @param b true if item is found
     */
    public void setItemFound(@SuppressWarnings("SameParameterValue") boolean b) { found = b;}
}
