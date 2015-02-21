package com.cs2340.shoppingwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kevin Bang on 2/19/2015.
 */
public class FriendDetail extends ActionBarActivity {
    private TextView textViewName, textViewEmail, textViewRating, textViewNumberOfSalesReports;
    Person friend;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        textViewName = (TextView)findViewById(R.id.textViewNameInput);
        textViewEmail = (TextView)findViewById(R.id.textViewEmailInput);
        textViewRating = (TextView)findViewById(R.id.textViewRatingInput);
        textViewNumberOfSalesReports = (TextView)findViewById(R.id.textViewNumberOfSalesInput);
        friend = Person.friends.get(Friends.personClicked);

        textViewName.setText(friend.getName());
        textViewEmail.setText(friend.getEmail());
        textViewRating.setText("");
        textViewNumberOfSalesReports.setText("0");

        back = (Button)findViewById(R.id.buttonBack);
    }

    /**
     * Goes back to the Friends Page
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), Friends.class));
    }

    public void removeFriend(View view) {
        //Need to figure out a way to remove yourself from that "friend's" friend list
        Person.friends.remove(Friends.personClicked);
        startActivity(new Intent(getApplicationContext(), Friends.class));
        Toast.makeText(getApplicationContext(), "Removed Friend",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
