package com.example.ljdintent3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener{
            val intent= Intent(this,Second_Activity::class.java)
            startActivityForResult(intent,1)
        }
        button2.setOnClickListener{
            val intent= Intent(this,Third_Activity::class.java)
            startActivity(intent)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->if(resultCode== Activity.RESULT_OK){
                val dd=data?.getStringExtra("data_return")
                //Toast.makeText(this,dd,Toast.LENGTH_SHORT).show()
                textview.text=dd
            }
        }
    }
}