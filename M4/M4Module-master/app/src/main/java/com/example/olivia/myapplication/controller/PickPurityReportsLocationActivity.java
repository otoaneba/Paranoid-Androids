package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.olivia.myapplication.model.ReportManager;
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
import java.util.List;
import java.util.Locale;

/**
 * Map that lets user pick the location for the PURITY REPORTS
 *
 */
public class PickPurityReportsLocationActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private MarkerOptions myMarker = new MarkerOptions();
    private ReportManager rptManager = new ReportManager();
    private ArrayList<LatLng> latlngList = new ArrayList<>(1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
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


        Intent putPinsIntent = new Intent(getApplicationContext(), ViewPurityReportsLocationActivity.class);
        Intent intent = new Intent(getApplicationContext(), CreateReportActivity.class);
        intent.putExtra("address", address);
        intent.putExtra("Position", Position);

        latlngList.add(Position);
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
    public String getAddressFromLatLng( LatLng latLng ) {
        Geocoder geocoder = new Geocoder(this);

        String address = "";
        try {
            address = geocoder
                    .getFromLocation( latLng.latitude, latLng.longitude, 1 )
                    .get( 0 ).getAddressLine( 0 );
        } catch (IOException e ) {

        }

        return address;
    }

    /**
     * public getter method for LatLng list
     * @return this LatLng list
     */

    public List<LatLng> getLatlng() {
        return latlngList;
    }
}
