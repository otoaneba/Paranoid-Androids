package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.olivia.myapplication.model.SourceReport;
import com.example.olivia.myapplication.model.User;

public class ShowSourceReportActivity extends AppCompatActivity {
    private User user;
    private TextView title,time,reportNumber,worker,location,condition,type;
    private SourceReport selectedReport;
    private Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_source_report);
        //Initializes cancel button
       try {
           cancelButton = (Button) findViewById(R.id.cancel_report_source); //
           //Gets report passed in from the Report Activity
           selectedReport = (SourceReport) getIntent()
                   .getSerializableExtra("selectedSourceReport");

           user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
           //Initializes widgets from the XML
           title = (TextView) findViewById(R.id.Title_sourceReport);
           time = (TextView) findViewById(R.id.time_sourceReport);
           reportNumber = (TextView) findViewById(R.id.report_number_sourceReport);
           worker = (TextView) findViewById(R.id.worker_sourceReport);
           location = (TextView) findViewById(R.id.location_sourceReport);
           condition = (TextView) findViewById(R.id.condition_sourceReport);
           type = (TextView) findViewById(R.id.type_sourceReport);
       }catch(Exception e) {
           Log.d("debug",e.toString());
       }
        //Sets values from selected report
        title.setText(selectedReport.getDate());
        time.setText("Time of Report: " + selectedReport.getTime());
        reportNumber.setText("Report Number: " + selectedReport.getReportNumber());
        worker.setText("Worker Name: " + selectedReport.getCreator());
        location.setText("Location: " + selectedReport.getLocation());
        type.setText("Type: " + selectedReport.getType());
        condition.setText("Water Condition: " + selectedReport.getCondition());

        //Cancel button returns to Report Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowSourceReportActivity.this, ViewSourceReportAcitvity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });

    }
}
