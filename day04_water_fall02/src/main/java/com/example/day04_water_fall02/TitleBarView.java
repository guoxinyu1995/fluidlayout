package com.example.day04_water_fall02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TitleBarView extends LinearLayout {
    private Context context;
    private EditText editText;
    private ImageView imageView;
    public TitleBarView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TitleBarView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    //第三步：设置成员变量
    private OnButtonClientListener onButtonClientListener;
    private void init() {
        View view = View.inflate(context,R.layout.title,null);
        editText = view.findViewById(R.id.edit_title);
        imageView = view.findViewById(R.id.search_title);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //第六步：在将要回调的地方，首先判断非空
                if(onButtonClientListener!=null){
                    //第七步：执行回调方法，传参
                    onButtonClientListener.onButtonClick(editText.getText().toString());
                }
            }
        });
        addView(view);
    }
    //第一步 定义一个接口
    public interface OnButtonClientListener{
        //第二步 写好方法和回传参数
        void onButtonClick(String str);
    }
    //第四步：传入，并且给成员变量赋值
    //第五步：在想要接受回调的地方，调用set方法，设置监听详见MainActivity第41行
    public void setOnButtonClientListener(OnButtonClientListener onButtonClientListener) {
        this.onButtonClientListener = onButtonClientListener;
    }
}
