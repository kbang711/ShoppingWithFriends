package com.cs2340.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class AddFriend extends ActionBarActivity {
    private EditText username = null;
    private EditText email = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        username = (EditText)findViewById(R.id.add_friend_username);
        email = (EditText)findViewById(R.id.add_friend_email);
    }

    public void addFriend(View view) {
        boolean exists = false;

        if (checkifFriend(username.getText().toString())){
            startActivity(new Intent(getApplicationContext(), Friends.class));
            Toast.makeText(getApplicationContext(), "Friend Already Added", Toast.LENGTH_SHORT).show();
        } else {

            for (int i = 0; i < Registration.person.size(); i++) {
                if (Registration.person.get(i).getEmail().
                        compareToIgnoreCase(email.getText().toString()) == 0) {
                    if (Registration.person.get(i).getName().
                            compareToIgnoreCase(username.getText().toString()) == 0) {
                        Person.addFriend("", username.getText().toString(), email.getText().toString());
                        exists = true;
                    }
                }
            }
            if (!exists) {
                Toast.makeText(getApplicationContext(), "Friend Not Found", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(getApplicationContext(), Friends.class));
                Toast.makeText(getApplicationContext(), "Friend Added", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkifFriend(String name){
        ArrayList<String> nameArr = new ArrayList<String>();
        for (Person item : Person.friends){
            nameArr.add(item.getName());
        }


        boolean isFriend = false;
        for(String item : nameArr){
            if (name.compareToIgnoreCase(item) == 0){
                isFriend = true;
            }
        }
        return isFriend;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
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
