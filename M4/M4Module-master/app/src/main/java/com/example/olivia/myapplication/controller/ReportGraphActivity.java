package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.olivia.myapplication.model.Graph;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.color.black;
import static com.example.olivia.myapplication.model.RetrieveGraphData.graphs;

/**
 * Created by Olivia on 3/16/2017.
 * Modified and add database on 03/22/2017 by Shuopeng Zhou
 * Allows the Manager to pick a location and a range of months and generates a plot
 * The manager can use the back button to go back to the main screen.
 */

public class ReportGraphActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_graph_new);
        final Spinner locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        final Spinner dateSpinner = (Spinner) findViewById(R.id.dateSpinner);
        final TextView endDateText = (TextView) findViewById(R.id.endDate);
        final Spinner graphTypeSpinner = (Spinner) findViewById(R.id.graphType);
        //Populates spinner with location
        ArrayList<String> locationList = new ArrayList<>();
        locationList.addAll(graphs.keySet());
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationList);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
        //Populates different graph types
        ArrayList<String> graphTypeList = new ArrayList<>(Arrays.asList("Line Graph","Bar Graph"));
        ArrayAdapter<String> graphTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, graphTypeList);
        graphTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        graphTypeSpinner.setAdapter(graphTypeAdapter);


        final LineChart chart = (LineChart) findViewById(R.id.chart);
        final List<Entry> entries = new ArrayList<>();
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

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawLabels(true);

        locationSpinner.setSelection(getIntent().getIntExtra("location",0));
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //If switched from Bar Graph gets previously selected location
                String location = locationSpinner.getSelectedItem().toString();
                getIntent().removeExtra("location");

                //Sets End Date text
                //Result list for specific locations
                ArrayList<Graph> result = graphs.get(location);

                String[] yearList = new String[result.size()];
                for (int i = 0; i < result.size(); i++){
                        yearList[i] = result.get(i).getMonth();
                }
                Arrays.sort(yearList);
                endDateText.setText(yearList[yearList.length - 1]);
                //Populates spinner for start date
                ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(ReportGraphActivity.this, android.R.layout.simple_spinner_item, yearList);
                dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dateSpinner.setAdapter(dateAdapter);
                entries.clear();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //does nothing
            }
        });

        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //If switched from Line Graph gets previously selected location
                String location = locationSpinner.getSelectedItem().toString();
                getIntent().removeExtra("location");

                ArrayList<Graph> result = graphs.get(location);

                String[] time = new String[result.size()];
                Double[] ppm = new Double[result.size()];


                for (int i = 0; i < result.size(); i++){
                    time[i] = result.get(i).getMonth();
                    ppm[i] = result.get(i).getVirusPPM();
                }
                entries.clear();
                //Adds entries after selected start date
                int start =  dateSpinner.getSelectedItem() != null ? dateSpinner.getSelectedItemPosition() : 0;
                if (Math.abs(start - ppm.length) >= 0) {
                    for (int i = start; i < ppm.length ; i++) {
                        // turn your data into Entry objects
                        BigDecimal ppmDecimal = new BigDecimal(ppm[i]);
                        entries.add(new Entry(Float.valueOf(time[i]), ppmDecimal.floatValue()));
                    }
                    LineDataSet dataset;
                    //Creates data set for entries of given location

                    dataset = new LineDataSet(entries, "Purity Reports"); // add entries to data set
                    dataset.setColor(R.color.colorAccentGreen);
                    dataset.setValueTextColor(black);
                    LineData lineData = new LineData(dataset);
                    chart.setData(lineData);
                    chart.invalidate(); // refresh
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //does nothing
            }

        });
        graphTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(ReportGraphActivity.this, BarGraphActivity.class);
                    intent.putExtra("location", locationSpinner.getSelectedItemPosition());
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //does nothing
            }

        });

        // database uses a timestamp (String), right now, Joe is getting the year value and plotting it
        // on the x axis. ( he is averaging the PPM value in a year and plotting that avg for one year)
        // if we wanted to make the data more specific (monthly or even daily) there needs to be more
        //coding to be done
        //

    }
}