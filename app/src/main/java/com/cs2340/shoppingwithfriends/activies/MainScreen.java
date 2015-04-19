package com.cs2340.shoppingwithfriends.activies;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
//Added more imports
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import com.cs2340.shoppingwithfriends.R;


/**
 * This class is the main screen of the app. You can click buttons to go to different pages.
 */
@SuppressWarnings("UnusedParameters")
public class MainScreen extends ActionBarActivity {
    /**
     * Called when activity is first created
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        Button gotoFriends = (Button)findViewById(R.id.mainscreen_friend_button);
        gotoFriends.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainScreen.this,Friends.class));
                        overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
                    }
                }
        );

        for (int i = 0; i < Login.current.getItems().size(); i++) {
            if (Login.current.getItems().get(i).getItemFound()) {
                Toast.makeText(getApplicationContext(), "Deal Found: " + Login.current.getItems().get(i).getItemName(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Goes to the Add Item screen
     * @param view View the app is on
     */
    public void addItem(View view) {
        startActivity(new Intent(MainScreen.this, AddItem.class));
        overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
    }

    /**
     * Goes to the Items List
     * @param view View the app is on
     */
    public void items(View view) {
        startActivity(new Intent(MainScreen.this, ItemList_Personal.class));
        overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
    }

    /**
     * Logs you out to the start screen
     * @param view View the app is on
     */
    public void logout(View view) {
        startActivity(new Intent(MainScreen.this, MainActivity.class));
        overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
        finish();
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
