package com.cs2340.shoppingwithfriends.activies;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

import com.cs2340.shoppingwithfriends.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;


@SuppressWarnings("unchecked")
public class MainActivity extends ActionBarActivity {

    /* Added variables - Kevin Bang*/
    private Button login, initRegister;
    private ImageView mImageView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Adding username, pw, and attempts
        Button login = (Button)findViewById(R.id.button1);
        Button initRegister = (Button)findViewById(R.id.button_initialRegister);

        login.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this,Login.class));
                        overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
                    }
                }
        );

        initRegister.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(MainActivity.this,Registration.class));
                        overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);
                    }
                }
        );


        mImageView = (ImageView) findViewById( R.id.main_logo );
        mImageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( mImageView.getVisibility() == View.VISIBLE ) {
                    hideImageCircular();
                }
                else {
                    revealImageCircular();
                }
            }
        });


        try {
            FileInputStream fis = getApplicationContext().openFileInput("file");
            ObjectInputStream fileIn = new ObjectInputStream(fis);
            Registration.person = (ArrayList<Person>) fileIn.readObject();
            /*for (int i = 0; i < Registration.person.size(); i++) {
                Registration.person.get(i).items = Registration.person.get(i).items;
            }*/
            //deleteFile("file");
            fileIn.close();
            fis.close();
            for(int i = 0; i < Registration.person.size(); i++) {
                Log.d("PULLING", Registration.person.get(i).getName());
                //KEEPS CRASHING THE APP AFTER PRESSING LOGOUT
                /*for(int j = 0; i < Registration.person.get(i).items.size(); j++) {
                    //Log.d("PULLING ITEM", Registration.person.get(i).items.get(j).getItemName());
                }*/
            }
        } catch (FileNotFoundException e) {
            Log.e("TEST FILE", "File not found");
        } catch (IOException e) {
            Log.e("TEST FILE", "IOException");
        } catch (ClassNotFoundException e) {
            Log.e("TEST FILE", "Class not found");
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

    @Override
    public void onBackPressed() {
        //Does Nothing
    }


    private void hideImageCircular() {
        int x = getX();
        int y = getY();
        int radius = getRadius();

        Animator anim =  ViewAnimationUtils.createCircularReveal(mImageView, x, y, radius, 0);

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mImageView.setVisibility( View.INVISIBLE );
            }
        });

        anim.start();
    }

    private void revealImageCircular() {
        int x = getX();
        int y = getY();
        int radius = getRadius();

        Animator anim = ViewAnimationUtils.createCircularReveal(mImageView, x, y, 0, radius);

        anim.setDuration( 1000 );
        anim.addListener( new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mImageView.setVisibility( View.VISIBLE );
            }
        });

        anim.start();
    }

    private int getX() {
        return ( mImageView.getLeft() + mImageView.getRight() ) / 2;
    }

    private int getY() {
        return ( mImageView.getTop() + mImageView.getBottom() ) / 2;
    }

    private int getRadius() {
        return mImageView.getWidth();
    }
}
