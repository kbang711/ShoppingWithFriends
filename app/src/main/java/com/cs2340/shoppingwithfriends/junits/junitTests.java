package com.cs2340.shoppingwithfriends.junits;

import android.test.InstrumentationTestCase;
import android.util.Log;
import android.view.View;

import com.cs2340.shoppingwithfriends.activies.Person;
import com.cs2340.shoppingwithfriends.activies.Item;
import com.cs2340.shoppingwithfriends.activies.Registration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by jli on 3/29/15.
 */
public class junitTests extends InstrumentationTestCase {


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
}
