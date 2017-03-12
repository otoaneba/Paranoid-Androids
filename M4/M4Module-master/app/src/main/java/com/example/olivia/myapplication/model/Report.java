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
    private double virusPPM;
    private double contaminatePPM;
    private String creator;
    private String date;
    private String condition;


    public Report(String time, String location, double vPPM, double cPPM, String condition,
                  int reportNum, String date) {
        this.time = time;
        this.location = location;
        virusPPM = vPPM;
        contaminatePPM = cPPM;
        this.condition = condition;
        reportNumber = reportNum;
        creator = User.getCurrentUser().toString();
        this.date = date;

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
    public double getVirusPPM() {
        return virusPPM;
    }
    public double getCombinationPPM() {
        return contaminatePPM;
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
