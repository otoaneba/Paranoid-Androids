package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.model.LatLng;

import com.example.olivia.myapplication.model.Report;
import com.example.olivia.myapplication.model.ReportManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * created by Naoto on 3/12/2017
 *
 * This is a class that lets users view past submitted PURITY REPORTS. The map wil display
 * pins on the locations with submitted purity reports
 *
 */
public class ViewPurityReportsLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ReportManager rptManager = new ReportManager();
    private List<Report> reportList = rptManager.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purity_reports_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
        LatLng LL = new LatLng(-34, 151);
        for (Report report : reportList) {
            LL = report.getLatLng();
           // String ad = report.getLocation();
            mMap.addMarker(new MarkerOptions().position(LL).title(report.showMap()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LL));
    }

}
