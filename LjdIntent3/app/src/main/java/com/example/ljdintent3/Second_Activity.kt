package com.example.ljdintent3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.second.*

class Second_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)
        button3.setOnClickListener{
            val str=edittext.text;
           // Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
            val intent=Intent()
            intent.putExtra("data_return",str.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
        button4.setOnClickListener{
           finish()

        }
    }

}