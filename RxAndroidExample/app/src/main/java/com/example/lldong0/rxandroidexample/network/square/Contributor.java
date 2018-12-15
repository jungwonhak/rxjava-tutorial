package com.example.lldong0.rxandroidexample.network.square;


import android.support.annotation.NonNull;

public class Contributor {
    private String login;
    private String url;
    private int id;

    @NonNull
    @Override
    public String toString() {
        return "login : " + login + "id : " + id + "url : " + url;
    }
}