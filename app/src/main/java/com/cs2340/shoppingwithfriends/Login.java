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

/**
 * Class that does the Login Page.
 */
public class Login extends ActionBarActivity {

    /* Added variables - Kevin Bang*/
    private EditText username = null;
    private EditText password = null;
    private TextView attempts;
    private Button login, cancel;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Adding username, pw, and attempts
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        attempts = (TextView)findViewById(R.id.textView5);
        attempts.setText(Integer.toString(counter));
        Button login = (Button)findViewById(R.id.button1);
        Button cancel = (Button)findViewById(R.id.cancel);

        cancel.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }
                }
        );
    }

    /**
     * Logins the User.
     * Checks to make sure all fields are filled, Username and Password are a match, and tells
     * the user if the username and password aren't a match.
     * @param view
     */
    public void login(View view) {
//        startActivity(new Intent(Login.this, MainScreen.class));
        if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all fields",
                    Toast.LENGTH_SHORT).show();
        } else if(Registration.checkCredentials(username.getText().toString(),
                password.getText().toString())) {
            startActivity(new Intent(getApplicationContext(), MainScreen.class));
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
