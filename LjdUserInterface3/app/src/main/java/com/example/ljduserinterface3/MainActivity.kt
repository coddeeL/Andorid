package com.example.ljduserinterface3

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      timepicker.setOnTimeChangedListener { view, hourOfDay, minute ->
          textview.text= "你选择的时间是： "+timepicker.hour.toString()+":"+timepicker.minute.toString()
      }
    }
}