package com.example.hi_tech1.swipe;

import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity implements View.OnClickListener {
    ViewPager vp;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        vp = (ViewPager) findViewById(R.id.viewpager1);
        FragmentManager fm =getSupportFragmentManager();
        vp.setAdapter(new MyAdapter(fm));

      /*  tv1=(TextView)findViewById(R.id.tv1);
         Typeface tf= Typeface.createFromAsset(getAssets(),"Fonts/Anklepants.ttf");
         tv1.setTypeface(tf);*/
    }
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        View dv=getWindow().getDecorView();
        if(hasFocus)
        {
            dv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onClick(View v) {

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
                fragment = new FragmentTwo();
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
                return "NGO";
            }
            if (position==1){
                return "ORPHANAGE";
            }
            if (position==2){
                return "OLD-AGE HOME";
            }
            return super.getPageTitle(position);
        }
    }
}

