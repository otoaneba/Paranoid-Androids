package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.olivia.myapplication.R;
import com.example.olivia.myapplication.model.PurityReport;
import com.example.olivia.myapplication.model.Report;

import org.w3c.dom.Text;

public class ShowReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_report);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        Report report2 = (Report) getIntent().getSerializableExtra("selectedReport");
        //PurityReport report2 = new PurityReport("Feb 14", "8:00", 39393, "Joe", "GA", "Aight", 400);

        TextView title = (TextView) findViewById(R.id.Title);
        TextView time = (TextView) findViewById(R.id.time);
        TextView reportNumber = (TextView) findViewById(R.id.report_number);
        TextView worker = (TextView) findViewById(R.id.worker);
        TextView location = (TextView) findViewById(R.id.location);
        TextView condition = (TextView) findViewById(R.id.condition);
        TextView contaminationPpm = (TextView) findViewById(R.id.contamination);
        TextView virusPpm = (TextView) findViewById(R.id.PPM);

        title.setText(report2.getDate());
        time.setText("Time of Report: " + report2.getTime());
        reportNumber.setText("Report Number: " + report2.getReportNumber());
        worker.setText("Worker Name: " + report2.getCreator());
        location.setText("Location: " + report2.getLocation());
        condition.setText("Water Condition: " + report2.getCondition());
        virusPpm.setText("Virus PPM: " + report2.getVirusPPM());
        contaminationPpm.setText("Contamination PPM: " + report2.getCombinationPPM());


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowReportActivity.this, ReportActivity.class));
            }
        });


    }





}
