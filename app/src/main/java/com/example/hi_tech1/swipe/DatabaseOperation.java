package com.example.hi_tech1.swipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HI-TECH1 on 7/19/2016.
 */
public class DatabaseOperation extends SQLiteOpenHelper {
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+"("+ TableData.TableInfo.USER_NAME+" TEXT,"+ TableData.TableInfo.USER_PASS+" TEXT,"+ TableData.TableInfo.USER_EMAIL+" TEXT,"+ TableData.TableInfo.USER_CON+" TEXT);";
public  Cursor CR=null;
    public DatabaseOperation(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");

    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    public void putInformation(DatabaseOperation dop,String name,String pass,String mail,String contact)

    {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASS, pass);
        cv.put(TableData.TableInfo.USER_EMAIL, mail);
        cv.put(TableData.TableInfo.USER_CON, contact);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One raw inserted");



    }

    public Cursor getInformation(DatabaseOperation dop)
    {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS, TableData.TableInfo.USER_EMAIL,
                TableData.TableInfo.USER_CON};
        CR = SQ.query(TableData.TableInfo.TABLE_NAME,coloumns, null, null, null, null, null);
        return CR;


    }

    public Cursor getUserPass(DatabaseOperation DOP, String user)
    {
        SQLiteDatabase SQ = DOP.getReadableDatabase();
        String selection = TableData.TableInfo.USER_NAME +" LIKE ?";
        String coloumns[] = {TableData.TableInfo.USER_PASS};
        String args[] = {user};
        CR = SQ.query(TableData.TableInfo.TABLE_NAME, coloumns, selection, args, null, null, null);
        return CR;

    }

    public void deleteUser(DatabaseOperation DOP, String user, String pass)
    {
        String selection = TableData.TableInfo.USER_NAME+ " LIKE ? AND "+ TableData.TableInfo.USER_PASS +" LIKE ?";
        //String coloumns[] = {TableInfo.USER_PASS};
        String args[] = {user,pass};
        SQLiteDatabase SQ = DOP.getWritableDatabase();
        SQ.delete(TableData.TableInfo.TABLE_NAME, selection, args);

    }

    public void updateUserInfo(DatabaseOperation DOP, String user_name, String user_pass, String new_user_name )
    {
        SQLiteDatabase SQ  = DOP.getWritableDatabase();
        String selection = TableData.TableInfo.USER_NAME+ " LIKE ? AND "+ TableData.TableInfo.USER_PASS +" LIKE ?";
        String args[] = {user_name,user_pass};
        ContentValues values = new ContentValues();
        values.put(TableData.TableInfo.USER_NAME, new_user_name);
        SQ.update(TableData.TableInfo.TABLE_NAME, values, selection, args);


    }


}

