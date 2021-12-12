package com.example.trackfit;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EndWorkoutFragment extends Fragment implements View.OnClickListener {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private TextView goalDaysDisplay;
    private TextView goalDistanceDisplay;
    private TextView goalCaloriesDisplay;

    private int caloriesBurntDuringWorkout;

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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.trackfit", Context.MODE_PRIVATE);

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
        gDays = sharedPreferences.getString("goalDays", "");;//fetch the goals the user inputted
        gDistance = sharedPreferences.getString("goalDistance", "");//fetch the goals the user inputted
        gCalories = sharedPreferences.getString("goalCalories", "");//fetch the goals the user inputted
        goalDaysDisplay.setText("0/"+gDays);//hardcoded till we figure out the days days++
        String curDist = "";
        goalDistanceDisplay.setText("0/" + gDistance);//hardcoded till we figure out the days
        goalCaloriesDisplay.setText("0/" + gCalories);

        int totalTime = getArguments().getInt("time");
        float totalDistance = getArguments().getFloat("distance");
        float totalSteps = getArguments().getFloat("steps");
        String weight = sharedPreferences.getString("Weight", "");

        caloriesBurntDuringWorkout = calculateCalories(totalTime, weight);

        duration.setText(formatTime(totalTime));
        distance.setText(df.format(totalDistance));
        stepsTaken.setText(String.valueOf((int) totalSteps));
        caloriesBurned.setText(String.valueOf(caloriesBurntDuringWorkout));
        pace.setText(calculatePace(totalTime, totalDistance));

        saveWorkout.setOnClickListener(this);
        deleteWorkout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int totalTime = getArguments().getInt("time");
        float totalDistance = getArguments().getFloat("distance");
        float totalSteps = getArguments().getFloat("steps");

        switch (v.getId()) {
            case R.id.deleteWorkoutButton:
                DeleteWorkoutDialogFragment deleteDialog = new DeleteWorkoutDialogFragment();
                deleteDialog.show(getActivity().getSupportFragmentManager(), "delete workout dialog");
                break;
            case R.id.saveWorkoutButton:
                Context context = getActivity().getApplicationContext();
                SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("WorkoutsDB", Context.MODE_PRIVATE,null);
                DBHelper dbHelper = new DBHelper(sqLiteDatabase);

                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = new Date();

                dbHelper.saveWorkout(dateFormat.format(date), formatTime(totalTime), df.format(totalDistance) + " mi",
                        calculatePace(totalTime, totalDistance),String.valueOf(caloriesBurntDuringWorkout), String.valueOf((int) totalSteps));

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

    private int calculateCalories(int duration, String weight) {
        int mins = duration / 60;
        double weight_kg = Integer.parseInt(weight) * 0.453592;
        return (int) (((9.8 * 3.5 * weight_kg) / 200) * mins);
    }

    private String calculatePace(int time, float distance) {
        int pace = (int) (time / distance);
        return formatTime(pace);
    }
}