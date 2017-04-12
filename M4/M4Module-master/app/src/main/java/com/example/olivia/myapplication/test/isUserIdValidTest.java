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
    public void isUserIdValidTest1() throws Exception {
        try {
            String falseCheck = ".false";
            String trueCheck = "iIlahelIOOO9";
            Assert.assertEquals(false, registerTest.isUserIdValid((falseCheck)));
            Assert.assertEquals(true, registerTest.isUserIdValid(trueCheck));
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail("not supposed to get this exception!");
        }
    }
}
