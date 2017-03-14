package com.example.olivia.myapplication.controller;

import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.gms.maps.model.LatLng;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;


import com.example.olivia.myapplication.model.ReportManager;
import com.example.olivia.myapplication.model.User;
import com.example.olivia.myapplication.model.waterQuality;

/**
 * This page allows you to create a new Purity Report.
 * If you click the create button, it saves the report
 * to the Report Activity. If you click cancel, the
 * report is not saved and the app returns to the
 * Report Activity.
 */
public class CreateReportActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_report);

        String address = "Address";
        LatLng reportLatLng = new LatLng(-33.852, 151.211);;
        try {
            Bundle extras = getIntent().getExtras();

            String ifNull= extras.getString("address");
            if (ifNull.length() != 0) {
                address = ifNull;
            }
            Double latitude = extras.getDouble("latitude");
            Double longitude = extras.getDouble("longitude");
            reportLatLng = new LatLng(latitude, longitude);

        } catch (Exception e) {
           Log.e("error",e.toString());
        }
        final String address1 = address;
        final LatLng reportLatLng1 = reportLatLng;


        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        //This is a ReportManager object that will store the new report
        final ReportManager manager = new ReportManager();

        //This is the current user passed in
        final User user = (User) getIntent().getSerializableExtra("user");
        //Initializes water conditions spinner
        final Spinner etSpinner = (Spinner) findViewById(R.id.etConditionSpinner_source);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, waterQuality.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner.setAdapter(adapter2);

        final Button mapButton = (Button) findViewById(R.id.location_button);
        //final EditText etTime = (EditText) findViewById(R.id.etTime);
        final TextView etLocation = (TextView) findViewById(R.id.addressTV_source);
        etLocation.setText(address);
        final EditText etVirusPPM = (EditText) findViewById(R.id.etVirusPPM_source);
        final EditText etContaminatePPM = (EditText) findViewById(R.id.etContaminatePPM_source);
        final Button registerButton = (Button) findViewById(R.id.createButton_source);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets current time and date
                Calendar c = new GregorianCalendar();
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
                SimpleDateFormat todayFormat = new SimpleDateFormat("MMMM dd");
                final String todayDate = "" + todayFormat.format(c.getTime()).toString();

                //Gets information from textboxes
                final String time = "" + timeFormat.format(c.getTime()).toString();
                final String location = "ad";
                final String virusPPM = etVirusPPM.getText().toString();
                final String contaminatePPM = etContaminatePPM.getText().toString();
                final String condition = etSpinner.getSelectedItem().toString();
                //Checks to see if there is a missing input
                if (/*time.isEmpty() || */location.isEmpty() ||virusPPM.isEmpty() || contaminatePPM.isEmpty() ) {
                    AlertDialog.Builder myAlert = new AlertDialog.Builder(CreateReportActivity.this);
                    myAlert.setMessage("Time,location,virusPPM and comninationPPM required")
                            .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                    myAlert.show();
                } else {
                    manager.addReport(time, address1, reportLatLng1, Double.parseDouble(virusPPM),
                            Double.parseDouble(contaminatePPM), condition,
                            manager.size() + 1, todayDate);
                    Intent intent = new Intent(CreateReportActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }
            }
        });
        //cancel button that takes a user back to the welcome screen
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateReportActivity.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateReportActivity.this, PickPurityReportsLocationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}
