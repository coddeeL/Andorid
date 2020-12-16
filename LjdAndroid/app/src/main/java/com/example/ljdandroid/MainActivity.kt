package com.example.ljdandroid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    //private  val data= listOf("first record","second record")
    private var id:Int = 0
    private var data=ArrayList<String>()
    lateinit var arrayAdapter:ArrayAdapter<String>

    val dbHelper=MydatabaseHelper(this,"student.db",4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var str=""
        //var data = ArrayList<String>()
    }

    override fun onStart() {
        super.onStart()

        arrayAdapter =ArrayAdapter<String>(this,
            R.layout.reminders_row, //使用哪个布局
            R.id.row_text, //布局中的哪个字段来显示数据
            data)
        listview.adapter=arrayAdapter
        val db=dbHelper.writableDatabase
        val cursor=db.rawQuery("select * from text1",null)
        if(cursor.moveToFirst()){
            do {
                var str:String=cursor.getString(cursor.getColumnIndex("text"))
                id=cursor.getInt(cursor.getColumnIndex("id"))
                println(str)
                if(!data.contains(str)){
                    data.add(0,str.toString())
                }
            }while(cursor.moveToNext())
            arrayAdapter.notifyDataSetChanged()
        }
        listview.setOnItemClickListener { parent, view, position, id ->
            val str=data[position]
            val intent = Intent(this,edit_text::class.java)
            intent.putExtra("extra_data",str)
            startActivity(intent)
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show()


        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //  return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_reminders,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //添加备忘录
            R.id.bwl-> {
                val intent = Intent(this,add_text::class.java)
                startActivity(intent)
                Toast.makeText(this,"新建", Toast.LENGTH_SHORT).show()
            }
            //新建
            R.id.file-> {
                val intent = Intent(this,fileActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"文件夹", Toast.LENGTH_SHORT).show()
            }
            R.id.newfile->{
                val inputServer = EditText(this)
                AlertDialog.Builder(this).apply {
                    setTitle("请输入文件夹名称")
                    setCancelable(false)
                    setView(inputServer)
                    setPositiveButton("OK") {dialog, which ->
                        var str=inputServer.text.toString()
                        val db=dbHelper.writableDatabase
                        db?.execSQL("insert into group1(fenzu) values(?)", arrayOf(str))
                        val intent = Intent(this@MainActivity,fileActivity::class.java)
                        startActivity(intent)
                    }
                    setNegativeButton("Cancel"){dialog, which ->
                    }
                    show()
                }
                Toast.makeText(this,"文件夹", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

}