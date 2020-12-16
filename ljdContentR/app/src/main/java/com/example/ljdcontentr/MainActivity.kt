package com.example.ljdcontentr

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var peopleId: String? = null
        button1.setOnClickListener {
            val str2 = edit1.text.toString()
            val uri = Uri.parse("content://com.example.ljdcontentp.provider/people")
           // text.text = "123"
            contentResolver.query(uri, null, "name='"+str2+"'", null, null)?.apply {
               // text.text = "1234"
                if (moveToNext()) {
                    //text.text = "12345"
                    val name = getString(getColumnIndex("name"))
                    peopleId = getString(getColumnIndex("sno"))
                    text.text = peopleId
                }
                else{
                    text.text = "该用户不存在"
                }
                close()
            }
        }
        button2.setOnClickListener {
            peopleId?.let {
                val uri = Uri.parse("content://com.example.ljdcontentp.provider/people/$peopleId")
                val str2 = edit1.text.toString()
                val value = contentValuesOf("name" to str2)
                contentResolver.update(uri, value, null, null)
                Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
