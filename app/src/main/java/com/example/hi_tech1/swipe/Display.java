package com.example.hi_tech1.swipe;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HI-TECH1 on 7/20/2016.
 */
public class Display  extends ListActivity {

    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = DatabaseHelper.Table_Name;
    private SQLiteDatabase newDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAndQueryDatabase();

        displayResultList();



    }
    private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("NGO Details");

        getListView().addHeaderView(tView);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);

    }
    private void openAndQueryDatabase() {
        try {
            DatabaseHelper dbHelper = new DatabaseHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor cursor = newDB.rawQuery("select * from " + tableName, null);

            if (cursor != null ) {
                if (cursor.moveToFirst()) {
                    do {
                        results.add("Code:"+cursor.getString(0)+"\n");
                        results.add("Name of the NGO:"+cursor.getString(1)+"\n");

                        results.add("Phone number:" + cursor.getString(2) + "\n");
                        results.add("Email:" + cursor.getString(3) + "\n");
                        results.add("Address:"+cursor.getString(4)+"\n");
                    }while (cursor.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            newDB.close();
        }

    }
}

