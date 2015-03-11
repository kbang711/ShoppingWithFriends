package com.cs2340.shoppingwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by Kevin Bang on 3/9/2015.
 */
public class ItemList_Personal extends ActionBarActivity {
    static int itemClicked;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist_personal);
        ArrayList<Item> itemList = new ArrayList<Item>();

        String[] itemName = new String[1];
        double[] itemPrice = new double[1];
        if (Person.items.size() == 0) {
            itemName[0] = "No Items Added";
        } else {
            itemName = new String[Person.items.size()];
            itemPrice = new double[Person.items.size()];
            for (int i = 0; i < Person.items.size(); i++) {
                itemName[i] = Person.items.get(i).getItemName();
                itemPrice[i] = Person.items.get(i).getItemPrice();
            }
        }
        for (int i = 0; i < itemName.length; i++) {
            itemList.add(new Item(itemName[i], itemPrice[i]));
        }
        ListAdapter arrItemAdapter = new ItemAdapter(this, itemList);
        ListView itemListView = (ListView)findViewById(R.id.itemsList);
        itemListView.setAdapter(arrItemAdapter);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Person.items.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Please add an item first",
                            Toast.LENGTH_SHORT).show();
                } else {
                    itemClicked = position;
                    startActivity(new Intent(getApplicationContext(), ItemDetail_Personal.class));
                }
            }
        });
    }

    /**
     * Goes back to the MainScreen
     * @param view
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), MainScreen.class));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
