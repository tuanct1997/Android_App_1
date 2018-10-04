package com.example.user.finalprojectapp1.Model;

public class StatusParamsModel {
    private String identifier;
    private StatusModel status;

    public StatusParamsModel(String identifier, StatusModel status) {
        this.identifier = identifier;
        this.status = status;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }
}
