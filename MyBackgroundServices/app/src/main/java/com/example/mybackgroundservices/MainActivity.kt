package com.example.mybackgroundservices

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.mybackgroundservices.databinding.ActivityMainBinding

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
        val oneTimeWorker =
            OneTimeWorkRequest.Builder(UploadWorker::class.java).addTag("MyWorker").build()

        workManagerInstance.enqueue(oneTimeWorker)
        workManagerInstance.getWorkInfoByIdLiveData(oneTimeWorker.id)
            .observe(this) {
                println("STATE ${it.state.name}")
                activityMainBinding.textView.text = it.state.name
            }

    }
}