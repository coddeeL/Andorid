package com.example.ljdbroadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{
            val intent= Intent("com.example.ljdbroadcast.gb")
            val str=edittext.text
            intent.putExtra("data",str.toString())
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }
}