package com.example.olivia.myapplication.test;
import com.example.olivia.myapplication.controller.RegisterActivity;
import org.junit.Assert;
import org.junit.Test;



/**
 * Created by Naoto on 4/10/2017.
 * J Unit test class that tests the isUserIdValid method in Register Activity
 */

public class isUserIdValidTest {
    private final RegisterActivity registerTest = new RegisterActivity();
    @Test
    public void isPasswordValidTest() throws Exception {
        String falseCheck = ".false";
        String trueCheck = "iIlahelIOOO9";
        Assert.assertEquals(false, registerTest.isPasswordValid(falseCheck));
        Assert.assertEquals(true, registerTest.isPasswordValid(trueCheck));
    }

}
