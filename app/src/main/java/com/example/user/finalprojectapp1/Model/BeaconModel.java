package com.example.user.finalprojectapp1.Model;

public class BeaconModel {
    private int id;
    private String identifier;
    private StatusModel statuses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public StatusModel getStatusModel() {
        return statuses;
    }

    public void setStatusModel(StatusModel statusModel) {
        this.statuses = statusModel;
    }
}
