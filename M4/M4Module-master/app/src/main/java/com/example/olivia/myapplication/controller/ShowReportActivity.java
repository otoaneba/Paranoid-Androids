package com.example.olivia.myapplication.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

//import com.example.olivia.myapplication.R;
import com.example.olivia.myapplication.model.PurityReport;

public class ShowReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_report);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //PurityReport report2 = (PurityReport) getIntent().getExtras().getParcelable("selectedReport");
        PurityReport report2 = new PurityReport("Feb 14", "8:00", 39393, "Joe", "GA", "Aight", 400);

        TextView title = (TextView) findViewById(R.id.Title);
        TextView time = (TextView) findViewById(R.id.time);
        TextView reportNumber = (TextView) findViewById(R.id.report_number);
        TextView worker = (TextView) findViewById(R.id.worker);
        TextView location = (TextView) findViewById(R.id.location);
        TextView condition = (TextView) findViewById(R.id.condition);
        TextView ppm = (TextView) findViewById(R.id.PPM);

        title.setText(report2.getDate());
        time.setText("Time of Report: " + report2.getDate());
        reportNumber.setText("Report Number: " + report2.getReportNumber());
        worker.setText("Worker Name: " + report2.getWorkerName());
        location.setText("Location: " + report2.getLocation());
        condition.setText("Water Condition: " + report2.getCondition());
        ppm.setText("Concentration PPM: " + report2.getVirusPPM());



    }





}
