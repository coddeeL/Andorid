package com.example.ljdservice1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(){
            val intent =Intent(this,MyService::class.java)
            startService(intent)
            text.text="Hello Service"
            //隐式启动
//            val intent=Intent("com.example.ljdservice1.Service1")
//            intent.setPackage("com.example.ljdservice1")
//            text.text="Hello Service"
//            startService(intent)
        }
        button2.setOnClickListener{
            val intent=Intent(this,MyService::class.java)
            stopService(intent)
        }
    }
}