package com.example.olivia.myapplication.test;
import com.example.olivia.myapplication.controller.CreateReportActivity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Shuopeng Zhou on 4/12/2017.
 * Check time stamp on creating report whether format is correct
 */

public class isTimeValidTest {
    private final CreateReportActivity reportTest = new CreateReportActivity();
    @Test
    public void isUserIdValidTest() throws Exception {
        try {
            String falseCheck = "30/22/9978";
            String trueCheck = "03/22/2017";
            Assert.assertEquals(false, reportTest.isTimeValid((falseCheck)));
            Assert.assertEquals(true, reportTest.isTimeValid(trueCheck));
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail("not supposed to get this exception!");
        }
    }
}
