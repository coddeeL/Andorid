package com.example.ljdcontentp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context,name:String,version:Int): SQLiteOpenHelper(context,name,null,version) {
    private val createBook="create table people("+
            "id integer primary key autoincrement,"+
            "sno text,"+
            "name text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        db?.execSQL("insert into people(sno,name) values(?,?)", arrayOf("1","ljd"))
        //Toast.makeText(context,"Create successed",Toast.LENGTH_LONG).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}