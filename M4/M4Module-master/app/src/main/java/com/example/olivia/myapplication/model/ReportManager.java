package com.example.olivia.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shuopeng Zhou on 3/1/2017.
 * ArrayList that stores user reports
 */

public class ReportManager {
    Report u;// Modified by Shuopeng Zhou to enable accessing the report from ViewReportActivity

    private static List<Report> reports = new ArrayList<Report>();

    public void addReport(String i, String n, String p, String e, String a, int t, String c) {
        reports.add(new Report(i,  n,  p, e, a, t, c));
    }
    public int size() {
        return reports.size();
    }

    public List<Report> getList() {
        return reports;
    }
}
