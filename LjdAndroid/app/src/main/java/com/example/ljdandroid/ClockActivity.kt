package com.example.ljdandroid

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import java.util.*

class ClockActivity : AppCompatActivity() {
    private var data= ArrayList<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>
    //private var OriStr=""
    val dbHelper=MydatabaseHelper(this,"student.db",4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //创建一个闹钟提醒的对话框,点击确定关闭铃声与页面
        val c = Calendar.getInstance()
        var str=intent.getStringExtra("data")
        val db=dbHelper.writableDatabase
        val cursor=db.rawQuery("select * from text1 where id=?",arrayOf(str))
        if(cursor.moveToFirst()){
            var s:String=cursor.getString(cursor.getColumnIndex("text"))
            val builder= AlertDialog.Builder(this).setTitle("闹钟").setMessage(s)
                .setPositiveButton("关闭闹铃"){
                        dialog: DialogInterface?, which: Int ->
                    finish()
                }
            builder.show()
            }
        else{
            finish()
        }
        }

    }
