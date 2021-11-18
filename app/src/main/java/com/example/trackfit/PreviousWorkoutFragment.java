package com.example.trackfit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PreviousWorkoutFragment extends Fragment {
    private ArrayList<PreviousWorkout> previousWorkoutsList;
    private RecyclerView recyclerView;
    private PreviousWorkoutRecyclerAdapter.RecyclerViewClickListener listener;

    public PreviousWorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        previousWorkoutsList = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_previous_workout, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        setPreviousWorkoutInfo();
        setAdapter();

        return view;
    }

    private void setAdapter() {
        setOnClickListener();
        PreviousWorkoutRecyclerAdapter adapter = new PreviousWorkoutRecyclerAdapter(previousWorkoutsList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new PreviousWorkoutRecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PreviousWorkoutDetailsFragment()).commit();
            }
        };
    }

    private void setPreviousWorkoutInfo() {
        previousWorkoutsList.add(new PreviousWorkout("2020/11/18","0:01:00"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/17","0:02:00"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
        previousWorkoutsList.add(new PreviousWorkout("2020/11/16","0:03:10"));
    }
}