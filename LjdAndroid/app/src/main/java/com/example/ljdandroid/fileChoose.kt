package com.example.ljdandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class fileChoose : AppCompatActivity() {
    private var data= ArrayList<String>()
    lateinit var arrayAdapter:ArrayAdapter<String>

    val dbHelper=MydatabaseHelper(this,"student.db",4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onStart() {
        super.onStart()
        arrayAdapter = ArrayAdapter<String>(this,
            R.layout.file_rows, //使用哪个布局
            R.id.row_text, //布局中的哪个字段来显示数据
            data)
        listview.adapter=arrayAdapter
        val db=dbHelper.writableDatabase
        val cursor=db.rawQuery("select * from group1",null)
        if(cursor.moveToFirst()){
            do {
                var str:String=cursor.getString(cursor.getColumnIndex("fenzu"))
                println(str)
                if(!data.contains(str)){
                    data.add(0,str.toString())
                }
            }while(cursor.moveToNext())
            arrayAdapter.notifyDataSetChanged()
            listview.setOnItemClickListener { parent, view, position, id ->
                val str=data[position]
                Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
                val intent=Intent()
                println("str++=="+str)
                intent.putExtra("data_return",str)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }
    }
}