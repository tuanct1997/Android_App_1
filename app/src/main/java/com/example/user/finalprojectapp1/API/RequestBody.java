package com.example.user.finalprojectapp1.API;

import com.example.user.finalprojectapp1.Model.StatusModel;
import com.example.user.finalprojectapp1.Model.StatusParamsModel;
import com.example.user.finalprojectapp1.Model.UserModel;



public class RequestBody {
    private String username;
    private String password;
    private UserModel user;
    private StatusModel status;
    private StatusParamsModel device;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public StatusParamsModel getDevice() {
        return device;
    }

    public void setDevice(StatusParamsModel device) {
        this.device = device;
    }

    public RequestBody() {

    }
}

