package com.example.xdworkouttracker.UserServices;


import android.content.Context;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;


public class JsonHandler {

    private ArrayList<User> users = new ArrayList<User>();;
    private int size = 0;
    private final static String fileName = "usersDB.json";

    public void addUser(User user){
        this.users.add(user);
        this.size++;
    }

    public void saveUsers(Context ctx){
        JsonArray userList = new JsonArray();
        for (User user : this.users) {
            userList.add(toJson(user));
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(userList.toJson());
            outputStreamWriter.close();
            System.out.println("The json has been saved:");
            System.out.println(ctx.getFilesDir());
        } catch (IOException ex) {
             ex.printStackTrace();
        }
    }
    public ArrayList<User> readAllUsers(Context ctx){
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(ctx.openFileInput(fileName));
            JsonArray users = Jsoner.deserializeMany(inputStreamReader);
            users = (JsonArray) users.get(0);
            ArrayList<User> readIn = new ArrayList<User>();
            for(Iterator i = users.iterator();i.hasNext();){
                JsonObject jsonObject = (JsonObject) i.next();
                User user = toClass(jsonObject);
                readIn.add(user);
            }
            inputStreamReader.close();
            this.users = readIn;
            return this.users;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonException e) {
            e.printStackTrace();
        }
        return this.users;
    }

    private JsonObject toJson(User user){
        JsonObject json = new JsonObject();

        json.put("username", user.getUsername());
        json.put("password", user.getPassword());
        json.put("weight", user.getWeight());
        json.put("height", user.getHeight());
        json.put("gender", user.isMale());
        return json;
    }

    private User toClass(JsonObject json){
        User user = new User();

        user.setUsername((String)json.get("username"));
        user.setPassword((String)json.get("password"));
        user.setWeight(Float.parseFloat(json.get("weight").toString()));
        user.setHeight(Float.parseFloat(json.get("height").toString()));
        user.setGender((Boolean) json.get("gender"));

        return user;
    }

    public int getSize() {
        return size;
    }
}
