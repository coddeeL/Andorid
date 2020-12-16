package com.example.ljduserinterface2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.active_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main)
        Date.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            textview.setText("你选择的日期是：" + Date.year.toString() + "/" + Date.month + "/" + Date.dayOfMonth)
            //  textview.setText("${Date.year}/${Date.month}/${Date.dayOfMonth}")
            // Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
        }
    }
}