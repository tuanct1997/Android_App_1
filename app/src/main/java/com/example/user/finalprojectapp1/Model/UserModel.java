package com.example.user.finalprojectapp1.Model;

public class UserModel {
    private String username;
    private String password;
    private String email;
    private String phone_number;
    private String created_at;
    private String update_at;
    private HomeModel HomeModel;

    public UserModel() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public com.example.user.finalprojectapp1.Model.HomeModel getHomeModel() {
        return HomeModel;
    }

    public void setHomeModel(com.example.user.finalprojectapp1.Model.HomeModel homeModel) {
        HomeModel = homeModel;
    }
}
