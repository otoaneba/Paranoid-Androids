package com.example.olivia.myapplication.model;

import java.io.Serializable;

/**
 * Created by Shuopeng Zhou on 3/1/2017.
 * Create a new report contains time, reportNumber,location, virusPPM and combinationPPM
 */

public class Report implements Serializable {
    private String time;
    private int reportNumber;
    private String location;
    private String virusPPM;
    private String combinationPPM;


    public Report(String i, String n, String p, String e, int t) {
        time = i;
        location = n;
        virusPPM = p;
        combinationPPM = e;
        reportNumber = t;
    }
}
