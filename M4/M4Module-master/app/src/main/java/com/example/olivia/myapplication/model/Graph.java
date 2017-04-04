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
            this.location = location;
            this.vPPM = vPPM;
        }

        public String getMonth() {
            return time;
        }
        public String getLocation() {
            return location;
        }
        public double getVirusPPM() {
            return vPPM;
        }


}
