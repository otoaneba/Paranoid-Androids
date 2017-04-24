package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.olivia.myapplication.R;
//import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.User;


/**
 * This page shows the details for an individual report.
 * You can return to the list of reports by clicking the
 * cancel button.
 */
public class ShowReportActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_report);

        //Initializes cancel button
        Button cancelButton = (Button) findViewById(R.id.cancel_button);

        //Gets report's values passed from the Report Activity
        String time_v = (String)getIntent().getSerializableExtra("time");
        int repNo_v = (Integer)getIntent().getSerializableExtra("repNo");
        String WorkerName_v = (String)getIntent().getSerializableExtra("WorkerName");
        String loc_v = (String)getIntent().getSerializableExtra("loc");
        String condition_v = (String)getIntent().getSerializableExtra("condition");
        double virus_v = (Double)getIntent().getSerializableExtra("virus");
        double contaminant_v = (Double)getIntent().getSerializableExtra("contam");
        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data

        //Initializes widgets from the XML
        TextView title = (TextView) findViewById(R.id.Title);
        TextView time = (TextView) findViewById(R.id.time);
        TextView reportNumber = (TextView) findViewById(R.id.report_number);
        TextView worker = (TextView) findViewById(R.id.worker);
        TextView location = (TextView) findViewById(R.id.location);
        TextView condition = (TextView) findViewById(R.id.condition);
        TextView contaminationPpm = (TextView) findViewById(R.id.contamination);
        TextView virusPpm = (TextView) findViewById(R.id.PPM);

        //Sets values from selected report
        String stringTitle = "Purity Report";
        title.setText(stringTitle);

        String stringTime = "Time of Report: " + time_v;
        time.setText(stringTime);

        String stringReportNumber = "Report Number: " + repNo_v;
        reportNumber.setText(stringReportNumber);

        String stringWorker = "Worker Name: " + WorkerName_v;
        worker.setText(stringWorker);

        String stringLocation = "Location " + loc_v;
        location.setText(stringLocation);

        String stringWaterCondition = "Water Condition: " + condition_v;
        condition.setText(stringWaterCondition);

        String stringVirusPPM = "Virus PPM: " + virus_v;
        virusPpm.setText(stringVirusPPM);

        String stringContaminationPPM = "Contamination PPM: " + contaminant_v;
        contaminationPpm.setText(stringContaminationPPM);

        //Cancel button returns to Report Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}