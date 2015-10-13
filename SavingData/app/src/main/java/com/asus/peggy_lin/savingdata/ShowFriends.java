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
import android.widget.ListAdapter;
import android.widget.ListView;

import org.w3c.dom.Text;

public class ShowFriends extends Activity {

    private FriendDBHelper mDbHelper;
    private SQLiteDatabase db_read;
    ListView lv;
    ArrayAdapter adapter;
    String TAG_FRIEND = "FRIEND_READ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friends);

        mDbHelper = new FriendDBHelper(this);

        lv = (ListView) findViewById(R.id.friends_list_view);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);

        //writeNewFriend();
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
                FriendDB.FriendList.COLUMN_NAME_NAME };

        Cursor cursor = db_read.query(
                FriendDB.FriendList.TABLE_NAME,  // The table to query
                q_friend,                               // The columns to return
                null,                                // selection: The columns for the WHERE clause
                null,                            // selectionArgs: The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if(cursor!= null && cursor.getCount() == 0)
            adapter.add("You have 0 Friends");
        else if(cursor!= null){
            if (cursor.moveToFirst()) {
                do {
                    adapter.add(cursor.getString(0));
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
