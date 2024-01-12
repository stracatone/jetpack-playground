package com.example.jetpacktestapp.compose.home

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor

import android.os.Build
import android.provider.CalendarContract
import android.telephony.SmsMessage
import android.telephony.TelephonyManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@SuppressLint("Range")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(onItemClick: (String) -> Unit = {}) {
    val context = LocalContext.current

    Column {
        Button(onClick = {
            println("WILL ATTEMPT CALENDAR READ")

            val cursor: Cursor? = context.getContentResolver().query(
                CalendarContract.Events.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // Access calendar event details
                    val title =
                        cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE))
                    println("TITLE: $title")
                }
                cursor.close()
            }

        }) {
            Text("Read Calendar")
        }
    }
}


class UnlockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null && intent.action.equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.d("+++", "Data extra: ${intent.getStringExtra(TelephonyManager.EXTRA_STATE)}")
            // Device unlocked, perform actions here
            // For example, start a service or launch an activity
            val bundle = intent.extras
            if (bundle != null) {
                // Extract the SMS messages
                val pdus = bundle["pdus"] as Array<Any>?
                if (pdus != null) {
                    val messageCount = pdus.size
//                    Log.d(TAG, "Number of incoming messages: $messageCount")

                    // You can handle each message individually if needed
                    for (pdu in pdus) {
                        val smsMessage: SmsMessage = SmsMessage.createFromPdu(pdu as ByteArray)
                        val messageBody: String = smsMessage.getMessageBody()
                        val senderPhoneNumber: String = smsMessage.getOriginatingAddress() ?: ""

                        // Process the message as needed
                        Log.d("+++", "Sender: $senderPhoneNumber, Message: $messageBody")
                    }
                    Log.d("+++", "msg count: $messageCount")
                }
            }

        }
    }
}