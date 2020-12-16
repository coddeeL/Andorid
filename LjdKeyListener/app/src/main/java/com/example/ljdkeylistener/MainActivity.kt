package com.example.ljdkeylistener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.SQLOutput

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edittext.setOnKeyListener { v, keyCode, event ->
            when(checkbox.isChecked){
                true -> false
                else -> {
                            var str="按键动作："+ event.action.toString()
                            str+="\n按键代码："+keyCode.toString()
                            str+="\n按键字符："+event.getUnicodeChar().toChar()
                            str+="\nUNICODE:"+event.getUnicodeChar().toString()
                            str+="\n重复次数:"+event.repeatCount.toString()
                            str+="\n功能键状态:"+event.metaState.toString()
                            str+="\n硬件编码:"+event.scanCode.toString()
                            str+="\n按键标志:"+event.flags.toString()
                            textview1.text=str
                            false
                }
            }

        }


    }
}