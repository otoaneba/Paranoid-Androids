package com.example.olivia.myapplication.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olivia.myapplication.model.Graph;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;


import static com.example.olivia.myapplication.model.RetrieveGraphData.graphs;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Olivia on 3/16/2017.
 */

public class ReportGraphActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_graph_new);
        final Spinner locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        final Spinner dateSpinner = (Spinner) findViewById(R.id.dateSpinner);
        final TextView endDateText = (TextView) findViewById(R.id.enddate);

//
//        reports.addReport("12:23 PM", "123 Some Rd", new LatLng(-33.852, 151.211), 3432.0, 8594.0, "SAFE",
//                20, "07/23/2006");
//        reports.addReport("4:55 PM", "123 Some Rd", new LatLng(-33.543, 152.0), 1243.0, 7689.34, "SAFE",
//                201, "06/30/2007");
//        reports.addReport("7:01 AM", "123 Some Rd", new LatLng(-32.999, 151.0), 2537.2, 7898.4, "SAFE",
//                32, "03/16/2008");
//        reports.addReport("11:34 AM", "123 Some Rd", new LatLng(-32.999, 151.0), 2453.7, 8000.3, "SAFE",
//                33, "01/03/2009");
//        reports.addReport("3:04 PM", "123 Some Rd", new LatLng(-32.999, 151.0), 1876.9, 7690.9, "SAFE",
//                43, "12/03/2010");
//
//        reports.addReport("9:03 AM", "32 What Dr", new LatLng(-475.384, 354.0), 987.3, 84.8, "SAFE",
//                55, "01/03/2013");
//        reports.addReport("10:44 AM", "32 What Dr", new LatLng(-475.384, 354.0), 899.4, 95.6, "SAFE",
//                56, "04/22/2014");
//        reports.addReport("1:02 PM", "32 What Dr", new LatLng(-475.465, 353.89), 1003.5, 79.8, "SAFE",
//                57, "08/09/2015");
//        reports.addReport("11:34 PM", "32 What Dr", new LatLng(-477.343, 354.67), 903.5, 90.5, "SAFE",
//                58, "06/17/2016");

        /// giving the manager the option to plot

        ArrayList<String> locationList = new ArrayList<>();
        locationList.addAll(graphs.keySet());
        //List<Graph> reportList = reports.getList();
//        for (int i = 0; i < locationList.size(); i++) {
//            Report report = reportList.get(i);
//            if (!locationList.contains(report.getLocation())) {
//                locationList.add(report.getLocation());
//                Log.d("Location added: ", report.getLocation());
//            }
//        }


        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationList);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);



        // start date is at least 3 years behind the end date so we can plot at least 3 points


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

        YAxis leftaxis = chart.getAxisLeft();
        leftaxis.setDrawLabels(true);

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String location = locationSpinner.getSelectedItem().toString();
                //Sets End Date text
                //Result list for specific locations
                ArrayList<Graph> result = graphs.get(location);


                Float[] yearlist = new Float[result.size()];

                for (int i = 0; i < result.size(); i++){
                    try {
                        yearlist[i] = result.get(i).getYear();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                Arrays.sort(yearlist);
                endDateText.setText("" + yearlist[yearlist.length - 1]);
                //Populates spinner for start date
                ArrayAdapter<Float> dateAdapter = new ArrayAdapter<Float>(ReportGraphActivity.this, android.R.layout.simple_spinner_item, yearlist);
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

                String location = locationSpinner.getSelectedItem().toString();


                ArrayList<Graph> result = graphs.get(location);


                Float[] time = new Float[result.size()];
                Double[] ppm = new Double[result.size()];


                for (int i = 0; i < result.size(); i++){
                    try {
                        time[i] = result.get(i).getYear();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ppm[i] = result.get(i).getVirusPPM();
                }
                entries.clear();
                //Adds entries after selected start date
                int start = dateSpinner.getSelectedItem() != null ? dateSpinner.getSelectedItemPosition() : 0;
                if (Math.abs(start - ppm.length) >= 3) {
                    for (int i = start; i < ppm.length ; i++) {
                        // turn your data into Entry objects
                        BigDecimal ppmDecimal = new BigDecimal(ppm[i]);
                        entries.add(new Entry(time[i], ppmDecimal.floatValue()));
                    }

                    //Creates data set for entries of given location
                    LineDataSet dataSet = new LineDataSet(entries, "Purity Reports"); // add entries to dataset
                    dataSet.setColor(R.color.seaGreen);
                    dataSet.setValueTextColor(R.color.greyPink);
                    LineData lineData = new LineData(dataSet);
                    chart.setData(lineData);


                    chart.invalidate(); // refresh
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Must be at least a 3 year interval";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //does nothing
            }

        });

        // database uses a timestamp (String), right now, Joe is getting the year value and plotting it
        // on the x axis. ( he is averaging the PPM value in a year and plotting that avg for one year)
        // if we wanted to make the data more specific (montly or even daily) there needs to be more
        //coding to be done
        //

    }
}