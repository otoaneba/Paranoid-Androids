package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.olivia.myapplication.model.SourceReport;
import com.example.olivia.myapplication.model.User;

public class ShowSourceReportActivity extends AppCompatActivity {
    private User user;
    private TextView source_title, source_time, source_reportNumber,
            source_worker, source_location, source_condition, source_type;
    private SourceReport selectedSourceReport;
    private Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_source_report);
        //Initializes cancel button

        cancelButton = (Button) findViewById(R.id.cancel_button_sourceReport);
        //Gets report passed in from the Report Activity
        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        String time_v = (String)getIntent().getSerializableExtra("time");
        int repNo_v = (Integer)getIntent().getSerializableExtra("repNo");
        String WorkerName_v = (String)getIntent().getSerializableExtra("WorkerName");
        String loc_v = (String)getIntent().getSerializableExtra("loc");
        String condition_v = (String)getIntent().getSerializableExtra("condition");
        String type_v = (String)getIntent().getSerializableExtra("type");

           //Initializes widgets from the XML
           source_title = (TextView) findViewById(R.id.Title_sourceReport);
           source_time = (TextView) findViewById(R.id.time_sourceReport);
           source_reportNumber = (TextView) findViewById(R.id.report_number_sourceReport);
           source_worker = (TextView) findViewById(R.id.worker_sourceReport);
           source_location = (TextView) findViewById(R.id.location_sourceReport);
           source_condition = (TextView) findViewById(R.id.condition_sourceReport);
           source_type = (TextView) findViewById(R.id.type_sourceReport);

        //Sets values from selected report
        source_title.setText("Source Report");
        source_time.setText("Time of Report: " + time_v);
        source_reportNumber.setText("Report Number: " + repNo_v);
        source_worker.setText("Worker Name: " + WorkerName_v);
        source_location.setText("Location: " + loc_v);
        source_type.setText("Type: " + type_v);
        source_condition.setText("Water Condition: " + condition_v);

        //Cancel button returns to Report Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowSourceReportActivity.this, ViewSourceReportActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });

    }
}
