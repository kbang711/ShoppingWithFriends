package com.cs2340.shoppingwithfriends;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//Added more imports
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    /* Added variables - Kevin Bang*/
    private EditText username = null;
    private EditText password = null;
    private TextView attempts;
    private Button login, initRegister;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Adding username, pw, and attempts
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        attempts = (TextView)findViewById(R.id.textView5);
        attempts.setText(Integer.toString(counter));
        login = (Button)findViewById(R.id.button1);
        initRegister = (Button)findViewById(R.id.button_initialRegister);
        initRegister.setOnClickListener(this);
    }

    /**
     * Method for when a button is clicked
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_initialRegister:
                initRegisterClick();
                break;
        }
    }

    /**
     * Starts the registration page when Register button is clicked
     */
    private void initRegisterClick() {
        startActivity(new Intent("com.cs2340.shoppingwithfriends.Registration"));
    }

    /* Login Method
     */
    public void login(View view) {
        if(username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Redirecting...",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials",
                    Toast.LENGTH_SHORT).show();
            attempts.setBackgroundColor(Color.RED);
            counter--;
            attempts.setText(Integer.toString(counter));
            if(counter == 0) {
                login.setEnabled(false);
            }
        }
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
