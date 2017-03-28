package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.olivia.myapplication.R;
import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.User;

import org.w3c.dom.Text;


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
        double contam_v = (Double)getIntent().getSerializableExtra("contam");
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
        title.setText("Purity Report");
        time.setText("Time of Report: " + time_v);
        reportNumber.setText("Report Number: " + repNo_v);
        worker.setText("Worker Name: " + WorkerName_v);
        location.setText("Location: " + loc_v);
        condition.setText("Water Condition: " + condition_v);
        virusPpm.setText("Virus PPM: " + virus_v);
        contaminationPpm.setText("Contamination PPM: " + contam_v);

        //Cancel button returns to Report Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowReportActivity.this, ViewReportActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });

    }
}