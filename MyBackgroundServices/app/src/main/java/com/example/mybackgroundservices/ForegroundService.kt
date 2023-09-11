package com.example.mybackgroundservices

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {
    override fun onBind(p0: Intent?): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("ForegroundService onStartCommand ")
        when (intent?.action) {
            Action.START.toString() -> start()
            Action.STOP.toString() -> stop()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    fun start() {
        val notification = NotificationCompat.Builder(this, "channelId")

        notification.setSmallIcon(R.drawable.baseline_bug_report_24)
        notification.setContentTitle("My Foreground Service")
        notification.setContentText("Scanning Memory Card...")
        notification.setOngoing(true)


        startForeground(1, notification.build())

    }

    fun stop() {
        stopSelf()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

enum class Action {
    START, STOP
}