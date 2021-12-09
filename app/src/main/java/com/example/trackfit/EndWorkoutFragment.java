package com.example.trackfit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Locale;

public class EndWorkoutFragment extends Fragment implements View.OnClickListener {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private TextView goalDaysDisplay;
    private TextView goalDistanceDisplay;
    private TextView goalCaloriesDisplay;
    private String gDays;
    private String gDistance;
    private String gCalories;
    private ProfileDetailsFragment Profile;
    public EndWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_end_workout, container, false);

        // Get references to UI elements
        Button saveWorkout = (Button) view.findViewById(R.id.saveWorkoutButton);
        Button deleteWorkout = (Button) view.findViewById(R.id.deleteWorkoutButton);
        TextView distance = (TextView) view.findViewById(R.id.totalDistance);
        TextView duration = (TextView) view.findViewById(R.id.totalElapsedTime);
        TextView stepsTaken = (TextView) view.findViewById(R.id.stepsTakenCounter);
        TextView caloriesBurned = (TextView) view.findViewById(R.id.caloriesCounter);
        TextView pace = (TextView) view.findViewById(R.id.paceTextView);
        goalDaysDisplay = (TextView) view.findViewById(R.id.daysProgressLabel);
        goalDistanceDisplay = (TextView) view.findViewById(R.id.distanceProgressLabel);
        goalCaloriesDisplay = (TextView) view.findViewById(R.id.caloriesProgressLabel);

        // Populate UI elements with correct values
        Profile = new ProfileDetailsFragment();
        gDays = String.valueOf(Profile.getGoalDays());//fetch the goals the user inputted
        gDistance = String.valueOf(Profile.getGoalDistance());//fetch the goals the user inputted
        gCalories = String.valueOf(Profile.getGoalCalories());//fetch the goals the user inputted
        goalDaysDisplay.setText("0/"+gDays);//hardcoded till we figure out the days days++
        goalDistanceDisplay.setText("0/" + gDistance);//hardcoded till we figure out the days
        goalCaloriesDisplay.setText("0/" + gCalories);

        int totalTime = savedInstanceState.getInt("time");
        float totalDistance = savedInstanceState.getFloat("distance");
        float totalSteps = savedInstanceState.getFloat("steps");

        duration.setText(formatTime(totalTime));
        distance.setText(df.format(totalDistance));
        stepsTaken.setText(String.valueOf((int) totalSteps));
        caloriesBurned.setText(String.valueOf((int) calculateCalories()));
        pace.setText(calculatePace(totalTime, totalDistance));

        saveWorkout.setOnClickListener(this);
        deleteWorkout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deleteWorkoutButton:
                DeleteWorkoutDialogFragment deleteDialog = new DeleteWorkoutDialogFragment();
                deleteDialog.show(getActivity().getSupportFragmentManager(), "delete workout dialog");
                break;
            case R.id.saveWorkoutButton:
                Context context = getActivity().getApplicationContext();
                SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("WorkoutsDB", Context.MODE_PRIVATE,null);
                DBHelper dbHelper = new DBHelper(sqLiteDatabase);

                // TODO: Hook up real data here
                dbHelper.saveWorkout("11/25/2021","20 Mins","2","10","200","1000");

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new WorkoutFragment()).commit();
                break;
        }
    }

    private String formatTime(int time) {
        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int secs = time % 60;

        return String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
    }

    private float calculateCalories() {
        // TODO
        return 0f;
    }

    private String calculatePace(int time, float distance) {
        int pace = (int) (time / distance);
        return formatTime(pace);
    }
}