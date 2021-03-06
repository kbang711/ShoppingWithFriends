package com.cs2340.shoppingwithfriends.activies;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.cs2340.shoppingwithfriends.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that does the main Friends page (has option to go to friends list or add friends)
 * Created by Kevin Bang on 2/10/2015.
 */
@SuppressWarnings("ALL")
public class Friends extends ActionBarActivity implements Serializable{
    private final ArrayList<Person> friendsList = new ArrayList<>();//dependency injection
    static int personClicked;

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
                       overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
                   }
               }
        );

        String[] friendName = new String[1];
        String[] friendEmail = new String[1];
        String[] friendUsername = new String[1];
        if (Login.current.getFriends().size() == 0) {
            friendName[0] = "No Friends";
            friendEmail[0] = "";
            friendUsername[0] = "";
        } else {
            friendName = new String[Login.current.getFriends().size()];
            friendEmail = new String[Login.current.getFriends().size()];
            for (int i = 0; i < Login.current.getFriends().size(); i++) {
                friendName[i] = Login.current.getFriends().get(i).getName();
                friendEmail[i] = Login.current.getFriends().get(i).getEmail();
                friendUsername[i] = Login.current.getFriends().get(i).getUsername();
            }
        }
        for (int i = 0; i < friendName.length; i++){
            friendsList.add(new Person(friendName[i], friendEmail[i], friendUsername[i], "", null, null));
        }

        ListAdapter arrFriendAdapter = new FriendsAdapter(this, friendsList);
        ListView friendListView = (ListView)findViewById(R.id.friendsList);
        friendListView.setAdapter(arrFriendAdapter);
        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Login.current.getFriends().size() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Add a Friend First",
                            Toast.LENGTH_SHORT).show();
                } else {
                    personClicked = position;
                    startActivity(new Intent(Friends.this, FriendDetail.class));
                    overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
                }
            }
        });
    }


    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), MainScreen.class));
        overridePendingTransition(R.animator.slide_left_enter, R.animator.slide_left_exit);
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
