package com.example.day04_water_fall01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.day04_water_fall01.db.WaterDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private WaterFall waterFall;
    private List<String> list = new ArrayList<>();
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        editText = findViewById(R.id.edit);
        button = findViewById(R.id.btn);
        waterFall = findViewById(R.id.water_fall);
        button1 = findViewById(R.id.clear);
        //点击
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String str = editText.getText().toString();
                //添加到数据库
                WaterDao.getIntance(MainActivity.this).add(str);
                //存入集合
                list.add(str);
                //List<String> select = WaterDao.getIntance(MainActivity.this).select();
                //设置数据
                waterFall.setList(list);
            }
        });
        //第二次进入从数据库查询展示页面
        List<String> select = WaterDao.getIntance(MainActivity.this).select();
        waterFall.setList(select);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterFall.removeAllViews();
                WaterDao.getIntance(MainActivity.this).delAll();
            }
        });
    }

}
