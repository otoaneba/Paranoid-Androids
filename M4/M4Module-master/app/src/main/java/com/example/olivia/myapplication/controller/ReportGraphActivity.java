package com.example.olivia.myapplication.controller;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olivia on 3/16/2017.
 */

public class ReportGraphActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_graph);
        LineChart chart = (LineChart) findViewById(R.id.chart);
        Double[] ppm = {21.5, 35.4, 14.3, 19.7};
        Double[] time = {2005.0, 2006.0, 2007.0, 2008.0};
        List<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < ppm.length; i++) {
            // turn your data into Entry objects
            BigDecimal ppmDecimal = new BigDecimal(ppm[i]);
            BigDecimal timeDecimal = new BigDecimal(time[i]);
            entries.add(new Entry(ppmDecimal.floatValue(), timeDecimal.floatValue()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(R.color.seaGreen);
        dataSet.setValueTextColor(R.color.greyPink);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }
}
