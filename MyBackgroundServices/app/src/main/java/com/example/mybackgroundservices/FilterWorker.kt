package com.example.mybackgroundservices

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class FilterWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        val data=inputData.getInt("num",1)
        try {
            for (i in 1..20) {

                 println("FilterWorker======== $i workedId $id")
                Thread.sleep(500L)
                this.setProgressAsync(Data.Builder().putInt("name",i*data).build())

            }

            return Result.success()
        }catch (e:Exception){
            return  Result.failure()
        }
    }


}