package com.example.ljdandroid

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.ljdandroid.add_text
import kotlinx.android.synthetic.main.add_text.*
import java.lang.Exception
import java.util.*

class edit_text : AppCompatActivity() {
    val dbHelper=MydatabaseHelper(this,"student.db",4)
    private var OriStr=""
    var id=0
    lateinit var alarmManager: AlarmManager
    lateinit var pi: PendingIntent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_text)
        OriStr= intent.getStringExtra("extra_data").toString()
        add_text.setText(OriStr)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_text,menu)
        return true
    }
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.saveTitle-> {
                val db=dbHelper.writableDatabase
                var str=add_text.text.toString()
                if(str==""){
                    db?.execSQL("delete from text1 where text=?", arrayOf(OriStr))
                }
                else if(str!=OriStr){
                    db?.execSQL("update text1 set text=? where text=?", arrayOf(str,OriStr))
                }
                Toast.makeText(this,"修改成功", Toast.LENGTH_SHORT).show()
            }
            R.id.backTitle->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.deleteTitle->{
                val db=dbHelper.writableDatabase
                var str=add_text.text.toString()
                Toast.makeText(this,"删除成功", Toast.LENGTH_SHORT).show()
                db?.execSQL("delete from text1 where text=?", arrayOf(OriStr))
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.moveTitle->{
                val intent = Intent(this,fileChoose::class.java)
                startActivityForResult(intent,3)
            }
            R.id.ClockTitle->{
                alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, ClockActivity::class.java)
                val db=dbHelper.writableDatabase
                val cursor=db.rawQuery("select id from text1 where text=?", arrayOf(add_text.text.toString()))
                if(cursor.moveToFirst()){
                         id=cursor.getInt(cursor.getColumnIndex("id"))
                        //    id=cursor.getInt(cursor.getColumnIndex("id"))
                        println("id+++"+id)
                        }
                intent.putExtra("data",id.toString())
                pi = PendingIntent.getActivity(this, id, intent, 0)
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
                            val db=dbHelper.writableDatabase
                            db?.execSQL("update text1 set time=? where text=?", arrayOf(c.timeInMillis,add_text.text.toString()))
                            Toast.makeText(
                                this, "闹钟设置完毕~",
                                Toast.LENGTH_SHORT
                            ).show()
                        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                            .get(Calendar.MINUTE), true
                    ).show()
            }
            R.id.noClockTitle->{
                alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                var time=0;
                val db=dbHelper.writableDatabase
                val cursor=db.rawQuery("select * from text1 where text=?", arrayOf(add_text.text.toString()))
                if(cursor.moveToFirst()){
                    id=cursor.getInt(cursor.getColumnIndex("id"))
                    time=cursor.getInt(cursor.getColumnIndex("time"))
                    println("time+++"+time)
                }
                if(time!=0){
                    pi = PendingIntent.getActivity(this, id, intent, 0)
                    alarmManager.cancel(pi)
                    Toast.makeText(this, "闹钟已取消", Toast.LENGTH_SHORT)
                        .show()
                }
                else{
                    Toast.makeText(this, "未设置闹钟", Toast.LENGTH_SHORT)
                        .show()
                }
}
        }
        return true
    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            3->if(resultCode== Activity.RESULT_OK){
                val db=dbHelper.writableDatabase
                var str= data?.getStringExtra("data_return")
                println("str++==="+str)
                println(data)
                db?.execSQL("update text1 set fenzu=? where text=?", arrayOf(str,add_text.text.toString()))
                Toast.makeText(this,"移入成功", Toast.LENGTH_SHORT).show()
            }

        }
    }

}