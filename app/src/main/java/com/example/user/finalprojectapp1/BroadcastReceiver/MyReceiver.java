package com.example.user.finalprojectapp1.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.user.finalprojectapp1.Background.UpdateStatusService;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("abc", "ewqew");

        context.startService(new Intent(context, UpdateStatusService.class));

    }
}
