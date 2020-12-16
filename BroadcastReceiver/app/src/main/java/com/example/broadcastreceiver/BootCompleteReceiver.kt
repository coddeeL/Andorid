package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
      //  TODO("BootCompleteReceiver.onReceive() is not implemented")
        Toast.makeText(context,"Boot Complete",Toast.LENGTH_SHORT).show()
    }
}
