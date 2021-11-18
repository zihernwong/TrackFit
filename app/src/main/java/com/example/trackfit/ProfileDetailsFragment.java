package com.example.trackfit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileDetailsFragment extends Fragment {
    private int goalDays;
    private float goalDistance;
    private int goalCalories;
    private EditText days;
    private EditText distance;
    private EditText calories;
    public ProfileDetailsFragment() {
        // Required empty public constructor
    }
    public int getGoalDays(){//going to be called when you decide whether to save or delete workout
        return this.goalDays;
    }
    public float getGoalDistance(){//going to be called when you decide whether to save or delete workout
        return this.goalDistance;
    }
    public int getGoalCalories(){//going to be called when you decide whether to save or delete workout
        return this.goalCalories;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_details, container, false);
        days = (EditText) view.findViewById(R.id.editDaysGoalProfileDetails) ;
        this.goalDays = Integer.parseInt(days.getText().toString());
        distance = (EditText) view.findViewById(R.id.editDistanceGoalProfileDetails) ;
        this.goalDistance = Float.parseFloat(distance.getText().toString());
        calories = (EditText) view.findViewById(R.id.editCaloriesGoalProfileDetails) ;
        this.goalCalories = Integer.parseInt(calories.getText().toString());
        return view;
    }
}