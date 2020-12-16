package com.example.ljdcontentresolver

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var peopleId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            val str2 = edit1.text.toString()
            val uri = Uri.parse("content://com.example.ljdcontentp.provider/people")
            text.text = "123"
            try {
                contentResolver.query(uri, null, null, null, null)?.apply {
                    text.text = "1234"
                    while (moveToNext()) {
                        text.text = "12345"
                        val name = getString(getColumnIndex("name"))
                        peopleId = getString(getColumnIndex("id"))
                        text.text = name
                    }
                    close()
                }
            } catch (e: Exception) {
                println("程序出现了未知异常，可能是您的人品太差了。${e.message}")
                //finally总会执行的
            }
            button2.setOnClickListener {
                peopleId?.let {
                    val uri = Uri.parse("content://com.example.ljdsqllitehelp2.provider/$peopleId")
                    val str2 = edit1.text.toString()
                    val value = contentValuesOf("name" to str2)
                    contentResolver.update(uri, value, null, null)
                }
            }
        }
    }
}