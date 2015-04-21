package com.cs2340.shoppingwithfriends.activies;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Basic Class that helps store and get the email, username,
 * and password of a user in one object.
 * Created by Kevin Bang on 2/5/2015.
 */
@SuppressWarnings("ALL")
public class Person implements Serializable{
    private final String name;
    private final String email;
    private final String username;
    private final String password;
    private static int rating = 0;
    private static int sales = 0;
    private ArrayList<Person> friends = new ArrayList<>();//dependency injection
    private ArrayList<Item> items = new ArrayList<>();//dependency injection

    /**
     * Constructor for a person that sets the email, username, and password
     * @param email Email address of the person
     * @param username Username of the person
     * @param password Password of the person
     */
    public Person(String name, String email, String username, String password, ArrayList<Person> friends, ArrayList<Item> items) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rating = 0;
        this.sales = 0;
        this.friends = friends;
        this.items = items;
    }

    /**
     * Gets the email of Person
     * @return Email of the person
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the username of the Person
     * @return Username of the person
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Gets the password of the Person
     * @return Password of the person
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the name of the Person
     * @return Name of the Person
     */
    public String getName() { return this.name; }

    /**
     * Adds friend into the ArrayList for the person
     * @param name Name of the friend
     * @param email Email of the friend
     * @param username Username of the friend
     */
    public static void addFriend(String name, String email, String username) {
        //friends.add(new Person(name, email, username, ""));
    }

    /**public void saveText(PrintWriter writer) {
        writer.println(name + "\t" + email + "\t" + username + "\t" + password);
    }

    public static Person parseEntry(String line) {
        String[] tok = line.split("[\t\n]");
        return new Person(tok[0], )
    }**/

    public static void addItem(String name, double price) {

        //items.add(new Item(name, price));
    }

    /**
     * Sets the rating
     * @param rate Rating you want to give the person
     */
    public static void setRating(int rate) {
        rating = rate;
    }

    /**
     * Gets the rating
     * @return rating
     */
    public static int getRating() {return rating;}

    /**
     * Sets the sale
     * @param sale Sale you want to set
     */
    public static void setSales(int sale) {
        sales = sale;
    }

    /**
     * Gets the sales
     * @return sales
     */
    public static int getSales() {return sales;}

    /**
     * Gets the items
     * @return items
     */
    public ArrayList<Item> getItems() { return this.items;}

    /**
     * Gets your friends
     * @return friends
     */
    public ArrayList<Person> getFriends() { return this.friends;}
}
