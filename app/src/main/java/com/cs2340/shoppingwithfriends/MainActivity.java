package com.cs2340.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    /* Added variables - Kevin Bang*/
    private Button login, initRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Adding username, pw, and attempts
        login = (Button)findViewById(R.id.button1);
        initRegister = (Button)findViewById(R.id.button_initialRegister);
    }

    /**
     * Method for when initial Registration button is clicked
     */
    public void initRegisterClick(View view) {
        startActivity(new Intent(getApplicationContext(), Registration.class));
    }

    /* Login Method
     */
    public void login(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
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
