package com.cs2340.shoppingwithfriends.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cs2340.shoppingwithfriends.R;

import java.util.ArrayList;

/**
 * This class looks at the list of items that you want.
 */
@SuppressWarnings({"ALL", "UnusedParameters"})
public class ItemList_Personal extends ActionBarActivity {
    static int itemClicked;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist_personal);
        ArrayList<Item> itemList = new ArrayList<>();

        String[] itemName = new String[1];
        String[] itemLocation = new String[1];
        double[] itemPrice = new double[1];
        boolean[] itemFound = new boolean[1];
        if (Login.current.getItems().size() == 0) {
            itemName[0] = "No Items Added";
        } else {
            itemName = new String[Login.current.getItems().size()];
            itemLocation = new String[Login.current.getItems().size()];
            itemPrice = new double[Login.current.getItems().size()];
            itemFound = new boolean[Login.current.getItems().size()];
            for (int i = 0; i < Login.current.getItems().size(); i++) {
                itemName[i] = Login.current.getItems().get(i).getItemName();
                itemLocation[i] = Login.current.getItems().get(i).getItemLocation();
                itemPrice[i] = Login.current.getItems().get(i).getItemPrice();
                itemFound[i] = Login.current.getItems().get(i).getItemFound();
            }
        }
        for (int i = 0; i < itemName.length; i++) {
            itemList.add(new Item(itemName[i], itemLocation[i], itemPrice[i], itemFound[i]));
        }
        ListAdapter arrItemAdapter = new ItemAdapter(this, itemList);
        ListView itemListView = (ListView)findViewById(R.id.itemsList);
        itemListView.setAdapter(arrItemAdapter);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Login.current.getItems().size() == 0) {
                    Toast.makeText(getApplicationContext(), "Please add an item first",
                            Toast.LENGTH_SHORT).show();
                } else {
                    itemClicked = position;
                    startActivity(new Intent(getApplicationContext(), ItemDetail_Personal.class));
                    overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
                }
            }
        });
    }

    /**
     * Goes back to the MainScreen
     * @param view View the app is on
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), MainScreen.class));
        overridePendingTransition(R.animator.slide_left_enter, R.animator.slide_left_exit);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
