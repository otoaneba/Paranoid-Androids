package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.olivia.myapplication.model.SourceReport;
import com.example.olivia.myapplication.model.User;

import static com.example.olivia.myapplication.model.RetrieveSourceReportData.reports;

/**
 * This is the page that shows a list of source reports. You
 * Have the option to either create source reports or show
 * details for individual source report.
 */
public class ViewSourceReportActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_action:
                Intent intent = new Intent(ViewSourceReportActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout_action:
                Intent intent1 = new Intent(ViewSourceReportActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
                return true;
        }
        return false;
    }

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_source_report_activity);
        //Initializes buttons on page

        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
//        Button cancelButton = (Button) findViewById(R.id.cancel_report_source);
//        Button viewMap = (Button) findViewById(R.id.view_source_report_map_button);
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addReport);
        FloatingActionButton locationButton = (FloatingActionButton) findViewById(R.id.locationButton);
        //The source reports need to be added to an array to be shown

        //Sets up list of source reports
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reports);
        final ListView reportList = (ListView) findViewById(R.id.report_list);
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

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSourceReportActivity.this, ViewPurityReportsLocationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSourceReportActivity.this, CreateSourceReportActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
//        //Cancel button returns to Main Screen Activity
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewSourceReportActivity.this, MainActivity.class);
//                intent.putExtra("user", user);
//                startActivity(intent);
//                finish();
//            }
//        });
//        //ViewMap button change display to the map page that views all the pin pointed locations
    }

}
