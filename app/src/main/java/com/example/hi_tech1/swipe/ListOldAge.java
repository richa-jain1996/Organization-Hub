package com.example.hi_tech1.swipe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListOldAge extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private String tableName = DatabaseHelper.Table_Name;
    private SQLiteDatabase db;
    String a,b,c,d,e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_old_age);

        openDB();
        populateListViewFromDB();
        registerListClickCallback();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }

    private void openDB() {
        databaseHelper = new DatabaseHelper(this);
        db=databaseHelper.getWritableDatabase();
    }
    private void closeDB() {
        databaseHelper.close();
    }

    public void populateListViewFromDB() {
        Cursor cursor = databaseHelper.getAllRows();


        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED! Runs on the UI thread, OK for small/short queries.
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields:
        String[] fromFieldNames = new String[]
                {DatabaseHelper.Col_1, DatabaseHelper.Col_2, DatabaseHelper.Col_3, DatabaseHelper.Col_4, DatabaseHelper.Col_5};
        int[] toViewIDs = new int[]
                {R.id.select_id,    R.id.select_name,     R.id.select_phone,           R.id.select_email,     R.id.select_address};

        // Create adapter to may columns of the DB onto elemesnt in the UI.
        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,		// Context
                        R.layout.item_layout,	// Row layout template
                        cursor,					// cursor (set of DB records to map)
                        fromFieldNames,			// DB Column names
                        toViewIDs				// View IDs to put information in
                );

        // Set the adapter for the list view
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(myCursorAdapter);
    }

    private void registerListClickCallback() {
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long idInDB) {


                displayToastForId(idInDB);
            }
        });
    }

    private void displayToastForId(long idInDB) {
        Cursor cursor = databaseHelper.getRow(idInDB);
        if (cursor.moveToFirst()) {

            a=cursor.getString(0);
            b=cursor.getString(1);
            c=cursor.getString(2);
            d=cursor.getString(3);
            e=cursor.getString(4);



           /* String message = "ID: " + cursor.getString(0) + "\n"
                    + "Name: " + cursor.getString(1) + "\n"
                    + "Phone: " + cursor.getString(2) + "\n"
                    + "Email: " + cursor.getString(3)+ "\n"
                    + "Address: " + cursor.getString(4);
            Toast.makeText(List.this, message, Toast.LENGTH_LONG).show();
            */
        }
        cursor.close();

        Intent i=new Intent(ListOldAge.this,ngodisplay.class);
        i.putExtra("aa",a);
        i.putExtra("bb",b);
        i.putExtra("cc",c);
        i.putExtra("dd",d);
        i.putExtra("ee",e);
        startActivity(i);
    }
}
