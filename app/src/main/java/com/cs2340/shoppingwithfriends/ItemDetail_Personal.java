package com.cs2340.shoppingwithfriends;

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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Kevin Bang on 3/11/2015.
 */
public class ItemDetail_Personal extends ActionBarActivity {
    Item item;
    private TextView textViewName, textViewPrice, textViewLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_personal);

        item = Person.items.get(ItemList_Personal.itemClicked);

        textViewName = (TextView)findViewById(R.id.textViewNameInput);
        textViewPrice = (TextView)findViewById(R.id.textViewPriceInput);
        textViewLocation = (TextView)findViewById(R.id.textViewLocationInput);

        textViewName.setText(item.getItemName());
        textViewPrice.setText('$' + String.valueOf(item.getItemName()));
        textViewPrice.setText("N/A");
    }

    /**
     * Goes back to the Friends Page
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), ItemDetail_Personal.class));
    }

    public void removeItem(View view) {
        Item itemRemoved = Person.items.get(ItemList_Personal.itemClicked);
        Person.items.remove(ItemList_Personal.itemClicked);
        startActivity(new Intent(getApplicationContext(), ItemList_Personal.class));
        Toast.makeText(getApplicationContext(), "Item Removed",
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