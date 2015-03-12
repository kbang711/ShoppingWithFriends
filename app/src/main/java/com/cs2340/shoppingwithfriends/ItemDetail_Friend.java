package com.cs2340.shoppingwithfriends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Kevin Bang on 3/12/2015.
 */
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
        item = Login.current.friends.get(ItemList_Friend.friendClicked).getItems().get(ItemList_Friend.itemClicked);
        textViewName = (TextView)findViewById(R.id.textViewNameInput);
        textViewPrice = (TextView)findViewById(R.id.textViewPriceInput);
        textViewLocation = (TextView)findViewById(R.id.textViewLocationInput);

        textViewName.setText(item.getItemName());
        textViewPrice.setText('$' + String.valueOf(item.getItemPrice()));
        textViewLocation.setText("N/A");
    }

    /**
     * Goes back to the Friends Page
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), ItemList_Friend.class));
    }

    public void removeItem(View view) {
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
