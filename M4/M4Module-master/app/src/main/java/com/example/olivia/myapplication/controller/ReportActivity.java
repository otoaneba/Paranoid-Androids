package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.ReportManager;
import com.example.olivia.myapplication.model.User;

import java.util.List;

import static com.example.olivia.myapplication.controller.R.id.cancelButton;
import static com.example.olivia.myapplication.controller.R.id.user;

/**
 * This is the page that shows a list of reports. You
 * Have the option to either create reports or show
 * details for individual reports.
 */
public class ReportActivity extends AppCompatActivity {
    private Button createButton, cancelButton;

    private ReportManager manager = new ReportManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);
        //Get current user
        final User user = User.getCurrentUser();
        //Initializes buttons on page
        createButton = (Button) findViewById(R.id.create_report);
        cancelButton = (Button) findViewById(R.id.cancel_report);

        //The reports need to be added to an array to be shown
        final List<Report> reports = manager.getList();

        //Sets up list of reports
        ListAdapter adapter = new ArrayAdapter<Report>(this, android.R.layout.simple_list_item_1, reports);
        final ListView reportList = (ListView) findViewById(R.id.report_list);
        reportList.setAdapter(adapter);
        reportList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ReportActivity.this, ShowReportActivity.class);
                        intent.putExtra("selectedReport", (Report) parent.getItemAtPosition(position));
                        Report selectedReport = (Report) reportList.getSelectedItem();
                        //intent.putExtra("selectedReport", selectedReport);
                        startActivity(intent);
                        finish();
                    }
                }
        );
        //Create button goes to create report page
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, createReport.class);
                //intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        //Cancel button returns to Main Screen Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
}
