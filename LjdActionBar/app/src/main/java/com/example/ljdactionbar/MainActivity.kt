package com.example.ljdactionbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(){
            supportActionBar?.show()
        }
        button2.setOnClickListener(){
            supportActionBar?.hide()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1->Toast.makeText(this,"选项菜单1",Toast.LENGTH_SHORT).show()
            R.id.item2->Toast.makeText(this,"选项菜单2",Toast.LENGTH_SHORT).show()
            R.id.item3->Toast.makeText(this,"选项菜单3",Toast.LENGTH_SHORT).show()
            R.id.item4->Toast.makeText(this,"选项菜单4",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}