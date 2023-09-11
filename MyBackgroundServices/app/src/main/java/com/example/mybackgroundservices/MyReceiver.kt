package com.example.mybackgroundservices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver:BroadcastReceiver() {
    override fun onReceive(context:   Context?, p1: Intent?) {
        println("MyReceiver onReceive ${System.currentTimeMillis()} ")

        val foregroundServiceIntent = Intent(context, ForegroundService::class.java)
        foregroundServiceIntent.action=Action.START.toString()

        context?.startForegroundService(foregroundServiceIntent);

    }
}