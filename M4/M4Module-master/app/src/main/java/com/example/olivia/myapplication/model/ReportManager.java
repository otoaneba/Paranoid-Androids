package com.example.olivia.myapplication.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsp32 on 3/1/2017.
 */

public class ReportManager {
    Report u;// Modified by Rayna to enable accessing the user from LoginActivity

    private List<Report> reports = new ArrayList<Report>();

    public void addReport(String i, String n, String p, String e, int t) {
        reports.add(new Report(i,  n,  p, e, t));
    }
    public int size() {
        return reports.size();
    }
}
