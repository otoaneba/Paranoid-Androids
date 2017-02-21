package com.example.olivia.myapplication.model;

import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Olivia on 2/12/2017.
 */


public class UserManager implements AuthenticationFacade, UserManagementFacade {
    User u;// Modified by Rayna to enable accessing the user from LoginActivity

    private static Map<String, User> users = new HashMap<>();

    public User findUserById(String name) {
        return users.get(name);
    }

    public void addUser(String id, String name, String pass, String email, String address, String userType) {
        User userObject = new User(id, name, pass, email, address, userType);
        users.put(name, userObject);
    }

    public void editUser(String name, String newName) {
        //User user = users.get(name);
        //String pass = user.PasswordGetter();
        //users.remove(name);
        //User newUser = new User(name, pass);
        //user.nameChanger(newName);
        //users.put(newName, newUser);
        System.out.println("ok");

    }

    public boolean handleLoginRequest(String name, String pass) {
        u = findUserById(name);//Modified by Rayna
        return u!=null && u.checkPassword(pass);
    }
    public User getUser(){ // Added by Rayna
        return u;
    } // Added by Rayna



}
