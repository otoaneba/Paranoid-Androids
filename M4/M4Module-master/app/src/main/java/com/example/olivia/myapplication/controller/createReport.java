package com.example.olivia.myapplication.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.GregorianCalendar;


import com.example.olivia.myapplication.model.ReportManager;
import com.example.olivia.myapplication.model.User;
import com.example.olivia.myapplication.model.waterQuality;

public class createReport extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        //This is a ReportManager object that will store the new report
        final ReportManager manager = new ReportManager();

        //This is the current user passed in
        final User user = (User) getIntent().getSerializableExtra("user");

        final Spinner etSpinner = (Spinner) findViewById(R.id.etConditionSpinner);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, waterQuality.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner.setAdapter(adapter2);

        final Button mapButton = (Button) findViewById(R.id.location_button);
        //final EditText etTime = (EditText) findViewById(R.id.etTime);
        final EditText etLocation = (EditText) findViewById(R.id.etLocation);
        final EditText etVirusPPM = (EditText) findViewById(R.id.etVirusPPM);
        final EditText etCombinationPPM = (EditText) findViewById(R.id.etCombinationPPM);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
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
                final String location = etLocation.getText().toString();
                final String virusPPM = etVirusPPM.getText().toString();
                final String combinationPPM = etCombinationPPM.getText().toString();
                final String condition = etSpinner.getSelectedItem().toString();
                //Checks to see if there is a missing input
                if (/*time.isEmpty() || */location.isEmpty() ||virusPPM.isEmpty() || combinationPPM.isEmpty() ) {
                    AlertDialog.Builder myAlert = new AlertDialog.Builder(createReport.this);
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
                    manager.addReport(time, location, virusPPM, combinationPPM, condition,
                            manager.size() + 1, todayDate);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });
        //cancel button that takes a user back to the welcome screen
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createReport.this, MapsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }

}
