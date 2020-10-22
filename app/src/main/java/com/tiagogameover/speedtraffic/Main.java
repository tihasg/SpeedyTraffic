package com.tiagogameover.speedtraffic;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tiagogameover.speedtraffic.gateway.DataService;
import com.tiagogameover.speedtraffic.gateway.GraphFragment;
import com.tiagogameover.speedtraffic.gateway.MonthFragment;


public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fm = getFragmentManager();
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();
    private Handler vHandler = new Handler();
    LinearLayout linearLayout;
    Thread dataUpdate;

   // metodo para consiguer pega a velocidade do roteador
   WifiManager wifiManager, wifiManager2;
    TextView txt_Wifi, txt_wifi_ip;
    WifiInfo wifi, wifi2;
    DhcpInfo dhcpInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        linearLayout=(LinearLayout)findViewById(R.id.roter);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.openDrawer(GravityCompat.START);
        if(!StoredData.isSetData) {
            StoredData.setZero();
        }

        if (!DataService.service_status) {
            Intent intent = new Intent(this, DataService.class);
            startService(intent);
        }

        Intent intentBC = new Intent();
        intentBC.setAction("com.tofabd.internetmeter");
        sendBroadcast(intentBC);
        doubleBackToExitPressedOnce = false;
        txt_Wifi = (TextView) findViewById(R.id.txt_Wifi);
        txt_wifi_ip = (TextView) findViewById(R.id.txt_wifi_ip);
        wifiManager2 = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        wifi2 = wifiManager2.getConnectionInfo();
        handler.post(callback);
        linearLayout.setVisibility(View.VISIBLE);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9333264661298583~2792260121");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    Handler handler = new Handler();

    public Runnable callback = new Runnable() {

        @Override

        public void run() {

            Boolean bool = wifiManager2.isWifiEnabled();

// TODO Auto-generated method stub

            handler.postDelayed(callback, 1000);

            wifiManager = (WifiManager)getApplicationContext(). getSystemService(WIFI_SERVICE);

            wifi = wifiManager.getConnectionInfo();

            String wifiinfo = WifiInfo.LINK_SPEED_UNITS;
            
            int speed = wifi.getLinkSpeed();

            String name = wifi.getSSID();

            String TEXT = "Wifi Nome : " + name + "\n" + "Velocidade passando no Roteador: " + String.valueOf(speed) + " " + wifiinfo ;

            txt_Wifi.setText(TEXT);

            /**** wifi ip ****/
            dhcpInfo = wifiManager.getDhcpInfo();
            int serverIp = dhcpInfo.gateway;
            String ipAdress = String.format("%d.%d.%d.%d", serverIp & 0xff, serverIp >> 8 & 0xff, serverIp >> 16 & 0xff, serverIp >> 24 & 0xff);

            txt_wifi_ip.setText(ipAdress);


        }

    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id ==  R.id.nav_teste) {
            Intent intent = new Intent(Main.this, Teste.class);
            startActivity(intent);
        } else if (id == R.id.nav_velocidade){
            linearLayout.setVisibility(View.VISIBLE);
            fm.beginTransaction().replace(R.id.container, new View_Gateway()).commit();
        } else if (id == R.id.nav_gateway) {
            linearLayout.setVisibility(View.VISIBLE);
            fragment = new GraphFragment();
        }else  if (id == R.id.historicp){
            linearLayout.setVisibility(View.GONE);
            fm.beginTransaction().replace(R.id.container, new View_Historico()).commit();
        }

        else if (id==R.id.nav_hitorico2){
            linearLayout.setVisibility(View.GONE);
            fragment = new MonthFragment();

        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, fragment);
            ft.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
