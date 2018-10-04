package com.example.user.finalprojectapp1.Background;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import com.estimote.coresdk.cloud.api.CloudCallback;
import com.estimote.coresdk.cloud.api.EstimoteCloud;
import com.estimote.coresdk.cloud.model.Device;
import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.common.exception.EstimoteCloudException;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.recognition.packets.EstimoteTelemetry;
import com.estimote.coresdk.service.BeaconManager;
import com.example.user.finalprojectapp1.API.AppPreferences;
import com.example.user.finalprojectapp1.API.Connections;
import com.example.user.finalprojectapp1.API.MyAsynctask;
import com.example.user.finalprojectapp1.API.RequestBody;
import com.example.user.finalprojectapp1.API.RequestModel;
import com.example.user.finalprojectapp1.Activites.Test;
import com.example.user.finalprojectapp1.BroadcastReceiver.MyReceiver;
import com.example.user.finalprojectapp1.MainActivity;
import com.example.user.finalprojectapp1.Model.AccelerometerModel;
import com.example.user.finalprojectapp1.Model.BeaconModel;
import com.example.user.finalprojectapp1.Model.StatusModel;
import com.example.user.finalprojectapp1.Model.StatusParamsModel;
import com.example.user.finalprojectapp1.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class UpdateStatusService extends Service {
    private final  long delay = 5*1000;
    private final long intevalPeriod = 5 * 1000;
    TimerTask task;
    long startTime =0;
    long endTime;
    private BeaconManager beaconManager;
    private static final String APP_ID = "leleismine-gmail-com-s-con-ovj"; // manage in : https://cloud.estimote.com/#/apps
    private static  final String APP_TOKEN = "f1bb97dfc635d5ac5d273465c3467f86"; // manage in : https://cloud.estimote.com/#/apps
    private int count =0;
    private int count1 = 0;
    private double  x, y, z, Adsvm, o;


    private AccelerometerModel accelerometerModel;
    public UpdateStatusService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        EstimoteSDK.initialize(getApplicationContext(), APP_ID, APP_TOKEN);
        /*task = new TimerTask() {
            @Override
            public void run() {
                synchronized (this){startTime = System.currentTimeMillis()/1000;
                    getAccelerometer("9d1513933f45435a54185a5f5901181b");

                    System.out.println("Hello !!!");
                    Log.d("hello", Long.toString(startTime-endTime));
                    // to run after 5 sec
                    endTime = System.currentTimeMillis()/1000;}
            }


        };*/
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setBackgroundScanPeriod(3000,0);
        beaconManager.setForegroundScanPeriod(3000,0);
        telemetryBeacon();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                    beaconManager.startTelemetryDiscovery();



            }
        });

        /*Timer timer = new Timer();
        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);*/


        return Service.START_STICKY;



    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        BroadcastReceiver mReceiver = new MyReceiver();
        registerReceiver(mReceiver, new IntentFilter("Receive"));
        Intent intent = new Intent();
        intent.setAction("Receive");
        sendBroadcast(intent);
        Log.d("dsad1", "dasdsa");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("dsad", "dasdsa");
    }


    private void postUpdateStatus(String identifier, String acceleration, String temperature,String room) {
        Test test = new Test();

        StatusModel statusModel = new StatusModel(acceleration,temperature,room);
        StatusParamsModel statusParamsModel = new StatusParamsModel(identifier,statusModel);
        RequestBody body = new RequestBody();
        body.setDevice(statusParamsModel);
        RequestModel req = new RequestModel();
        req.setRequest_method(RequestModel.POST); //Request Method
        req.setBody_request(body);
        req.setApi_url(req.postUpdateStatus()); // Select Url

        //doBack(req);
        MyAsynctask asynctask = new MyAsynctask();
        asynctask.setContext(UpdateStatusService.this);
        asynctask.setProcessing_listener(new MyAsynctask.MyAsyncTaskListener() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(String result) {
                if(result != null){
                    Log.d("met",result);
                }
            }
        });

        asynctask.execute(req);
    }


    private  void telemetryBeacon(){
        beaconManager.setTelemetryListener(new BeaconManager.TelemetryListener() {
            @Override
            public void onTelemetriesFound(List<EstimoteTelemetry> telemetries) {
                Log.d("telemetry", Integer.toString(telemetries.size()));
                if(telemetries.size() > 0) {

                    count = count+ 1;
                    Log.d("telemetry1", "abcabxc");
                    for(EstimoteTelemetry telemetry :telemetries){

                        String device_id = telemetry.deviceId.toString();
                        device_id = device_id.substring(1,device_id.length()-1);
                        String accelerometer = String.format("%.2f;%.2f;%.2f",telemetry.accelerometer.x ,telemetry.accelerometer.y ,telemetry.accelerometer.z);
                        accelerometer= accelerometer.replaceAll(",",".");
                        String room = AppPreferences.getInstance(UpdateStatusService.this).getRoom();
                        postUpdateStatus(device_id,accelerometer,telemetry.temperature.toString(),room);


                    }
                }
                /*Log.d("timer1",Integer.toString(count));
                Log.d("timer2",Integer.toString(count1));*/
                endTime = System.currentTimeMillis();
                if((count -count1)== 5){
                    Log.d("timer",Long.toString((endTime-startTime)/1000));
                    startTime = endTime;
                    count1 = count;
                }
            }
        });
    }
    private void getUsers() {
        RequestModel req = new RequestModel();
        req.setRequest_method(RequestModel.GET); //Request Method
        req.setApi_url(req.getUser()); // Select Url

        doBack(req);
    }

    /*private  void getAccelerometer(String identifier){
        RequestModel req = new RequestModel();
        req.setIdentifierStatus(req.getIdentifierStatus().concat(identifier));
        Log.d("accelerometer",req.getAccelerometer());
        req.setApi_url(req.getAccelerometer());
        req.setRequest_method(RequestModel.GET);
        MyAsynctask asynctask = new MyAsynctask();
        asynctask.setContext(UpdateStatusService.this);
        asynctask.setProcessing_listener(new MyAsynctask.MyAsyncTaskListener() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(String result) {
                if(result != null && result.length()!=0){
                    Log.d("met",result);
                    Log.d("met",Integer.toString(result.length()));
                    result = result.substring(1,result.length()-1);
                    BeaconModel beaconModel =new Gson().fromJson(result, new TypeToken<BeaconModel>() {}.getType());
                    if (beaconModel != null) {
                        if(beaconModel.getStatusModel() != null){
                            String acceleration = beaconModel.getStatusModel().getAcceleration();

                            Log.d("object",beaconModel.getStatusModel().getAcceleration());
                            double[] doubleArray = Arrays.stream(acceleration.split(";"))
                                    .mapToDouble(Double::parseDouble)
                                    .toArray();
                            Log.d("object",doubleArray[0]+"  "+doubleArray[1]+"   "+doubleArray[2]);
                            accelerometerModel = new AccelerometerModel(doubleArray[0],doubleArray[1],doubleArray[2]);



                        }
                    }






                }

            }
        });
        asynctask.execute(req);
    }*/



    private  String doBack(RequestModel requestModel){

        try {
            if(requestModel.getRequest_method() == RequestModel.GET){
                Log.d("back","get");
                return Connections.getInstance(getApplicationContext()).get_api(requestModel);
            }else if(requestModel.getRequest_method() == RequestModel.POST){
                Log.d("back","post");
                return Connections.getInstance(getApplicationContext()).post_api(requestModel);
            }
            else if(requestModel.getRequest_method()== RequestModel.UPDATE){
                return Connections.getInstance(getApplicationContext()).update_api(requestModel);
            }
            else if(requestModel.getRequest_method()== RequestModel.DELETE){
                return Connections.getInstance(getApplicationContext()).delete_api(requestModel);
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
