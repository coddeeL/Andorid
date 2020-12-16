package com.example.ljdconp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper=DBHelp(this,"student.db",1)
        button1.setOnClickListener{
            val db=dbHelper.writableDatabase
            var str1=edit1.text.toString()
            var str2=edit2.text.toString()
            //Toast.makeText(this,"成功", Toast.LENGTH_SHORT).show()
            db.execSQL("insert into people(sno,name) values(?,?)", arrayOf(str1,str2))
            Toast.makeText(this,"添加成功", Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener{
            val db=dbHelper.writableDatabase
            var str3=edit3.text.toString()
            var cursor= db.rawQuery("select * from people where sno ="+str3,null)
            if(cursor.moveToFirst()){
                var name=cursor.getString(cursor.getColumnIndex("name"))
                text1.text=name
            }
        }
    }
}