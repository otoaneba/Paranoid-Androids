package com.example.olivia.myapplication.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsp32 on 3/13/2017.
 */

public class SourceReportManager {
    SourceReport u;// Modified by Shuopeng Zhou to enable accessing the report from ViewReportActivity

    private static List<SourceReport> reports = new ArrayList<SourceReport>();

    public void addReport(String time, String location, LatLng reportLatLng,String type,
                          String condiiton, int reportNum, String date) {
        reports.add(new SourceReport(time, location, reportLatLng, type, condiiton, reportNum, date));
    }
    public int size() {
        return reports.size();
    }

    public List<SourceReport> getList() {
        return reports;
    }
}
