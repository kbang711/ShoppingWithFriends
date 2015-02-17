package com.cs2340.shoppingwithfriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// We can create custom adapters
class FriendsAdapter extends ArrayAdapter<FriendsListObj> {

    public FriendsAdapter(Context context, List<FriendsListObj> fList){

        super(context, R.layout.friends_list_layout, fList);

    }
    // Override getView which is responsible for creating the rows for our list
    // position represents the index we are in for the array.

    // convertView is a reference to the previous view that is available for reuse. As
    // the user scrolls the information is populated as needed to conserve memory.

    // A ViewGroup are invisible containers that hold a bunch of views and
    // define their layout properties.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // The LayoutInflator puts a layout into the right View
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        // inflate takes the resource to load, the parent that the resource may be
        // loaded into and true or false if we are loading into a parent view.
        View theView = theInflater.inflate(R.layout.friends_list_layout, parent, false);

        // We retrieve the text from the array
        FriendsListObj fPerson = getItem(position);

        // Get the TextView we want to edit
        TextView nameView = (TextView) theView.findViewById(R.id.friends_name_textview);
        TextView otherView = (TextView) theView.findViewById(R.id.friends_other_textview);

        // Put the next TV Show into the TextView
        nameView.setText(fPerson.getName());
        otherView.setText(fPerson.getOther());

        return theView;

    }
}