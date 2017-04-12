package com.example.olivia.myapplication.test;
import com.example.olivia.myapplication.model.RetrieveUserData;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by John on 2017-04-11.
 */

public class IsExistedUserTest {
    RetrieveUserData userData = new RetrieveUserData();
    @Test
    public void isExistUserTest() throws Exception {
        String check = "user";
        Assert.assertEquals(true, userData.isExistedUser(check));
    }
}
