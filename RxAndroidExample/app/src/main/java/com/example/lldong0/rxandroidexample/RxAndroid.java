package com.example.lldong0.rxandroidexample;

import android.app.Application;

import com.example.lldong0.rxandroidexample.network.volley.LocalVolley;


public class RxAndroid extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LocalVolley.init(getApplicationContext());
    }
}
