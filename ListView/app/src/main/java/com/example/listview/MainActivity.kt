package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val data= listOf("Apple","Banana","Orange","WaterMelon","Pear","Grape","Pineapple","Strawberry"
    ,"Cherry","Mango","Apple","Banana","Orange","WaterMelon","Pear","Grape","Pineapple","Strawberry"
            ,"Cherry","Mango"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapater=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        listView.adapter=adapater
    }
}