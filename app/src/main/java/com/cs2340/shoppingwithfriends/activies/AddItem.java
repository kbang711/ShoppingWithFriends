package com.cs2340.shoppingwithfriends.activies;
        import android.content.Context;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.content.Intent;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
//Added more imports
        import android.widget.EditText;
        import android.widget.Toast;
        import android.view.View;

        import com.cs2340.shoppingwithfriends.R;

        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.ObjectOutputStream;

/**
 * This class is where we add items to your personal list.
 */
@SuppressWarnings("ALL")
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

    /**
     * Adds item to your personal list
     * @param view View app is on
     */
    public void addItem(View view) {
        if (itemName.getText().toString().equals("") || itemPrice.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please fill in all fields",
                    Toast.LENGTH_SHORT).show();
        } else {

            // this is used to find which item the current one is from Registration.person
            // cant use static current because static current is a COPY of Registration.person (I think...)
            int currentIndex = 0;
            for (int j = 0; j < Registration.person.size(); j++) {
                if (Registration.person.get(j).getEmail().equals(Login.current.getEmail())){
                    currentIndex = j;
                }
            }

            Log.d("asdfasdfasdfasdf", currentIndex + "");
            Registration.person.get(currentIndex).getItems().add(
                    new Item(itemName.getText().toString(), "N/A",
                        Double.parseDouble(itemPrice.getText().toString()), false
                    )
            );
            startActivity(new Intent(getApplicationContext(), MainScreen.class));
            overridePendingTransition(R.animator.slide_right_enter, R.animator.slide_right_exit);

            try {
                FileOutputStream fos = getApplicationContext().openFileOutput("file", Context.MODE_PRIVATE);
                ObjectOutputStream fileOut = new ObjectOutputStream(fos);
                fileOut.writeObject(Registration.person);
                fileOut.close();
                fos.close();
                for (int i = 0; i < Registration.person.size(); i++) {
                    Log.d("PUSHING", Registration.person.get(i).getName());
                    for (int j = 0; j < Registration.person.get(i).getItems().size(); j++) {
                        Log.d("PUSHING ITEM", Registration.person.get(i).getItems().get(j).getItemName());
                    }
                }
            } catch (IOException e) {
                Log.e("TEST FILE", "Failed to create file");
            }
            finish();
        }
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), MainScreen.class));
        overridePendingTransition(R.animator.slide_left_enter, R.animator.slide_left_exit);
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
