package com.example.olivia.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import java.util.Map;

import static android.R.id.list;


/**
 * Created by Shuopeng Zhou on 3/1/2017.
 * ArrayList that stores user reports
 */

public class PurityReportManager {
    Report u;// Modified by Shuopeng Zhou to enable accessing the report from ViewReportActivity

    private static List<Report> reports = new ArrayList<Report>();
    private static HashMap<String, List<Report>> locationmap = new HashMap<>();

    public void addReport(String time, String location, LatLng reportLatLng, double vPPM, double cPPM,
                          String condition, int reportNum, String date) {
        Report report = new Report(time, location, reportLatLng, vPPM, cPPM, condition, reportNum, date);
        reports.add(report);
        //Creates multimap for location and reports
        if (locationmap.containsKey(report.getLocation())) {
            List<Report> list = locationmap.get(report.getLocation());
            list.add(report);
        } else {
            List<Report> list = new ArrayList<>();
            list.add(report);
            locationmap.put(report.getLocation(), list);
        }
    }
    public int size() {
        return reports.size();
    }

    public List<Report> getList() {
        return reports;
    }

    /**
     * Returns a list of all the reports with given location
     * @param loc location being searched for
     * @return list of reports with given location
     */
    public List<Report> getByLocation(String loc) {
        return locationmap.get(loc);
    }

    /**
     * Returns the ppm of the reports that have the given
     * location
     * @param loc location of reports
     * @return an array of ppm values
     */
    public Double[] getPPM(String loc) {
        List<Report> chosenReport = getByLocation(loc);
        Double[] ppm = new Double[chosenReport.size()];
        for (int i = 0; i < ppm.length; i++) {
            ppm[i] = new Double(chosenReport.get(i).getVirusPPM());
        }
        return ppm;
    }
    /**
     * Returns the years of the reports that have the given
     * location
     * @param loc location of reports
     * @return an array of years
     * */
    public Float[] getYears(String loc) {
        List<Report> chosenReport = getByLocation(loc);
        Float[] years = new Float[chosenReport.size()];
        for (int i = 0; i < years.length; i++) {
            String date = chosenReport.get(i).getDate();
            years[i] = Float.parseFloat(date.substring(6));
        }
        return years;
    }

    /**
     * Returns the dates of the reports that have the given
     * location
     * @param loc location of reports
     * @return an array of ppm values
     */
    public Double[] getDates(String loc) {
        List<Report> chosenReport = getByLocation(loc);
        Double[] ppm = new Double[chosenReport.size()];
        for (int i = 0; i < ppm.length; i++) {
            ppm[i] = new Double(chosenReport.get(i).getVirusPPM());
        }
        return ppm;
    }

}
