package com.example.olivia.myapplication.test;
import com.example.olivia.myapplication.controller.RegisterActivity;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author Rayna
 * Check password validation on registration page whether format is correct
 */

public class IsPasswordValidTest {
    private final RegisterActivity ra = new RegisterActivity();
    @Test
    public void isPasswordValidTest() throws Exception {
        String falseCheck = "user";
        String trueCheck = "uU1asdfghjk1";
        Assert.assertEquals(false, ra.isPasswordValid(falseCheck));
        Assert.assertEquals(true, ra.isPasswordValid(trueCheck));
    }
}
