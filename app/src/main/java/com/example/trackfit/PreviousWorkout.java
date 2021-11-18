package com.example.trackfit;

public class PreviousWorkout {
    private String date;
    private String duration;

    public PreviousWorkout(String date, String duration) {
        this.date = date;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
