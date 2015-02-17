package com.cs2340.shoppingwithfriends;

/**
 * Created by jli on 2/17/15.
 */
public class FriendsListObj {

    private String name;
    private String other;

    public FriendsListObj(String name, String other){
        this.name = name;
        this.other = other;
    }

    public String getName(){
        return this.name;
    }

    public String getOther(){
        return this.other;
    }

}
