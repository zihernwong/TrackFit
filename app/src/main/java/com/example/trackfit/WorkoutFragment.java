package com.example.trackfit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.lang.Math;

import java.util.ArrayList;

public class WorkoutFragment extends Fragment implements View.OnClickListener {
    private ArrayList<String>quotes = new ArrayList<String>();
    private TextView quotesTextView;
    public WorkoutFragment() {
        this.quotes.add("The Pain You Feel Today, Will Be The Strength You Feel Tomorrow");
        this.quotes.add("You Don't Have To Be Extreme, Just Consistent");
        quotes.add("All Progress Takes Place Outside Your Comfort Zone");
        quotes.add("Later = Never. Do It Now");
        quotes.add("A Little Progress Each Day Adds Up To Big Results");
        quotes.add("Be Somebody Nobody Thought You Could Be");
        quotes.add("When You Feel Like Quitting, Think About Why You Started");
        quotes.add("Find Your Fire");
        quotes.add("Push Through The Pain Every Single Day");
        quotes.add("Turn The Pain Into Power");
        quotes.add("If It's Easy You Are Doing It Wrong");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        Button startWorkout = (Button) view.findViewById(R.id.startWorkoutButton);
        quotesTextView = (TextView) view.findViewById(R.id.quoteTextView) ;
        String toDisplayQuote ="";
        int randQuote = (int)(Math.random() * 9);
        toDisplayQuote = quotes.get(randQuote);
        quotesTextView.setText(toDisplayQuote);
        startWorkout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startWorkoutButton:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CurrentWorkoutFragment()).commit();
                break;
        }
    }

}