package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.olivia.myapplication.model.User;

/*Shuopeng Zhou
/*This activity is intend to show source report when you click the option, it provides functionality of passing data between
/*two activities
 */


public class ShowSourceReportActivity extends AppCompatActivity {
    private User user;
 //   private SourceReport selectedSourceReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_source_report);
        //Initializes cancel button

        Button cancelButton = (Button) findViewById(R.id.cancel_button_sourceReport);
        //Gets report passed in from the Report Activity
        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        String time_v = (String)getIntent().getSerializableExtra("time");
        int repNo_v = (Integer)getIntent().getSerializableExtra("repNo");
        String WorkerName_v = (String)getIntent().getSerializableExtra("WorkerName");
        String loc_v = (String)getIntent().getSerializableExtra("loc");
        String condition_v = (String)getIntent().getSerializableExtra("condition");
        String type_v = (String)getIntent().getSerializableExtra("type");

           //Initializes widgets from the XML
//        TextView source_title = (TextView) findViewById(R.id.Title_sourceReport);
        TextView source_time = (TextView) findViewById(R.id.time_sourceReport);
        TextView source_reportNumber = (TextView) findViewById(R.id.report_number_sourceReport);
        TextView source_worker = (TextView) findViewById(R.id.worker_sourceReport);
        TextView source_location = (TextView) findViewById(R.id.location_sourceReport);
        TextView source_condition = (TextView) findViewById(R.id.condition_sourceReport);
        TextView source_type = (TextView) findViewById(R.id.type_sourceReport);

        //Sets values from selected report

        ///String stringSourceTitle = "Source Report";
//        source_title.setText(stringSourceTitle);

        String stringSourceTime = "Time of Report: " + time_v;
        source_time.setText(stringSourceTime);

        String stringSourceReportNumber = "Report Number: " + repNo_v;
        source_reportNumber.setText(stringSourceReportNumber);

        String stringSourceWorker = "Worker Name: " + WorkerName_v;
        source_worker.setText(stringSourceWorker);

        String stringSourceLocation = "Location: " + loc_v;
        source_location.setText(stringSourceLocation);

        String stringSourceType = "Type: " + type_v;
        source_type.setText(stringSourceType);

        String stringSourceCondition = "Water Condition: " + condition_v;
        source_condition.setText(stringSourceCondition);

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
