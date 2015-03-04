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

/**
 * Created by Kevin Bang on 3/4/2015.
 */
public class AddItem extends ActionBarActivity {
    private EditText itemName = null;
    private EditText itemPrice = null;
    /**
     * Called when activity is first created
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemName = (EditText)findViewById(R.id.add_item_name);
        itemPrice = (EditText)findViewById(R.id.add_item_price);
    }

    public void addItem(View view) {
        if (itemName.getText().toString().equals("") || itemPrice.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all fields",
                    Toast.LENGTH_SHORT).show();
        } else {
            Person.addItem(itemName.getText().toString(), Double.parseDouble(
                            itemPrice.getText().toString()));
            startActivity(new Intent(getApplicationContext(), MainScreen.class));
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
