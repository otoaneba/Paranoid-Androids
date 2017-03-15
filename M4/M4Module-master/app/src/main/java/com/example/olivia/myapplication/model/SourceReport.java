package com.example.olivia.myapplication.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Shuopeng Zhou on 3/13/2017.
 * This page is to create a Source report store time, reportNumber, local, creator, data, type
 * condition and report geocode
 */

public class SourceReport implements Serializable {
        private String time;
        private int reportNumber;
        private String location;
        private String creator;
        private String date;
        private String type;
        private String condition;
        private LatLng reportLatLng;

        public SourceReport(String time, String location, LatLng reportLatLng, String type, String condition,
                      int reportNum, String date) {
            this.time = time;
            this.location = location;
            this.condition = condition;
            this.type = type;
            reportNumber = reportNum;
            creator = User.getCurrentUser().toString();
            this.date = date;
            this.reportLatLng = reportLatLng;

        }

        public String toString() {
            return reportNumber + ", " + creator + ", " + location + ", " + date;
        }
        public String showMap() {
            return "No." + reportNumber + ",WaterCondition: " + condition + ", Watertype: " + type;
        }
        public String getTime() {
            return time;
        }
        public String getLocation() {
            return location;
        }
        public String getType() { return type;};
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
        public LatLng getLatLng() { return reportLatLng;}

}

