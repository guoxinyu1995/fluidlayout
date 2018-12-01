package com.example.day04_water_fall02;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day04_water_fall02.db.WaterDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private WaterFall waterFall;
    private EditText editText;
    private Button button;
    private List<String> list = new ArrayList<>();
    private WaterFall waterFall_seach;
    private WaterFall waterFall_hot;
    private TitleBarView title;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        waterFall_seach = findViewById(R.id.water_fall_search);
        waterFall_hot = findViewById(R.id.water_fall_hot);
        title = findViewById(R.id.title);
        button = findViewById(R.id.clear);
        //点击
        title.setOnButtonClientListener(new TitleBarView.OnButtonClientListener() {
            private String uuid1;
            @Override
            public void onButtonClick(final String str) {
                if (str.equals("")) {
                    return;
                } else {
                   final TextView textView = new TextView(MainActivity.this);
                    //随机字符串当做唯一标识
                    UUID uuid = UUID.randomUUID();
                    textView.setTag(uuid);
                    uuid1 = String.valueOf(textView.getTag());
                    WaterDao.getIntance(MainActivity.this).add(str, uuid1);
                    textView.setText(str);
                    textView.setTextSize(20);
                    textView.setBackgroundResource(R.drawable.textshape);
                    waterFall_seach.addView(textView);
                    //长按删除
                    textView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            WaterDao.getIntance(MainActivity.this).del(uuid1);
                            waterFall_seach.removeView(v);
                            return true;
                        }
                    });
                    //点击提示
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this,"点击了"+str,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        //第二次进入页面从数据库查出来展示
        List<WaterBean> select = WaterDao.getIntance(MainActivity.this).select();
        if (select.size() != 0) {
            for (final WaterBean s : select) {
                final TextView textView = new TextView(MainActivity.this);
                textView.setText(s.getName());
                textView.setTextSize(20);
                textView.setBackgroundResource(R.drawable.textshape);
                waterFall_seach.addView(textView);
                //长按删除
                textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        WaterDao.getIntance(MainActivity.this).del(s.getUuid());
                        waterFall_seach.removeView(v);
                        return true;
                    }
                });
                //点击提示
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"点击了"+s.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        //热门搜索
        String[] str = new String[]{
                "水煮肉片", "锅包肉", "鱼香肉丝", "水煮鱼", "木须肉", "地三鲜", "尖椒干豆腐"
                , "干锅土豆片", "汉堡", "炸鸡", "可乐", "啤酒", "火腿", "米饭"
        };
        for (int i = 0; i < str.length; i++) {
            TextView textView1 = new TextView(MainActivity.this);
            textView1.setText(str[i]);
            textView1.setTextSize(20);
            textView1.setBackgroundResource(R.drawable.textshape);
            waterFall_hot.addView(textView1);
        }
        //清空历史
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaterDao.getIntance(MainActivity.this).delAll();
                waterFall_seach.removeAllViews();
            }
        });
    }
}
