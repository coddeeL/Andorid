package com.example.ljdservicebind

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var count:MyService.Count
    private val connection=object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            count=service as MyService.Count
            Toast.makeText(applicationContext,"service connected",Toast.LENGTH_SHORT).show()
        }
        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(){
            val intent=Intent(this,MyService::class.java)
            bindService(intent,connection, Context.BIND_AUTO_CREATE)
        }
        button2.setOnClickListener(){
            Toast.makeText(this,"in onDestory",Toast.LENGTH_SHORT).show()
            unbindService(connection)
        }
        button3.setOnClickListener(){
          count.CountNum();
          Toast.makeText(this,"计数值为：${count.getNum()}",Toast.LENGTH_SHORT).show()
        }
    }
}