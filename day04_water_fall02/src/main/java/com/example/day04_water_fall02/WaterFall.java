package com.example.day04_water_fall02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class WaterFall extends LinearLayout {
    /**
     * 孩子中最高的一个
     */
    private int mChildMaxHeight;
    /**
     * 每个孩子的左右间距
     * 默认是20单位是px
     */
    private int mHSpace = 20;
    /**
     * 每一行的上下间距
     * 20是默认值，单位是px
     */
    private int mVSpace = 20;

    public WaterFall(Context context) {
        super(context);
    }

    public WaterFall(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //拿到父容器的宽高以及计算模式
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        //测量孩子的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //寻找孩子中最高的的一个孩子，找到的值会放在mChildMaxHeight变量中
        findMaxChildMaxHeight();
        //初始化值
        int left = 0, top = 0;
        //循环所有的孩子
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            //判断是否是一行的开头
            if (left != 0) {
                //如果子元素的宽度+左边距>大于夫需要换行了，因为放不下
                if ((left + view.getMeasuredWidth()) > sizeWidth) {
                    //计算出下一行的top
                    top += mChildMaxHeight + mVSpace;
                    //重新计算 left需要归零
                    left = 0;
                }
            }

            left += view.getMeasuredWidth() + mHSpace;
        }
        setMeasuredDimension(sizeWidth, (top + mChildMaxHeight) > sizeHeight ? sizeHeight : top + mChildMaxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        findMaxChildMaxHeight();
        //开始安排孩子的位置
        //出始化
        int left = 0, top = 0;
        //循环所有的孩子
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            //判断是否是一行的开头
            if (left != 0) {
                //需要换行了，因为放不下
                if ((left + view.getMeasuredWidth()) > getWidth()) {
                    //计算出下一行的top
                    top += mChildMaxHeight + mVSpace;
                    left = 0;
                }
            }
            //安排孩子的位置
            view.layout(left, top, left + view.getMeasuredWidth(), top + mChildMaxHeight);
            left += view.getMeasuredWidth() + mHSpace;
        }
    }

    //寻找孩子中最高的一个孩子
    private void findMaxChildMaxHeight() {
        mChildMaxHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            //返回指定位置的视图
            View view = getChildAt(i);
            //指定的高度是否打于最高的高度
            if (view.getMeasuredHeight() > mChildMaxHeight) {
                //如果大于就把指定的高度赋值最高的
                mChildMaxHeight = view.getMeasuredHeight();
            }
        }
    }
}
