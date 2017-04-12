package com.example.olivia.myapplication.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Shuopeng Zhou on 3/13/2017.
 * This page is to create a Source report store time, reportNumber, local, creator, data, type
 * condition and report geocode
 */

    public class SourceReport implements Serializable {
        final private String rptNum;
        final private String time;
        final private String location;
        final private String creator;
        final private String type;
        final private String condition;
        final private LatLng reportLatLng;



    public SourceReport(String rptNum,String time, String location, String creator, String condition, String type,
                  String lat, String longitude) {
        this.rptNum = rptNum;
        this.time = time;
        this.location = location;
        this.creator = creator;
        this.type = type;
        this.condition = condition;
        this.reportLatLng = new LatLng(Double.parseDouble(lat),Double.parseDouble(longitude));
    }

    public String toString() {
        return rptNum + " | " + creator + " | " + location + " | " + time;
    }
        public String showMap() {
            return "No." + rptNum + ",WaterCondition: " + condition + ", WaterType: " + type;
        }
        public String getTime() {
            return time;
        }
        public String getLocation() {
            return location;
        }
        public String getType() { return type;}

    public int getReportNumber() {
            return Integer.parseInt(rptNum);
        }
        public String getCreator() {
            return creator;
        }
        public String getCondition() {
            return condition;
        }
        public LatLng getLatLng() { return reportLatLng;}

}

