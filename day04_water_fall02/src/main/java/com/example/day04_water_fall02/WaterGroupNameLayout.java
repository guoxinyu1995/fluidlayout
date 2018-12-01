package com.example.day04_water_fall02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class WaterGroupNameLayout extends TextView {
    public WaterGroupNameLayout(Context context) {
        super(context);
    }

    public WaterGroupNameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //自定义属性
        //第一步，在value文件夹下建立一个attr.xml文件，
        //第二步，写<declear....标签
        //第三步，写<attr 标签 name：方法名 format：属性
        //第四步，在自定义view中的有AttributeSet的构造方法里写以下代码
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.WeekFlowLayout);
        int color = array.getColor(R.styleable.WeekFlowLayout_textColor, Color.BLUE);
        //第五步，在布局文件根控件中写xmlns:app="http://schemas.android.com/apk/res-auto"
        //第六步，在想要调用自定义属性的控件中添加app:方法名=“想要设置的值”
        setTextColor(color);
        //第七步最后要回收
        array.recycle();
    }
}
