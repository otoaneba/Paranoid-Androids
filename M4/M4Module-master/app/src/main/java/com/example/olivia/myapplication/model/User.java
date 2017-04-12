package com.example.olivia.myapplication.model;

import java.io.Serializable;

/**
 * Created by Olivia and Naoto on 2/12/2017.
 *
 * Super class of all users. This class takes in an id, name, password, email, address, and
 * the userType () when registered. Has the appropriate getters and
 * setters.
 */

public class User implements Serializable {
    final private String id;
    final private String name;
    final private String password;
    final private String email;
    final private String address;
    final private String userType;
    private static User currentUser;

    /**
     * constructor for the user that takes in 6 String parameters and creates a new User object
     * when initialized
     * @param i is the user id
     * @param n stands for name for user name
     * @param p stands for password for user password
     * @param e stands for email for user email
     * @param a stands for address for user address
     * @param u stands for userType
     */

    public User(String i, String n, String p, String e, String a, String u) {
        id = i;
        name = n;
        password = p;
        email = e;
        address = a;
        userType = u;
    }

    /**
     * accessor for user ide
     * @return returns a user id for this user object
     */
    public String getId() {
        return id;
    }

    /**
     * accessor for the user name
     * @return returns user name for this user object
     */
    public String getName() {
        return name;
    } // Added by Rayna

    /**
     * accessor for password
     * @return returns a password for this user object
     */
    public String getPassword() { return password; }

    /**
     * accessor for user email
     * @return returns user email for this user object
     */
    public String getEmail() {
        return email;
    }

    /**
     * accessor for user address
     * @return returns a address from this user object
     */
    public String getAddress() {
        return address;
    }

    /**
     * accessor for user type
     * @return returns a userType (user, worker, manager, or admin) of this user object
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Setter for user email. Used when a user decides to edit his/her profile
     * @param newEmail String parameter that takes in the new email address and sets it to
     *                 the users email address
     */
//    public void setEmail(String newEmail) {
//        email = newEmail;
//    }

    /**
     * Setter for the user address. Used when the user edits his/her profile
     *
     * @param newAddress takes in a new String newAddress that sets it as the new address of the
     *                   users' address
     */
//    public void setAddress(String newAddress) {
//        address = newAddress;
//    }

    /**
     * a method that checks if the password that the user typed matches the actual user password
     * @param pass passes in the String pass that was typed in the textEdit field
     * @return returns true if the password matched, false otherwise
     */
    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public String toString() {
        return name;
    }

    public static void setCurrentUser(User u) {
        currentUser = u;
    }

//    public static User getCurrentUser() {
//        return currentUser;
//    }

}
