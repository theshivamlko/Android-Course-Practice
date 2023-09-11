package com.example.mybackgroundservices

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.mybackgroundservices.databinding.ActivityMainBinding
import java.util.UUID
import java.util.concurrent.TimeUnit
import android.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        activityMainBinding.button.setOnClickListener {

            startSimpleWorkManager()
        }

        val intent = Intent(this, MyService::class.java)
        activityMainBinding.button3.setOnClickListener {

            startService(intent)
        }


        activityMainBinding.button4.setOnClickListener {
            stopService(intent)
        }


        activityMainBinding.startBrodcast.setOnClickListener {
            val receiver = Intent(this, MyReceiver::class.java)
            receiver.action="SHIVAM"

            val  myReceiver:MyReceiver= MyReceiver()

            registerReceiver(myReceiver,IntentFilter("SHIVAM"))
           sendBroadcast(receiver)
        }

        val foregroundServiceIntent = Intent(this, ForegroundService::class.java)
        foregroundServiceIntent.action=Action.START.toString()
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0
        )
        val notificationChannel = NotificationChannel(
            "channelId",
            "Channel Name Service",
            NotificationManager.IMPORTANCE_HIGH
        )
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(notificationChannel)

        activityMainBinding.button5.setOnClickListener {
            startForegroundService(foregroundServiceIntent)
        }
        activityMainBinding.button6.setOnClickListener {
            stopService(foregroundServiceIntent)
        }



    }

    val workManagerInstance = WorkManager.getInstance(this)
    var workedId = UUID.randomUUID()

    fun startSimpleWorkManager() {
        val data = Data.Builder().putInt("num", -10).build()

        val constraint = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()


        val oneTimeWorker =
            OneTimeWorkRequest.Builder(UploadWorker::class.java)
                .addTag("MyWorker")
                //    .setConstraints(constraint)
                .setInputData(data)
                .build()

        val filterWorker = OneTimeWorkRequestBuilder<FilterWorker>()
            .addTag("FilterWorker2")
            .build()

        // Multiple workers start parallel
        val parallelWorker = mutableListOf<OneTimeWorkRequest>()
        parallelWorker.add(filterWorker)
        parallelWorker.add(oneTimeWorker)
        parallelWorker.add(filterWorker)

        /*  workManagerInstance.beginWith(filterWorker).then(oneTimeWorker).then(parallelWorker)
              .enqueue()*/

        workManagerInstance.enqueue(oneTimeWorker)

        workedId = oneTimeWorker.id
        println("workedId ${workedId}")

        workManagerInstance.getWorkInfoByIdLiveData(oneTimeWorker.id)
            .observe(this) {
                println("STATE ${it.state.name}")
                activityMainBinding.textView.text = it.state.name
                val work = workManagerInstance.getWorkInfoById(oneTimeWorker.id).get()
                println("Worker1 ${work.outputData}")
                println("Worker2 ${work.progress}")
            }


    }

    fun periodicManager() {
        // min periodic for worker is 15 min
        val periodicWorker =
            PeriodicWorkRequest.Builder(FilterWorker::class.java, 16, TimeUnit.MINUTES).build()
        workManagerInstance.enqueue(periodicWorker)


    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy ${workedId.node()}")
        workManagerInstance.cancelWorkById(workedId)

    }

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val time = p1?.getStringExtra("time")
            println("BroadcastReceiver $time")
        }

    }

    override fun onResume() {
        super.onResume()

        registerReceiver(receiver, IntentFilter(MyService.UPDATE_TIME))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
}