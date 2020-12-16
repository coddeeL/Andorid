package com.example.ljdandroid

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MydatabaseHelper (val context: Context, name:String, version:Int): SQLiteOpenHelper(context,name,null,version) {
    private val createGroup1="create table group1(fenzu text primary key not null)"
    private val createText1="create table text1(id integer primary key autoincrement,text text not null,fenzu text,time INTEGER,foreign key(fenzu) references group1(fenzu) on delete cascade on update cascade)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createGroup1)
        db?.execSQL(createText1)
        db?.execSQL("insert into group1(fenzu) values(?)", arrayOf("Default"))
        db?.execSQL("insert into text1(text,fenzu,time) values(?,?,?)", arrayOf("第一条备忘录","Default","0"))
        //Toast.makeText(context,"Create successed",Toast.LENGTH_LONG).show()
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists group1")
        db?.execSQL("drop table if exists text1")
        onCreate(db)
    }
}