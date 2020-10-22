package com.tiagogameover.speedtraffic.velocidade_wifi;

import com.tiagogameover.speedtraffic.View_Gateway;

public class Functional {
    public static void startSpeedTest(View_Gateway mainActivity) {
        double res = 0;
        for (int i = 0; i < 10; i++) {
            new DownloadImage(mainActivity).execute();
            res = res + mainActivity.tt;
        }
        int result = (int) res / 10;
        mainActivity.connectionProgress.setProgress(result);
    }
}
