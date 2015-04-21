package com.cs2340.shoppingwithfriends.activies;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
//Added more imports
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.cs2340.shoppingwithfriends.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import android.service.textservice.SpellCheckerService.Session;


/**
 * Class that does the Login Page.
 */
@SuppressWarnings({"UnusedParameters", "unchecked"})

public class Login extends ActionBarActivity {

    /* Added variables - Kevin Bang*/
    private EditText username = null;
    private EditText password = null;
    private TextView attempts;
    private int counter = 3;
    public static Person current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Adding username, pw, and attempts
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        attempts = (TextView)findViewById(R.id.textView5);
        attempts.setText(Integer.toString(counter));
        //login(findViewById(R.id.button1));
        //Button login = (Button)findViewById(R.id.button1);
        Button cancel = (Button)findViewById(R.id.cancel);
        LoginButton loginButton = (LoginButton)findViewById(R.id.fb_login_button);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        CallbackManager callbackManager;
        callbackManager = CallbackManager.Factory.create();


        private Session.StatusCallback statusCallback = new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state,
                             Exception exception) {
                if (state.isOpened()) {
                    Log.d("MainActivity", "Facebook session opened.");
                } else if (state.isClosed()) {
                    Log.d("MainActivity", "Facebook session closed.");
                }
            }
        };

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "Success",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainScreen.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Error logging in",
                        Toast.LENGTH_SHORT).show();
            }
        });


        cancel.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(Login.this,MainActivity.class));
                        overridePendingTransition(R.animator.slide_left_enter, R.animator.slide_left_exit);
                    }
                }
        );

        //My way to load instances
        try {
            FileInputStream fis = getApplicationContext().openFileInput("file");
            ObjectInputStream fileIn = new ObjectInputStream(fis);
            Registration.person = (ArrayList<Person>) fileIn.readObject();
            fileIn.close();
            fis.close();
        } catch (FileNotFoundException e) {
            Log.e("TEST FILE", "File not found");
        } catch (IOException e) {
            Log.e("TEST FILE", "IOEXCEPTION");
        } catch (ClassNotFoundException e) {
            Log.e("TEST FILE", "Class not found");
        }
    }

    /**
     * Logins the User.
     * Checks to make sure all fields are filled, Username and Password are a match, and tells
     * the user if the username and password aren't a match.
     * @param view View the app is on
     */
    public void login(View view) {
        //startActivity(new Intent(Login.this, MainScreen.class));
        DataHolder model = DataHolder.getInstance();
        File file = new File(this.getFilesDir(), "data.json");
        model.loadJson(file);

        if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all fields",
                    Toast.LENGTH_SHORT).show();
        } else if(Registration.checkCredentials(username.getText().toString(),
                password.getText().toString())) {
            startActivity(new Intent(getApplicationContext(), MainScreen.class));
            overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
            for (int i = 0; i < Registration.person.size(); i++) {
                if (Registration.person.get(i).getUsername().equals(username.getText().toString())) {
                    current = Registration.person.get(i);
                }
            }
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials",
                    Toast.LENGTH_SHORT).show();
            attempts.setBackgroundColor(Color.RED);
            counter--;
            attempts.setText(Integer.toString(counter));
            if(counter == 0) {
                Toast.makeText(getApplicationContext(), "0 Attempts left",
                        Toast.LENGTH_SHORT).show();
                findViewById(R.id.button1).setEnabled(false);
                //login.setEnabled(false);
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
