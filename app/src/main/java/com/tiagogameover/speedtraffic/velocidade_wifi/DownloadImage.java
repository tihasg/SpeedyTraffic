package com.tiagogameover.speedtraffic.velocidade_wifi;

import android.os.AsyncTask;
import android.util.Log;

import com.tiagogameover.speedtraffic.R;
import com.tiagogameover.speedtraffic.View_Gateway;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;


public class DownloadImage extends AsyncTask<Integer, Integer, String> {

    private View_Gateway mainActivity;
    long v;

    public DownloadImage(View_Gateway mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Integer... params) {

        SpeedTestSocket speedTestSocket = new SpeedTestSocket();

        // add a listener to wait for speedtest completion and progress
        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

            @Override
            public void onCompletion(SpeedTestReport report) {
                // called when download/upload is finished
                Log.v("speedtest", "[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
                Log.v("speedtest", "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
            long u=report.getReportTime()-report.getStartTime();
                long w =(report.getTotalPacketSize()/1000)/(u/1000);
                int c = (int) w;
            }

            @Override
            public void onError(SpeedTestError speedTestError, String errorMessage) {
                // called when a download/upload error occur
                Log.v("speedtest", "Error : " + speedTestError.name());
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {
                // called to notify download/upload progress
                long start = report.getStartTime();
                long stop = report.getReportTime();
                long time =stop-start;
                long size = report.getTemporaryPacketSize();
                v = size/time;
                int vv = (int) v;
                int pp= (int) percent;
                Log.v("speedtest", "[PROGRESS] progress : " + percent + "%");
                Log.v("speedtest", "[PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                Log.v("speedtest", "[PROGRESS] rate in bit/s   : " + report.getTransferRateBit());

                publishProgress(vv);
                //publishProgress(pp);
            }
        });

        speedTestSocket.startDownload("http://2.testdebit.info/fichiers/1Mo.dat");

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mainActivity.arcProgress.setProgress(values[0]);
        //mainActivity.progressBar.setProgress(values[0]);
        if(values[0]<10){
            mainActivity.ivWifi.setImageResource(R.drawable.ic_wifi_1);
        }else if (10<values[0] && values[0]<20){
            mainActivity.ivWifi.setImageResource(R.drawable.ic_wifi_2);
        }else if (20<values[0] && values[0]<30){
            mainActivity.ivWifi.setImageResource(R.drawable.ic_wifi_3);
        }else if (30<values[0] && values[0]<50){
            mainActivity.ivWifi.setImageResource(R.drawable.ic_wifi_4);
        }else if (50<values[0] && values[0]<70){
            mainActivity.ivWifi.setImageResource(R.drawable.ic_wifi_5);
        }else if (70<values[0] && values[0]<100){
            mainActivity.ivWifi.setImageResource(R.drawable.ic_wifi_6);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mainActivity.progressBar.setProgress(0);
    }
}