package com.example.mybackgroundservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.Timer
import java.util.TimerTask

class MyService : Service() {

    companion object{
        val UPDATE_TIME = "UPDATE_TIME"

    }

    val timer = Timer()

    init {
        println("MyService init")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        println("MyService onStartCommand")

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val intent = Intent(UPDATE_TIME)
                intent.putExtra("time", System.currentTimeMillis().toString())
                sendBroadcast(intent)
            }

        }, 0, 2000)
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        println("MyService onDestroy")
        timer.cancel()

    }
}