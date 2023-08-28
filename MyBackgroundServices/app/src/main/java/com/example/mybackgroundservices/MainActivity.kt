package com.example.mybackgroundservices

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.mybackgroundservices.databinding.ActivityMainBinding
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

    fun startSimpleWorkManager() {
        val workManagerInstance = WorkManager.getInstance(this)

        val constraint = Constraints.Builder().setRequiresCharging(true).build()


        val oneTimeWorker =
            OneTimeWorkRequest.Builder(UploadWorker::class.java)
                .addTag("MyWorker")
                .setConstraints(constraint)
                .build()

        workManagerInstance.enqueue(oneTimeWorker)
        workManagerInstance.getWorkInfoByIdLiveData(oneTimeWorker.id)
            .observe(this) {
                println("STATE ${it.state.name}")
                activityMainBinding.textView.text = it.state.name
            }

        Handler().postDelayed({

            val work = workManagerInstance.getWorkInfoById(oneTimeWorker.id).get()
            println("Worker1 ${work.outputData}")
            println("Worker2 ${work.progress}")


        }, 3000)


    }
}