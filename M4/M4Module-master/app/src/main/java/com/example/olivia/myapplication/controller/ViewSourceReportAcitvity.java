package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.ReportManager;
import com.example.olivia.myapplication.model.SourceReport;
import com.example.olivia.myapplication.model.SourceReportManager;
import com.example.olivia.myapplication.model.User;

import java.util.List;

public class ViewSourceReportAcitvity extends AppCompatActivity {
    Button cancelButton;
    Button viewMap;
    private SourceReportManager manager = new SourceReportManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_source_report_acitvity);
        //Initializes buttons on page
        final User user = (User) getIntent().getSerializableExtra("user"); //Obtaining data

        cancelButton = (Button) findViewById(R.id.cancel_report_source);

        //The reports need to be added to an array to be shown
        final List<SourceReport> reports = manager.getList();

        //Sets up list of reports
        ListAdapter adapter = new ArrayAdapter<SourceReport>(this, android.R.layout.simple_list_item_1, reports);
        final ListView reportList = (ListView) findViewById(R.id.report_list_source);
        reportList.setAdapter(adapter);
        reportList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ViewSourceReportAcitvity.this, ShowReportActivity.class);
                        intent.putExtra("selectedReport", (SourceReport) parent.getItemAtPosition(position));
                        SourceReport selectedReport = (SourceReport) reportList.getSelectedItem();
                        intent.putExtra("selectedReport", selectedReport);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        //Cancel button returns to Main Screen Activity
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSourceReportAcitvity.this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSourceReportAcitvity.this, ViewSourceReportsLocationActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }

}
