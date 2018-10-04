package com.example.user.finalprojectapp1;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.coresdk.cloud.api.CloudCallback;
import com.estimote.coresdk.cloud.api.EstimoteCloud;
import com.estimote.coresdk.cloud.model.Device;
import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.common.exception.EstimoteCloudException;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.recognition.packets.EstimoteTelemetry;
import com.estimote.coresdk.service.BeaconManager;

import com.example.user.finalprojectapp1.Background.UpdateStatusService;
import com.example.user.finalprojectapp1.Beacon.BeaconAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "log_main";

    private BeaconManager beaconManager;


    private BeaconAdapter adapter;
    private RecyclerView recycler;

    private static final String APP_ID = "leleismine-gmail-com-s-con-ovj"; // manage in : https://cloud.estimote.com/#/apps
    private static final String APP_TOKEN = "f1bb97dfc635d5ac5d273465c3467f86"; // manage in : https://cloud.estimote.com/#/apps


    private double x,y,z,x1,y1,z1, Asvm, Adsvm , o ,Agsvm,Agdsvm = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.activity_main_recycler);
        EstimoteSDK.initialize(MainActivity.this, APP_ID, APP_TOKEN);
        telemetryBeacon();

    }


    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.setForegroundScanPeriod(4000, 0);
                beaconManager.startTelemetryDiscovery();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        beaconManager.stopTelemetryDiscovery();

    }




    private void setRecyclerView(final List<EstimoteTelemetry> estimoteTelemetries){
        if(adapter == null){
            adapter = new BeaconAdapter(estimoteTelemetries);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recycler.setLayoutManager(layoutManager);
            recycler.setItemAnimator(new DefaultItemAnimator());
            recycler.setAdapter(adapter);

            adapter.setOnItemClickListener(new BeaconAdapter.onItemClickListener() {
                @Override
                public void onClick(int position) {
                    Log.d("potttt",Integer.toString(position));


                }
            });
        }else{
            adapter.update(estimoteTelemetries);

        }
    }


    private void telemetryBeacon(){

        beaconManager = new BeaconManager(MainActivity.this);
        beaconManager.setTelemetryListener(new BeaconManager.TelemetryListener() {
            @Override
            public void onTelemetriesFound(List<EstimoteTelemetry> telemetries) {

                if(telemetries.size() > 0) {

                    setRecyclerView(telemetries);
                }
            }
        });
    }

}
