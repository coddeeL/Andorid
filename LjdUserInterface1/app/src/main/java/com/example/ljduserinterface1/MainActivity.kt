package com.example.ljduserinterface1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.second_layout.*
import kotlinx.android.synthetic.main.third_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 //       setContentView(R.layout.second_layout)
//        checkbox1.setOnClickListener(){
//            Toast.makeText(this,"checkbox1 selected",Toast.LENGTH_SHORT).show()
//        }
//        checkbox2.setOnClickListener(){
//            Toast.makeText(this,"checkbox2 selected",Toast.LENGTH_SHORT).show()
//        }
//        radio1.setOnClickListener(){
//            Toast.makeText(this,"radio1 selected",Toast.LENGTH_SHORT).show()
//        }
//        radio2.setOnClickListener(){
//            Toast.makeText(this,"radio2 selected",Toast.LENGTH_SHORT).show()
//        }
        setContentView(R.layout.third_layout)
        rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this, "rating:" + rating.toString(),Toast.LENGTH_LONG).show();
        }



    }
}