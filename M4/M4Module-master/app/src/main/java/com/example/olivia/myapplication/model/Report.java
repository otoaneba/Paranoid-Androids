package com.example.olivia.myapplication.model;

import java.io.Serializable;

import static com.example.olivia.myapplication.controller.R.id.condition;

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
    private String creator;
    private String date;
    private String condition;


    public Report(String i, String n, String p, String e, String c, int t, String d) {
        time = i;
        location = n;
        virusPPM = p;
        combinationPPM = e;
        condition = c;
        reportNumber = t;
        creator = User.getCurrentUser().toString();
        date = d;

    }

    public String toString() {
        return creator + " " + location + " " + date;
    }

    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
    public String getVirusPPM() {
        return virusPPM;
    }
    public String getCombinationPPM() {
        return combinationPPM;
    }
    public int getReportNumber() {
        return reportNumber;
    }
    public String getCreator() {
        return creator;
    }
    public String getDate() {
        return date;
    }
    public String getCondition() {
        return condition;
    }

}
