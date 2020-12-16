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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_text.*
import kotlinx.android.synthetic.main.reminders_row.*
import java.util.ArrayList
import java.util.Collection

class EmpytDefault : AppCompatActivity() {
    private var data= ArrayList<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>
    private var OriStr=""
    val dbHelper=MydatabaseHelper(this,"student.db",4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_bwl)
    }
    override fun onStart() {
        super.onStart()
        data.clear()
        OriStr= intent.getStringExtra("file_data").toString()
        row_text.text=OriStr
        arrayAdapter =ArrayAdapter<String>(this,
            R.layout.reminders_row, //使用哪个布局
            R.id.row_text, //布局中的哪个字段来显示数据
            data)
        listview.adapter=arrayAdapter
        val db=dbHelper.writableDatabase
        val cursor=db.rawQuery("select * from text1 where fenzu=?", arrayOf(OriStr))
        println("Oristr="+OriStr)

        if(cursor.moveToFirst()){
            do {
                var str:String=cursor.getString(cursor.getColumnIndex("text"))
            //    id=cursor.getInt(cursor.getColumnIndex("id"))
                println(str)
                if(!data.contains(str)){
                    data.add(0,str.toString())

                }
            }while(cursor.moveToNext())
            arrayAdapter.notifyDataSetChanged()
        }
        listview.setOnItemClickListener { parent, view, position, id ->
            val str=data[position]
            val intent = Intent(this,edit_FileText::class.java)
            intent.putExtra("extra_data",str)
            startActivityForResult(intent,2)
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode){
//            1->if(resultCode== Activity.RESULT_OK){
//               onStart()
//            }
//            2->if(resultCode==Activity.RESULT_OK){
//                onStart()
//            }
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_back,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addTitle-> {
                val intent = Intent(this,add_FileText::class.java)
                intent.putExtra("extra_data",OriStr)
                startActivityForResult(intent,1)
            }
            R.id.backTitle->{
                val intent = Intent(this, fileActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.deliteFile->{
                val db=dbHelper.writableDatabase
                db?.execSQL("PRAGMA foreign_keys=ON;")
                db?.execSQL("delete from group1 where fenzu=?", arrayOf(OriStr))



                db?.execSQL("delete from text1 where fenzu=?", arrayOf(OriStr))




                val intent = Intent(this, fileActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return true
    }
}
