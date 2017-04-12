package com.example.olivia.myapplication;
import com.example.olivia.myapplication.controller.RegisterActivity;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rayna on 2017-04-11.
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
