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
import com.google.android.gms.maps.model.LatLng;

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
        Button updateButton = (Button) findViewById(R.id.updateButton);

        //Bundle bundle = getIntent().getParcelableExtra("bundle");
        final LatLng latLng_v = getIntent().getExtras().getParcelable("latlng");

        //Gets report's values passed from the Report Activity
        String date_v = (String) getIntent().getSerializableExtra("date");
        String time_v = (String) getIntent().getSerializableExtra("time");
        int repNo_v = (Integer) getIntent().getSerializableExtra("repNo");
        String WorkerName_v = (String) getIntent().getSerializableExtra("WorkerName");
        final String loc_v = (String) getIntent().getSerializableExtra("loc");
        String condition_v = (String) getIntent().getSerializableExtra("condition");
        double virus_v = (Double) getIntent().getSerializableExtra("virus");
        double contam_v = (Double) getIntent().getSerializableExtra("contam");
        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data

        //Initializes widgets from the XML
        TextView title = (TextView) findViewById(R.id.Title);
        TextView time = (TextView) findViewById(R.id.time);
        TextView reportNumber = (TextView) findViewById(R.id.report_number);
        TextView worker = (TextView) findViewById(R.id.worker);
        final TextView location = (TextView) findViewById(R.id.location);
        TextView condition = (TextView) findViewById(R.id.condition);
        TextView contaminationPpm = (TextView) findViewById(R.id.contamination);
        TextView virusPpm = (TextView) findViewById(R.id.PPM);

        //Sets values from selected report
        title.setText("Purity Report: " + date_v);
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
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowReportActivity.this, CreateReportActivity.class);
                intent.putExtra("address", loc_v);
                intent.putExtra("user", user);
                intent.putExtra("reportLatLng", latLng_v);
                startActivity(intent);
                finish();
            }
        });

    }
}
