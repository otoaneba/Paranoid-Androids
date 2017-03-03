package com.example.olivia.myapplication.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shuopeng Zhou on 3/1/2017.
 * Database for stroing report
 */

public class ReportManager {
    Report u;// Modified by Shuopeng Zhou to enable accessing the report from ReportActivity

    private List<Report> reports = new ArrayList<Report>();

    public void addReport(String i, String n, String p, String e, int t) {
        reports.add(new Report(i,  n,  p, e, t));
    }
    public int size() {
        return reports.size();
    }
}
