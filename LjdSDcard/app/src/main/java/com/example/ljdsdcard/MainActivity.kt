package com.example.ljdsdcard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            writecheckNeedPermissions()
        }
        button2.setOnClickListener{
           readcheckNeedPermissions()
        }
    }
  fun read(){
      try {
          val file = File(
              getSdCardPath()
              ,"file.txt"
          )
          val br = BufferedReader(FileReader(file))
          var readline = ""
          val sb = StringBuffer()
         do {
              try {
                  readline=br.readLine()
              }catch (e:java.lang.Exception){
                  println(e)
                  break;
              }
            if(readline==null) break;
              println("readline:$readline")
              sb.append(readline)
          } while (true)
          br.close()
          println("读取成功：$sb")
          edit2.setText(sb)
      } catch (e: java.lang.Exception) {
          e.printStackTrace()
      }
  }

    fun write(){
        try {
            val file = File(
                getSdCardPath()
                ,"file.txt"
            )
            //第二个参数意义是说是否以append方式添加内容
            val bw = BufferedWriter(FileWriter(file, true))
            val info = edit1.text.toString()
            bw.write(info)
            bw.flush()
            println("写入成功")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun writecheckNeedPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED


        ) {
            //多个权限一起申请
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 1
            )
        }
        else{
            write()
        }

    }
    private fun readcheckNeedPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            //多个权限一起申请
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), 2
            )
        }
        else{
            read()
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
                    write()
                }

            }
            2-> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                read()
            }
        }
    }

    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     *
     * @return
     */
    fun isSdCardExist(): Boolean {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)
    }

    /**
     * 获取SD卡根目录路径
     *
     * @return
     */
    fun getSdCardPath(): String? {
        val exist = isSdCardExist()
        var sdpath: String? = ""
        sdpath = if (exist) {
            Environment.getExternalStorageDirectory()
                    .absolutePath
        } else {
            "不适用"
        }
        return sdpath
    }

    /**
     * 获取默认的文件路径
     *
     * @return
     */
    fun getDefaultFilePath(): String? {
        var filepath = ""
        val file = File(Environment.getExternalStorageDirectory(),
                "abc.txt")
        filepath = if (file.exists()) {
            file.getAbsolutePath()
        } else {
            "不适用"
        }
        return filepath
    }
}