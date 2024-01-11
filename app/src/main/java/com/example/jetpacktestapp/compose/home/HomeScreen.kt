package com.example.jetpacktestapp.compose.home

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(onItemClick: (String) -> Unit = {}) {
    val filter = IntentFilter()
    filter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
    val receiver = UnlockReceiver()
    LocalContext.current.registerReceiver(receiver, filter, RECEIVER_EXPORTED)
}


class UnlockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null && intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            // Device unlocked, perform actions here
            // For example, start a service or launch an activity
            Log.d("+++", "Phone unlocked")
        }
    }
}