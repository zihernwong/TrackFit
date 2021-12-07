package com.example.trackfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewProfileActivity extends AppCompatActivity {
    String usernameKey;
    LocationManager locationManager;
    LocationListener locationListener;
    Button createProfileButton;
    boolean profileFound = false;
    EditText etFirstName;
    EditText etLastName;
    EditText etDOB;
    EditText etHeightFeet;
    EditText etHeightInches;
    EditText etWeight;
    String fNameFromSP;
    String lNameFromSP;
    String dobFromSP;
    String hFeetFromSP;
    String hInchesFromSP;
    String weightFromSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);


        //TODO: Will check shared preferences for profile details. If none are found we display this page
        // where a new user will enter their details. If details are found then we display the main application
        // but we still need to get location.

        locationStartup();


        SharedPreferences sharedPreferences = getSharedPreferences("com.example.trackfit", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            //this.fNameFromSP = sharedPreferences.getString("FirstName","");
            //System.out.println("This code goes here");
           displayApp();
        } else {
            //System.out.println("Brev");
            createProfileButton = findViewById(R.id.newProfileButton);
            etFirstName = findViewById(R.id.editFirstNameNewProfile);
            etLastName = findViewById(R.id.editLastNameNewProfile);
            etDOB = findViewById(R.id.editDOBNewProfile);
            etHeightFeet = findViewById(R.id.editHeightFeetProfileDetails);
            etHeightInches = findViewById(R.id.editHeightInchesProfileDetails);
            etWeight = findViewById(R.id.editWeightProfileDetails);
            String fName = etFirstName.getText().toString();
            String lName = etLastName.getText().toString();
            String dBirth = etDOB.getText().toString();
            String hFeet = etHeightFeet.getText().toString();
            String hInches = etHeightInches.getText().toString();
            String weight = etWeight.getText().toString();
            //this.fNameFromSP = sharedPreferences.getString("FirstName"," ghgfh");
            createProfileButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    // TODO: Save data in shared preferences first

                    SharedPreferences.Editor editor = getSharedPreferences("com.example.trackfit", Context.MODE_PRIVATE).edit();
                    editor.putString(usernameKey, "name");
                    editor.putString("FirstName", "Hern1");
                    editor.apply();
                    //sharedPreferences.edit().putString(usernameKey, "name").apply();
                    //sharedPreferences.edit().putString("FirstName", fName).apply();
                    //sharedPreferences.edit().putString("LastName", lName).apply();
                    //sharedPreferences.edit().putString("dateOfBirth", dBirth).apply();
                    //sharedPreferences.edit().putString("heightFeet", hFeet).apply();
                    //sharedPreferences.edit().putString("hieghtInches", hInches ).apply();
                    //sharedPreferences.edit().putString("Weight", weight).apply();
                    displayApp();
                }
            });
        }

    }
//SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//SharedPreferences.Editor editor = sharedPref.edit();
//editor.putInt(getString(R.string.saved_high_score_key), newHighScore);
//editor.apply();


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
    public String getfNameFromSP(){
        return this.fNameFromSP;
    }
    public void displayApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}