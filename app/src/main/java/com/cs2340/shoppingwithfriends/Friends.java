package com.cs2340.shoppingwithfriends;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Class that does the main Friends page (has option to go to friends list or add friends)
 * Created by Kevin Bang on 2/10/2015.
 */
public class Friends extends ActionBarActivity{

    ArrayList<FriendsListObj> friendListPeople= new ArrayList<FriendsListObj>();
    /**
     * Called when activity is first created
     */
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

        String[] name = new String[1];
        String[] other = new String[1];
        if (Person.friends.size() == 0) {
            name[0] = "";
            other[0] = "";
        } else {
            name = new String[Person.friends.size()];
            other = new String[Person.friends.size()];
            for (int i = 0; i < Person.friends.size(); i++) {
                name[i] = Person.friends.get(i).getName();
                other[i] = Person.friends.get(i).getOther();
            }
        }

        for (int i = 0; i < name.length; i++){
            friendListPeople.add(new FriendsListObj(name[i], other[i]));
        }

        ListAdapter arrFriendAdapter = new FriendsAdapter(this, friendListPeople);

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
