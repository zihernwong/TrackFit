package com.example.trackfit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CurrentWorkoutFragment extends Fragment implements View.OnClickListener {

    public CurrentWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_workout, container, false);
        Button stopWorkout = (Button) view.findViewById(R.id.saveWorkoutButton);
        stopWorkout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        EndWorkoutDialogFragment endDialog = new EndWorkoutDialogFragment();
        endDialog.show(getActivity().getSupportFragmentManager(), "end workout dialog");
    }
}