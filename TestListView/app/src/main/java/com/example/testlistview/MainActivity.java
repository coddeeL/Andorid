package com.example.testlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
public class MainActivity extends AppCompatActivity {
    EditText et;
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter <String>aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.myEditText);
        lv = (ListView)findViewById(R.id.myListView);

        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, al);
        lv.setAdapter(aa);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, 1, 1, "Add");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menu){
        if(menu.getItemId() == 1){
            al.add(0,et.getText().toString());
            aa.notifyDataSetChanged();
			/*可以在修改适配器绑定的数组后，不用重新刷新Activity，通知Activity更新ListView。
			    如果不加,当新添加一行数据后,将不会立即显示,直到下一次触发后.*/
            et.setText("");
        }
        return true;

    }
    }
