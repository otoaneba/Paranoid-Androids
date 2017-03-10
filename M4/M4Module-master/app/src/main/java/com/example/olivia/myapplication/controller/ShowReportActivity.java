package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.olivia.myapplication.R;
import com.example.olivia.myapplication.model.Report;

import org.w3c.dom.Text;

/**
 * This page shows the details for an individual report.
 * You can return to the list of reports by clicking the
 * cancel button.
 */
public class ShowReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_report);
        //Initializes cancel button
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        //Gets report passed in from the Report Activity
        Report selectedReport = (Report) getIntent().getSerializableExtra("selectedReport");

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
        title.setText(selectedReport.getDate());
        time.setText("Time of Report: " + selectedReport.getTime());
        reportNumber.setText("Report Number: " + selectedReport.getReportNumber());
        worker.setText("Worker Name: " + selectedReport.getCreator());
        location.setText("Location: " + selectedReport.getLocation());
        condition.setText("Water Condition: " + selectedReport.getCondition());
        virusPpm.setText("Virus PPM: " + selectedReport.getVirusPPM());
        contaminationPpm.setText("Contamination PPM: " + selectedReport.getCombinationPPM());

        //Cancel button returns to Report Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowReportActivity.this, ViewReportActivity.class));
            }
        });

    }
}
