package com.example.trackfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }

    // Bottom navigation bar selecting one of the three activities (Xiao)
    private NavigationBarView.OnItemSelectedListener bottomnavFunction = new NavigationBarView.OnItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.previous_workout:
                    fragment = new PreviousWorkoutFragment();
                    break;
                case R.id.workout:
                    fragment = new WorkoutFragment();
                    break;
                case R.id.profile:
                    fragment = new ProfileDetailsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };


}