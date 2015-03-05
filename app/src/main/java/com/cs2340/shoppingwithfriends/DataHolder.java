package com.cs2340.shoppingwithfriends;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Kevin Bang on 3/4/2015.
 */
public class DataHolder {
    private static DataHolder instance = new DataHolder();

    public static DataHolder getInstance() { return instance; }

    ArrayList<Person> users = new ArrayList<Person>();

    private DataHolder() {
        makeData();
    }

    private void makeData() {
        for (int i = 0; i < Registration.person.size(); i++) {
            users.add(Registration.person.get(i));
        }
    }

    /**public void saveText(File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(users.size());
            Log.d("DEBUG", "Wrote user count: " + users.size());
            for (Person u : users) {
                u.saveText(writer);
                Log.d("DEBUG", "Wrote user: " + u.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            Log.e("ModelSingleton", "Failed to open save text file!");
        }
    }

    public void loadText(File file) {
        try {
            Log.d("DEBUG", "File size: " + file.length());
            BufferedReader reader = new BufferedReader((new FileReader(file)));
            users.clear();
            String countStr = reader.readLine();
            Log.d("DEBUG", "Read " + countStr + " users");
            int count = Integer.parseInt(countStr);
            for (int i = 0; i < count; i++) {
                String line = reader.readLine();
                Log.d("DEBUG", "Processing line: " + line);
                Person u = Person.parseEntry(line);
                users.add(u);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Log.e("ModelSingleton", "Failed to open text file for loading!");
        } catch (IOException e) {
            Log.e("ModelSingleton", "Error reading an entry from text file");
        }
    }**/

    public void saveJson(File out) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(out);
            Gson gson = new Gson();
            String outString = gson.toJson(users);
            Log.d("DEBUG", "JSON SAVED: " + outString);
            writer.println(outString);
            writer.close();
        } catch (FileNotFoundException e) {
            Log.e("ModelSingleton", "Failed to open json file for output");
        }
    }

    public void loadJson(File in) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(in));
            String inString = input.readLine();
            Log.d("DEBUG", "JSON: " + inString);
            Gson gson = new Gson();
            Type fooType = new TypeToken<ArrayList<Person>>() {}.getType();
            ArrayList<Person> ul = gson.fromJson(inString, fooType);
            users.clear();
            users.addAll(ul);
            input.close();
        } catch(IOException e) {
            Log.e("ModelSingleton", "Failed to open/read the buffered reader for json");
        }
    }

    public ArrayList<Person> getUsers() { return users;}
}
