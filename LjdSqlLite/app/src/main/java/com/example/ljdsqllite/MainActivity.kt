package com.example.ljdsqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper=MyDatabaseHelper(this,"people.db",1)
        var str="";
        button1.setOnClickListener{
            val db=dbHelper.writableDatabase
            var str1=edit1.text.toString()
            var str2=edit2.text.toString()
            var str3=edit3.text.toString()
            db.execSQL("insert into people(name,age,tall) values(?,?,?)", arrayOf(str1,str2 ,str3))
            Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show()
        }
        button2.setOnClickListener{
            var str="";
            val db=dbHelper.writableDatabase
            val cursor=db.rawQuery("select * from people",null)
            if(cursor.moveToFirst()){
                do {
                    val id=cursor.getInt(cursor.getColumnIndex("id"))
                    val name=cursor.getString(cursor.getColumnIndex("name"))
                    val age=cursor.getInt(cursor.getColumnIndex("age"))
                    val tall=cursor.getDouble(cursor.getColumnIndex("tall"))
                    str+="id:"+id+" 姓名:"+name+" 年龄:"+age+" 身高:"+tall
                    str+="\n"
                    textbox.text=str;
                }while(cursor.moveToNext())
            }
        }
        button3.setOnClickListener{
            textbox.text=""
        }
        button4.setOnClickListener{
            val db=dbHelper.writableDatabase
            db.execSQL("delete from people where id> ?", arrayOf("0"))
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show()
        }
        button5.setOnClickListener{
            val db=dbHelper.writableDatabase
            val str=edit4.text.toString()
            val cursor=db.rawQuery("select * from people where id="+str,null)
            if(cursor.moveToFirst()){
                db.execSQL("delete from people where id= ?", arrayOf(str))
                Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show()
            }
           else
            {
                Toast.makeText(this,"该ID不存在",Toast.LENGTH_SHORT).show()
            }

        }
        button6.setOnClickListener{
            var str=""
            val db=dbHelper.writableDatabase
            val str1=edit4.text.toString()
            val cursor=db.rawQuery("select * from people where id="+str1,null)
            if(cursor.moveToFirst()){
                val id=cursor.getInt(cursor.getColumnIndex("id"))
                val name=cursor.getString(cursor.getColumnIndex("name"))
                val age=cursor.getInt(cursor.getColumnIndex("age"))
                val tall=cursor.getDouble(cursor.getColumnIndex("tall"))
                str+="id:"+id+" 姓名:"+name+" 年龄:"+age+" 身高:"+tall
                str+="\n"
                textbox.text=str;
                Toast.makeText(this,"查询成功",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,"该ID不存在",Toast.LENGTH_SHORT).show()
            }
        }
        button7.setOnClickListener{
            var str=""
            val db=dbHelper.writableDatabase
            val str1=edit4.text.toString()
            val cursor=db.rawQuery("select * from people where id="+str1,null)
            if(cursor.moveToFirst()){
                if(edit1.text.toString()!=""){
                    db.execSQL("update people set name=? where id=?", arrayOf(edit1.text.toString(),edit4.text.toString()))
                }
                if(edit2.text.toString()!=""){
                    db.execSQL("update people set age=? where id=?", arrayOf(edit2.text.toString(),edit4.text.toString()))
                }
                if(edit3.text.toString()!=""){
                    db.execSQL("update people set tall=? where id=?", arrayOf(edit2.text.toString(),edit4.text.toString()))
                }
                Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,"该ID不存在",Toast.LENGTH_SHORT).show()
            }
        }
    }
}