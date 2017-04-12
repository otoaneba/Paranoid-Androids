package com.example.olivia.myapplication;

import android.content.Intent;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.olivia.myapplication.model.*;
import com.google.android.gms.maps.model.LatLng;
import com.example.olivia.myapplication.controller.*;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public User newUser;
    public UserManager manager;


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAddNewUser() throws Exception {
        try {
            UserManager manager = new UserManager();
            assertEquals(true, manager.addUser("userTest", "testName", "pass", "email", "address", "user"));
        } catch (NullPointerException e) {
            e.printStackTrace();
            Assert.fail("should not have this exception");
        }
    }

    @Test
    public void testDeleteUser() throws Exception {
        try {
            UserManager manager = new UserManager();
            manager.addUser("DeleteTest", "testName", "pass", "email", "address", "user");
            assertEquals(true, manager.deleteUser("DeleteTest"));
        } catch (NullPointerException e){
            e.printStackTrace();
            Assert.fail("should not have this exception");
        }
    }

//    @Test
//    public void testGetAddressFromLtLg() throws Exception {
//        try {
//            LatLng ln = new LatLng(-34, 151);
//        }
//    }
}