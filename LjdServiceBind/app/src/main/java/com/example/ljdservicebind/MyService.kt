package com.example.ljdservicebind

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast

class MyService : Service() {
    private val mBinder=Count()
    class Count:Binder(){
        private var num=0;
        fun getNum():Int{
            return num
        }
        fun CountNum(){
            num++
        }
}
    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }


}
