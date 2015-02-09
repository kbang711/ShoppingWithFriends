package com.cs2340.shoppingwithfriends;

/**
 * Basic Class that helps store and get the email, username,
 * and password of a user in one object.
 * Created by Kevin Bang on 2/5/2015.
 */
public class Person {
    private String name, email, username, password;

    /**
     * Constructor for a person that sets the email, username, and password
     * @param email Email address of the person
     * @param username Username of the person
     * @param password Password of the person
     */
    public Person(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
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
}
