package com.example.olivia.myapplication.test;
import org.junit.Assert;
import org.junit.Test;
import com.example.olivia.myapplication.controller.CreateSourceReportActivity;
/**
 * Created by Julian on 4/12/2017.
 * J Unit class that tests the isValidLatLong method in CreateSourceReportActivity
 */

public class isValidLatLongTest {
    private final CreateSourceReportActivity reportTest = new CreateSourceReportActivity();
    @Test
    public void isValidLatLongTest() {
        Double goodLat = (double) 89;
        Double badLat = (double) (124);
        Double goodLong = (double) (147);
        Double badLong = (double) (289);

        Double negGoodLat = (double) (-45);
        Double negBadLat = (double) (-465);
        Double negGoodLong = (double) (-175);
        Double negBadLong = (double) (-200);

        Assert.assertEquals(true, reportTest.isValidLatLong(goodLat,goodLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(goodLat,badLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(badLat,goodLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(badLat,badLong));

        Assert.assertEquals(true, reportTest.isValidLatLong(negGoodLat,negGoodLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(negGoodLat,negBadLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(negBadLat,negGoodLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(negBadLat,negBadLong));

        Assert.assertEquals(true, reportTest.isValidLatLong(negGoodLat,goodLong));
        Assert.assertEquals(true, reportTest.isValidLatLong(goodLat,negGoodLong));

        Assert.assertEquals(false, reportTest.isValidLatLong(badLat,negBadLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(negBadLat,badLong));

        Assert.assertEquals(false, reportTest.isValidLatLong(negGoodLat,badLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(goodLat,negBadLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(negBadLat,goodLong));
        Assert.assertEquals(false, reportTest.isValidLatLong(badLat,negGoodLong));

    }

}
