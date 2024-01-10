package com.example.jetpacktestapp.compose.home

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(onItemClick: (String) -> Unit = {}) {
    val filter = IntentFilter()
    filter.addAction(Intent.ACTION_CAMERA_BUTTON)
    val receiver = UnlockReceiver()
    LocalContext.current.registerReceiver(receiver, filter, RECEIVER_EXPORTED)
}


class UnlockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null && intent.action == Intent.ACTION_CAMERA_BUTTON) {
            // Device unlocked, perform actions here
            // For example, start a service or launch an activity
            Log.d("+++", "Phone unlocked")
        }
    }
}