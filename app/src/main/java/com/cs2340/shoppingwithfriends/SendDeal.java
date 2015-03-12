package com.cs2340.shoppingwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Kevin Bang on 3/12/2015.
 */
public class SendDeal extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_deal);
    }

    /**
     * Goes back to the Friends Page
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), ItemDetail_Friend.class));
    }

    public void sendDeal(View view) {
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
