package com.example.hi_tech1.swipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HI-TECH1 on 7/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_PATH="/data/data/com.example.hi_tech1.swipe/databases/*";
    public static final String Database_Name="project.db";
    public static final String Table_Name="NGO";
    public static final String Col_1="_id";
    public static final String Col_2="name";
public DatabaseHelper dbHelper;
    public static final String Col_3="phone";
    public static final String Col_4="email";
    public static final String Col_5="address";
    public static final String Col_6="lattitude";
    public static final String Col_7="longitude";
    public static final String Col_8="type";

    final String createTable=" create table "+Table_Name+"( _id integer primary key autoincrement, name text, phone text, email text,address varchar, lattitude text, longitude text, type text)";
    public static SQLiteDatabase db;
    public DatabaseHelper(Context c)
    {
        super(c,Database_Name,null,1);
    }
    public static final String[] ALL_KEYS = new String[] {Col_1, Col_2, Col_3, Col_4, Col_5, Col_8};

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(createTable);
    }
    public void onUpgrade(SQLiteDatabase db,int old,int newVersion)
    {
        db.execSQL(" DROP TABLE IF EXIST"+Table_Name);
        onCreate(db);

    }
    public  boolean insertData(String name,String phone,String email,String address,String lattitude,String longitude)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_2,name);

        contentValues.put(Col_3,phone);
        contentValues.put(Col_4,email);
        contentValues.put(Col_5,address);
        contentValues.put(Col_6,lattitude);
        contentValues.put(Col_7,longitude);
        //contentValues.put(Col_8,type);

        long res=db.insert(Table_Name, null, contentValues);
        if(res == 0)
            return false;
        else
            return true;
    }
    public Cursor getAllRows() {
        String where = null;
        Cursor c = 	db.query(true, Table_Name, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor viewAll()
    {
        db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+Table_Name,null);
        return cursor;
    }
    public boolean updateAll(String Id,String name,String phone,String email,String address,String lattitude,String longitude,String type)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_1,Id);
        contentValues.put(Col_2,name);

        contentValues.put(Col_3, phone);
        contentValues.put(Col_4,email);
        contentValues.put(Col_5,address);
        contentValues.put(Col_6,lattitude);
        contentValues.put(Col_7,longitude);
        contentValues.put(Col_8,type);


        db.update(Table_Name, contentValues, "_id=?", new String[]{Id});
        db.close();
        return true;
    }
    public boolean deleteRow(String id)
    {
        db=this.getWritableDatabase();
        db.delete(Table_Name, "_id= ? ", new String[]{id});
        db.close();
        return true;
    }
    public Cursor getRow(long rowId) {
        String where = Col_1 + "=" + rowId;
        Cursor c = 	db.query(true, Table_Name, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

}

