package com.example.loactionwalaapp;

import java.util.Timer;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.model.LatLng;

public class MyLocation {
    Timer timer1;
    LocationManager lm;
    LocationResult locationResult;
    boolean gps_enabled=false;
    boolean network_enabled=false;

    @SuppressLint("MissingPermission")
    public LatLng getLocation(Context context, LocationResult result)
    {
         // latlng return karne wala function likho

        LatLng latlng = new LatLng(0,0);
        return latlng;

    }

}
