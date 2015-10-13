package com.asus.peggy_lin.savingdata;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ShowFriends extends Activity {

    private FriendDBHelper mDbHelper;
    private SQLiteDatabase db_read;
    ListView lv;
    //ArrayAdapter adapter;
    FriendAdapter adapter;
    String TAG_FRIEND = "FRIEND_READ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friends);

        mDbHelper = new FriendDBHelper(this);

        // Construct the data source
        ArrayList<Friend> arrayOfFriends = new ArrayList<Friend>();
        // Create the adapter to convert the array to views
        adapter = new FriendAdapter(this, arrayOfFriends);
        // Attach the adapter to a ListView

        lv = (ListView) findViewById(R.id.friends_list_view);
//        adapter = new ArrayAdapter(this, R.layout.friend_list);
        lv.setAdapter(adapter);

        showFriendList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.friend_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG_FRIEND, "clicked");
                goToEdit();
            }});

    }

    public void showFriendList(){
        db_read = mDbHelper.getReadableDatabase();

        String[] q_friend = {
                FriendDB.FriendList.COLUMN_NAME_NAME,
                FriendDB.FriendList.COLUMN_NAME_GENDER,
                FriendDB.FriendList.COLUMN_NAME_PHONE,
                FriendDB.FriendList.COLUMN_NAME_FAV};

        Cursor cursor = db_read.query(
                FriendDB.FriendList.TABLE_NAME,  // The table to query
                q_friend,                               // The columns to return
                null,                                // selection: The columns for the WHERE clause
                null,                            // selectionArgs: The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if(cursor!= null && cursor.getCount() == 0){
            Friend noFriend = new Friend("You have no Friends", null, null, null);
            adapter.add(noFriend);
        }
        else if(cursor!= null){
            if (cursor.moveToFirst()) {
                do {
                    // Add item to adapter
                    Friend newFriend = new Friend(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                    adapter.add(newFriend);

                } while (cursor.moveToNext());
            }
        }
        else
            Log.d(TAG_FRIEND, "error with no cursor");
    }

    public void goToEdit(){
        //go to External Storage Practice
        Intent intent = new Intent(this, FriendEdit.class);
        startActivity(intent);
    }


}
