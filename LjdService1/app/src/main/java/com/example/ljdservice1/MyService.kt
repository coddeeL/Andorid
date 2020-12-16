package com.example.ljdservice1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast


class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"in onCreate",Toast.LENGTH_SHORT).show()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"in onStartCommand",Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"in onDestroy",Toast.LENGTH_SHORT).show()
    }
}
