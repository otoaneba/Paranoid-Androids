package com.example.olivia.myapplication.model;

import java.io.Serializable;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Shuopeng Zhou on 3/1/2017.
 * Create a new report contains time, reportNumber,location, virusPPM and combinationPPM
 */

public class Report implements Serializable {
    final private String time;
    final private String rptNum;
    final private String location;
    final private String vPPM;
    final private String cPPM;
    final private String creator;
    final private String quality;
    final private LatLng reportLatLng;


    public Report(String rptNum,String time, String location, String creator, String vPPM, String cPPM, String quality,
                  String lat, String longitude) {
        this.rptNum = rptNum;
        this.time = time;
        this.location = location;
        this.creator = creator;
        this.quality = quality;
        this.vPPM = vPPM;
        this.cPPM = cPPM;
        this.reportLatLng = new LatLng(Double.parseDouble(lat),Double.parseDouble(longitude));
    }



    public String toString() {
        return rptNum + " | " + creator + " | " + location + " | " + time;
    }
    public String showMap() {
        return "No." + rptNum + ", Quality: " + quality + ", Virus: " + getVirusPPM() + ", Contamination: " + getCombinationPPM();
    }
    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
    public double getVirusPPM() {
        return Double.parseDouble(vPPM);
    }
    public double getCombinationPPM() {
        return Double.parseDouble(cPPM);
    }
    public int getReportNumber() {
        return Integer.parseInt(rptNum);
    }
    public String getCreator() {
        return creator;
    }
    public String getCondition() {
        return quality;
    }
    public LatLng getLatLng() { return reportLatLng;}

}