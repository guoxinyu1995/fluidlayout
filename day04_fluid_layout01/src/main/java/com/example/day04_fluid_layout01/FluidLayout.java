package com.example.day04_fluid_layout01;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
//瀑布流
public class FluidLayout extends LinearLayout {
    //每次最大允许字符串个数
    private final int mMaxSize = 20;
    //传入的字符传
    List<String> list = new ArrayList<>();
    private Context mContext;
    public FluidLayout(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public FluidLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        //设置最外层LinearLayout布局为垂直
        setOrientation(VERTICAL);
    }

    public void setList(List<String> list) {
        this.list = list;
        showData();
    }

    private void showData() {
        //因为每一次都要新画，所以要先移除
        removeAllViews();
        //优先向跟布局添加一条横向布局
        LinearLayout linearLayout_h = (LinearLayout) View.inflate(mContext,
                R.layout.item_water_fall_h,null);
        addView(linearLayout_h);
        //定义临时变量来计算最后一行已有的字符串长度
        int len = 0;
        //遍历
        for(String str:list){
            //将此字符串长度与记录的已有字符串长度相加
            len += str.length();
            //如果长度大于规定最大长度，说明这一行放不下，需要换行
            if(len>mMaxSize){
                //向跟布局添加一条横向布局
                linearLayout_h = (LinearLayout) View.inflate(mContext,
                        R.layout.item_water_fall_h,null);
                addView(linearLayout_h);
                //因为换行，所有这个字符串的长度就是最后一行长度
                len = str.length();
            }
            //添加textview并赋值
            View view = View.inflate(mContext,R.layout.item_water_fall_t,null);
            TextView textView = view.findViewById(R.id.water_text);
            textView.setText(str);
            linearLayout_h.addView(view);
            //设置权重，让每一行内所有控件相加充满整行，并合理分配
            LinearLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
            params.weight = 1;
            view.setLayoutParams(params);
        }
    }
}
