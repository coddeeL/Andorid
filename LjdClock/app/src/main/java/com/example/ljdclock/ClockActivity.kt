package com.example.ljdclock

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class ClockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //创建一个闹钟提醒的对话框,点击确定关闭铃声与页面
        val builder=AlertDialog.Builder(this).setTitle("闹钟").setMessage("鲁佳栋快起床~")
            .setPositiveButton("关闭闹铃"){
                dialog: DialogInterface?, which: Int ->
                finish()
            }
        builder.show()


    }
}