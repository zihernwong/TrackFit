package com.example.trackfit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class CurrentWorkoutFragment extends Fragment implements View.OnClickListener, SensorEventListener {

    private int seconds = 0;
    private float stepsTaken = 0f;
    private float distanceTraveled = 0f;

    private SensorManager sensorManager;
    private Button stopWorkoutButton;
    private TextView durationTextView;
    private TextView currentDistanceTextView;

    public CurrentWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_workout, container, false);

        stopWorkoutButton = (Button) view.findViewById(R.id.saveWorkoutButton);
        durationTextView = (TextView) view.findViewById(R.id.currentElapsedTime);
        currentDistanceTextView = (TextView) view.findViewById(R.id.currentDistance);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        stopWorkoutButton.setOnClickListener(this);

        // Getting the step sensor
        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);

        runTimer();
        runDistance();

        return view;
    }

    @Override
    public void onClick(View v) {
        EndWorkoutDialogFragment endDialog = new EndWorkoutDialogFragment();
        endDialog.show(getActivity().getSupportFragmentManager(), "end workout dialog");
    }

    // Don't need to implement for steps in this project
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    // When the sensor detects a step, update the steps taken value
    @Override
    public void onSensorChanged(SensorEvent event) {
        stepsTaken = event.values[0];
        currentDistanceTextView.setText((int) stepsTaken);
    }

    // Starts the timer and updated UI every second
    private void runTimer() {
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                durationTextView.setText(time);
                seconds++;

                handler.postDelayed(this, 1000);
            }
        });
    }

    private void runDistance() {

    }
}