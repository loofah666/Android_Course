package com.asus.peggy_lin.savingdata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;


public class ShowFriends extends ActionBarActivity {

    private FriendDBHelper mDbHelper;
    private SQLiteDatabase mDBRead;
    private static final String TAG_FRIEND = "FRIEND_READ";
    ListView lv;
    Toolbar toolbar;
//    FriendAdapter adapter;
    RecyclerView recyclerView;
    String sharedPreferenceName = "";
    FriendListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friends);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultValue = "";
        sharedPreferenceName = sharedPref.getString(getString(R.string.saved_name_str), defaultValue);
        Log.d(TAG_FRIEND,"SharedPreference: "+ sharedPreferenceName);

        initToolbar();

        mDbHelper = new FriendDBHelper(this);

        ArrayList<Friend> arrayOfFriends = new ArrayList<Friend>();
//        adapter = new FriendAdapter(this, arrayOfFriends);
//        lv = (ListView) findViewById(R.id.friends_list_view);
//        lv.setAdapter(adapter);

        //List<Friend> friends = new ArrayList<Friend>();
        recyclerView = (RecyclerView) findViewById(R.id.friends_list_view);
        adapter = new FriendListAdapter(this, arrayOfFriends);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showFriendList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.friend_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG_FRIEND, "clicked");
                goToAddFriend();
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_show);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void showFriendList(){
        mDBRead = mDbHelper.getReadableDatabase();

        String[] q_friend = {
                FriendDB.FriendList.COLUMN_NAME_ID,
                FriendDB.FriendList.COLUMN_NAME_NAME,
                FriendDB.FriendList.COLUMN_NAME_GENDER,
                FriendDB.FriendList.COLUMN_NAME_PHONE,
                FriendDB.FriendList.COLUMN_NAME_FAV};

        Cursor cursor = mDBRead.query(
                FriendDB.FriendList.TABLE_NAME,  // The table to query
                q_friend,                               // The columns to return
                null,                                // selection: The columns for the WHERE clause
                null,                            // selectionArgs: The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if(cursor!= null && cursor.getCount() == 0){
            Friend noFriend = new Friend(null, "You have no Friends", null, null, null);
            adapter.add(noFriend);
        }
        else if(cursor!= null){
            if (cursor.moveToFirst()) {
                do {
                    Friend newFriend = new Friend(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4));
                    Log.d(TAG_FRIEND, "id: " + cursor.getInt(0));
                    adapter.add(newFriend);

                } while (cursor.moveToNext());
            }
        }
        else
            Log.d(TAG_FRIEND, "error with no cursor");
    }

    public void goToAddFriend(){
        //addFriend
        Intent intent = new Intent(this, FriendAdd.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);

        return true;
    }




}
