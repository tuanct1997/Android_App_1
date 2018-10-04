package com.example.user.finalprojectapp1.API;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Connections {
    private static Connections instance;
    private static final String TAG = "connections";
    public static final String ERROR_STATUS = "HTTP_ERROR";
    public static final String UNAUTHORIZATION_ERROR = "401";
    private Gson gson;
    private OkHttpClient client;
    private Context context;

    public Connections(Context context) {
        this.context = context;
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public static Connections getInstance(Context context) {
        if (instance == null) {
            instance = new Connections(context);
        }
        return instance;
    }

    public String post_api(RequestModel req) throws Exception {

        //check API send body or not? If you have => Send
        if(req.getBody_request() != null){
            MediaType mediaType = MediaType.parse("application/json");

            String url = req.getApi_url();
            req.setApi_url(null);

            String json = gson.toJson(req.getBody_request());
            Log.d("log_post_api", url);
            Log.d("log_post_body", json);
            okhttp3.RequestBody body = RequestBody.create(mediaType, json);
            Request request = null;

            //check session in local or not...!!!
            if(AppPreferences.getInstance(context).getSession() != null){
                request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .addHeader("content-type", "application/json")
                        .addHeader("authorization", AppPreferences.getInstance(context).getSession().getAccess_token())
                        .addHeader("cache-control", "no-cache")
                        .build();

                Log.d("log_post_token", AppPreferences.getInstance(context).getSession().getAccess_token());
            }else{
                request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .addHeader("content-type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();
            }

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    if(response.code() == 401) return UNAUTHORIZATION_ERROR;
                    else return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            MediaType mediaType = MediaType.parse("application/json");

            String url = req.getApi_url();
            req.setApi_url(null);
            String json ="";
            if(req.getBody_request()!=null){
                json  = gson.toJson(req.getBody_request());
            }




            Log.d("log_post_api", url);
            Log.d("log_post_body", json);
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);
            Request request = null;

            //check have Session in Local or not
            if(AppPreferences.getInstance(context).getSession() != null){
                request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .addHeader("content-type", "application/json")
                        .addHeader("authorization", AppPreferences.getInstance(context).getSession().getAccess_token())
                        .addHeader("cache-control", "no-cache")
                        .build();

                Log.d("log_post_token", AppPreferences.getInstance(context).getSession().getAccess_token());
            }else{
                request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .addHeader("content-type", "application/json")
                        .addHeader("cache-control", "no-cache")
                        .build();
            }

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    if(response.code() == 401) return UNAUTHORIZATION_ERROR;
                    else return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public String get_api(RequestModel req) throws Exception {
        String url = req.getApi_url();
        req.setApi_url(null);
        Request request = null;

        if (AppPreferences.getInstance(context).getSession() != null) {
            request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", AppPreferences.getInstance(context).getSession().getAccess_token())
                    .addHeader("cache-control", "no-cache")
                    .build();

        } else {
            request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();
        }

        // check Sever co tra gia tri ve k
        try {
            Response response = client.newCall(request).execute();
            Log.d("","");
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                if (response.code() == 401) return UNAUTHORIZATION_ERROR;
                else return null;
            }
        } catch (IOException e) {
//            e.printStackTrace();
            Log.e("get",e.getMessage().toString());
            return null;
        }
    }

    public String delete_api(RequestModel req) throws Exception {
        MediaType mediaType = MediaType.parse("application/json");

        String url = req.getApi_url();
        req.setApi_url(null);
        String json = gson.toJson(req.getBody_request());
        Log.d("log_delete_api", url);
        Log.d("log_delete_body", json);
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);
        Request request = null;
        if(AppPreferences.getInstance(context).getSession() != null){
            request = new Request.Builder()
                    .url(url)
                    .delete(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", AppPreferences.getInstance(context).getSession().getAccess_token())
                    .addHeader("cache-control", "no-cache")
                    .build();

            Log.d("log_delete_token", AppPreferences.getInstance(context).getSession().getAccess_token());
        }else{
            request = new Request.Builder()
                    .url(url)
                    .delete(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();
        }

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                if(response.code() == 401) return UNAUTHORIZATION_ERROR;
                else return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String update_api(RequestModel req) throws Exception {
        MediaType mediaType = MediaType.parse("application/json");

        String url = req.getApi_url();
        req.setApi_url(null);

        String json = gson.toJson(req.getBody_request());
        Log.d("log_update_api", url);
        Log.d("log_update_body", json);
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);
        Request request = null;
        if(AppPreferences.getInstance(context).getSession() != null){
            request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", AppPreferences.getInstance(context).getSession().getAccess_token())
                    .addHeader("cache-control", "no-cache")
                    .build();

            Log.d("log_update_token", AppPreferences.getInstance(context).getSession().getAccess_token());
        }else{
            request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();
        }

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                if(response.code() == 401) return UNAUTHORIZATION_ERROR;
                else return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
