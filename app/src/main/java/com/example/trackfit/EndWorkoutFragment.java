package com.example.trackfit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EndWorkoutFragment extends Fragment implements View.OnClickListener {

    public EndWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_end_workout, container, false);
        Button saveWorkout = (Button) view.findViewById(R.id.saveWorkoutButton);
        Button deleteWorkout = (Button) view.findViewById(R.id.deleteWorkoutButton);
        saveWorkout.setOnClickListener(this);
        deleteWorkout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new WorkoutFragment()).commit();
    }
}