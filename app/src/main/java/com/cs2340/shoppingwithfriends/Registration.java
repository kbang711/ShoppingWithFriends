package com.cs2340.shoppingwithfriends;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
//Added more imports
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.util.ArrayList;

/**
 * Class that does the Registration page.
 * Created by Kevin Bang on 1/30/2015.
 */
public class Registration extends ActionBarActivity {
    private EditText username = null;
    private EditText password = null;
    private EditText password2 = null;
    private EditText email = null;
    private EditText name = null;
    private Button register, cancel;
    public static ArrayList<Person> person = new ArrayList<>();
    /**
     * Called when activity is first created
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = (EditText)findViewById(R.id.editTextName);
        email = (EditText)findViewById(R.id.editTextEmail);
        username = (EditText)findViewById(R.id.editTextUser);
        password = (EditText)findViewById(R.id.editTextPW);
        password2 = (EditText)findViewById(R.id.editTextPW2);
        register = (Button)findViewById(R.id.buttonRegister);
        cancel = (Button)findViewById(R.id.cancel);
    }

    /**
     * Registers the user.
     * Checks to make sure each field is filled, the
     * username or email doesn't already exist, and if the passwords don't match.
     * @param view
     */
    public void register(View view) {
        if (username.getText().toString().equals("") || email.getText().toString().equals("") ||
                password.getText().toString().equals("") ||
                password2.getText().toString().equals("") || name.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in each field",
                    Toast.LENGTH_SHORT).show();
        } else if (checkEmailUser(email.getText().toString(), username.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Email or Username already exists",
                    Toast.LENGTH_SHORT).show();
        } else if (!password.getText().toString().equals(password2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords do not match",
                    Toast.LENGTH_SHORT).show();
        } else if (!isValidEmailAddress(email.getText().toString())) {
            Toast.makeText(getApplicationContext(), "invalid email address",
                    Toast.LENGTH_SHORT).show();
            email.setText("");
        } else {
            person.add(new Person(email.getText().toString(), username.getText().toString(),
                    password.getText().toString()));
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
    }

    /**
     * Gets the list of registered users
     * @return List of registered users
     */
    public static ArrayList<Person> getList() {
        return person;
    }

    /**
     * Checks for existing email or username in the list.
     * @param email Email being checked for
     * @param username Username being checked for
     * @return True if Username or Email already exists in the list.
     */
    public static boolean checkEmailUser(String email, String username) {
        boolean done = false;
        for (int i = 0; i < person.size(); i++) {
            if (person.get(i).getEmail().equals(email)) {
                done = true;
                i = person.size();
            } else if (person.get(i).getUsername().equals(username)) {
                done = true;
                i = person.size();
            }
        }
        return done;
    }

   public boolean isValidEmailAddress(String email) {
       java.util.regex.Pattern p =  java.util.regex.Pattern
               .compile("[A-Za-z0-9._\\%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
       java.util.regex.Matcher m = p.matcher(email);
       return m.matches();
   }

    /**
     * Checks the username and password to make sure both are connected.
     * @param username Username being checked for
     * @param password Password being checked for
     * @return True if Username and Password are a match
     */
    public static boolean checkCredentials(String username, String password) {
        boolean done = false;
        for (int i = 0; i < person.size(); i++) {
            if (person.get(i).getUsername().equals(username)) {
                if (person.get(i).getPassword().equals(password)) {
                    done = true;
                    i = person.size();
                }
            }
        }
        return done;
    }

    /**
     * Cancel method to go back to main screen
     * @param view
     * @return
     */
    public void cancel(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
