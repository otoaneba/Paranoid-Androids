package com.example.olivia.myapplication.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.olivia.myapplication.model.ReportManager;
import com.example.olivia.myapplication.model.userType;

public class createReport extends AppCompatActivity {
private static ReportManager reports = new ReportManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        final Spinner etSpinner = (Spinner) findViewById(R.id.etConditionSpinner);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userType.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etSpinner.setAdapter(adapter2);

        final EditText etTime = (EditText) findViewById(R.id.etTime);
        final EditText etLocation = (EditText) findViewById(R.id.etLocation);

        final EditText etVirusPPM = (EditText) findViewById(R.id.etVirusPPM);
        final EditText etCombinationPPM = (EditText) findViewById(R.id.etCombinationPPM);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String time = etTime.getText().toString();
                final String location = etLocation.getText().toString();
                final String virusPPM = etVirusPPM.getText().toString();
                final String combinationPPM = etCombinationPPM.getText().toString();
                reports.addReport(time, location, virusPPM, combinationPPM, reports.size() + 1);
                if (time.isEmpty() || location.isEmpty() ||virusPPM.isEmpty() || combinationPPM.isEmpty() ) {
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
                    startActivity(new Intent(getApplicationContext(), DummyApp.class));
                    finish();
                }
            }
        });
//cancel button that takes a user back to the welcome screen
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DummyApp.class));
            }
        });
    }

}
