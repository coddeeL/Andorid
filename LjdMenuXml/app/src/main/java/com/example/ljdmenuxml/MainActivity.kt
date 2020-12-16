package com.example.ljdmenuxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      //  return super.onCreateOptionsMenu(menu)
       menuInflater.inflate(R.menu.menu1,menu)
        return true
    }
}