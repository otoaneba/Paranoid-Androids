package com.example.olivia.myapplication.model;

import java.io.Serializable;

/**
 * a class that handles the viewing of water trend of the app. displays a graph with x axis and
 * y axis with vPPM as the y axis and the month on the x axis
 * Created by Shuopeng Zhou on 3/26/2017.
 * Used vPPM for Graph Y-axis
 */

public class Graph implements Serializable{
    final private String time;
    final private double vPPM;

    public Graph(String time, double vPPM) {
        this.time = time;
        this.vPPM = vPPM;
    }

    /**
     * public method that returns a month for a specific DateTime stamp
     * @return returns a time of the month
     */
    public String getMonth() {
            return time;
        }

    /**
     * public method that returns a vPPM of the purity report
     * @return returns a vPPM of the specific purity report
     */
    public double getVirusPPM() {
            return vPPM;
        }


}
