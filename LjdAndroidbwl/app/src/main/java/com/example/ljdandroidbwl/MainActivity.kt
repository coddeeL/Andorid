package com.example.ljdandroidbwl

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.reminders_row.*


class MainActivity : AppCompatActivity() {
    private var mListView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mListView = findViewById(R.id.reminders_list_view) as ListView
        val arrayAdapter = ArrayAdapter<String>(
            this,  //  当前Activity的Context对象
            R.layout.reminders_row,  //使用哪个布局
            row_text, arrayOf("first record", "second record")
        )
        reminders_list_view.setAdapter(arrayAdapter)
    }
}