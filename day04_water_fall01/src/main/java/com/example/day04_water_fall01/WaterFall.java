package com.example.day04_water_fall01;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day04_water_fall01.db.WaterDao;

import java.util.ArrayList;
import java.util.List;

public class WaterFall extends LinearLayout {
    //定义常量允许字符串最大长度
    private final int mMaxSize = 20;
    //定义集合
    private List<String> list = new ArrayList<>();
    private Context mContext;

    public WaterFall(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public WaterFall(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        //设置最外层的LinearLayout为垂直
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
                R.layout.water_fall_h, null);
        addView(linearLayout_h);
        //定义临时变量来计算最后一行已有的字符串长度
        int len = 0;
        //遍历
        for (int i = 0; i < list.size(); i++) {
            final String str = list.get(i);
            //将此字符串长度与记录的已有字符串长度相加
            len += str.length();
            //如果长度大于规定最大长度，说明这一行放不下，需要换行
            if(len>mMaxSize){
                //向跟布局添加一条横向布局
                linearLayout_h = (LinearLayout) View.inflate(mContext,
                        R.layout.water_fall_h,null);
                addView(linearLayout_h);
                //因为换行，所有这个字符串的长度就是最后一行长度
                len = str.length();
            }
            //添加textview并赋值
            View view = View.inflate(mContext,R.layout.water_fall_t,null);
            TextView textView = view.findViewById(R.id.water_text);
            textView.setText(str);
            linearLayout_h.addView(view);
            //设置权重
            LinearLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
            params.weight = 1;
            view.setLayoutParams(params);
            //点击
            final int index = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"点击了"+list.get(index),Toast.LENGTH_SHORT).show();
                }
            });
            //长按删除
            view.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //删除集合
                    list.remove(index);
                    //删除数据库
                    WaterDao.getIntance(mContext).del(str);
                    //刷新重新画
                    showData();
                    return false;
                }
            });
        }
    }
}
