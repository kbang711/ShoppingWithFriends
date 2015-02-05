package com.cs2340.shoppingwithfriends;

/**
 * Created by Kevin Bang on 2/5/2015.
 */
public class Person {
    private String email, username, password;

    /**
     * Constructor for a person
     * @param email Email address of the person
     * @param username Username of the person
     * @param password Password of the person
     */
    public Person(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
