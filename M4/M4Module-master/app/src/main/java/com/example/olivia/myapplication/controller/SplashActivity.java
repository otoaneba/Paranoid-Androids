package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.olivia.myapplication.model.RetrieveUserData;

/**
 * A welcome screen that displays while the app initially loads.
 * @author Olivia
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, RetrieveUserData.class);
        startActivity(intent);
        finish();
    }
}
