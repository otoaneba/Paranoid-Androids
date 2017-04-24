package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.olivia.myapplication.model.RetrieveGraphData;
import com.example.olivia.myapplication.model.RetrievePurityReportData;
import com.example.olivia.myapplication.model.RetrieveSourceReportData;
import com.example.olivia.myapplication.model.RetrieveUserData;
import com.example.olivia.myapplication.model.User;

/**
 * @author Kyung Jun Lee
 *
 * MainActivity is the main page of the water app once the user logs in to his/her profile.
 * Depends on the current user's user type, the app shows different functionalities.
 * Modified by Kyung Jun Lee on 3/8/2017
 */

public class MainActivity extends AppCompatActivity {
    private User user;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        //MenuItem item = menu.findItem(R.id.action_settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_action:
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                return true;
            case R.id.logout_action:
                Intent intent1 = new Intent(MainActivity.this, RetrieveUserData.class);
                startActivity(intent1);
                finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
            }
        } catch (Exception e) {
            Log.d("debug", "something went wrong");
        }

        //shows the current user's name and type of the user.
        TextView _userInfo = (TextView) findViewById(R.id._userInfo);
        _userInfo.setText("Hello \n" + user.getName());

//        Button submitSourceReport = (Button) findViewById(R.id.submit_source);
        Button viewSourceReport = (Button) findViewById(R.id.view_source);
//        Button purityReport = (Button) findViewById(R.id.report_purity);
        Button viewPurity = (Button) findViewById(R.id.view_purity);
        Button historyGraph = (Button) findViewById(R.id.history_graph);
        Button _security = (Button) findViewById(R.id._security);

        /*if the user logs in, screen displays following functionality:
            - Submit a report on water availability
            - View available water sources
            - My Profile (Edit)
            - Sign out
        */

        if (user.getUserType().contains("user")) {
//            purityReport.setVisibility(View.GONE);
            viewPurity.setVisibility(View.GONE);
            historyGraph.setVisibility(View.GONE);
            _security.setVisibility(View.GONE);
            Log.d("error", "if was here");

            /*if the worker logs in, screen displays following functionality:
               - Submit a report on water availability
               - View available water sources
               - Report on water purity levels
               - My Profile (Edit)
               - Sign out
             */
        } else if (user.getUserType().contains("worker")) {
            viewPurity.setVisibility(View.GONE);
            historyGraph.setVisibility(View.GONE);
            _security.setVisibility(View.GONE);

            /*if the worker logs in, screen displays following functionality:
               - Submit a report on water availability
               - View available water sources
               - Report on water purity levels
               - View historical reports
               - View trends of water purity
               - My Profile (Edit)
               - Sign out
             */
        } else if (user.getUserType().contains("manager")) {
            _security.setVisibility(View.GONE);
        } else if (user.getUserType().contains("admin")) {
//            purityReport.setVisibility(View.GONE);
            viewPurity.setVisibility(View.GONE);
            historyGraph.setVisibility(View.GONE);
        }
////        submitSourceReport.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CreateSourceReportActivity.class);
//                intent.putExtra("user",user);
//                startActivity(intent);
//            }
//        });
        viewSourceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RetrieveSourceReportData.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

////        purityReport.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
//                intent.putExtra("user",user);
//                startActivity(intent);
//            }
//        });

        viewPurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RetrievePurityReportData.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        historyGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RetrieveGraphData.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}
