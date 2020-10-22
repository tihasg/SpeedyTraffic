package com.tiagogameover.speedtraffic.gateway;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DataReceiver extends BroadcastReceiver {
    // public DataReceiver() {
    //   }

    @Override
    public void onReceive(Context context, Intent intent) {

        //check service running or not
        // if not then restart service
        if (!DataService.service_status) {
            Intent intentService = new Intent(context, DataService.class);
            context.startService(intentService);
        }

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        // throw new UnsupportedOperationException("Not yet implemented");
    }


}
