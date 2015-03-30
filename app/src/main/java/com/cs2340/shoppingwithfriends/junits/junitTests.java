package com.cs2340.shoppingwithfriends.junits;

import android.test.InstrumentationTestCase;

import com.cs2340.shoppingwithfriends.activies.AddFriend;
import com.cs2340.shoppingwithfriends.activies.AddItem;
import com.cs2340.shoppingwithfriends.activies.Login;
import com.cs2340.shoppingwithfriends.activies.Person;
import com.cs2340.shoppingwithfriends.activies.Item;
import com.cs2340.shoppingwithfriends.activies.Registration;
import java.util.ArrayList;

/**
 * Junits for Shopping With Friends
 */
public class junitTests extends InstrumentationTestCase {
    /**
     * Junits for checkCredentials done by Jack Li
     */
    public void testCheckCredentials() {
        Registration.person.clear();

        ArrayList<Person> pFriends = new ArrayList<Person>();
        ArrayList<Item> pItems = new ArrayList<Item>();

        Person p1 = new Person("Jack", "jack@jack.com", "jack26", "password123", pFriends, pItems);
        Person p2 = new Person("Gack", "jack@jack.com", "jack26", "password123", pFriends, pItems);
        Person p3 = new Person("Jack", "gack@jack.com", "jack26", "password123", pFriends, pItems);
        Person p4 = new Person("Jack", "jack@jack.com", "gack26", "password123", pFriends, pItems);
        Person p5 = new Person("Jack", "jack@jack.com", "jack26", "gassword123", pFriends, pItems);

        Registration.person.add(p1);
        Registration.person.add(p2);
        Registration.person.add(p3);
        Registration.person.add(p4);
        Registration.person.add(p5);

        assertTrue("Correct credentials", Registration.checkCredentials("jack26", "password123"));
        assertFalse("Testing wrong username", Registration.checkCredentials("wrong username", "password123"));
        assertFalse("Testing wrong password", Registration.checkCredentials("jack26", "wrong password"));
        assertFalse("Testing wrong username and password", Registration.checkCredentials("wrong username", "wrong password"));
    }

    /**
     * Junits for checkIfFriend by Kevin Bang
     */
    public void testCheckIfFriend() {
        //Setup
        Registration.person.clear();
        AddFriend adding = new AddFriend();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Person mainPerson = new Person("Kevin Bang","bang@gatech.edu", "bang", "bang", friends, items);
        Person test1 = new Person("Friend One", "friend1@gatech.edu", "friend1", "friend1", friends, items);
        Person test2 = new Person("Friend Two", "friend2@gatech.edu", "friend2", "friend2", friends, items);
        Person test3 = new Person("Friend Three", "friend3@gatech.edu", "friend3", "friend3", friends, items);
        Login.current = mainPerson;
        Registration.person.add(mainPerson);
        Registration.person.add(test1);
        Registration.person.add(test2);
        Registration.person.add(test3);
        Registration.person.get(1).getFriends().add(test1);
        Registration.person.get(1).getFriends().add(test2);

        assertTrue("Friend exists", adding.checkIfFriend("Friend One"));
        assertTrue("Friend exists", adding.checkIfFriend("Friend Two"));
        assertFalse("Friend doesn't exist", adding.checkIfFriend("Friend Three"));
    }
}
