package com.example.olivia.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import java.util.Map;

/**
 * Created by Shuopeng Zhou on 3/1/2017.
 * ArrayList that stores user reports
 */

public class PurityReportManager {
    Report u;// Modified by Shuopeng Zhou to enable accessing the report from ViewReportActivity

    private static List<Report> reports = new ArrayList<Report>();

    public void addReport(String time, String location, LatLng reportLatLng, double vPPM, double cPPM,
                          String condition, int reportNum, String date) {
        reports.add(new Report(time, location, reportLatLng, vPPM, cPPM, condition, reportNum, date));
    }
    public int size() {
        return reports.size();
    }

    public List<Report> getList() {
        return reports;
    }
}
