package com.example.ljdsharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener{
            if(checkbox.isChecked){
                val editor=getSharedPreferences("data",Context.MODE_PRIVATE).edit()
                val prefs=getSharedPreferences("data",Context.MODE_PRIVATE)
                val str=prefs.getString("key","")
                editor.putString("key",str+edit1.text.toString())
                editor.apply()
                Toast.makeText(this,"追加成功",Toast.LENGTH_LONG).show()
            }
            else{
                 val editor=getSharedPreferences("data",Context.MODE_PRIVATE).edit()
                 editor.putString("key",edit1.text.toString())
                 editor.apply()
                 Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show()
            }
        }
        button2.setOnClickListener{
            val prefs=getSharedPreferences("data",Context.MODE_PRIVATE)
            val str=prefs.getString("key","")
            edit2.setText(str)
            Toast.makeText(this,"显示成功",Toast.LENGTH_LONG).show()
        }
    }
}