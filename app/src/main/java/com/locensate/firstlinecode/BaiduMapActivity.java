package com.locensate.firstlinecode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

public class BaiduMapActivity extends AppCompatActivity {

    private LocationClient locationClient;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(App.getApplication());
        setContentView(R.layout.activity_baidu_map);
        mapView = (MapView) findViewById(R.id.map_bai_du);

    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
