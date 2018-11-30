package com.example.day04_fluid_layout01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
//瀑布流
public class MainActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<>();
    private EditText editText;
    private Button button;
    private FluidLayout fluidLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //获取资源id
        editText = findViewById(R.id.edit);
        button = findViewById(R.id.btn);
        fluidLayout = findViewById(R.id.water_fall);
        //点击获取值
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String str = editText.getText().toString();
                //存入集合
                list.add(str);
                //设置数据
                fluidLayout.setList(list);
            }
        });
    }
}
