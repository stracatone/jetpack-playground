package com.example.jetpacktestapp

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.jetpacktestapp.compose.home.PhoneCallReceiver

class CallCounterWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(
    appContext,
    params
) {

    override suspend fun doWork(): Result {
        Log.d("+++", "Worker started, some work done.")
        return Result.success()
    }
}