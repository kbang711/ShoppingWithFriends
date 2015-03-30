package com.cs2340.shoppingwithfriends.activies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cs2340.shoppingwithfriends.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class goes into more detail for a specific friend chosen.
 */
@SuppressWarnings("ALL")
public class FriendDetail extends ActionBarActivity {
    private TextView textViewName, textViewEmail, textViewRating, textViewNumberOfSalesReports;
    private RatingBar ratingBar;
    private Person friend;
    private Button back;
    static int clickedPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        textViewName = (TextView)findViewById(R.id.textViewNameInput);
        textViewEmail = (TextView)findViewById(R.id.textViewEmailInput);
        //textViewRating = (TextView)findViewById(R.id.textViewRatingInput);
        textViewNumberOfSalesReports = (TextView)findViewById(R.id.textViewNumberOfSalesInput);
        friend = Login.current.getFriends().get(Friends.personClicked);
        clickedPerson = Friends.personClicked;

        textViewName.setText(friend.getName());
        textViewEmail.setText(friend.getEmail());
        //textViewRating.setText("");
        textViewNumberOfSalesReports.setText("0");

        back = (Button)findViewById(R.id.buttonBack);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setNumStars(0);
    }

    /**
     * Goes back to the Friends Page
     * @param view View the app is on
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), Friends.class));
    }

    /**
     * Removes a friend from your friends list
     * @param view View the app is on
     */
    public void removeFriend(View view) {
        //Need to figure out a way to remove yourself from that "friend's" friend list
        //Person friendRemove = Login.current.getFriends().get(Friends.personClicked);
        Login.current.getFriends().remove(Friends.personClicked);
        //Remove yourself from the friend's friends list
        /*for (int i = 0; i < Registration.person.size(); i++) {
            if (Registration.person.get(i).equals(friendRemove)) {
                Registration.person.get(i).friends.remove(i);
            }
        }*/ //I don't know how to get yourself... The if statement above is incorrect
        startActivity(new Intent(getApplicationContext(), Friends.class));
        Toast.makeText(getApplicationContext(), "Removed Friend",
                Toast.LENGTH_SHORT).show();

        try {
            FileOutputStream fos = getApplicationContext().openFileOutput("file", Context.MODE_PRIVATE);
            ObjectOutputStream fileOut = new ObjectOutputStream(fos);
            fileOut.writeObject(Registration.person);
            fileOut.close();
            fos.close();
        } catch (IOException e) {
            Log.e("TEST FILE", "Failed to create file");
        }
        finish();
    }

    /**
     * Views the items of your friend
     * @param view View the app is on
     */
    public void viewItems(View view) {
        startActivity(new Intent(getApplicationContext(), ItemList_Friend.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
