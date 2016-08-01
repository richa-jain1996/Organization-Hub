package com.example.hi_tech1.swipe;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserDisplay extends FragmentActivity {
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        vp = (ViewPager) findViewById(R.id.viewpager1);
        FragmentManager fm =getSupportFragmentManager();
        vp.setAdapter(new MyAdapter(fm));
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            android.support.v4.app.Fragment fragment = null;

            if(position==0){
                fragment = new FragmentOne();
            }else if(position==1){
                fragment = new SoundProfiles();
            }else{
                fragment = new FragmentThree();
            }

            return fragment;
        }

        @Override
        public int getCount() {

            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position==0){
                return "Map";
            }
            if (position==1){
                return "Saved Reminders";
            }
            if (position==2){
                return "Sound Profiles";
            }
            return super.getPageTitle(position);
        }
    }
}

