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
import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.User;
import static com.example.olivia.myapplication.model.RetrievePurityReportData.reports;

/**
 * This is the page that shows a list of reports. You
 * Have the option to either view report locations or show
 * details for individual reports.
 */
public class ViewReportActivity extends AppCompatActivity {

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
                Intent intent = new Intent(ViewReportActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout_action:
                Intent intent1 = new Intent(ViewReportActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_report_layout);
        //Get current user
        final User user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        //Initializes buttons on page
//        Button locationButton = (Button) findViewById(R.id.location);
//        Button cancelButton = (Button) findViewById(R.id.cancel_report);
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addReport);
        FloatingActionButton locationButton = (FloatingActionButton) findViewById(R.id.locationButton);

        //The reports need to be added to an array to be shown
        //final List<Report> reports = manager.getList();

        //Sets up list of reports
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reports);
        final ListView reportList = (ListView) findViewById(R.id.report_list);
        reportList.setAdapter(adapter);
        reportList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ViewReportActivity.this, ShowReportActivity.class);

                        //store selected report's information into local variables
                        Report report = (Report) parent.getItemAtPosition(position);
                        String time = report.getTime();
                        int repNo = report.getReportNumber();
                        String WorkerName =report.getCreator();
                        String loc = report.getLocation();
                        String condition = report.getCondition();
                        double virusPPM = report.getVirusPPM();
                        double contaminantPPM = report.getCombinationPPM();

                        //Pass all the selected report's information to the ShowReportActivity
                        intent.putExtra("time",time);
                        intent.putExtra("repNo",repNo);
                        intent.putExtra("WorkerName",WorkerName);
                        intent.putExtra("loc",loc);
                        intent.putExtra("condition",condition);
                        intent.putExtra("virus",virusPPM);
                        intent.putExtra("contam",contaminantPPM);
                        intent.putExtra("user",user);
                        startActivity(intent);
                    }
                });
//        //Location button goes to create report page
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewReportActivity.this, ViewPurityReportsLocationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewReportActivity.this, CreateReportActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
//        //Cancel button returns to Main Screen Activity
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ViewReportActivity.this, MainActivity.class);
//                intent.putExtra("user", user);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
}