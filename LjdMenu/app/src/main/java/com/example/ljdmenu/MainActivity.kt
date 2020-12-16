package com.example.ljdmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(textview);
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(1,0,0,"上下文菜单1")
        menu?.add(1,1,0,"上下文菜单2")
        menu?.add(1,2,0,"上下文菜单3")
        menu?.add(1,3,0,"上下文菜单4")
        val s1=menu?.addSubMenu(1,0,1,"子菜单")
        s1?.setHeaderTitle("子菜单")
        s1?.add(1,0,1,"子菜单项1")
        s1?.add(1,0,1,"子菜单项2")
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add(0,0,0,"菜单项1")
        menu?.add(0,1,0,"菜单项2")
        menu?.add(0,2,0,"菜单项3")
        menu?.add(0,3,0,"菜单项4")
        return true
    }

}