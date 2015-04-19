package com.cs2340.shoppingwithfriends.junits;

import android.test.InstrumentationTestCase;

import com.cs2340.shoppingwithfriends.activies.AddFriend;
import com.cs2340.shoppingwithfriends.activies.AddItem;
import com.cs2340.shoppingwithfriends.activies.FriendDetail;
import com.cs2340.shoppingwithfriends.activies.Friends;
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

        ArrayList<Person> pFriends = new ArrayList<>();
        ArrayList<Item> pItems = new ArrayList<>();

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

    /**
     * Junits for checkEmailUser by Royce Kim
     */
    public void testCheckEmailUser() {
        Registration.person.clear();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();

        Person p1 = new Person("P1", "p1@p1.com", "User1", "ps1", friends, items);
        Person p2 = new Person("P2", "p2@p2.com", "User2", "ps2", friends, items);

        Registration.person.add(p1);
        Registration.person.add(p2);

        assertTrue("Email exists", Registration.checkEmailUser("p1@p1.com", "test1"));
        assertTrue("Username exists", Registration.checkEmailUser("email@email.com", "User2"));
        assertFalse("Neither exist", Registration.checkEmailUser("email@email.com", "testUser"));
    }

    /**
     * Junits for removeFriend by Abhilash Nair
     */
    public void testRemoveFriend() {
        Registration.person.clear();
        @SuppressWarnings("UnusedAssignment") FriendDetail remove = new FriendDetail();
        @SuppressWarnings("UnusedAssignment") Friends removed = new Friends();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Person mainPerson = new Person("Abhilash Nair","anair35@gatech.edu", "dam", "bam", friends, items);
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

        mainPerson.getFriends().remove(test1);

        assertFalse("Check for removal from friends list", mainPerson.getFriends().contains(test1));
        assertTrue("Check for friend that is not removed from friends list", mainPerson.getFriends().contains(test2));

    }
    /**
     * Junits for AddItem by Jihad Iqbal
     */
    public void testAddItem() {
        Registration.person.clear();
        @SuppressWarnings("UnusedAssignment") AddItem item = new AddItem();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Person mainPerson = new Person("Jihad Iqbal","Iqbal.j@gatech.edu", "jam", "bam", friends, items);
        Login.current = mainPerson;
        Registration.person.add(mainPerson);
        Item one = new Item("phone","target", 2,  true);
        Item two = new Item("car", "walmart", 3, false);
        Item three = new Item("sailboat", "internet", 4, true);
        mainPerson.getItems().add(one);
        mainPerson.getItems().add(two);

        assertTrue("Check if user added item", mainPerson.getItems().contains(one));
        assertTrue("Check if user added item", mainPerson.getItems().contains(mainPerson.getItems().get(1)));
        assertFalse("Check if item three is not in list", mainPerson.getItems().contains(three));


    }
    public void testValidEmail() {
        Registration email = new Registration();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Person Person1 = new Person("Jihad Iqbal","Iqbal.j@gatech.edu", "jam", "bam", friends, items);
        Person Person2 = new Person("Abhi nair","anair35@gatech.edu", "jam", "bam", friends, items);
        Person wrongPerson1 = new Person("James Franco","Iqbal.j@gatech", "jam", "bam", friends, items);
        Person wrongPerson2 = new Person("Dave Franko","Iqbalgatech.edu", "jam", "bam", friends, items);
        String correctEmail = "abhilashnair1994@gmail.com";
        String wrongEmail = "blah@gamcom";

        assertTrue("Should be correct email format", correctEmail.matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"));
        assertFalse("Should be wrong email format ", wrongEmail.matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"));

        assertTrue("Should be correct email format", Person1.getEmail().matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"));
        assertTrue("Should be correct email format", Person2.getEmail().matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"));
        assertFalse("Should be wrong email format", wrongPerson1.getEmail().matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"));
        assertFalse("Should be wrong email format", wrongPerson2.getEmail().matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"));

    }

    public void testPassword() {
        Registration password = new Registration();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Person Person1 = new Person("Jihad Iqbal","Iqbal.j@gatech.edu", "jamsdfkasdfs", "bam", friends, items);
        Person Person2 = new Person("Abhi nair","anair35@gatech.edu", "jam", "bam", friends, items);
        Person Person3 = new Person("James Franco","Iqbal.j@gatech", "123", "bam", friends, items);
        Person Person4 = new Person("Dave Franko","Iqbalgatech.edu", "jam", "bam", friends, items);
        Person Person5 = new Person("Dave Franko","Iqbalgatech.edu", "jam", "bam", friends, items);

        assertTrue("Should be correct password format", Person1.getPassword().matches("[0-9A-Za-z](\\w|\\S)*$"));
        assertTrue("Should be correct password format ", Person2.getPassword().matches("[0-9A-Za-z](\\w|\\S)*$"));
        assertTrue("Should be correct password format ", Person3.getPassword().matches("[0-9A-Za-z](\\w|\\S)*$"));
        assertTrue("Should be correct password format ", Person4.getPassword().matches("[0-9A-Za-z](\\w|\\S)*$"));
        assertTrue("Should be correct password format ", Person5.getPassword().matches("[0-9A-Za-z](\\w|\\S)*$"));

    }

    public void testUsername() {
        Registration username = new Registration();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Person Person1 = new Person("Jihad Iqbal","Iqbal.j@gatech.edu", "jamsdfkasdfs", "bam", friends, items);
        Person Person2 = new Person("Abhi nair","anair35@gatech.edu", "jam", "bam", friends, items);
        Person Person3 = new Person("James Franco","Iqbal.j@gatech", "123", "bam", friends, items);
        Person Person4 = new Person("Dave Franko","Iqbalgatech.edu", "jam", "bam", friends, items);
        Person Person5 = new Person("Dave Franko","Iqbalgatech.edu", "jam", "bam", friends, items);

        assertTrue("Should be correct password format", Person1.getUsername().matches("[0-9A-Za-z](\\w|\\S)*$"));
        assertTrue("Should be correct password format ", Person2.getUsername().matches("[0-9A-Za-z](\\w|\\S)*$"));
        assertTrue("Should be correct password format ", Person3.getUsername().matches("[0-9A-Za-z](\\w|\\S)*$"));
        assertTrue("Should be correct password format ", Person4.getUsername().matches("[0-9A-Za-z](\\w|\\S)*$"));
        assertTrue("Should be correct password format ", Person5.getUsername().matches("[0-9A-Za-z](\\w|\\S)*$"));
    }

    public void testName() {
        Registration email = new Registration();
        ArrayList<Person> friends = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Person Person1 = new Person("Jihad Iqbal","Iqbal.j@gatech.edu", "jamsdfkasdfs", "bam", friends, items);
        Person Person2 = new Person("Abhi nair","anair35@gatech.edu", "jam", "bam", friends, items);
        Person Person3 = new Person("James Franco","Iqbal.j@gatech", "123", "bam", friends, items);
        Person Person4 = new Person("Dave Franko","Iqbalgatech.edu", "jam", "bam", friends, items);
        Person Person5 = new Person("123213 232132","Iqbalgatech.edu", "jam", "bam", friends, items);

        assertTrue("Should be correct name format", Person1.getName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*"));
        assertTrue("Should be correct name format", Person2.getName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*"));
        assertTrue("Should be correct name format", Person3.getName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*"));
        assertTrue("Should be correct name format", Person4.getName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*"));
        assertFalse("Should be correct name format", Person5.getName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*"));
    }

}
