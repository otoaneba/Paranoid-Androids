package com.example.olivia.myapplication.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Shuopeng Zhou on 3/13/2017.
 * This page is to create a Source report store time, reportNumber, local, creator, data, type
 * condition and report geocode
 */

    public class SourceReport implements Serializable {
        private String rptNum;
        private String time;
        private String location;
        private String creator;
        private String type;
        private String condition;
        private LatLng reportLatLng;



    public SourceReport(String rptNum,String time, String location, String creator, String condition, String type,
                  String lat, String longt) {
        this.rptNum = rptNum;
        this.time = time;
        this.location = location;
        this.creator = creator;
        this.type = type;
        this.condition = condition;
        this.reportLatLng = new LatLng(Double.parseDouble(lat),Double.parseDouble(longt));
    }

    public String toString() {
        return rptNum + " | " + creator + " | " + location + " | " + time;
    }
        public String showMap() {
            return "No." + rptNum + ",WaterCondition: " + condition + ", Watertype: " + type;
        }
        public String getTime() {
            return time;
        }
        public String getLocation() {
            return location;
        }
        public String getType() { return type;};
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

