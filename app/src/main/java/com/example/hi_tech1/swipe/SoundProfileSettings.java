package com.example.hi_tech1.swipe;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by HI-TECH1 on 7/23/2016.
 */
public class SoundProfileSettings extends PreferenceFragment {
    EditText ed1;
    private Button addlist;
    private ListView lv;
    private ArrayList<String> al;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addPreferencesFromResource(R.xml.myprefs);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
