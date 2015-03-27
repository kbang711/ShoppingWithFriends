package com.cs2340.shoppingwithfriends;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/**
 * This class goes into detail of an item in your friend's item list.
 */
@SuppressWarnings("ALL")
public class ItemDetail_Friend extends ActionBarActivity {
    Item item;
    private TextView textViewName, textViewPrice, textViewLocation;
    static int personClicked;
    static int itemChosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_friend);

        personClicked = ItemList_Friend.friendClicked;
        itemChosen = ItemList_Friend.itemClicked;
        item = Login.current.getFriends().get(ItemList_Friend.friendClicked).getItems().get(ItemList_Friend.itemClicked);
        textViewName = (TextView)findViewById(R.id.textViewNameInput);
        textViewPrice = (TextView)findViewById(R.id.textViewPriceInput);
        textViewLocation = (TextView)findViewById(R.id.textViewLocationInput);

        textViewName.setText(item.getItemName());
        textViewPrice.setText('$' + String.valueOf(item.getItemPrice()));
        textViewLocation.setText(item.getItemLocation());
    }

    /**
     * Goes back to the Friends Page
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), ItemList_Friend.class));
    }

    /**
     * Sends the deal to the friend
     * @param view View the app is on
     */
    public void sendDeal(View view) {
        startActivity(new Intent(getApplicationContext(), SendDeal.class));
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
