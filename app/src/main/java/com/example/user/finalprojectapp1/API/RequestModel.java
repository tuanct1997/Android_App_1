package com.example.user.finalprojectapp1.API;



public class RequestModel {
    public static final int GET = 1;
    public static final int POST = 2;
    public static final int UPDATE = 3;
    public static final int DELETE = 4;


    private String api_url;
    private int request_method;
    private RequestBody body_request;

    private String identifierStatus = "http://192.168.1.8:3000/status/identifier/";
    public RequestModel() {

    }

    public void setIdentifierStatus(String identifierStatus) {
        this.identifierStatus = identifierStatus;
    }

    public String getIdentifierStatus() {
        return identifierStatus;
    }




    public String getUser(){return "http://192.168.1.8:3000/users";}

    public String postLogin(){return "http://192.168.1.8:3000/login";}

    public String getHomeOfUser(){return "http://192.168.1.8:3000/users/homes";}

    public String getAllBeaconOfUser(){return "http://192.168.1.8:3000/users/devices";}

    public String getAllUserHome(){return "http://192.168.1.8:3000/user_homes";}
    public String postUpdateStatus(){return "http://192.168.1.8:3000/users/devices/status";}

    /*public String getUser(){return "http://192.168.1.38:3000/users";}

    public String getAccelerometer(){ return identifierStatus;}

    public String postLogin(){return "http://192.168.1.38:3000/login";}

    public String getHomeOfUser(){return "http://192.168.1.38:3000/users/homes";}

    public String getAllBeaconOfUser(){return "http://192.168.1.38:3000/users/devices";}

    public String postUpdateStatus(){return "http://192.168.1.38:3000/users/devices/status";}*/


//    ------------------------------------------------------------

    public String getApi_url() {
        return api_url;
    }
    //set the URL
    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }
    //get request
    public int getRequest_method() {
        return request_method;
    }
    // define request method
    public void setRequest_method(int request_method) {
        this.request_method = request_method;
    }
    //body for some function like POST need body
    public RequestBody getBody_request() {
        return body_request;
    }

    public void setBody_request(RequestBody body_request) {
        this.body_request = body_request;
    }


}
