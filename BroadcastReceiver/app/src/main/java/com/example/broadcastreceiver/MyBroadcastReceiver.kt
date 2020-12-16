package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
       // TODO("MyBroadcastReceiver.onReceive() is not implemented")
        Toast.makeText(context,"received in MyBroadcastReceiver",Toast.LENGTH_SHORT).show()
        abortBroadcast()
    }
}
