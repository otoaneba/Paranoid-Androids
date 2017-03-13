package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.olivia.myapplication.model.User;
/**
 * Created by Olivia on 2/12/2017.
 *
 * MainActivity is the main page of the water app once the user logs in to his/her profile.
 * As of now, the only functionality is that the user can view the profile and edit it to
 * his/her liking
 */

public class MainActivity extends AppCompatActivity {

    private Button logoutButton;
    private Button profileButton;
    private Button reportButton;
    private Button viewPastReports;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                user = (User)getIntent().getSerializableExtra("user"); //Obtaining data
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_main);
        } catch (Exception e) {
            System.out.println(e);
        }

        logoutButton = (Button) findViewById(R.id.logout_button);
        profileButton = (Button) findViewById(R.id.profile_button);
        reportButton = (Button) findViewById(R.id.report_button);
        viewPastReports = (Button) findViewById(R.id._viewPastReportsButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewReportActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        viewPastReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPurityReportsLocationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
