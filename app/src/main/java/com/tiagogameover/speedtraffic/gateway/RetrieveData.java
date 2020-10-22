package com.tiagogameover.speedtraffic.gateway;

import android.net.TrafficStats;

import java.util.ArrayList;
import java.util.List;


public class RetrieveData {


    static long totalUpload = 0;
    static long totalDownload = 0;
    static long totalUpload_n = 0;
    static long totalDownload_n = 0; //for notification


    //  static long totalDownload = TrafficStats.getTotalRxBytes();

    /**
     *
     * @return downloaded and uploaded data per second
     */
    public static List<Long> findData() {
        List<Long> allData = new ArrayList<>();

        long newTotalDownload, incDownload, newTotalUpload, incUpload;


        //totalDownload and totalUpload set to zero if new session starts
        if (totalDownload == 0)
            totalDownload = TrafficStats.getTotalRxBytes();

        if (totalUpload == 0)
            totalUpload = TrafficStats.getTotalTxBytes();

        //calculate downloaded or uploaded data in every second
        newTotalDownload = TrafficStats.getTotalRxBytes();
        incDownload = newTotalDownload - totalDownload;

        newTotalUpload = TrafficStats.getTotalTxBytes();
        incUpload = newTotalUpload - totalUpload;


        //total downloaded and uploaded data after started new session
        totalDownload = newTotalDownload;
        totalUpload = newTotalUpload;

        allData.add(incDownload);
        allData.add(incUpload);


        return allData;


        //return incDownload + incUpload;
    }

    public static long getNotificationData() {

        long newTotalDownload, incDownload, newTotalUpload, incUpload;

        if (totalDownload_n == 0)
            totalDownload_n = TrafficStats.getTotalRxBytes();

        if (totalUpload_n == 0)
            totalUpload_n = TrafficStats.getTotalTxBytes();

        newTotalDownload = TrafficStats.getTotalRxBytes();
        incDownload = newTotalDownload - totalDownload_n;

        newTotalUpload = TrafficStats.getTotalTxBytes();
        incUpload = newTotalUpload - totalUpload_n;


        totalDownload_n = newTotalDownload;
        totalUpload_n = newTotalUpload;


        return incDownload + incUpload;
    }

}
