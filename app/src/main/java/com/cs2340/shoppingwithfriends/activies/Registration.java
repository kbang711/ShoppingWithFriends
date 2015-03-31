package com.cs2340.shoppingwithfriends.activies;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//Added more imports
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import com.cs2340.shoppingwithfriends.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Class that does the Registration page.
 * Created by Kevin Bang on 1/30/2015.
 */
@SuppressWarnings("UnusedParameters")
public class Registration extends Activity{
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
        //Button register = (Button)findViewById(R.id.buttonRegister);
        Button cancel = (Button)findViewById(R.id.cancel);

        cancel.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(Registration.this,MainActivity.class));
                    }
                }
        );
    }

    /**
     * Registers the user.
     * Checks to make sure each field is filled, the
     * username or email doesn't already exist, and if the passwords don't match.
     * @param view View the app is on
     */
    public void register(View view) {
        DataHolder model = DataHolder.getInstance();

        if (username.getText().toString().equals("") || email.getText().toString().equals("") ||
                password.getText().toString().equals("") ||
                password2.getText().toString().equals("") || name.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in each field",
                    Toast.LENGTH_SHORT).show();
        } else if (!isValidEmailAddress(email)) {
            Toast.makeText(getApplicationContext(), "Invalid email address",
                    Toast.LENGTH_SHORT).show();
        } else if (!testUsername(username)) {
            Toast.makeText(getApplicationContext(), "Invalid username",
                    Toast.LENGTH_SHORT).show();
        } else if (!testPassword(password)) {
            Toast.makeText(getApplicationContext(), "Invalid password",
                    Toast.LENGTH_SHORT).show();
            password.setText("");
        } else if (!testName(name)) {
            Toast.makeText(getApplicationContext(), "Invalid name",
                    Toast.LENGTH_SHORT).show();
        } else if (checkEmailUser(email.getText().toString(), username.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Email or Username already exists",
                    Toast.LENGTH_SHORT).show();
        } else if (!password.getText().toString().equals(password2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords do not match",
                    Toast.LENGTH_SHORT).show();
            password2.setText("");
        } else {
            person.add(new Person(name.getText().toString(), email.getText().toString(),
                    username.getText().toString(), password.getText().toString(), new ArrayList<Person>(), new ArrayList<Item>()));
            startActivity(new Intent(getApplicationContext(), Login.class));
            File file = new File(this.getFilesDir(), "data.json");
            model.saveJson(file);
            //trying out a way to save instances
            try {
                FileOutputStream fos = getApplicationContext().openFileOutput("file", Context.MODE_PRIVATE);
                ObjectOutputStream fileOut = new ObjectOutputStream(fos);
                fileOut.writeObject(person);
                fileOut.close();
                fos.close();
            } catch (IOException e) {
                Log.e("TEST FILE", "Failed to create file");
            }
            finish();
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

    /**
     * Checks if the email is valid
     * @param s Email the user input
     * @return true if valid email
     */
    boolean isValidEmailAddress(EditText s) {
       return s.getText().toString().matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
    }

    /**
     * Tests the password
     * @param s Password user inputs
     * @return true if password matches
     */
    boolean testPassword(EditText s) {
        return s.getText().toString().matches("[0-9A-Za-z](\\w|\\S)*$");
    }

    /**
     * Tests the username
     * @param s Username the user inputs
     * @return true if the username matches
     */
    boolean testUsername(EditText s) {
        return s.getText().toString().matches("[0-9A-Za-z](\\w|\\S)*$");
    }

    /**
     * Tests the name
     * @param s Name the user inputs
     * @return true if the name matches
     */
    boolean testName(EditText s) {
        return s.getText().toString().matches("^([a-zA-Z'-]+\\s+){1,4}[a-zA-z'-]+$");
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

// --Commented out by Inspection START (3/27/2015 2:44 PM):
//    /**
//     * Cancel method to go back to main screen
//     * @param view View the app is on
//     */
//    public void cancel(View view) {
//        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//    }
// --Commented out by Inspection STOP (3/27/2015 2:44 PM)

}
