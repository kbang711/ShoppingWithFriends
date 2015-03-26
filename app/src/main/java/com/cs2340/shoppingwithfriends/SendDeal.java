package com.cs2340.shoppingwithfriends;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Kevin Bang on 3/12/2015.
 */
public class SendDeal extends ActionBarActivity {
    private EditText price = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_deal);

        price = (EditText)findViewById(R.id.add_price);
    }

    /**
     * Goes back to the Friends Page
     */
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), ItemDetail_Friend.class));
    }

    public void sendDeal(View view) {
        if (price.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all fields",
                    Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(price.getText().toString()) > Login.current.getFriends().
                get(ItemDetail_Friend.personClicked).getItems().get(ItemDetail_Friend.itemChosen).
                getItemPrice()) {
            Toast.makeText(getApplicationContext(), "Price is above Friend's price threshold",
                    Toast.LENGTH_SHORT).show();
        } else {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setContentTitle("Deal Received")
                            .setContentText("Deal Received for " + Login.current.getFriends().
                                    get(ItemDetail_Friend.personClicked).getItems().get(ItemDetail_Friend.itemChosen).
                                    getItemName());
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(001, mBuilder.build());
            Login.current.getFriends().get(ItemDetail_Friend.personClicked).getItems().get(ItemDetail_Friend.itemChosen).setItemFound(true);
            startActivity(new Intent(getApplicationContext(), ItemList_Friend.class));
            Toast.makeText(getApplicationContext(), "Deal Sent",
                    Toast.LENGTH_SHORT).show();
        }

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
