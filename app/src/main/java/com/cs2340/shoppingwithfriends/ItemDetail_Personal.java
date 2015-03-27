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
 * This class goes into detail of an item in your personal item list.
 */
@SuppressWarnings({"ALL", "UnusedParameters"})
public class ItemDetail_Personal extends ActionBarActivity {
    private Item item;
    private TextView textViewName, textViewPrice, textViewLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail_personal);

        item = Login.current.getItems().get(ItemList_Personal.itemClicked);

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
        startActivity(new Intent(getApplicationContext(), ItemList_Personal.class));
    }

    /**
     * Gets the location
     * @param view View the app is on
     */
    public void getLocation(View view) {
        startActivity(new Intent(getApplicationContext(), Maps.class));
    }

    /**
     * Removes the item from your item list
     * @param view View the app is on
     */
    public void removeItem(View view) {
        //Item itemRemoved = Login.current.getItems().get(ItemList_Personal.itemClicked);
        Login.current.getItems().remove(ItemList_Personal.itemClicked);
        startActivity(new Intent(getApplicationContext(), ItemList_Personal.class));
        Toast.makeText(getApplicationContext(), "Item Removed",
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

        try {
            FileInputStream fis = getApplicationContext().openFileInput("file");
            ObjectInputStream fileIn = new ObjectInputStream(fis);
            Registration.person = (ArrayList<Person>) fileIn.readObject();
            fileIn.close();
            fis.close();
            for(int i = 0; i < Registration.person.size(); i++) {
                Log.d("PULLING", Registration.person.get(i).getName());
                for(int j = 0; i < Registration.person.get(i).getItems().size(); j++) {
                    Log.d("PULLING ITEM", Registration.person.get(i).getItems().get(j).getItemName());
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("TEST FILE", "File not found");
        } catch (IOException e) {
            Log.e("TEST FILE", "IOEXCEPTION");
        } catch (ClassNotFoundException e) {
            Log.e("TEST FILE", "Class not found");
        }

        finish();
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