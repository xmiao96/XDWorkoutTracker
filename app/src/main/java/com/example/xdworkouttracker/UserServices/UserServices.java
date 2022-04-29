package com.example.xdworkouttracker.UserServices;

import android.content.Context;

import java.util.ArrayList;

public class UserServices {
    private ArrayList<User> allUsers;
    private JsonHandler jsh;
    Context ctx;

    public UserServices(Context ctx){
        this.ctx = ctx;
        jsh = new JsonHandler();
        this.allUsers = jsh.readAllUsers(ctx);
    }

    public boolean registerUser(User user){
        if(!isUsernameExisted(allUsers, user.getUsername())){
            jsh.addUser(user);
            jsh.saveUsers(ctx);
            return true;
        }
        System.out.println("Username existed, please try again.");
        return false;
    }

    public boolean isUsernameExisted(ArrayList<User> userList, String username){
        for(User u : userList){
            if (u.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public User loginUser(String username, String password){
        for(User u: allUsers){
            if(username.equals(u.getUsername())){
                if(password.equals(u.getPassword())){
                    System.out.println("Login Successfully.");
                    return u;
                }
                return null;
            }
        }
        return null;
    }

}
