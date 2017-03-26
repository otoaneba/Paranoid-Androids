package com.example.olivia.myapplication.controller;

import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.ReportManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.example.olivia.myapplication.model.ReportManager;
import com.example.olivia.myapplication.model.User;
import com.example.olivia.myapplication.model.waterQuality;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Olivia on 3/16/2017.
 */

public class ReportGraphActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_graph_new);
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        final Spinner dateSpinner = (Spinner) findViewById(R.id.dateSpinner);
        final ReportManager reports = new ReportManager();

        //// TODO: 3/24/2017 hook up database when complete

        reports.addReport("12:23 PM", "123 Some Rd", new LatLng(-33.852, 151.211), 3432.0, 8594.0, "SAFE",
                20, "07/23/2006");
        reports.addReport("4:55 PM", "123 Some Rd", new LatLng(-33.543, 152.0), 1243.0, 7689.34, "SAFE",
                201, "06/30/2007");
        reports.addReport("7:01 AM", "123 Some Rd", new LatLng(-32.999, 151.0), 2537.2, 7898.4, "SAFE",
                32, "03/16/2008");
        reports.addReport("11:34 AM", "123 Some Rd", new LatLng(-32.999, 151.0), 2453.7, 8000.3, "SAFE",
                33, "01/03/2009");
        reports.addReport("3:04 PM", "123 Some Rd", new LatLng(-32.999, 151.0), 1876.9, 7690.9, "SAFE",
                43, "12/03/2010");

        reports.addReport("9:03 AM", "32 What Dr", new LatLng(-475.384, 354.0), 987.3, 84.8, "SAFE",
                55, "01/03/2013");
        reports.addReport("10:44 AM", "32 What Dr", new LatLng(-475.384, 354.0), 899.4, 95.6, "SAFE",
                56, "04/22/2014");
        reports.addReport("1:02 PM", "32 What Dr", new LatLng(-475.465, 353.89), 1003.5, 79.8, "SAFE",
                57, "08/09/2015");
        reports.addReport("11:34 PM", "32 What Dr", new LatLng(-477.343, 354.67), 903.5, 90.5, "SAFE",
                58, "06/17/2016");

        //// TODO: 3/24/2017 check and make sure there are at least 3 date for each location becore
        /// giving the manager the option to plot

        ArrayList<String> locationList = new ArrayList<>();
        List<Report> reportList = reports.getList();
        for (int i = 0; i < reportList.size(); i++) {
            Report report = reportList.get(i);
            if (!locationList.contains(report.getLocation())) {
                locationList.add(report.getLocation());
                Log.d("Location added: ", report.getLocation());
            }
        }
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationList);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        // TODO: 3/24/2017 Pull the correct dates from the chosen location. make sure the last
        // start date is at least 3 years behind the end date so we can plot at least 3 points

        //// TODO: 3/24/2017 plot the manager chosen data

        LineChart chart = (LineChart) findViewById(R.id.chart);
        Double[] ppm = {21.5, 35.4, 14.3, 19.7};
        Float[] time = {2005f, 2006f, 2007f, 2008f};
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < ppm.length ; i++) {
            // turn your data into Entry objects
            BigDecimal ppmDecimal = new BigDecimal(ppm[i]);
            entries.add(new Entry(time[i], ppmDecimal.floatValue()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(R.color.seaGreen);
        dataSet.setValueTextColor(R.color.greyPink);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        //Sets interval to 1
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new DefaultAxisValueFormatter(0));
        xAxis.setGranularity(1f);


        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDoubleTapToZoomEnabled(true);
        chart.setHighlightPerDragEnabled(false);
        chart.setHighlightPerTapEnabled(false);

        YAxis leftaxis = chart.getAxisLeft();
        leftaxis.setDrawLabels(true);
        chart.invalidate(); // refresh

    }
}
