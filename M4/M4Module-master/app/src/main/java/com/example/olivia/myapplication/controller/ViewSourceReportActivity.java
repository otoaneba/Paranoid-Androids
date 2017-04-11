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

import com.example.olivia.myapplication.model.SourceReport;
import com.example.olivia.myapplication.model.User;

import java.util.List;

import static com.example.olivia.myapplication.model.RetrieveSourceReportData.reports;

/**
 * This is the page that shows a list of source reports. You
 * Have the option to either create source reports or show
 * details for individual source report.
 */
public class ViewSourceReportActivity extends AppCompatActivity {
    Button cancelButton;
    Button viewMap;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_source_report_activity);
        //Initializes buttons on page

        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        cancelButton = (Button) findViewById(R.id.cancel_report_source);
        viewMap = (Button) findViewById(R.id.view_source_report_map_button);

        //The source reports need to be added to an array to be shown
        final List<SourceReport> sourceReports = reports;

        //Sets up list of source reports
        ListAdapter adapter = new ArrayAdapter<SourceReport>(this, android.R.layout.simple_list_item_1, sourceReports);
        final ListView reportList = (ListView) findViewById(R.id.report_list_source);
        reportList.setAdapter(adapter);
        reportList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(ViewSourceReportActivity.this, ShowSourceReportActivity.class);

                        //store selected report's information into local variables
                        SourceReport report = (SourceReport) parent.getItemAtPosition(position);
                        String time = report.getTime();
                        int repNo = report.getReportNumber();
                        String WorkerName =report.getCreator();
                        String loc = report.getLocation();
                        String condition = report.getCondition();
                        String type = report.getType();

                        //Pass all the selected report's information to the ShowSourceReportActivity
                        intent.putExtra("repNo",repNo);
                        intent.putExtra("time",time);
                        intent.putExtra("WorkerName",WorkerName);
                        intent.putExtra("loc",loc);
                        intent.putExtra("condition",condition);
                        intent.putExtra("type",type);
                        intent.putExtra("user",user);
                        startActivity(intent);
                    }
                }
        );
        //Cancel button returns to Main Screen Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSourceReportActivity.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        //ViewMap button change display to the map page that views all the pin pointed locations
        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSourceReportActivity.this, ViewSourceReportsLocationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

}
