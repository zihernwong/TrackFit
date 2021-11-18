package com.example.trackfit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PreviousWorkoutDetailsFragment extends Fragment implements View.OnClickListener{


    public PreviousWorkoutDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_previous_workout_details, container, false);
        Button backButton = (Button)view.findViewById(R.id.previousWorkoutBackButton);
        backButton.setOnClickListener(this);
        Button deleteButton = (Button)view.findViewById(R.id.previousWorkoutDeleteButton);
        deleteButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previousWorkoutDeleteButton:
                Bundle bundle = new Bundle();
                bundle.putString("sourceLocation", "prevWorkoutFragment");
                DeleteWorkoutDialogFragment deleteDialog = new DeleteWorkoutDialogFragment();
                deleteDialog.setArguments(bundle);
                deleteDialog.show(getActivity().getSupportFragmentManager(), "delete workout dialog");
                break;
            case R.id.previousWorkoutBackButton:

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PreviousWorkoutFragment()).commit();
                break;
        }

    }
}