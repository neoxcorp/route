package com.route.model.repositories;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.route.model.callbacks.LocationCallback;
import com.route.utils.Permissions;

import javax.inject.Inject;

import static android.content.Context.LOCATION_SERVICE;

public class LocationRepository implements LocationListener {

    public static final int REQUEST_CODE = 678;

    private Activity activity;

    private LocationCallback locationCallback;

    private LocationManager locationManager;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Inject
    public LocationRepository() {
    }

    public void initLocationServices(Activity activity, LocationCallback locationCallback) {
        this.activity = activity;
        this.locationCallback = locationCallback;

        this.locationManager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);

        findingMyLocation();
    }

    public void findingMyLocation() {
        if (Permissions.isGranted(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                && Permissions.isGranted(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            getMyLocation();
        } else {
            Permissions.requestSeveral(activity, REQUEST_CODE,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION });
        }
    }

    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(activity, location -> {
                    if (location != null) {
                        locationCallback.onLocationChanged(new LatLng(location.getLatitude(),
                                location.getLongitude()));
                    }
                });

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                10, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000,
                2, this);
    }

    private void stopFindingMyLocation() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        stopFindingMyLocation();
        locationCallback.onLocationChanged(new LatLng(location.getLatitude(),
                location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //
    }

    @Override
    public void onProviderEnabled(String provider) {
        //
    }

    @Override
    public void onProviderDisabled(String provider) {
        //
    }
}
