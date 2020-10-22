package com.tiagogameover.speedtraffic.velocidade_wifi;

import android.os.AsyncTask;

import com.tiagogameover.speedtraffic.View_Gateway;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PingIP extends AsyncTask<Object, Object, String> {
    private Elements li = null;
    private View_Gateway mainActivity;
    private String title;

    public PingIP(View_Gateway mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Object... params) {
        String url = "https://www.google.com";
        String concole = null;


            concole = "ping -c 1 " + url;

        Process myProcess = null;
        try {
            myProcess = Runtime.getRuntime().exec(concole);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = Jsoup.connect("http://checkip.org/").get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (doc != null) {
            Elements ul = doc.select("h1");
            li = ul.select("li");
            title = ul.toString();
        } else title = "0";

        return title;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String text;
        if (title != null) {
            text = title.substring(title.indexOf(";\">") + 3, title.indexOf("</span"));
        } else
            text = "No inet";
        mainActivity.tvYourIp.setText(text);

    }
}