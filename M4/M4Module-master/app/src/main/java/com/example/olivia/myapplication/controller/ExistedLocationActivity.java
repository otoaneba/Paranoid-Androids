package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.User;

import static com.example.olivia.myapplication.model.RetrievePurityReportData.reports;

public class ExistedLocationActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existed_location);
        Button cancel = (Button) findViewById(R.id.cancel_button_existing);

        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reports);
        final ListView reportList = (ListView) findViewById(R.id.existed_report_list);
        reportList.setAdapter(adapter);
        reportList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ExistedLocationActivity.this, CreateReportActivity.class);

                        //store selected report's information into local variables
                        Report report = (Report) parent.getItemAtPosition(position);
                        String loc = report.getLocation();
                        double lat = report.getLatLng().latitude;
                        double lon = report.getLatLng().longitude;
                        intent.putExtra("user",user);
                        intent.putExtra("address",loc);
                        intent.putExtra("latitude",lat);
                        intent.putExtra("longitude",lon);
                        startActivity(intent);
                    }
                });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExistedLocationActivity.this, CreateReportActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });
    }
}