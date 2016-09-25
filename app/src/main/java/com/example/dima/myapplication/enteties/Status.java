package com.example.dima.myapplication.enteties;

/**
 * Created by Dima on 23.09.2016.
 */

public enum Status {
    ATTENDING("Attending"), NOT_ATTENDING("Not attending");
    private String s;

    Status(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
