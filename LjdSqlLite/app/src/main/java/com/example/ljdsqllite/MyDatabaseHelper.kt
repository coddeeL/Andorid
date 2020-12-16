package com.example.ljdsqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context,name:String,version:Int): SQLiteOpenHelper(context,name,null,version) {
    private val createBook="create table people("+
            "id integer primary key autoincrement,"+
            "name text,"+
            "age integer,"+
            "tall real)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        Toast.makeText(context,"Create successed",Toast.LENGTH_LONG).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}