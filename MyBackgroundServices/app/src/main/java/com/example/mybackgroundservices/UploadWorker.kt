package com.example.mybackgroundservices

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {

        try {
            for (i in 1..20) {
                println("UploadWorker $i")
                Thread.sleep(200L)

            }

            return Result.success()
        }catch (e:Exception){
            return  Result.failure()
        }
    }


}