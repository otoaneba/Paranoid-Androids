package com.example.olivia.myapplication.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shuopeng Zhou on 3/26/2017.
 * Used vPPM for Graph Y-axis
 */

public class Graph implements Serializable{
        private String time;
        private String location;
        private double vPPM;



        public Graph(String location,String time, double vPPM) {
            this.time = time;
            this.location = location;g
            this.vPPM = vPPM;
        }

        public String getMonth() {
            return time;
        }
<<<<<<< HEAD
        public Float getYear() throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Float year = new Float(cal.get(Calendar.YEAR));
            return year;
        }

        public Float getMonth() throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Float month = new Float(cal.get(Calendar.MONTH));
            return month;
        }
=======
>>>>>>> 7595063caa84d4ab3e7991aa39da21a12d148ec5
        public String getLocation() {
            return location;
        }
        public double getVirusPPM() {
            return vPPM;
        }


}
