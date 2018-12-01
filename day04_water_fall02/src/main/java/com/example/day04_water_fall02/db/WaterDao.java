package com.example.day04_water_fall02.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.day04_water_fall02.WaterBean;

import java.util.ArrayList;
import java.util.List;

public class WaterDao {
    private static WaterDao intance;
    private Context context;
    private final SqlitHelper sqlitHelper;
    private final SQLiteDatabase sb;

    public WaterDao(Context context) {
        this.context = context;
        sqlitHelper = new SqlitHelper(context);
        sb = sqlitHelper.getWritableDatabase();
    }

    public static WaterDao getIntance(Context context) {
        if (intance==null){
            intance = new WaterDao(context);
        }
        return intance;
    }
    //添加
    public void add(String name,String uuid){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("uuid",uuid);
        sb.insert("waters",null,values);
    }
    //删除
    public void del(String uuid ){
        sb.delete("waters","uuid = ?",new String[]{uuid});
    }
    //删除所有
    public void delAll(){
        sb.delete("waters",null,null);
    }
    //查询
    public List<WaterBean> select(){
        List<WaterBean> list = new ArrayList<>();
        Cursor waters = sb.query("waters", null, null, null, null, null, null, null);
        if(waters!=null){
            while (waters.moveToNext()){
                String name = waters.getString(waters.getColumnIndex("name"));
                String uuid = waters.getString(waters.getColumnIndex("uuid"));
                WaterBean waterBean = new WaterBean(name,uuid);
                list.add(waterBean);
            }
        }
        return list;
    }
}
