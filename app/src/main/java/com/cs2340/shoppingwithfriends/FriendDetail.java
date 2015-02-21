package com.cs2340.shoppingwithfriends;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Kevin Bang on 2/19/2015.
 */
public class FriendDetail extends ActionBarActivity {
    private TextView textViewName, textViewEmail, textViewRating, textViewNumberOfSalesReports;
    Person friend;

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
