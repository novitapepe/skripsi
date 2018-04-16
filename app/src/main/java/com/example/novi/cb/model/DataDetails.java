package com.example.novi.cb.model;

public class DataDetails {
    private String sensor;
    private String value;
    private String time;

    public DataDetails(String sensor, String value, String time) {
        this.sensor = sensor;
        this.value = value;
        this.time = time;
    }

    public String getSensor() {
        return sensor;
    }

    public String getValue() {
        return value;
    }

    public String getTime() {
        return time;
    }
}
