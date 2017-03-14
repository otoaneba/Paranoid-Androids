package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.olivia.myapplication.model.User;

/**
 * Created by Olivia on 2/12/2017.
 *
 * MainActivity is the main page of the water app once the user logs in to his/her profile.
 * As of now, the only functionality is that the user can view the profile and edit it to
 * his/her liking
 */

public class MainActivity extends AppCompatActivity {

    private TextView _userInfo;
    private User user;
    Button _submit, _view, _purityLevel, _viewHistory,
            _trend, _security, _signOut, _profile;

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
        _userInfo = (TextView) findViewById(R.id._userInfo);
        _userInfo.setText("Hello, " + user.getName() + "(" + user.getUserType() + ")");

        _submit = (Button) findViewById(R.id._submit);
        _view = (Button) findViewById(R.id._view);
        _purityLevel = (Button) findViewById(R.id._purityLevel);
        _viewHistory = (Button) findViewById(R.id._viewHistory);
        _trend = (Button) findViewById(R.id._trend);
        _security = (Button) findViewById(R.id._security);
        _profile = (Button) findViewById(R.id._profile);
        _signOut = (Button) findViewById(R.id._signOut);

        /*if the user logs in, screen displays following functionalities:
            - Submit a report on water availability
            - View available water sources
            - My Profile (Edit)
            - Sign out
        */
        if (user.getUserType().equals("user")) {
            _purityLevel.setVisibility(View.GONE);
            _viewHistory.setVisibility(View.GONE);
            _trend.setVisibility(View.GONE);
            _security.setVisibility(View.GONE);
            /*if the worker logs in, screen displays following functionalities:
               - Submit a report on water availability
               - View available water sources
               - Report on water purity levels
               - My Profile (Edit)
               - Sign out
             */
        } else if (user.getUserType().equals("worker")) {
            _viewHistory.setVisibility(View.GONE);
            _trend.setVisibility(View.GONE);
            _security.setVisibility(View.GONE);

            /*if the worker logs in, screen displays following functionalities:
               - Submit a report on water availability
               - View available water sources
               - Report on water purity levels
               - View historical reports
               - View trends of water purity
               - My Profile (Edit)
               - Sign out
             */
        } else if (user.getUserType().equals("manager")) {
            _security.setVisibility(View.GONE);
        }
         //else (admin page), it will show all the functionalities.
        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateSourceReportActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });
        _signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        _view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewSourceReportAcitvity.class);
                startActivity(intent);
                finish();
            }
        });

        _profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });
        _purityLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });

        _viewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewReportActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }
//    private TextView userInfo;
//    private Button logoutButton;
//    private Button profileButton;
//    private Button reportButton;
//    private User user;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        try {
//            Bundle extras = getIntent().getExtras();
//            if (extras != null) {
//                user = (User)getIntent().getSerializableExtra("user"); //Obtaining data
//            }
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.content_main);
//        } catch (Exception e) {
//            Log.d("debug","something went wrong");
//        }
//        //displays the current user's name and userType on the top-left screen.
//        userInfo = (TextView) findViewById(R.id.userInfo_text);
//        userInfo.setText(user.getName() + " (" + user.getUserType() + ")");
//        logoutButton = (Button) findViewById(R.id.logout_button);
//        profileButton = (Button) findViewById(R.id.profile_button);
//        reportButton = (Button) findViewById(R.id.report_button);
//
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        reportButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ViewReportActivity.class);
//                intent.putExtra("user", user);
//                startActivity(intent);
//            }
//        });
//
//        profileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//                intent.putExtra("user", user);
//                startActivity(intent);
//            }
//        });
//
//    }
}
