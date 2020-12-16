package com.example.ljdsqllitehelp2

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyContentProvider : ContentProvider() {

    private val peopleDir=0
    private val peopleItem=1
    private val authority="com.example.ljdsqllitehelp2.provider"

    private var dbHelper:MyDatabaseHelper?=null;


    private val uriMathcher by lazy{
        val matcher =UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority,"people",peopleDir)
        matcher.addURI(authority,"people/#",peopleItem)
        matcher

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri)=when(uriMathcher.match(uri)) {
        peopleDir->"vnd.android.cursor.dir/vnd.com.example.ljdsqllitehelp2.provider.people"
        peopleItem->"vnd.android.cursor.Item/vnd.com.example.ljdsqllitehelp2.provider.people"
      else->null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null;
    }

    override fun onCreate()=context?.let {
      dbHelper=MyDatabaseHelper(it,"student.db",1)
        true
    }?:false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    )=dbHelper?.let {
        val db=it.readableDatabase
        val cursor=when(uriMathcher.match(uri)){
            peopleDir->db.query("people",projection,selection,selectionArgs,null,null,sortOrder)
            peopleItem->{
                val peopleId=uri.pathSegments[1]
                db.query("people",projection,"id=?", arrayOf(peopleId),null,null,sortOrder)
            }
            else->{

                null
            }
        }
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) =dbHelper?.let{
        val db=it.writableDatabase
        val updataRows=when(uriMathcher.match(uri)){
            peopleDir->db.update("people",values,selection,selectionArgs)
            peopleItem->{
                val peopleId=uri.pathSegments[1]
                db.update("people",values,"id=?", arrayOf(peopleId))
            }
            else->0
        }
        updataRows

    }?:0

}
