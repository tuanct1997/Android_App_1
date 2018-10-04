package com.example.user.finalprojectapp1.API;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class MyAsynctask extends AsyncTask<RequestModel,Void,String> {
    private Context context;
    private MyAsyncTaskListener processing_listener;

    public interface MyAsyncTaskListener{
        void onPreExecute();
        void onPostExecute(String result);
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MyAsyncTaskListener getProcessing_listener() {
        return processing_listener;
    }

    public void setProcessing_listener(MyAsyncTaskListener processing_listener) {
        this.processing_listener = processing_listener;
    }


    // before ask use this for login to API.

    @Override
    protected void onPreExecute() {

        if (processing_listener!=null)processing_listener.onPreExecute();

    }

    // the response from API, result is JSON .toString().
    @Override
    protected void onPostExecute(String result) {
        if (processing_listener!=null) processing_listener.onPostExecute(result);

    }

    // when in call we do it in background for waiting to format it in the frontend. this will wait for the response, when get the result
    // do on Post Execute
    @Override
    protected String doInBackground(RequestModel... params) {
        // call from RequestModel class
        RequestModel requestModel = params[0];


        // CALLING METHOD
        try {
            if(requestModel.getRequest_method() == RequestModel.GET){
                return Connections.getInstance(context).get_api(requestModel);
            }else if(requestModel.getRequest_method() == RequestModel.POST){
                return Connections.getInstance(context).post_api(requestModel);
            }
            else if(requestModel.getRequest_method()== RequestModel.UPDATE){
                return Connections.getInstance(context).update_api(requestModel);
            }
            else if(requestModel.getRequest_method()== RequestModel.DELETE){
                return Connections.getInstance(context).delete_api(requestModel);
            }
            else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
