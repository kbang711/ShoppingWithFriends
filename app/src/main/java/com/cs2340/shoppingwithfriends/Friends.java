package com.cs2340.shoppingwithfriends;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Class that does the main Friends page (has option to go to friends list or add friends)
 * Created by Kevin Bang on 2/10/2015.
 */
public class Friends extends Activity{

    ArrayList<FriendsListObj> friendListPeople= new ArrayList<FriendsListObj>();
    /**
     * Called when activity is first created
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        FriendsListObj friend1 = new FriendsListObj("Barack Obama", "America");
        FriendsListObj friend2 = new FriendsListObj("Snoop Dogg", "fo shizzle");
        FriendsListObj friend3 = new FriendsListObj("2 Chainz", "hes got 2 chains");

        friendListPeople.add(friend1);
        friendListPeople.add(friend2);
        friendListPeople.add(friend3);


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
