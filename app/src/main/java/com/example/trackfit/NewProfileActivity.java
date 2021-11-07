package com.example.trackfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewProfileActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    Button createProfileButton;
    boolean profileFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        //TODO: Will check shared preferences for profile details. If none are found we display this page
        // where a new user will enter their details. If details are found then we display the main application
        // but we still need to get location.

        locationStartup();

         if (profileFound) { displayApp(); }

        createProfileButton = findViewById(R.id.newProfileButton);
        createProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // TODO: Save data in shared preferences first
                displayApp();
            }
        });

    }

    public void locationStartup() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(@NonNull Location location) {
                // TODO
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}
            @Override
            public void onProviderEnabled(String s) {}
            @Override
            public void onProviderDisabled(String s) {}
        };

        if (Build.VERSION.SDK_INT < 23) {
            //TODO
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    //TODO
                }
            }
        }
    }

    public void displayApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}