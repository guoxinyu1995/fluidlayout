package com.example.day04_water_fall02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class WaterTitle extends LinearLayout {
    private Context context;
    public WaterTitle(Context context) {
        super(context);
        this.context = context;
    }

    public WaterTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = View.inflate(context,R.layout.title_water,null);
    }
}
