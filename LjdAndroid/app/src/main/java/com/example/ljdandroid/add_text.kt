package com.example.ljdandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ljdandroid.add_text
import kotlinx.android.synthetic.main.add_text.*
import java.util.ArrayList

class add_text : AppCompatActivity() {
    val dbHelper=MydatabaseHelper(this,"student.db",4)
//    private var data= ArrayList<String>()
//    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_text)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_1,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.saveTitle-> {
                val db=dbHelper.writableDatabase
                var str=add_text.text.toString()
                db?.execSQL("insert into text1(text,fenzu,time) values(?,?,?)", arrayOf(str,"Default","0"))
                Toast.makeText(this,"添加成功", Toast.LENGTH_SHORT).show()
                finish()
            }
            R.id.backTitle->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.deleteTitle->{
                add_text.setText("")
            }
        }
        return true
    }
}