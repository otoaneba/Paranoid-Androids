package com.example.olivia.myapplication.model;

// --Commented out by Inspection START (4/10/17, 10:53 PM):
///**
// * Created by Naoto on 2/18/2017.
// *
// * a sub class of Worker class. takes in a name and a password. A manger can do everything that
// * a worker can do. Managers also have a viewWaterReport method that lets them view past water
// * report for analysis
// */
//
public class Manager extends Worker {
    /**
     *constructor for Manager class. Creates a new manager class when initialized
     * @param id is the user id
     * @param name stands for name for user name
     * @param password stands for password for user password
     * @param email stands for email for user email
     * @param address stands for address for user address
     * @param userType stands for userType
     */

    @SuppressWarnings("unused")
    public Manager(String id, String name, String password, String email, String address, String userType) {
        super(id, name, password, email, address, userType);
    }

// --Commented out by Inspection START (4/11/17, 8:47 PM):
//    /**
//     * a method that lets Managers view past water reports for analysis
//     */
//    public void viewWaterReport() {
//        System.out.println("viewed water report");
//    }
// --Commented out by Inspection STOP (4/11/17, 8:47 PM)
}

