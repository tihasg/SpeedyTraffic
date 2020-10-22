package com.tiagogameover.speedtraffic;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.*;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.tiagogameover.speedtraffic.velocidade_wifi.DownloadImage;
import com.tiagogameover.speedtraffic.velocidade_wifi.PingIP;

import java.net.URL;


@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class View_Gateway extends Fragment implements View.OnClickListener {
    public String mURL = "https://sonikelf.ru/attach/img/1302869217-clip-64kb.jpg";
    public URL url;
    public ArcProgress arcProgress;
    private Button buttonStart;
    private static int progressBarStatus = 0;
    public String q;
    public double tt;
    public TextView tvYourIp;
    public ProgressBar progressBar;
    public ImageView ivWifi;
    public ProgressBar connectionProgress;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public android.view.View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final android.view.View v = inflater.inflate(R.layout.activity_gatway, container, false);

        arcProgress = (ArcProgress)v. findViewById(R.id.arc_progress);
        arcProgress.setProgress(progressBarStatus);
        buttonStart = (Button)v. findViewById(R.id.btn_start_test);
        buttonStart.setOnClickListener(this);
        tvYourIp=(TextView)v.findViewById(R.id.tv_your_ip_dat);
        progressBar = (ProgressBar)v. findViewById(R.id.progressBar);
        ivWifi = (ImageView)v.findViewById(R.id.iv_signal_rang);
        ivWifi.setImageResource(R.drawable.ic_wifi_1);
        new PingIP(this).execute();
        return v;
    }

    public void startSpeedTest() {
        double res = 0;
        for (int i = 0; i < 10; i++) {
            new DownloadImage(this).execute();
            res = res + tt;
        }
        int resres = (int) res / 10;
        arcProgress.setProgress(resres);
    }

    @Override
    public void onClick(android.view.View view) {
        switch (view.getId()) {
            case R.id.btn_start_test:
                new DownloadImage(this).execute();
                //startSpeedTest();
                buttonStart.setText("Testando a velocidade da internet...");
        }
    }

}
