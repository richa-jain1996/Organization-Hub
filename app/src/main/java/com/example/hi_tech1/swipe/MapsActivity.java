package com.example.hi_tech1.swipe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private String tableName = DatabaseHelper.Table_Name;
    //private GoogleMap mMap;
    private SQLiteDatabase newDB;
    String lat,lon,name;
    double a,b;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {
            DatabaseHelper dbHelper = new DatabaseHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor cursor = newDB.rawQuery("select * from " + tableName, null);

            if (cursor != null ) {
                if (cursor.moveToFirst()) {
                    do {

                        name=cursor.getString(1);
                        lat=cursor.getString(5);
                        lon=cursor.getString(6);

                    }while (cursor.moveToNext());
                }
            }

            mMap = googleMap;


            a=Double.parseDouble(lat);
            b=Double.parseDouble(lon);
            LatLng destination = new LatLng(a,b);
            mMap.addMarker(new MarkerOptions().position(destination).title("Marker in "+ name));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination,10));



        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            newDB.close();
        }
    }
}
