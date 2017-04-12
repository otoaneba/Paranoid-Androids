package com.example.olivia.myapplication.test;
import com.example.olivia.myapplication.controller.RegisterActivity;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Kyung Jun Lee
 * Check email validation on registration page whether format is correct
 */

public class IsEmailValidTest {
    private final RegisterActivity test = new RegisterActivity();
    @Test
    public void isEmailValidFalseTest() throws Exception {
        String falseCheck = "hello";
        Assert.assertEquals(false, test.isEmailValid(falseCheck));

    }

    @Test
    public void isEmailValidTrueTest() throws Exception {
        String trueCheck = "giveMe100@gmail.com";
        Assert.assertEquals(true, test.isEmailValid(trueCheck));
    }
}
