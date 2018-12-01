package com.example.day04_water_fall01.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.day04_water_fall01.Bean.WateBean;

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
    public void add(String name){
        ContentValues values = new ContentValues();
        values.put("name",name);
        sb.insert("waters",null,values);
    }
    //删除
    public void del(String name ){
        sb.delete("waters","name = ?",new String[]{name});
    }
    //删除所有
    public void delAll(){
        sb.delete("waters",null,null);
    }
    //查询
    public List<String> select(){
        List<String> list = new ArrayList<>();
        Cursor waters = sb.query("waters", null, null, null, null, null, null, null);
        if(waters!=null){
            while (waters.moveToNext()){
                String name = waters.getString(waters.getColumnIndex("name"));
                list.add(name);
            }
        }
        return list;
    }
}
