package com.example.mybackgroundservices

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        val data=inputData.getInt("num",1)
        try {
            for (i in 1..20) {
                 println("UploadWorker3 $i workedId $id")
                Thread.sleep(1000L)
                this.setProgressAsync(Data.Builder().putInt("name",i*data).build())

            }

            return Result.success()
        }catch (e:Exception){
            return  Result.failure()
        }
    }


}