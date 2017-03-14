package com.example.olivia.myapplication.controller;

import android.content.Intent;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import java.util.List;

import static com.example.olivia.myapplication.controller.R.id.user;

public class PickSourceReportLocationActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private User user;
    private GoogleMap mMap;
    private MarkerOptions mySourceMarker = new MarkerOptions();
    private ArrayList<LatLng> sourcelatlngList = new ArrayList<>(1000);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_source_report_location);
        user = (User) getIntent().getSerializableExtra("user"); //Obtaining data
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
        mMap.setOnMapLongClickListener(this);

    }
    @Override
    public void onMapLongClick(LatLng point) {

        mMap.addMarker(mySourceMarker
                .position(point)
                .title(point.toString())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        LatLng Position = mySourceMarker.getPosition();
        Log.d("debug", mySourceMarker.getPosition().toString());
        String address = getAddressFromLatLng(mySourceMarker.getPosition());
        Log.d("address", address);


        Intent intent = new Intent(getApplicationContext(), CreateSourceReportActivity.class);
        intent.putExtra("address", address);
        intent.putExtra("Position", Position);
        intent.putExtra("user",user);
        sourcelatlngList.add(Position);
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

            System.out.println(e.getMessage());
        }

        return address;
    }

    /**
     * public getter method for LatLng list
     * @return this LatLng list
     */

    public List<LatLng> getSourceLatlng() {
        return sourcelatlngList;
    }
}
