package com.example.ljdcontacts

import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.*
import android.provider.ContactsContract.RawContacts
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_CONTACTS)!=
                PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.WRITE_CONTACTS),1)
            }
            else
                insert()
        }

        button2.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!=
                PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_CONTACTS),2)
            }
            else
                select()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    insert()
                }else{
                    Toast.makeText(this,"你拒绝了",Toast.LENGTH_SHORT).show()
                }
            }
            2->{
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    select()
                }else{
                    Toast.makeText(this,"你拒绝了",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun insert() {
        val cr = contentResolver
        /*向联系人中插入一行数据*/
        val values = ContentValues()
        var uri: Uri = cr.insert(RawContacts.CONTENT_URI, values)!!
        val raw_contact_id = ContentUris.parseId(uri)
        values.clear()
        //插入姓名
        var str1=edit1.text.toString()
        var str2=edit2.text.toString()
        values.put(StructuredName.RAW_CONTACT_ID, raw_contact_id)
        values.put(StructuredName.DISPLAY_NAME, str1)
        values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
        uri = cr.insert(ContactsContract.Data.CONTENT_URI, values)!!
        //插入电话信息
        values.clear()
        values.put(Phone.RAW_CONTACT_ID, raw_contact_id)
        values.put(Phone.NUMBER, str2) //添加号码
        values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
        values.put(Phone.TYPE, Phone.TYPE_MOBILE) //添加号码类型
        uri = cr.insert(ContactsContract.Data.CONTENT_URI, values)!!
    }
    private fun select(){
        var str=""
        text.text=str;
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)?.apply {
            while (moveToNext()){
                val name=getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                if(name!=null && number!=null){
                    str+=name+" "+number+"\n"
                }
            }
            text.text=str;
            close()
        }
    }
}
    /*插入*/
//        insert();
//        /*查询*/
//       // select();
//
//    }
//    private fun select() {
//        val cr = contentResolver
//        /*第一个参数为Uri，第二个参数为查询哪些列，第三个参数为查询条件,第五个参数为排序方式*/
//        /*查询id和姓名*/
//        val c: Cursor? = cr.query(
//            ContactsContract.Contacts.CONTENT_URI,
//            arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME),
//            null,
//            null,
//            null
//        )
//        if (c != null) {
//            while (c.moveToNext()) {
//                val id: Int = c.getInt(c.getColumnIndex("_ID"))
//                val name: String = c.getString(c.getColumnIndex("DISPLAY_NAME"))
//                Log.i("MainActivity", "_ID $id")
//                Log.i("MainActivity", "DISPLAY_NAME $name")
//                /*根据id查询联系人的电话号码*/
//                val everyName: Cursor? = cr.query(
//                    Phone.CONTENT_URI,
//                    arrayOf(Phone.NUMBER, Phone.TYPE),
//                    Phone.CONTACT_ID + "=" + id,
//                    null,
//                    null
//                )
//                if (everyName != null) {
//                    while (everyName.moveToNext()) {
//                        /*查询电话号码类型，type为0表示座机电话，type为1表示移动电话*/
//                        val type: Int = everyName.getInt(everyName.getColumnIndex(Phone.TYPE))
//                        if (type == Phone.TYPE_HOME) {
//                            Log.i(
//                                "MainActivity",
//                                "座机电话" + everyName.getString(everyName.getColumnIndex(Phone.NUMBER))
//                            )
//                        } else if (type == Phone.TYPE_MOBILE) {
//                            Log.i(
//                                "MainActivity",
//                                "移动电话" + everyName.getString(everyName.getColumnIndex(Phone.NUMBER))
//                            )
//                        }
//                    }
//                    everyName.close()
//                }
//                /*根据id查询联系人的邮箱地址*/
//                val everyEmail: Cursor? = cr.query(
//                    Email.CONTENT_URI,
//                    arrayOf(Email.DATA, Email.TYPE),
//                    Email.CONTACT_ID + "=" + id,
//                    null,
//                    null
//                )
//                if (everyEmail != null) {
//                    while (everyEmail.moveToNext()) {
//                        val type: Int = everyEmail.getInt(everyEmail.getColumnIndex(Email.TYPE))
//                        if (type == Email.TYPE_WORK) {
//                            Log.i(
//                                "MainActivity",
//                                "工作邮箱" + everyEmail.getString(everyEmail.getColumnIndex(Email.DATA))
//                            )
//                        }
//                    }
//                    everyEmail.close()
//                }
//            }
//            c.close()
//        }
//    }
//

