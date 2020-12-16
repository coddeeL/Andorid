package com.example.ljd31

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDialog()
    }
    fun userDialog(){
        val dialog=AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("详细内容")
                .setNegativeButton("取消",null)
                .setPositiveButton("确定",null)
                .create()
                .show()
    }

}