package com.example.olivia.myapplication.test;
import com.example.olivia.myapplication.controller.RegisterActivity;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Olivia on 4/12/2017.
 *
 * Tests to see if isHomeAddressValid performs as expected
 */

public class IsHomeAddressValidTest {
    private final RegisterActivity test = new RegisterActivity();

    @Test
    public void isHomeAddressWrongTest() throws Exception {
        String wrongAdd = "home";
        String wrongAdd2 = "123";
        String wrongAdd3 = ".3#h";
        String goodAdd = "123 Home";
        String goodAdd2 = "123 home add 30907";
        Assert.assertEquals(true, test.isHomeAddressValid(goodAdd));
        Assert.assertEquals(false, test.isHomeAddressValid(wrongAdd));
        Assert.assertEquals(false, test.isHomeAddressValid(wrongAdd2));
        Assert.assertEquals(false, test.isHomeAddressValid(wrongAdd3));
        Assert.assertEquals(true, test.isHomeAddressValid(goodAdd2));
    }
}
