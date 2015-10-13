package com.asus.peggy_lin.savingdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Peggy_Lin on 2015/10/13.
 */
public class FriendDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FriendList.db";
    String TAG_DB = "TAG_DB";

    private static final String FRIENDS_TABLE_CREATE =
            "CREATE TABLE " + FriendDB.FriendList.TABLE_NAME + " (" +
                    FriendDB.FriendList.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    FriendDB.FriendList.COLUMN_NAME_NAME + " TEXT," +
                    FriendDB.FriendList.COLUMN_NAME_GENDER + " INTEGER," +
                    FriendDB.FriendList.COLUMN_NAME_PHONE + " TEXT,"+
                    FriendDB.FriendList.COLUMN_NAME_FAV + " TEXT);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FriendDB.FriendList.TABLE_NAME;

    public FriendDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FRIENDS_TABLE_CREATE);
        Log.d(TAG_DB, "in ONCREATE");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        Log.d(TAG_DB, "in UPGRADE");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}