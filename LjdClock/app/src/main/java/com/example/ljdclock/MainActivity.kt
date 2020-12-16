package com.example.ljdclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var alarmManager: AlarmManager
    private lateinit var pi: PendingIntent
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this@MainActivity, ClockActivity::class.java)
        pi = PendingIntent.getActivity(this@MainActivity, 0, intent, 0)
        button1.setOnClickListener(){
            val currentTime: Calendar = Calendar.getInstance()
            TimePickerDialog(
                this, 0,
                 { timePicker, hourOfDay, minute -> //设置当前时间
                    val c = Calendar.getInstance()
                    c.setTimeInMillis(System.currentTimeMillis())
                    // 根据用户选择的时间来设置Calendar对象
                    c.set(Calendar.HOUR, hourOfDay)
                    c.set(Calendar.MINUTE, minute)
                    // ②设置AlarmManager在Calendar对应的时间启动Activity
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi)
                    Log.e("HEHE", c.getTimeInMillis().toString() + "") //这里的时间是一个unix时间戳
                    // 提示闹钟设置完毕:
                    Toast.makeText(
                            this, "闹钟设置完毕~",
                    Toast.LENGTH_SHORT
                    ).show()
                }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                    .get(Calendar.MINUTE), true
            ).show()

        }
        button2.setOnClickListener(){
            alarmManager.cancel(pi)
            Toast.makeText(this, "闹钟已取消", Toast.LENGTH_SHORT)
                .show()
        }
    }
}