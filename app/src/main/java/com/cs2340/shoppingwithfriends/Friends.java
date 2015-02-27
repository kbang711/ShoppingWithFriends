package com.cs2340.shoppingwithfriends;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Class that does the main Friends page (has option to go to friends list or add friends)
 * Created by Kevin Bang on 2/10/2015.
 */
public class Friends extends ActionBarActivity{

    /**
     * Called when activity is first created
     */
    ArrayList<Person> friendsList = new ArrayList<Person>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);


        Button addFriend = (Button)findViewById(R.id.add_friend);
        addFriend.setOnClickListener(
               new Button.OnClickListener(){
                   public void onClick(View v) {
                       startActivity(new Intent(Friends.this, AddFriend.class));
                   }
               }
        );

        String[] friendName = new String[1];
        String[] friendEmail = new String[1];
        String[] friendUsername = new String[1];
        if (Person.friends.size() == 0) {
            friendName[0] = "No Friends";
            friendEmail[0] = "";
            friendUsername[0] = "";
        } else {
            friendName = new String[Person.friends.size()];
            friendEmail = new String[Person.friends.size()];
            for (int i = 0; i < Person.friends.size(); i++) {
                friendName[i] = Person.friends.get(i).getName();
                friendEmail[i] = Person.friends.get(i).getEmail();
                friendUsername[i] = Person.friends.get(i).getUsername();
            }
        }
        for (int i = 0; i < friendName.length; i++){
            friendsList.add(new Person(friendName[i], friendEmail[i], friendUsername[i], ""));
        }

        ListAdapter arrFriendAdapter = new FriendsAdapter(this, friendsList);
        ListView friendListView = (ListView)findViewById(R.id.friendsList);
        friendListView.setAdapter(arrFriendAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
