package com.example.user.finalprojectapp1.Beacon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.coresdk.cloud.api.CloudCallback;
import com.estimote.coresdk.cloud.api.EstimoteCloud;
import com.estimote.coresdk.cloud.model.Device;
import com.estimote.coresdk.common.config.EstimoteSDK;
import com.estimote.coresdk.common.exception.EstimoteCloudException;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.recognition.packets.EstimoteTelemetry;
import com.estimote.coresdk.service.BeaconManager;
import com.estimote.proximity_sdk.proximity.EstimoteCloudCredentials;
import com.estimote.proximity_sdk.proximity.ProximityAttachment;
import com.estimote.proximity_sdk.proximity.ProximityObserver;
import com.estimote.proximity_sdk.proximity.ProximityObserverBuilder;
import com.estimote.proximity_sdk.proximity.ProximityZone;

import com.example.user.finalprojectapp1.MainActivity;
import com.example.user.finalprojectapp1.R;

import java.util.List;
import java.util.UUID;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import static com.estimote.coresdk.common.config.EstimoteSDK.getApplicationContext;

public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.ViewHolder> {

    private onItemClickListener onItemClickListener;
    private List<EstimoteTelemetry> estimoteTelemetries;

    public BeaconAdapter(List<EstimoteTelemetry> estimoteTelemetries) {

        this.estimoteTelemetries = estimoteTelemetries;

    }

    @Override
    public BeaconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beacon, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return estimoteTelemetries.size();
    }

    public void update(List<EstimoteTelemetry> estimoteTelemetries){
        this.estimoteTelemetries = estimoteTelemetries;
        this.notifyDataSetChanged();
    }

    public interface onItemClickListener {
        void onClick(int position);
    }
    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView uuid_tv, major_minor_tv, tempearature_tv,accelerometer_tv,location_tv,distance_tv;
        private LinearLayout layout;
        private ProximityObserver proximityObserver;

        private ViewHolder(View view) {
            super(view);
            uuid_tv = (TextView) view.findViewById(R.id.item_beacon_uuid);
            major_minor_tv= (TextView) view.findViewById(R.id.item_beacon_major_and_minor);
            tempearature_tv = (TextView) view.findViewById(R.id.item_beacon_temperature);
            accelerometer_tv = view.findViewById(R.id.item_beacon_accelerometer);
            location_tv = view.findViewById(R.id.item_beacon_location);
            layout = (LinearLayout) view.findViewById(R.id.item_beacon_layout);



        }

        public void bind(final int position){
            // find the same beacon uuid.
            EstimoteCloud.getInstance().fetchDeviceDetails(estimoteTelemetries.get(position).deviceId, new CloudCallback<Device>() {
                @Override
                public void success(Device device) {

                    UUID uuid = device.settings.advertisers.iBeacon.get(0).uuid;
                    int major = device.settings.advertisers.iBeacon.get(0).major;
                    int minor = device.settings.advertisers.iBeacon.get(0).minor;


                    /**
                     * Example
                     * String name = device.shadow.name;
                     * int major = device.settings.advertisers.iBeacon.get(0).major;
                     * int minor = device.settings.advertisers.iBeacon.get(0).minor;
                     */
                    uuid_tv.setText(uuid.toString());
                    major_minor_tv.setText(major +" : "+  minor);
                    location_tv.setText(R.string.device_name);
                    tempearature_tv.setText(Double.toString(estimoteTelemetries.get(position).temperature));
                    accelerometer_tv.setText(estimoteTelemetries.get(position).accelerometer.toString());

                }

                @Override
                public void failure(EstimoteCloudException serverException) {
                    Log.d("error", "failed to connect to estimote cloud");
                }
            });


            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) onItemClickListener.onClick(position);
                }
            });
        }




    }



}