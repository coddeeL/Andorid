package com.example.ljdandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ljdandroid.add_text
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_text.*
import java.util.ArrayList

class fileActivity : AppCompatActivity() {
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
                val intent = Intent(this,EmpytDefault::class.java)
                intent.putExtra("file_data",str)
                startActivity(intent)
                Toast.makeText(this,str, Toast.LENGTH_SHORT).show()


            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //  return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.file_addmessage,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.addTitle-> {
                val inputServer = EditText(this)
                AlertDialog.Builder(this).apply {
                    setTitle("请输入文件夹名称")
                    setCancelable(false)
                    setView(inputServer)
                    setPositiveButton("OK") {dialog, which ->
                        var str=inputServer.text.toString()
                        val db=dbHelper.writableDatabase
                        db?.execSQL("insert into group1(fenzu) values(?)", arrayOf(str))
                        onStart()
                    }
                    setNegativeButton("Cancel"){dialog, which ->
                    }
                    show()
                }
                Toast.makeText(this,"新建文件夹", Toast.LENGTH_SHORT).show()

            }
            R.id.backTitle-> {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"返回", Toast.LENGTH_SHORT).show()
                finish()
            }



        }
        return true
    }

}