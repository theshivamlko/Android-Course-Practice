package com.example.mybackgroundservices

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.mybackgroundservices.databinding.ActivityMainBinding
import java.util.UUID
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        activityMainBinding.button.setOnClickListener {

            startSimpleWorkManager()
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

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy ${workedId.node()}")
        workManagerInstance.cancelWorkById(workedId)

    }
}