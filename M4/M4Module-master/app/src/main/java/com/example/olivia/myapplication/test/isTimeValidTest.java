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
            String falseCheck = "30229978";
            String trueCheck = "20170322";
            Assert.assertEquals(false, reportTest.isTimeValid((falseCheck)));
            Assert.assertEquals(true, reportTest.isTimeValid(trueCheck));
        } catch (Exception e){
            e.printStackTrace();
            Assert.fail("not supposed to get this exception!");
        }
    }
}
