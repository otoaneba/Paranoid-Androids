package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.olivia.myapplication.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Map that lets user pick the location for the PURITY REPORTS
 *
 */
public class PickPurityReportsLocationActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {
    private User user;
    private GoogleMap mMap;
    final private MarkerOptions myMarker = new MarkerOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapLongClickListener(this);
    }

    //function that lets user to long press on the map to place a pin on the map
    @Override
    public void onMapLongClick(LatLng point) {

        mMap.addMarker(myMarker
                .position(point)
                .title(point.toString())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        LatLng Position = myMarker.getPosition();
        Log.d("debug", myMarker.getPosition().toString());
        String address = getAddressFromLatLng(myMarker.getPosition());
        Log.d("address", address);


        Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
        intent.putExtra("address", address);
        intent.putExtra("Position", Position);
        intent.putExtra("user",user);
        //latLngList.add(Position);
        intent.putExtra("latitude", Position.latitude);
        intent.putExtra("longitude", Position.longitude);
        startActivity(intent);
        finish();
    }

    /**
     * a public method that parse a LatLng and returns an address of that LatLng
     * @param latLng a LatLng that a user picks to submit a PURITY REPORT
     * @return a string representation of address
     */
    public String getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(this);

        String address = "";
        try {
            address = geocoder
                    .getFromLocation( latLng.latitude, latLng.longitude, 1 )
                    .get( 0 ).getAddressLine( 0 );
        } catch (IOException e ) {

            System.out.println(e.getMessage());
        }

        return address;
    }

}