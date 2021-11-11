package com.example.trackfit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Button;

public class WorkoutFragment extends Fragment implements View.OnClickListener {

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        Button b = (Button) view.findViewById(R.id.button3);
        b.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CurrentWorkoutFragment()).commit();
                break;
        }
    }

}