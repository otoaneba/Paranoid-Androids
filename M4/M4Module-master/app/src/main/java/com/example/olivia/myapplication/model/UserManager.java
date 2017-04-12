package com.example.olivia.myapplication.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Olivia and Naoto on 2/12/2017.
 *
 * a class that initializes a HashMap that holds all of our user accounts.
 */


public class UserManager implements AuthenticationFacade, UserManagementFacade {
    private User u;// Modified by Rayna to enable accessing the user from LoginActivity

    final private static Map<String, User> users = new HashMap<>();

    /**
     * a public method that finds user by userID. used to display profile information in profile
     * edit page.
     * @param id takes in the id of the user
     * @return returns the id of the user?    <-- needs clarification
     */
    public User findUserById(String id) {
        return users.get(id);
    }

    /**
     * constructor that, when initialized, creates a new user object with the following parameters
     *
     * @param id user id from registration page
     * @param name user name from registration page
     * @param pass user password from registration page
     * @param email user email from registration page
     * @param address user home address from registration page
     * @param userType user type that he/she must choose from a spinner
     *
     */
    public boolean addUser(String id, String name, String pass, String email, String address, String userType) {
        User userObject = new User(id, name, pass, email, address, userType);
        users.put(id, userObject);
        return true;
    }

    /**
     * method that lets the UserManager class delete a specific user by id
     * @param id takes in the userId that gets searched to be removed
     */
    public boolean deleteUser(String id) {
        users.remove(id);
        return true;
    }
//    public void deleteUser(String id) {
//        users.remove(id);
//    }


    /**
     * method that checks if the user typed in a valid userId and password, or if anything
     * at all
     * @param id takes in a String id that should correspond to userId
     * @param pass takes in a String password that will get checked against the userID
     * @return true if the login is successful with the valid credentials, false otherwise
     */

    public boolean handleLoginRequest(String id, String pass) {
        u = findUserById(id);//Modified by Rayna
        return u!=null && u.checkPassword(pass);
        //return true;
    }

    /**
     * public accessor that lets other classes access a user object inside the Map
     * @return returns the user object
     */
    public User getUser(){ // Added by Rayna
        return u;
    } // Added by Rayna

}
