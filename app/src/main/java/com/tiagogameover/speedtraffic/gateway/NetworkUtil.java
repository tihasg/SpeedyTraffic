package com.tiagogameover.speedtraffic.gateway;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tofa on 2/22/2016.
 */
public class NetworkUtil {


    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;


    /**
     * @param context
     * @return Connection Type which is Wifi or Mobile or No Connection
     */

    public static int getConnectivityStatus(Context context) {
        //Toast.makeText(context, "Working", Toast.LENGTH_SHORT).show();
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }

        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == NetworkUtil.TYPE_WIFI) {
            status = "wifi_enabled";
        } else if (conn == NetworkUtil.TYPE_MOBILE) {
            status = "mobile_enabled";
        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
            status = "no_connection";
        }
        return status;
    }

    public static List<String> getConnectivityInfo(Context context) {


        List<String> connInfo = new ArrayList<>();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        String network_status = getConnectivityStatusString(context);

        // if (null != activeNetwork) {
        if (network_status == "wifi_enabled") {

            connInfo.add("wifi_enabled");

            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifiManager.getConnectionInfo();

            int rssi = info.getRssi();
            int level = WifiManager.calculateSignalLevel(rssi, 10);
            int percentage = (int) ((level / 9.0) * 100);


            connInfo.add(info.getSSID());
            connInfo.add(Integer.toString(percentage) + " %");


            return connInfo;
        } else if (network_status.equals("mobile_enabled")) {

            connInfo.add("mobile_enabled");
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            connInfo.add(manager.getNetworkOperatorName());

            //  int temp = manager.getPhoneCount();



            return connInfo;
        } else
            connInfo.add("no_connection");

        return connInfo;

    }
}
