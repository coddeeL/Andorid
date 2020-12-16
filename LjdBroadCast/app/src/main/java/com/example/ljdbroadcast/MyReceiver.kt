package com.example.ljdbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        //TODO("MyReceiver.onReceive() is not implemented")
        val s=intent.getStringExtra("data")
        Toast.makeText(context,"广播的消息为:"+s.toString() ,Toast.LENGTH_LONG).show()
    }
}
