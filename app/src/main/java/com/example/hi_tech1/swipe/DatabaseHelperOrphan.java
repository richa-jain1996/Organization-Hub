package com.example.hi_tech1.swipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HI-TECH1 on 7/28/2016.
 */
public class DatabaseHelperOrphan extends SQLiteOpenHelper{
    public static final String DATABASE_PATH="/data/data/com.example.hi_tech1.swipe/databases/*";
    public static final String Database_Name="project.db";
    public static final String Table_Name="Orphan";
    public static final String Col_1="_id";
    public static final String Col_2="name";
    public DatabaseHelperOrphan dbHelper;
    public static final String Col_3="phone";
    public static final String Col_4="email";
    public static final String Col_5="address";
    public static final String Col_6="lattitude";
    public static final String Col_7="longitude";

    final String createTable=" create table "+Table_Name+"( _id integer primary key autoincrement, name text, phone text, email text,address varchar, lattitude text, longitude text)";
    public static SQLiteDatabase db1;
    public DatabaseHelperOrphan(Context c)
    {
        super(c,Database_Name,null,1);
    }
    public static final String[] ALL_KEYS = new String[] {Col_1, Col_2, Col_3, Col_4, Col_5};

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
        db1=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_2,name);

        contentValues.put(Col_3,phone);
        contentValues.put(Col_4,email);
        contentValues.put(Col_5,address);
        contentValues.put(Col_6,lattitude);
        contentValues.put(Col_7,longitude);

        long res=db1.insert(Table_Name, null, contentValues);
        if(res == 0)
            return false;
        else
            return true;
    }
    public Cursor getAllRowso() {
        String where = null;
        Cursor c = 	db1.query(true, Table_Name, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor viewAll()
    {
        db1=this.getWritableDatabase();
        Cursor cursor=db1.rawQuery("select * from "+Table_Name,null);
        return cursor;
    }
    public boolean updateAll(String Id,String name,String phone,String email,String address,String lattitude,String longitude)
    {
        db1=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_1,Id);
        contentValues.put(Col_2,name);

        contentValues.put(Col_3, phone);
        contentValues.put(Col_4,email);
        contentValues.put(Col_5,address);
        contentValues.put(Col_6,lattitude);
        contentValues.put(Col_7,longitude);


        db1.update(Table_Name, contentValues, "_id=?", new String[]{Id});
        db1.close();
        return true;
    }
    public boolean deleteRow(String id)
    {
        db1=this.getWritableDatabase();
        db1.delete(Table_Name, "_id= ? ", new String[]{id});
        db1.close();
        return true;
    }
    public Cursor getRow(long rowId) {
        String where = Col_1 + "=" + rowId;
        Cursor c = 	db1.query(true, Table_Name, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

}


