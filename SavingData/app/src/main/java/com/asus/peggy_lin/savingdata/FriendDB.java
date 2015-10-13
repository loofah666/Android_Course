package com.asus.peggy_lin.savingdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Peggy_Lin on 2015/10/13.
 */
public final class FriendDB {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FriendDB() {}

    /* Inner class that defines the table contents */
    public static abstract class FriendList implements BaseColumns {
        public static final String TABLE_NAME = "friend";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_FAV = "favourite";
    }
}


