package com.example.hi_tech1.swipe;

import android.provider.BaseColumns;

/**
 * Created by HI-TECH1 on 7/19/2016.
 */
public class TableData
{public TableData()
{

}

    public static abstract class TableInfo implements BaseColumns
    {

        public static final String USER_NAME = "user_name" ;
        public static final String USER_PASS = "user_pass" ;
        public static final String USER_EMAIL = "email" ;
        public static final String USER_CON = "con" ;
        public static final String DATABASE_NAME = "userdb97";
        public static final String TABLE_NAME = "regtb97";

    }
}

