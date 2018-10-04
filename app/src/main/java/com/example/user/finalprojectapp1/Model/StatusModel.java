package com.example.user.finalprojectapp1.Model;

public class StatusModel {


    private String acceleration;
    private String temperature;
    private String location;

    public StatusModel( String acceleration, String temperature, String location) {

        this.acceleration = acceleration;
        this.temperature = temperature;
        this.location = location;
    }

    public StatusModel(){

    }

    public String getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(String acceleration) {
        this.acceleration = acceleration;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
