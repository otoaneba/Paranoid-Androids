package com.example.olivia.myapplication.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * a class that handles the viewing of water trend of the app. displays a graph with x axis and
 * y axis with vPPM as the y axis and the month on the x axis
 * Created by Shuopeng Zhou on 3/26/2017.
 * Used vPPM for Graph Y-axis
 */

public class Graph implements Serializable{
    final private String time;
    final private double vPPM;

        public Graph(String location,String time, double vPPM) {
            this.time = time;
            this.vPPM = vPPM;
        }

    /**
     * returns a year for a specific DateTime stamp
     * @return a float value of the year
     * @throws ParseException
     */
    public Float getYear() throws ParseException {
            SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
            Date date = sdf.parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Float year = new Float(cal.get(Calendar.YEAR));
            return year;
        }

    /**
     * public method that returns a month for a specific DateTime stamp
     * @return returns a time of the month
     */
    public String getMonth() {
            return time;
        }

    /**
     * public method that returns the location of the graph
     * @return returns the specific address for the report
     */
  //  public String getLocation() {
    //        return location;
      //  }

    /**
     * public method that returns a vPPM of the purity report
     * @return returns a vPPM of the specific purity report
     */
    public double getVirusPPM() {
            return vPPM;
        }


}
